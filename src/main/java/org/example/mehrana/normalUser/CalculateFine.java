package org.example.mehrana.normalUser;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.Borrowing;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();

        boolean done = true;

        for (Borrowing borrowing : database.getAllBorrowing()) {
//            System.out.println(borrowing.getDaysLeft());
            if (borrowing.getBook().getName().matches(bookName) && borrowing.getUser().getName().matches(user.getName())) {
                if (borrowing.getDaysLeft() < 0) {
                    System.out.println("you are late!!" + "you have to pay" +
                            borrowing.getDaysLeft() * 50 + "as fine");
                } else {
                    System.out.println("you don`t have to pay fine\n");
                }
                done = false;
                break;
            }
        }
        if (done){
            System.out.println("you didn't borrow");
            user.menu(database, user);
        }
    }
}
