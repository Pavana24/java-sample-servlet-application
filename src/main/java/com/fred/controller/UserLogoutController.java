package com.fred.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class UserLogoutController extends AbstractController {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        // Destroy session and redirect to home
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/servlet-sample");
    }
}