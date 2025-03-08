package org.example.mehrana.entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Borrowing {
    LocalDate startDate;
    LocalDate endDate;
    int daysLeft;
    Book book;
    User user;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Borrowing(Book book, User user) {
        startDate = LocalDate.now();
        endDate = startDate.plusDays(14);
        Period period = Period.between(startDate, endDate);
        daysLeft = period.getDays();
        this.book = book;
        this.user = user;
    }

    public Borrowing(LocalDate startDate, LocalDate endDate,Book book, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysLeft = Period.between(endDate, LocalDate.now()).getDays();
        this.book = book;
        this.user = user;
    }


    public String getStartDate() {
        return formatter.format(startDate);
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return formatter.format(endDate);
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDaysLeft() {
        return Period.between(endDate, LocalDate.now()).getDays();
    }
    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Borrowing time: "+ startDate + "\nExpiry date: "+ endDate +"\nDays left: "+ daysLeft;
    }
    public String toString2() {
        return  getStartDate() +"<N/>"+ getEndDate() +"<N/>"+getDaysLeft()+"<N/>"+book.getName()+"<N/>"+user.getName()+"<N/>";
    }
}
