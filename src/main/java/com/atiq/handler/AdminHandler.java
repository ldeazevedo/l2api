package com.atiq.handler;

import com.atiq.model.Donation;
import net.sf.l2j.L2DatabaseFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdminHandler extends HttpServlet {

    private static final String GET_ALL_DONATIONS_QUERY = "SELECT " +
            "so.id, so.login, sp.package_name, order_status, payment_method, order_date, ref " +
            "FROM site_orders so " +
            "JOIN site_packages sp ON so.package_id=sp.id " +
            "ORDER BY order_date DESC";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("donations", getDonations());
        try {
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (ServletException e1) {
            e1.printStackTrace();
        }
    }

    public List<Donation> getDonations() {
        List<Donation> donations = new ArrayList<>();
        try (Connection conn = L2DatabaseFactory.getInstance().getConnection()) {
            PreparedStatement ps = conn.prepareStatement(GET_ALL_DONATIONS_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Donation donation = new Donation();
                donation.setId(rs.getInt("id"));
                donation.setLogin(rs.getString("login"));
                donation.setPackageName(rs.getString("package_name"));
                donation.setStatus(checkStatus(rs.getInt("order_status")));
                donation.setPaymentMethod(checkPaymentMethod(rs.getInt("payment_method")));
                donation.setDate(checkDate(rs.getLong("order_date")));
                donation.setRef(rs.getString("ref"));
                donations.add(donation);
            }
            return donations;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String checkStatus(int status) {
        switch (status) {
            case 0:
                return "IN PROGRESS";
            case 1:
                return "DELIVERED";
            default:
                return "NO DATA";
        }
    }

    private String checkPaymentMethod(int paymentMethod) {
        switch (paymentMethod) {
            case 1:
                return "Paypal";
            case 2:
                return "Paygol";
            case 12:
                return "PayU";
            default:
                return "NO DATA";
        }
    }

    private String checkDate(long epoch) {
        Instant instant = Instant.ofEpochSecond(epoch);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        return ZonedDateTime.ofInstant(instant, ZoneOffset.ofHours(-3)).format(formatter);
    }
}