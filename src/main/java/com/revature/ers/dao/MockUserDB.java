package com.revature.ers.dao;

import com.revature.ers.models.Role;
import com.revature.ers.models.User;

import java.util.*;

public class MockUserDB {

    private Map<String, User> udb;

    private static MockUserDB db;

    private MockUserDB() {udb = new HashMap<>();}

    static public MockUserDB getInstance() {
        if(db == null){
            db = new MockUserDB();
        }
        return db;
    }

    public Map<String, User> getUdb() {return udb;}

    public void populateUsers() {
        User u1 = new User(1111, "anthony01", "password", Role.EMPLOYEE);
        User u2 = new User(2222, "jen01", "password", Role.FINANCE_MANAGER);
        User u3 = new User(3333, "michael01", "password", Role.EMPLOYEE);
        User u4 = new User(4444, "diego01", "password", Role.FINANCE_MANAGER);


        udb.put(u1.getUsername(), u1);
        udb.put(u2.getUsername(), u2);
        udb.put(u3.getUsername(), u3);
        udb.put(u4.getUsername(), u4);
    }
}
