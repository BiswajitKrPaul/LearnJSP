package com.tech.controllers;

import com.tech.dao.UserDao;
import com.tech.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class getDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        ArrayList<User> allUser = userDao.getAllUser();
        req.setAttribute("alluser", allUser);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/showdata.jsp");
        rd.forward(req, resp);
    }
}
