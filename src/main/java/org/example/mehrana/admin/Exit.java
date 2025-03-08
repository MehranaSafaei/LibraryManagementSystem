package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Admin;
import org.example.mehrana.entity.NormalUser;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class Exit implements IOOperation {
    Scanner scanner;
    Database database;
    User user;
    @Override
    public void oper(Database database, User user) {
        this.database = database;
        this.user = user;

        System.out.println("\nAre you sure you want to Exit?\n" +
                "1.yes\n2.no(Main menu");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("0.exit\n1.login\n2.new user");
            scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
//                    System.exit(0);
                    System.out.println("Exiting...");
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    newUser();
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }else if (choice == 2) {
            user.menu(database, user);
        }
    }
    private void login() {
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.next().trim();  // Get input from user
        System.out.println("please Enter your email address: ");
        String email = scanner.next().trim(); //(trim): delete extra spaces
        System.out.println("login created: " + phoneNumber + " with email: " + email);
        int n = database.login(phoneNumber, email);
        if (n != -1) {
            User user = database.getUser(n);
            user.menu(database,user);
        } else {
            System.out.println("user does`t exist! ");
            ;
        }
    }

    private void newUser() {
        System.out.println("Please enter your name: ");
        String name = scanner.next();  // Get user's name
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.next();  // Get phone number
        System.out.println("Please enter your email address: ");
        String email = scanner.next();
        System.out.println("1. Admin\n2. Normal User");
        int choice = scanner.nextInt();
        User user;
        if (choice == 1) {
            user = new Admin(name, phoneNumber, email);
        } else {
            user = new NormalUser(name, phoneNumber, email);
        }
        database.addUser(user);
        user.menu(database,user);
    }

}
