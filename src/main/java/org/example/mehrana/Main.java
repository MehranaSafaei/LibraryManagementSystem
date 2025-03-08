package org.example.mehrana;

import org.example.mehrana.entity.Admin;
import org.example.mehrana.entity.NormalUser;
import org.example.mehrana.entity.User;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner scanner;
    static Database database;

    public static void main(String[] args) throws IOException {
        database = new Database();

        System.out.println("Welcome to Library Management System");
        int choice = 0;
//        do {
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
//        } while (true);
//        scanner.close();
    }


    private static void login() {
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

    private static void newUser() {
        System.out.println("Please enter your name: ");
        String name = scanner.next();  // Get user's name
        if (database.userExists(name)){
            System.out.println("User exists");
            newUser();
        }
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
