package com.atiq.handler.player;

import com.atiq.model.APIPlayer;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.Player;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersHandler extends HttpServlet {

    public PlayersHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<APIPlayer> players = World.getInstance().getPlayers().stream().map(APIPlayer::new).collect(Collectors.toList());
        request.setAttribute("players", players);
        try {
            request.getRequestDispatcher("/players.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String playerName = request.getParameter("player_name");
        String action = request.getParameter("action");

        if ("KICK".equals(action)) {
            Player activeChar = World.getInstance().getPlayer(playerName);
            if (activeChar != null)
                activeChar.getClient().closeNow();
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{\"status\":\"OK\"}");
    }
}
