package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.GlobalObjectStore;
import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.models.Authorization;
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
        Authorization authorization = new ObjectMapper().readValue(req.getInputStream(), Authorization.class);
        User checkUser = service.login(authorization.getUsername(), authorization.getPassword());
        if(checkUser != null){
            resp.setStatus(200);
            resp.getWriter().print(new ObjectMapper().writeValueAsString(checkUser));
            resp.setHeader("access-control-expose-headers", "authToken");
            resp.setHeader("authToken", checkUser.getUsername());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new ObjectMapper().readValue(req.getInputStream(), User.class);
        GlobalObjectStore.addObject(u.getUsername(), u);
        dao.create(u.getUsername(), u.getPassword(), u.getFirst(), u.getLast(), u.getEmail());
        resp.setStatus(201);
        resp.getWriter().print(dao.getUserByUserName(u.getUsername()));
        resp.setHeader("access-control-expose-headers", "authToken");
        resp.setHeader("authToken", u.getUsername());
    }

}
