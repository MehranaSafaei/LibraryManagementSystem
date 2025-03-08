package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class DeleteBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the book name: ");
        String bookName = scanner.nextLine();
        int bookId = database.getBook(bookName);
        if (bookId > -1) {
            database.deleteBook(bookId);
            System.out.println("Book deleted successfully.\n");
        }else{
            System.out.println("Book not found.\n");
        }
        scanner.close();
        user.menu(database, user);

    }
}
