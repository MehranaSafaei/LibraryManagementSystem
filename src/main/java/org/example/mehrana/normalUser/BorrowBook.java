package org.example.mehrana.normalUser;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Book;
import org.example.mehrana.entity.Borrowing;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class BorrowBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("\nEnter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int i = database.getBook(bookName);
        if (i > -1) {
            Book book = database.getBook(i);
            boolean n = true;
            for (Borrowing borrowing : database.getBrw()) {
                if (borrowing.getBook().getName().matches(bookName) && borrowing.getUser().getName().matches(user.getName())) {
                    n = false;
                    System.out.println("you have borrow this book before!");
                }
            }
//        if (i <= -1 && n) {
            if (n) {
                if (book.getBrwCopies() > 1) {
                    Borrowing borrowing = new Borrowing(book, user);
                    book.setBrwCopies(book.getBrwCopies() - 1);
                    database.borrowBook(borrowing, book, i);
                    System.out.println("you must return the book before 14 days from now\n" +
                            "Expiry date: " + borrowing.getEndDate() + "\nenjoy!\n");
                } else {
                    System.out.println("this book isn`t available for borrowing!");
                }
            }
        } else {
            System.out.println("Book does`t exist!");
        }
        user.menu(database, user);
    }
}
