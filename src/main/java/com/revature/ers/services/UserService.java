package com.revature.ers.services;


import com.revature.ers.dao.UserDao;
import com.revature.ers.dao.UserDaoImp;
import com.revature.ers.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDaoImp ud;

    public UserService(UserDaoImp ud){
        this.ud = ud;
    }


    public User login(String username, String password) {
        if(ud.getUserByUserName(username) != null && ud.getUserByUserName(username).getPassword().equals(password)){
            return ud.getUserByUserName(username);
        } else{
            throw new UsernameOrPasswordIncorrectException();
        }
    }
    public List<User> allUsers(){
        List<User> userList = ud.getAllUsers();
        return userList;
    }
}
