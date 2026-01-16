package com.bank;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // In-memory account storage
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    @Override
    public void init() {
        // Pre-create an account (optional)
        accounts.add(new BankAccount(101, "Jayanth", "1234", 5000));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int accNo = Integer.parseInt(req.getParameter("accno"));
        String pass = req.getParameter("password");

        BankAccount acc = null;

        for (BankAccount a : accounts) {
            if (a.getAccNo() == accNo && a.checkPassword(pass)) {
                acc = a;
                break;
            }
        }

        if (acc != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", acc);
            res.sendRedirect("menu.html");
        } else {
            res.getWriter().println("Invalid Account Number or Password");
        }
    }

    // Static method to add accounts (for future registration)
    public static void addAccount(BankAccount acc) {
        accounts.add(acc);
    }
}
