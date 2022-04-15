package com.revature.ers.dao;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.User;

import java.util.List;

public interface ReimbursementDao {
    //Reimbursements in reimbursement table must be able to get reimbursement by user_id, and status reimb_id
    //also must be able to create a reimbursement, it used to be getting the reimbursement by the user in like a list
    //but not anymore because we do sql commands, also must be able to update the reimbursement
    public Reimbursement createReimbursement(Reimbursement r);
    public List<Reimbursement> getReimbursementUser(User u);
    public List<Reimbursement> getReimbursementByID(int id);
    public void updateReimbursement(Reimbursement r, User u);
    public void cancelReimbursement(Reimbursement r);
    public void cancelReimbursement(int id);
}
