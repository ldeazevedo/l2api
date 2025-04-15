package com.atiq.handler;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.l2j.gameserver.model.World;

public class AnnounceHandler extends HttpServlet {

    public AnnounceHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String announce = request.getParameter("ann");
        World.announceToOnlinePlayers(announce, false);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}