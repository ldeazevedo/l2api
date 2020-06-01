package com.atiq.handler;

import net.sf.l2j.gameserver.model.World;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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