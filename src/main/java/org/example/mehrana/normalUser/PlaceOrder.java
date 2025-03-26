package org.example.mehrana.normalUser;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Book;
import org.example.mehrana.entity.Order;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class PlaceOrder implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Order order = new Order();
        System.out.println("\nEnter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int i = database.getBook(bookName);
        if (i <= -1) {
            System.out.println("Book does not exist");
        } else {
            Book book = database.getBook(i);
            order.setBook(book);
            order.setUser(user);

            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();
            order.setQuantity(quantity);
            order.setPrice(book.getPrice() * quantity);
            int bookIndex = database.getBook(bookName);
            book.setQty(book.getQty() - quantity);
            database.AddOrder(order, book, bookIndex);
            System.out.println("Order placed successfully\n");
        }
        user.menu(database, user);
    }
}
