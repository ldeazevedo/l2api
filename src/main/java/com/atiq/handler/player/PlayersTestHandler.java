package com.atiq.handler.player;

import com.atiq.model.APIPlayer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class PlayersTestHandler extends HttpServlet {

    public PlayersTestHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        APIPlayer APIPlayer1 = new APIPlayer(1, "Emmanuel", 55, 4320, 5500, 1200, 0, 5, 10, 80364, 147100, -2450, null);
        APIPlayer APIPlayer2 = new APIPlayer(2, "Sabrina", 60, 2000, 3500, 2800, 400, 20, 12, 80364, 147100, -2450, null);

        request.setAttribute("players", Arrays.asList(APIPlayer1, APIPlayer2));

        try {
            request.getRequestDispatcher("/players.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
    }
}
