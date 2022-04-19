package com.revature.ers.dao;

import com.revature.ers.models.User;

import java.util.List;

public interface UserDao {

    public User getUserByUserName(String username);

    public void create(String one, String two, String three, String four, String five);

    public List<User> getAllUsers();
    //  public User create(User userToBeCreated);


}
