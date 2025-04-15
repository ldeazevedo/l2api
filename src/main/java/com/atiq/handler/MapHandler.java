package com.atiq.handler;

import com.atiq.model.APIPlayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.l2j.gameserver.model.World;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MapHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<APIPlayer> onlinePlayers = World.getInstance().getPlayers().stream().map(APIPlayer::new).collect(Collectors.toList());
        request.setAttribute("onlinePlayers", onlinePlayers);
        request.getRequestDispatcher("/map.jsp").forward(request, response);
    }
}
