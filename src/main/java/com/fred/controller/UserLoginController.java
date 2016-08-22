package com.fred.controller;

import com.fred.dao.UserDAO;
import com.fred.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginController extends AbstractController {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        this.renderer.dispatcherFor("/WEB-INF/templates/user/login.twig.html").render(request, response);
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user;

            if (email.equals("") || password.equals("")) {
                throw new Exception("Informe e-mail e senha");
            }

            // Search user by email and password
            try {
                UserDAO userDAO = new UserDAO();
                user = userDAO.findByEmailAndPassword(email, password);
            } catch (Exception e) {
                throw new Exception("Problema ao realizar login");
            }

            if (user == null) {
                throw new Exception("E-mail e/ou senha incorretos");
            }

            // Save user on session and redirect him to home
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30);
            session.setAttribute("user", user);
            response.sendRedirect("/servlet-sample");

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            this.renderer.dispatcherFor("/WEB-INF/templates/user/login.twig.html").render(request, response);
        }
    }
}