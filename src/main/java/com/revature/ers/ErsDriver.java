package com.revature.ers;


import com.revature.ers.dao.*;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.Role;
import com.revature.ers.models.User;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.UserService;

import java.util.*;

public class ErsDriver {

    private static UserDaoImp uDao = new UserDaoImp();
    private static UserService uServ = new UserService(uDao);
    private static ReimbursementDaoImp rDao = new ReimbursementDaoImp();
    private static ReimbursementService rServ = new ReimbursementService(rDao);

    public static void main(String[] args) {

        User loggedin = null;
        boolean done = false;

        Scanner scan = new Scanner(System.in);

        while(!done){

            if (loggedin == null){
               System.out.println("Press 1 to login, or 2 to register for an account:");
               int choice = scan.nextInt();
               scan.nextLine();
               if(choice == 1){
                   System.out.println("Enter your username:");
                   String username = scan.nextLine();
                   System.out.println("Enter your password:");
                   String password = scan.nextLine();
                   loggedin = uServ.login(username, password);
                   System.out.println(loggedin);
               } else if (choice == 2) {
                   System.out.println("What is your first name?");
                   String first = scan.nextLine();
                   System.out.println("What is your last name?");
                   String last = scan.nextLine();
                   System.out.println("Enter a username:");
                   String username = scan.nextLine();
                   System.out.println("Enter a password:");
                   String password = scan.nextLine();
                   System.out.println("Enter your email:");
                   String email = scan.nextLine();
                   uDao.create(username,password,first,last,email);
                   break;
               }else {
                   System.out.println("I didn't quite catch that");
               }
            }
            else if(loggedin!=null && loggedin.getRole().equals(Role.EMPLOYEE)) {
                System.out.println("Welcome to the Employee Reimbursement System," + loggedin.getFirst() + " " + loggedin.getLast() + ".");
                System.out.println("What would you like to do today?");
                System.out.println("Press 1 to create a reimbursement.");
                System.out.println("Press 2 to cancel a pending reimbursement.");
                System.out.println("Press 3 to view all past reimbursements, including pending and completed.");
                System.out.println("Press 4 to edit a past reimbursement");
                int choice = scan.nextInt();
                scan.nextLine();
                switch(choice){
                    case 1:
                        System.out.println("How much do you need to be reimbursed?");
                        double requested = scan.nextDouble();
                        scan.nextLine();
                        System.out.println("What is the type of Reimbursement? Food, Lodging, or Travel?");
                        String type = scan.nextLine();
                        rServ.createReimbursement(loggedin, requested, type);

                        System.out.println(rDao.getReimbursementUser(loggedin));

                        break;

                    case 2:
                        System.out.println("These are all of your pending requests.");
                        List<Reimbursement> allPending = rDao.getPendingReimbursementByUser(loggedin);
                        Iterator<Reimbursement> pIterate = allPending.iterator();
                        while (pIterate.hasNext()){
                            Reimbursement r = pIterate.next();
                            System.out.println(r);
                            System.out.println();
                        }
                        System.out.println("Which reimbursement would you like to delete?");
                        System.out.println("Please input the ID number of the corresponding reimbursement you would like to cancel.");
                        int delete = scan.nextInt();
                        scan.nextLine();
                        rDao.cancelReimbursement(delete, loggedin);
                        break;
                    case 3:
                        List<Reimbursement> viewAll = rServ.getAllReimbursements(loggedin);
                        Iterator<Reimbursement> rIterate = viewAll.iterator();
                        while (rIterate.hasNext()){
                            Reimbursement r = rIterate.next();
                            System.out.println(r);
                            System.out.println();
                        }
                        break;
                    case 4:
                        System.out.println("These are all of your pending requests.");
                        List<Reimbursement> editList = rDao.getPendingReimbursementByUser(loggedin);
                        Iterator<Reimbursement> eIterate = editList.iterator();
                        while (eIterate.hasNext()){
                            Reimbursement r = eIterate.next();
                            System.out.println(r);
                            System.out.println();
                        }
                        System.out.println("Which reimbursement would you like to alter?");
                        System.out.println("Please input the ID number of the corresponding reimbursement you would like to alter.");
                        int edit = scan.nextInt();
                        scan.nextLine();
                        System.out.println("What is the new amount that you would like to be reimbursed for?");
                        double amount = scan.nextDouble();
                        scan.nextLine();
                        rDao.editReimbursement(amount, loggedin, edit);
                        break;
                    default:
                        System.out.println("I didn't get that, re-enter your choice.");
                }

                System.out.println("Are you done with your system today? Type y or n");
                if(scan.nextLine().equals("y")){
                    done = true;
                }
            } else if(loggedin!=null && loggedin.getRole().equals(Role.FINANCE_MANAGER)){
                System.out.println("Welcome to the Authentication Service for the ERS, " + loggedin.getFirst() + " " + loggedin.getLast() + ".");
                System.out.println("What would you like to do today?");
                System.out.println("Press 1 if you would like to view all reimbursements.");
                System.out.println("Press 2 if you would like to approve or deny a pending reimbursement.");
                int choice = scan.nextInt();
                scan.nextLine();
                switch(choice){
                    case 1:
                        System.out.println("How would you like to filter the list?");
                        System.out.println("0 for Pending, 1 for Approved, and 2 for Denied.");
                        int filter = scan.nextInt();
                        scan.nextLine();
                        System.out.println("These are all the reimbursements waiting to be completed:");
                        List<Reimbursement> allStatus = rServ.getAllReimbursementsStatus(filter);
                        Iterator<Reimbursement> sIterate = allStatus.iterator();
                        while (sIterate.hasNext()){
                            Reimbursement r = sIterate.next();
                            System.out.println(r);
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("Which reimbursement would you like to approve or deny, please enter its ID:");
                        int update = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Press 1 to approve or 2 to deny the reimbursement.");
                        int status = scan.nextInt();
                        scan.nextLine();
                        rDao.completeReimbursement(update, status, loggedin);
                        break;
                }
                System.out.println("Are you done with your system today? Type y or n");
                if(scan.nextLine().equals("y")){
                    done = true;
                }
            }
        }
        System.out.println("Goodbye, have a nice day!");
    }
}

