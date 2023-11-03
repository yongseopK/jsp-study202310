package com.jsp.chap04;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvc/register")
public class RegViewServlet extends HttpServlet {

    public static final String FORWARD_REF = "/WEB-INF/chap04/dancer/register.jsp";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(FORWARD_REF);
        rd.forward(req, resp);
    }
}
