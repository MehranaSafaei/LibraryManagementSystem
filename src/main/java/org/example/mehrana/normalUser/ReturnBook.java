package org.example.mehrana.normalUser;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Book;
import org.example.mehrana.entity.Borrowing;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class ReturnBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        if (!database.getBrw().isEmpty()){
            for (Borrowing borrowing: database.getBrw()){
                if (borrowing.getBook().getName().matches(bookName) && borrowing.getUser().getName().matches(user.getName())){
                    Book book = borrowing.getBook();
                    int i = database.getAllBook().indexOf(book);
                    if (borrowing.getDaysLeft()<0){
                        System.out.println("you are late!!"+ "you have to pay"+
                                Math.abs(borrowing.getDaysLeft()*50)+ "as fine");
                    }
                        book.setBrwCopies(book.getBrwCopies());
                        database.returnBook(borrowing,book,i);
                        System.out.println("Book returned\n");
                        break;
                    }else {
                        System.out.println("you didn't borrow this book!\n");
                    }
                }
            }else {
            System.out.println("you didn't borrow this book!\n");
        }
        user.menu(database, user);
    }
}
