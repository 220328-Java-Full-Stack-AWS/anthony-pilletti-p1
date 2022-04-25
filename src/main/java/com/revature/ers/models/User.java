package com.revature.ers.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class User{
    private int id;
    private String username;
    private String password;
    private String first;
    private String last;
    private String email;
    private Role role;

    public User() {
    }

    public User(String username, String password, String first, String last, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.email = email;
    }

    public User(int id, String username, String password, String first, String last, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setRole(int role) {
        if(role == 1){
            this.role = Role.EMPLOYEE;
        } else if (role == 2){
            this.role = Role.FINANCE_MANAGER;
        }
    }
    public void setRole(String role){
        if(role.equals("Employee")){
            this.role = Role.EMPLOYEE;
        } else if(role.equals("Finance Manager")){
            this.role = Role.FINANCE_MANAGER;
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User that = (User) o;
//        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
