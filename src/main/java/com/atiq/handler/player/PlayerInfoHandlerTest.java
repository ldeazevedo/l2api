package com.atiq.handler.player;

import com.atiq.model.APIItem;
import com.atiq.model.APIPlayer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.l2j.gameserver.model.World;

import java.io.IOException;
import java.util.Collections;

public class PlayerInfoHandlerTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        APIItem apiItem = new APIItem(57, "Adena", 500000, "INVENTORY");
        APIPlayer APIPlayer1 = new APIPlayer(1, "Emmanuel", 55, 4320, 5500, 1200, 0, 5, 10, 80364, 147100, -2450, Collections.singletonList(apiItem));

        request.setAttribute("player", APIPlayer1);

        request.getRequestDispatcher("/playerinfo.jsp").forward(request, response);
    }
}