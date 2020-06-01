package com.atiq.handler;

import com.atiq.model.BannedPlayer;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.World;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler extends HttpServlet {

    private static final String BANNED_CHARACTERS_QUERY = "SELECT p.*, c.char_name " +
            "FROM punishments p " +
            "JOIN characters c " +
            "ON (p.key=c.char_name OR p.key=c.charId) " +
            "WHERE p.type not in ('JAIL')";

    public ServerHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("playersonline", World.getInstance().getPlayers().size());
        request.setAttribute("banned_players", null);
        try {
            request.getRequestDispatcher("/server.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
    }

    private List<BannedPlayer> getBannedCharacters() {
        List<BannedPlayer> bannedPlayerList = new ArrayList<>();
        try (Connection conn = L2DatabaseFactory.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(BANNED_CHARACTERS_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String charName = rs.getString("char_name");
                String key = charName.isEmpty() ? rs.getString("key") : charName;
                String punishedBy = rs.getString("punishedBy");
                String reason = rs.getString("reason");
                String type = rs.getString("type");
                String affect = rs.getString("affect");

                bannedPlayerList.add(new BannedPlayer(key, punishedBy, reason, type, affect));
            }
            return bannedPlayerList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
