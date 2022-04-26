package com.revature.ers.dao;


import com.revature.ers.models.User;
import com.revature.ers.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImp implements UserDao {

    @Override
    public User getUserByUserName(String username) {
        User u = new User();
        try{
            String sql = "select eu.USER_ID, USERNAME, eu.ERS_PASSWORD, eu.FIRST_NAME, eu.LAST_NAME, eu.EMAIL, eur.USER_ROLE " +
                "from ERS_USERS eu " +
                "join ERS_USER_ROLES eur " +
                "on eu.ROLE_ID =eur.ROLE_ID " +
                "where eu.USERNAME = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                u.setId(results.getInt("USER_ID"));
                u.setUsername(results.getString("USERNAME"));
                u.setPassword(results.getString("ERS_PASSWORD"));
                u.setFirst(results.getString("FIRST_NAME"));
                u.setLast(results.getString("LAST_NAME"));
                u.setEmail(results.getString("EMAIL"));
                u.setRole(results.getString("USER_ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void create(String one, String two, String three, String four, String five) {
        String sql = "INSERT INTO ERS_USERS (USERNAME, ERS_PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE_ID) "
            + "values (?, ?, ?, ?, ?, 0);";
        try{
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setString(1, one);
            pstmt.setString(2, two);
            pstmt.setString(3, three);
            pstmt.setString(4, four);
            pstmt.setString(5, five);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM ERS_USERS ";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                User u = new User();
                u.setId(results.getInt("USER_ID"));
                u.setUsername(results.getString("USERNAME"));
                u.setPassword(results.getString("ERS_PASSWORD"));
                u.setFirst(results.getString("FIRST_NAME"));
                u.setLast(results.getString("LAST_NAME"));
                u.setEmail(results.getString("EMAIL"));
                u.setRole(results.getInt("ROLE_ID"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
