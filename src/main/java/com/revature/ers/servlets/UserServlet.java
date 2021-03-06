package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.exceptions.UsernameNotUniqueException;
import com.revature.ers.models.Authorization;
import com.revature.ers.models.User;
import com.revature.ers.services.UserService;
import org.postgresql.util.PSQLException;

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
        User user = dao.getUserByUserName(req.getHeader("username"));
        String json = mapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch(req.getHeader("mode")) {
            case "register":
                User u = new ObjectMapper().readValue(req.getInputStream(), User.class);
                if(dao.getUserByUserName(u.getUsername()).getUsername() == null){
                    dao.register(u.getUsername(), u.getPassword(), u.getFirst(),u.getLast(),u.getEmail());
                    resp.setStatus(201);
                    User u2 = dao.getUserByUserName(u.getUsername());
                    String json = mapper.writeValueAsString(u2);
                    resp.setContentType("application/json");
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", u.getUsername());
                    resp.getWriter().print(json);
                }else{
                    resp.setStatus(400);
                }
                break;
            case "login":
                Authorization authorization = new ObjectMapper().readValue(req.getInputStream(), Authorization.class);
                User checkUser = service.login(authorization.getUsername(), authorization.getPassword());
                if (checkUser != null) {
                    resp.setStatus(200);
                    User u3 = dao.getUserByUserName(checkUser.getUsername());
                    String json2 = mapper.writeValueAsString(u3);
                    resp.setContentType("application/json");
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", checkUser.getUsername());
                    resp.getWriter().print(json2);
                } else {
                    resp.setStatus(401);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User updateUser = new ObjectMapper().readValue(req.getInputStream(), User.class);
        resp.setStatus(200);
        resp.getWriter().print(new ObjectMapper().writeValueAsString(updateUser));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(200);
    }

}
