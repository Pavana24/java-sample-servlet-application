package com.fred.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends AbstractController {

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        if (this.checkUserAuthentication(request, response)) {
            this.renderer.dispatcherFor("/WEB-INF/templates/user/index.twig.html").render(request, response);
        }
    }
}