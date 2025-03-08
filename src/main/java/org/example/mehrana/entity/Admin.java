package org.example.mehrana.entity;

import org.example.mehrana.*;
import org.example.mehrana.admin.*;
import org.example.mehrana.normalUser.Search;

import java.util.Scanner;

public class Admin extends User {


    public Admin(String name) {
        super(name);
        this.operation = new IOOperation[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }
    public Admin(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operation = new IOOperation[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()        };
    }
    @Override
    public void menu(Database database, User user){
        System.out.println("1. View Books ");
        System.out.println("2. Add Books ");
        System.out.println("3. Delete Books ");
        System.out.println("4. search ");
        System.out.println("5. Delete All Books ");
        System.out.println("6. view orders ");
        System.out.println("7. Exit ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        this.operation[choice-1].oper(database, user);
        scanner.close();
    }

    public String toString(){
       return name + "<N/>"+ email + "<N/>"+ phoneNumber + "<N/>" + "Admin";
    }
}
