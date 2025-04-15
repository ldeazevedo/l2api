package com.atiq.handler.player;

import com.atiq.model.APIPlayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.l2j.gameserver.model.World;

import java.io.IOException;

public class PlayerInfoHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String playerName = request.getParameter("playerName");
        if (playerName != null && !playerName.isEmpty())
            request.setAttribute("player", new APIPlayer(World.getInstance().getPlayer(playerName)));
        request.getRequestDispatcher("/playerdata.jsp").forward(request, response);
    }
}