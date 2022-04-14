package com.revature.ers.services;


import com.revature.ers.dao.UserDao;
import com.revature.ers.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.ers.models.User;

import java.util.List;

public class UserService {
    private UserDao ud;

    public  UserService(UserDao ud){ this.ud = ud; }

    public User login(String username, String password){
        if(ud.getUserByUserName(username) != null && ud.getUserByUserName(username).getPassword().equals(password)){
            return ud.getUserByUserName(username);
        } else {
            throw new UsernameOrPasswordIncorrectException();
        }
    }

    public List<User> allUsers(){
        List<User> userList = ud.getAllUsers();
        return userList;
    }
}
