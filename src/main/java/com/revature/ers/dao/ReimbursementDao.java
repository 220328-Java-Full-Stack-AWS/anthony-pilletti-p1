package com.revature.ers.dao;

import com.revature.ers.models.Reimbursement;
import java.util.List;

public interface ReimbursementDao {
    List<Reimbursement> getReimbursementUser(String username);

    void createReimbursement(Reimbursement r);
}
