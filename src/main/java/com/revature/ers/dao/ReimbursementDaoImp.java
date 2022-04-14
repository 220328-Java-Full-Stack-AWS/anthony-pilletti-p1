package com.revature.ers.dao;

import com.revature.ers.models.Reimbursement;

import java.util.*;



public class ReimbursementDaoImp implements ReimbursementDao{



    private MockReimbursementDB db = MockReimbursementDB.getInstance();

    @Override
    public List<Reimbursement> getReimbursementUser(String username) { return db.getRdb().get(username); }

    @Override
    public void createReimbursement(Reimbursement r){
        String username = r.getAuthor().getUsername();

        List<Reimbursement> reimbursements = db.getRdb().get(username);

        if(reimbursements != null){
            reimbursements.add(r);
        } else {
            reimbursements = new ArrayList<>();
            reimbursements.add(r);
        }


        db.getRdb().put(username, reimbursements);
    }

}
