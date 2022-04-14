package com.revature.ers.dao;

import com.revature.ers.models.User;

import java.util.*;

public class UserDaoImp implements UserDao {

    private MockUserDB db = MockUserDB.getInstance();

    @Override
    public void saveUser(User u){
        db.getUdb().put(u.getUsername(), u);
    }

    public List<User> getAllUsers() { return new ArrayList<User>(db.getUdb().values());}

    public User getUserByUserName(String username) { return db.getUdb().get(username);}

    public void updateUser(User u){db.getUdb().put(u.getUsername(), u); }

}
