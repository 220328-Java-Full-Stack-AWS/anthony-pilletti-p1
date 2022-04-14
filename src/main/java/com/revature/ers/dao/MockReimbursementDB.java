package com.revature.ers.dao;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Status;
import com.revature.ers.models.User;

import java.util.*;


public class MockReimbursementDB {
    private Map<String, List<Reimbursement>> rdb;

    private static MockReimbursementDB db;

    private MockReimbursementDB() { rdb = new HashMap<>();}

    public static MockReimbursementDB getInstance(){
        if(db == null){
            db = new MockReimbursementDB();
        }
        return db;
    }

    public Map<String, List<Reimbursement>> getRdb() { return rdb;}

    public void populateReimbursements() {

        User u1 = MockUserDB.getInstance().getUdb().get("anthony01");
        User u2 = MockUserDB.getInstance().getUdb().get("jen01");
        User u3 = MockUserDB.getInstance().getUdb().get("michael01");
        User u4 = MockUserDB.getInstance().getUdb().get("diego01");

        Reimbursement r1 = new Reimbursement(1321, Status.APPROVED, u1, u2, 1000);
        Reimbursement r2 = new Reimbursement(2321, Status.DENIED, u1, u2, 100000);
        Reimbursement r3 = new Reimbursement(3321, Status.PENDING, u1, u4, 1000);

        List<Reimbursement> u1Reimbursements = new ArrayList<>();
        u1Reimbursements.add(r1);
        u1Reimbursements.add(r2);
        u1Reimbursements.add(r3);

        Reimbursement r4 = new Reimbursement(4675, Status.APPROVED, u3, u4, 100000000);
        Reimbursement r5 = new Reimbursement(6375, Status.APPROVED, u3, u2, 100000000);
        Reimbursement r6 = new Reimbursement(4567, Status.APPROVED, u3, u4, 100000000);

        List<Reimbursement> u3Reimbursements = new ArrayList<>();
        u3Reimbursements.add(r4);
        u3Reimbursements.add(r5);
        u3Reimbursements.add(r6);

        rdb.put(u1.getUsername(), u1Reimbursements);
        rdb.put(u3.getUsername(), u3Reimbursements);



    }
}
