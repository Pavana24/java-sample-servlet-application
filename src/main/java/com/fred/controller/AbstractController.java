package com.fred.controller;

import com.fred.model.User;
import org.jtwig.web.servlet.JtwigRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AbstractController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();

    /**
     * Check user session. If not found, redirect to login page
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return boolean
     */
    protected boolean checkUserAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // Set user to be accessible on template
            request.setAttribute("user", user);
            return true;
        }

        // Avoid redirect on login page
        if (!request.getRequestURI().equals("/servlet-sample/login")) {
            response.sendRedirect("login");
        }
        return false;
    }
}
