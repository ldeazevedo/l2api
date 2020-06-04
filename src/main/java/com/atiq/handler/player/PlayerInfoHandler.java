package com.atiq.handler.player;

import com.atiq.model.APIPlayer;
import net.sf.l2j.gameserver.model.World;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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