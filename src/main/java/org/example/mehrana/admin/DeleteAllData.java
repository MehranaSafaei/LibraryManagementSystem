package org.example.mehrana.admin;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;
import org.example.mehrana.entity.User;

import java.util.Scanner;

public class DeleteAllData implements IOOperation {
    @Override
    public void oper(Database database, User user) {

        System.out.println("\nAre you sure you want to delete all data?\n" +
                "1.yes\n2.no(Main menu");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            database.deleteAllData();
        } else if (choice == 2) {
            user.menu(database, user);
        }
    }
}
