package com.revature.ers;


import com.revature.ers.dao.*;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Type;
import com.revature.ers.models.User;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.UserService;

import java.util.*;

public class ErsDriver {

//    public static void main(String[] args){
//        UserDaoImp dao = new UserDaoImp();
//        User user = new User("ohyeah", "ohyeah", "ohyeah", "ohyeah", "ohyeah");
//
//        user = dao.create(user);
//        System.out.println(user);
//
//        user = dao.getUserByUserName("anthony01");
//        System.out.println(user);
//    }

    private static UserDao uDao = new UserDaoImp();
    private static UserService uServ = new UserService(uDao);
    private static ReimbursementDao rDao = new ReimbursementDaoImp();
    private static ReimbursementService rServ = new ReimbursementService(rDao);

    public static void main(String[] args) {


        User loggedin = null;
        boolean done = false;

        Scanner scan = new Scanner(System.in);

        while(!done){

            if (loggedin == null){
               System.out.println("Press 1 to login:");
               int choice = scan.nextInt();
               scan.nextLine();
               if(choice == 1){
                   System.out.println("Enter your username:");
                   String username = scan.nextLine();
                   System.out.println("Enter your password:");
                   String password = scan.nextLine();
                   loggedin = uServ.login(username, password);
                   System.out.println(loggedin);
               } else {
                   System.out.println("I didn't quite catch that");
               }
            }
            else {
                System.out.println("Welcome to the ERS");
                System.out.println("What would you like to do today");
                System.out.println("Press 1 to view reimbursements, 2 to create a reimbursement, 3 to view all users");
                int choice = scan.nextInt();
                scan.nextLine();
                switch(choice){
                    case 1:
                        List<Reimbursement> viewAll = rServ.getAllReimbursements(loggedin);
                        Iterator<Reimbursement> rIterate = viewAll.iterator();
                        while (rIterate.hasNext()){
                            Reimbursement r = rIterate.next();
                            System.out.println("Author: " + r.getAuthor().getUsername() + "\t\tReimbursement ID: " + r.getId());
                            System.out.println("Resolver: " + r.getResolver().getUsername() + "\t\tAmount Requested: $" + r.getAmount());
                            System.out.println("Current Status: " + r.getStatus().toString());
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("How much do you need to be reimbursed?");
                        double requested = scan.nextDouble();
                        scan.nextLine();
                        System.out.println("What is the type of Reimbursement? Food, Lodging, or Travel?");
                        String type = scan.nextLine();
                        rServ.createReimbursement(loggedin, requested, type);

                        System.out.println(rDao.getReimbursementUser(loggedin));

                        break;
                    case 3:
                        System.out.println("Here are all the users using the ERS");
                        List<User> allUsers = uServ.allUsers();
                        Iterator<User> uIterate = allUsers.iterator();
                        while(uIterate.hasNext()){
                            User u = uIterate.next();
                            System.out.println(u.getUsername());
                        }
                        break;
                    default:
                        System.out.println("I didn't get, re-enter your choice.");
                }

                System.out.println("Are you dont with your business today? Type y or n");
                if(scan.nextLine().equals("y")){
                    done = true;
                }
           }
        }
        System.out.println("Goodbye");
    }
}

