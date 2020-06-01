package com.atiq.handler;

import net.sf.l2j.gameserver.Shutdown;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServerRestartHandler extends HttpServlet {

    public ServerRestartHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int time = 60; //By default 1 minute
        try {
            time = Integer.parseInt(req.getParameter("time"));
            if (time < 0 || time > 1000)
                time = 60;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Shutdown.getInstance().startShutdown(null, "", time, true);
    }
}
