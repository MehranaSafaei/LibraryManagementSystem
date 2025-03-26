package org.example.mehrana.normalUser;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class Search implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("\nEnter book name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        int i = database.getBook(name);
        if (i>-1){
            System.out.println("\n"+database.searchBook(i).toString()+"\n");
        }else {
            System.out.println("Book not found");
        }
    }
}
