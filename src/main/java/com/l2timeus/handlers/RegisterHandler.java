package com.l2timeus.handlers;

import net.sf.l2j.commons.crypt.BCrypt;
import net.sf.l2j.commons.lang.StringUtil;
import net.sf.l2j.loginserver.data.sql.AccountTable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String cPasswrod = req.getParameter("cpassword");

        if (StringUtil.isEmpty(username, password, cPasswrod) || !password.equals(cPasswrod)) {
            resp.sendError(500, "Error when creating password");
            return;
        }

        AccountTable.getInstance().createAccount(username, BCrypt.hashpw(password, BCrypt.gensalt()), System.currentTimeMillis());

        resp.setStatus(200);
    }
}