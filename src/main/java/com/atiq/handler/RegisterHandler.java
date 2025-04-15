package com.atiq.handler;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.l2j.commons.crypt.BCrypt;
import net.sf.l2j.commons.lang.StringUtil;
import net.sf.l2j.loginserver.data.sql.AccountTable;

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

        AccountTable.getInstance().createAccount(username, BCrypt.hashPw(password, BCrypt.generateSalt()), System.currentTimeMillis());

        resp.setStatus(200);
    }
}