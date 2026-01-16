package com.bank;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            res.getWriter().println("Please login first!");
            return;
        }

        BankAccount acc = (BankAccount) session.getAttribute("account");
        String action = req.getParameter("action");
        double amt = Double.parseDouble(req.getParameter("amount"));

        PrintWriter out = res.getWriter();

        if ("deposit".equals(action)) {
            acc.deposit(amt);
            out.println("Deposit successful. Current Balance: " + acc.getBalance());
        } else if ("withdraw".equals(action)) {
            if (acc.withdraw(amt)) {
                out.println("Withdraw successful. Current Balance: " + acc.getBalance());
            } else {
                out.println("Insufficient balance. Current Balance: " + acc.getBalance());
            }
        } else {
            out.println("Invalid action!");
        }
    }
}
