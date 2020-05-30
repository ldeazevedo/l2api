package com.l2timeus.handlers;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayerDataHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String playerName = request.getParameter("playerName");
        Player activeChar = World.getInstance().getPlayer(playerName);
        if (activeChar != null) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(activeChar);
        }
    }
}
