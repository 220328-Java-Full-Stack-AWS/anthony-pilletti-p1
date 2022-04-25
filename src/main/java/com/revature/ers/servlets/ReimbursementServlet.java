package com.revature.ers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dao.ReimbursementDaoImp;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ReimbursementServlet extends HttpServlet {
    private UserDaoImp uDao;
    private UserService uServ;
    private ReimbursementDaoImp rDao;
    private ReimbursementService rServ;
    private ObjectMapper mapper;

    public ReimbursementServlet() { super(); }

    @Override
    public void init() throws ServletException{
        this.uDao = new UserDaoImp();
        this.uServ = new UserService(uDao);
        this.rDao = new ReimbursementDaoImp();
        this.rServ = new ReimbursementService(rDao);
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch(req.getHeader("mode")){
            case "user":
                List<Reimbursement> all = rDao.getReimbursementUser(uDao.getUserByUserName(req.getHeader("author")));
                Iterator<Reimbursement> aIterate = all.iterator();
                while(aIterate.hasNext()){
                    Reimbursement a = aIterate.next();
                    String json = mapper.writeValueAsString(a);
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    resp.getWriter().print(json);
                }
                break;
            case "status":
                List<Reimbursement> status = rServ.getAllReimbursementsStatus(Integer.parseInt(req.getHeader("status")));
                Iterator<Reimbursement> sIterate = status.iterator();
                while(sIterate.hasNext()){
                    Reimbursement r = sIterate.next();
                    String json = mapper.writeValueAsString(r);
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    resp.getWriter().print(json);
                }
                break;
            case "pendinguser":
                List<Reimbursement> pending = rDao.getPendingReimbursementByUser(uDao.getUserByUserName(req.getHeader("author")));
                Iterator<Reimbursement> pIterate = pending.iterator();
                while(pIterate.hasNext()){
                    Reimbursement p = pIterate.next();
                    String json = mapper.writeValueAsString(p);
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    resp.getWriter().print(json);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rServ.createReimbursement(uDao.getUserByUserName(req.getHeader("username")), Double.parseDouble(req.getHeader("amount")),req.getHeader("type"));
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch(req.getHeader("mode")){
            case "amount":
                rDao.editReimbursement(Double.parseDouble(req.getHeader("amount")), uDao.getUserByUserName(req.getHeader("username")), Integer.parseInt(req.getHeader("id")));
                resp.setStatus(204);
                break;
            case "completed":
                rDao.completeReimbursement(Integer.parseInt(req.getHeader("id")), Integer.parseInt(req.getHeader("status")),uDao.getUserByUserName(req.getHeader("resolver")));
                break;
            default:
                resp.setStatus(400);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rDao.cancelReimbursement(Integer.parseInt(req.getHeader("id")), uDao.getUserByUserName(req.getHeader("username")));
        resp.setStatus(202);
    }
}
