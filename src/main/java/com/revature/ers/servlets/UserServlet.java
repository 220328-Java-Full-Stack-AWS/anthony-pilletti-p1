package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.models.User;
import com.revature.ers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private UserDaoImp dao;
    private UserService service;
    private ObjectMapper mapper;

    public UserServlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
        this.dao = new UserDaoImp();
        this.service = new UserService(dao);
        this.mapper = new ObjectMapper();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model = service.login(req.getHeader("username"), req.getHeader("password"));
        String json = mapper.writeValueAsString(model);
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao.create(req.getHeader("username"), req.getHeader("password"), req.getHeader("first"), req.getHeader("last"), req.getHeader("email"));
        User model = service.login(req.getHeader("username"), req.getHeader("password"));
        String json = mapper.writeValueAsString(model);
        resp.setContentType("application/json");
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

}
