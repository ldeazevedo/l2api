package com.l2timeus.handlers;

import org.eclipse.jetty.servlet.DefaultServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloHandler extends DefaultServlet {

    public HelloHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/players.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
        super.doGet(request, response);
    }
}
