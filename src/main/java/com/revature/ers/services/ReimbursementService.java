package com.revature.ers.services;

import com.revature.ers.dao.ReimbursementDao;
import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Role;
import com.revature.ers.models.Status;
import com.revature.ers.models.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReimbursementService {
    private ReimbursementDao rd;
    private static UserDao uDao = new UserDaoImp();
    private static UserService uServ = new UserService(uDao);

    public ReimbursementService(ReimbursementDao rd){ this.rd = rd; }

    public List<Reimbursement> getAllReimbursements(User u){


        List<Reimbursement> allReimbursements = new ArrayList<>();



        if(rd.getReimbursementUser(u.getUsername()) == null){
            return allReimbursements;
        } else {
            allReimbursements.addAll(rd.getReimbursementUser(u.getUsername()));
            return allReimbursements;
        }

    }

    public void createReimbursement(User u, double amount) {
        int id = (int) (1000 + (Math.random() * 10000));
        List<User> viewAll = uServ.allUsers();
        Iterator<User> uIterate = viewAll.iterator();
        User res = uIterate.next();
        while(uIterate.hasNext()){
            res = uIterate.next();
            if(res.getRole().equals(Role.FINANCE_MANAGER)){
                res = res;
                break;
            }

        }
        Reimbursement r = new Reimbursement(id, Status.PENDING, u, res, amount);
        rd.createReimbursement(r);
    }

}
