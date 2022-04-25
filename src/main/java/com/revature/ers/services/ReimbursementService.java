package com.revature.ers.services;

import com.revature.ers.dao.ReimbursementDao;
import com.revature.ers.dao.ReimbursementDaoImp;
import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.models.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReimbursementService {
    private ReimbursementDaoImp rd;
    private UserDaoImp uDao = new UserDaoImp();
    private UserService uServ = new UserService(uDao);

    public ReimbursementService(ReimbursementDaoImp rd){ this.rd = rd; }

    public List<Reimbursement> getAllReimbursements(User u){

        List<Reimbursement> allReimbursements = new ArrayList<>();

        if(rd.getReimbursementUser(u) == null){
            return allReimbursements;
        } else {
            allReimbursements.addAll(rd.getReimbursementUser(u));
            return allReimbursements;
        }

    }

    public List<Reimbursement> getAllReimbursementsStatus(int status){

        List<Reimbursement> allReimbursements = new ArrayList<>();
        List<User> viewAll = uServ.allUsers();
        Iterator<User> uIterate = viewAll.iterator();
        while (uIterate.hasNext()){
            User u = uIterate.next();
            if(rd.getReimbursementByStatus(status, u) != null){
                allReimbursements.addAll(rd.getReimbursementByStatus(status, u));
            }
        }
        return allReimbursements;
    }


    public void createReimbursement(User u, double amount, String type) {
        Reimbursement r = new Reimbursement(amount, u, type);
        rd.createReimbursement(r);
    }




}
