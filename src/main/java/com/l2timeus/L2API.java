package com.l2timeus;

import com.l2timeus.handlers.*;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.security.*;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.security.Password;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.logging.Logger;

public class L2API {

    private static final String WEBROOT_INDEX = "/webapp/";
    private static final Logger LOG = Logger.getLogger(L2API.class.getName());

    private static int port = 8090;
    private static Server server;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");

        try {
            startServer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void startServer() throws Exception {
        server = new Server();

        // Define ServerConnector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());
        https.setSecureScheme("https");
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(L2API.class.getResource("/keystore.ks").toExternalForm());
        sslContextFactory.setKeyStorePassword("@qate3!");
        sslContextFactory.setKeyManagerPassword("@qate3!");
        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https));
        sslConnector.setPort(8091);
        server.addConnector(sslConnector);


        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault(server);
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        URI baseUri = getWebRootResourceUri();
        LOG.info("Base URI: " + baseUri);

        // Create Servlet context
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setResourceBase(baseUri.toASCIIString());
        servletContextHandler.setSecurityHandler(getBasicAuth());

        servletContextHandler.setWelcomeFiles(new String[]{"index.html"});

        // Since this is a ServletContextHandler we must manually configure JSP support.
        enableEmbeddedJspSupport(servletContextHandler);

        ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
        holderDefault.setInitParameter("resourceBase", baseUri.toASCIIString());
        holderDefault.setInitParameter("dirAllowed", "false");
        servletContextHandler.addServlet(holderDefault, "/");
        server.setHandler(servletContextHandler);

        servletContextHandler.addServlet(new ServletHolder(new PlayersHandler()), "/players");
        servletContextHandler.addServlet(new ServletHolder(new ServerHandler()), "/server");
        servletContextHandler.addServlet(new ServletHolder(new ServerRestartHandler()), "/server/restart");
        servletContextHandler.addServlet(new ServletHolder(new LSLogsHandler()), "/server/lslogs");
        servletContextHandler.addServlet(new ServletHolder(new GSLogsHandler()), "/server/gslogs");
        servletContextHandler.addServlet(new ServletHolder(new AnnounceHandler()), "/server/announce");
        servletContextHandler.addServlet(new ServletHolder(new ChatHandler()), "/server/chatlogs");
        servletContextHandler.addServlet(new ServletHolder(new AdminHandler()), "/admin");
        servletContextHandler.addServlet(new ServletHolder(new PlayerDataHandler()), "/playerinfo");
        servletContextHandler.addServlet(new ServletHolder(new RegisterHandler()), "/register");

        //TODO: TESTS
        servletContextHandler.addServlet(new ServletHolder(new PlayersTestHandler()), "/playerstest");

        System.out.println("Registered APIs:");
        Arrays.stream(servletContextHandler.getServletHandler().getServletMappings()).forEach(s -> System.out.println(s.getPathSpecs()[0]));

        server.start();
        server.join();
        server.dump();
    }

    private static URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException {
        URL indexUri = L2API.class.getResource(WEBROOT_INDEX);
        if (indexUri == null) {
            throw new FileNotFoundException("Unable to find resource " + WEBROOT_INDEX);
        }
        // Points to wherever /webroot/ (the resource) is
        return indexUri.toURI();
    }

    private static void enableEmbeddedJspSupport(ServletContextHandler servletContextHandler) throws IOException {
        // Establish Scratch directory for the servlet context (used by JSP compilation)
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");

        if (!scratchDir.exists()) {
            if (!scratchDir.mkdirs()) {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        servletContextHandler.setAttribute("javax.servlet.context.tempdir", scratchDir);

        // Set Classloader of Context to be sane (needed for JSTL)
        // JSP requires a non-System classloader, this simply wraps the
        // embedded System classloader in a way that makes it suitable
        // for JSP to use
        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], L2API.class.getClassLoader());
        servletContextHandler.setClassLoader(jspClassLoader);

        // Manually call JettyJasperInitializer on context startup
        servletContextHandler.addBean(new JspStarter(servletContextHandler));

        // Create / Register JSP Servlet (must be named "jsp" per spec)
        ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
        holderJsp.setInitOrder(0);
        holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
        holderJsp.setInitParameter("fork", "false");
        holderJsp.setInitParameter("xpoweredBy", "false");
        holderJsp.setInitParameter("compilerTargetVM", "1.8");
        holderJsp.setInitParameter("compilerSourceVM", "1.8");
        holderJsp.setInitParameter("keepgenerated", "true");
        servletContextHandler.addServlet(holderJsp, "*.jsp");
    }

    public static class JspStarter extends AbstractLifeCycle implements ServletContextHandler.ServletContainerInitializerCaller {
        JettyJasperInitializer sci;
        ServletContextHandler context;

        public JspStarter(ServletContextHandler context) {
            this.sci = new JettyJasperInitializer();
            this.context = context;
            StandardJarScanner standardJarScanner = new StandardJarScanner();
            standardJarScanner.setScanManifest(false);
            standardJarScanner.setScanAllDirectories(false);
            standardJarScanner.setJarScanFilter((jarScanType, s) -> !s.equalsIgnoreCase("l2jserver.jar"));
            this.context.setAttribute("org.apache.tomcat.JarScanner", standardJarScanner);
        }

        @Override
        protected void doStart() throws Exception {
            ClassLoader old = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(context.getClassLoader());
            try {
                sci.onStartup(null, context.getServletContext());
                super.doStart();
            } finally {
                Thread.currentThread().setContextClassLoader(old);
            }
        }
    }

    private static final SecurityHandler getBasicAuth() {
        HashLoginService loginService = new HashLoginService();
        loginService.setName("Private!");

        UserStore userStore = new UserStore();
        userStore.addUser("admin", new Password("@qate3!"), new String[]{"admin"});

        loginService.setUserStore(userStore);

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__BASIC_AUTH);
        constraint.setRoles(new String[]{"admin"});
        constraint.setAuthenticate(true);

        ConstraintMapping cm = new ConstraintMapping();
        cm.setConstraint(constraint);
        cm.setPathSpec("/*");

        ConstraintSecurityHandler csh = new ConstraintSecurityHandler();
        csh.setAuthenticator(new BasicAuthenticator());
        csh.setRealmName("myrealm");
        csh.addConstraintMapping(cm);
        csh.setLoginService(loginService);

        return csh;
    }
}
