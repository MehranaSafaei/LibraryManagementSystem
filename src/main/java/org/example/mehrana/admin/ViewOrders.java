package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Order;
import org.example.mehrana.entity.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int i = database.getBook(bookName);
        if (i > -1){
            System.out.println("Book\t\tUser\t\tPrice\t\tQuantity");
            for (Order order: database.getAllOrders()){
                if (order.getBook().equals(bookName)){
                    System.out.println(order.getBook().getName() + "\t\t" + order.getUser().getName() + "\t\t"+ order.getPrice() + "\t\t" + order.getQuantity());
                }
            }
            System.out.println();
        }else {
            System.out.println("Book does not exist\n");
        }
        user.menu(database, user);
    }
}
