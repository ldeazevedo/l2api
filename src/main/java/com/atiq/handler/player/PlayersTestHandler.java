package com.atiq.handler.player;

import com.atiq.model.APIPlayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

public class PlayersTestHandler extends HttpServlet {

    public PlayersTestHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        APIPlayer APIPlayer1 = new APIPlayer(1, "Emmanuel", 55, 4320, 5500, 1200, 0, 5, 10, 80364, 147100, -2450, null);

        request.setAttribute("players", Collections.singletonList(APIPlayer1));

        try {
            request.getRequestDispatcher("/players.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
    }
}
