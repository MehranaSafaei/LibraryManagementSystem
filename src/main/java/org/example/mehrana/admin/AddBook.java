package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Book;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class AddBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("Enter the name of the book");
        String bookName = scanner.nextLine();
        if (database.getBook(bookName) > -1){
            System.out.println("There is already a book with the name " + bookName);
            user.menu(database, user);
            return;
        }else {
            book.setName(bookName);
            System.out.println("Enter the author of the book");
            book.setAuthor(scanner.next());
            System.out.println("Enter the publisher of the book");
            book.setPublisher(scanner.next());
            System.out.println("Enter book collection address ");
            book.setAddress(scanner.next());
            System.out.println("Enter the qty of the book");
            book.setQty(scanner.nextInt());
            System.out.println("Enter the price of the book");
            book.setPrice(scanner.nextDouble());
            System.out.println("Enter borrowing copies of the book");
            book.setBrwCopies(scanner.nextInt());
            database.AddBook(book);
            System.out.println("Book added successfully\n");
            user.menu(database, user);
        }
    }
}
