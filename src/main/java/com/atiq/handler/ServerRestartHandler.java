package com.atiq.handler;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.l2j.gameserver.Shutdown;

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
