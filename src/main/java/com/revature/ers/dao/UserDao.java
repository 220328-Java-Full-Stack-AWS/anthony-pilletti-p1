package com.revature.ers.dao;

import com.revature.ers.exceptions.UsernameNotUniqueException;
import com.revature.ers.models.User;
import org.postgresql.util.PSQLException;

import java.util.List;

public interface UserDao {

    public User getUserByUserName(String username);

    public void register(String one, String two, String three, String four, String five) throws UsernameNotUniqueException;

    public List<User> getAllUsers();
    //  public User create(User userToBeCreated);


}
