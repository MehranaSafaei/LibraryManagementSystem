package org.example.mehrana.entity;

import org.example.mehrana.Database;
import org.example.mehrana.admin.Exit;
import org.example.mehrana.IOOperation;
import org.example.mehrana.admin.ViewOrders;
import org.example.mehrana.normalUser.BorrowBook;
import org.example.mehrana.normalUser.CalculateFine;
import org.example.mehrana.normalUser.ReturnBook;
import org.example.mehrana.normalUser.Search;

import java.util.Scanner;

public class NormalUser extends User {

    public NormalUser(String name) {
        super(name);
        this.operation = new IOOperation[]{
                new ViewOrders(),
                new Search(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public NormalUser(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operation = new IOOperation[]{
                new ViewOrders(),
                new Search(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }
    @Override
    public void menu(Database database, User user){
        System.out.println("1. view Books ");
        System.out.println("2. search ");
        System.out.println("3. place Order ");
        System.out.println("4. Borrow Book ");
        System.out.println("5. Calculate Fine ");
        System.out.println("6. Return Book ");
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
