package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Book;
import org.example.mehrana.entity.User;

import java.util.ArrayList;

public class ViewBooks implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("View books");
        ArrayList<Book> books = database.getAllBook();
        System.out.println("Name\tAuthor\t\tpublisher\tCLA\tQty\tPrice" +
                "\tBrw cps");
        for (Book book : books) {
            System.out.println(book.getName()+"\t"+book.getAuthor()+"\t\t"+book.getPublisher()+"\t"+book.getAddress()+"\t"+book.getQty()+"\t"+book.getPrice()+"\t"+book.getBrwCopies());
        }
        System.out.println();
        user.menu(database, user);
    }
}
