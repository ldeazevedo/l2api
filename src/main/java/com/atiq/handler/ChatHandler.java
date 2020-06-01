package com.atiq.handler;

import com.atiq.Util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatHandler extends HttpServlet {

    public ChatHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String log = Util.readFile("./log/chat/chat.txt");
        String[] logParsedString = log.split("<br>");

        StringBuilder stringBuilder = new StringBuilder();

        for (String chatLine : logParsedString) {
            String[] chatLineParsed = chatLine.split("]");
            String playerName = chatLineParsed[1].split("\\[")[1].trim();
            String type = chatLineParsed[1].split("\\[")[0].trim();
            String color;
            switch (type) {
                case "SHOUT":
                    color = "orange";
                    break;
                case "TRADE":
                    color = "violet";
                    break;
                default:
                    continue;
            }

            int startingIndex = chatLine.indexOf("]", 20) + 1;
            String chatText = chatLine.substring(startingIndex);

            if (chatText.contains("Type") && chatText.contains("Color") && chatText.contains("Underline"))
                continue;
            if (playerName.isEmpty())
                continue;

            stringBuilder.append("<font color=\"").append(color).append("\">")
                    .append(playerName).append(": ").append(chatText).append("</font>")
                    .append("<br>");
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(stringBuilder);
    }
}