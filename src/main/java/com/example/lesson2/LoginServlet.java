package com.example.lesson2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "Admin12345";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a = req.getParameter("a");
        HttpSession session = req.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("use_login");

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String reg = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{9,}";
        String str = req.getParameter("age");
        Integer age = null;

        if (!(str.length() == 0) && str.matches("[\\d]+"))
            age=Integer.parseInt(req.getParameter("age"));

        if (password.equals("") || login.equals("") || age == null) {
            req.setAttribute("fields", "Ввели неверный формат возраста или оставили поля  пустые");
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        } else if (LOGIN.equals(login) && PASS.equals(password) && password.matches(reg) && age >= 18) {
            HttpSession session = req.getSession(true);
            session.setAttribute("use_login", login);
        } else if (LOGIN.equals(login) && PASS.equals(password) && age < 18) {
            req.setAttribute("age", "Вам нет 18");
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        } else {
            req.setAttribute("wrongData", "Вы ввели неверный логин или пароль");
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        }
        resp.sendRedirect("index.jsp");
    }
}