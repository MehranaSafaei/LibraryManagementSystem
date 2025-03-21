package org.example.mehrana.entity;

import org.example.mehrana.Database;
import org.example.mehrana.IOOperation;

abstract public class User {

    protected String name;
    protected String email;
    protected String phoneNumber;
    protected IOOperation[] operation;

    public User() {
    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    abstract public String toString();

    abstract public void menu(Database database, User user);
}
