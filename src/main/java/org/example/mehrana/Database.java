package org.example.mehrana;

import org.example.mehrana.entity.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Database {

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<String> bookNames = new ArrayList<String>();
    ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();


    private File usersfile = new File("C:\\Library Management System\\Data\\Users");
    private File booksfile = new File("C:\\Library Management System\\Data\\Books");
    private File ordersFile = new File("C:\\Library Management System\\Data\\Orders");
    private File borrowingsFile = new File("C:\\Library Management System\\Data\\borrowings");
    private File folder = new File("C:\\Library Management System\\Data");

    public Database() throws IOException {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        createFileIfNotExists(usersfile);
        createFileIfNotExists(booksfile);
        createFileIfNotExists(ordersFile);
        createFileIfNotExists(borrowingsFile);

        getUsers();
        getBooks();
        getOrders();
        getBorrowings();
    }
    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error creating file: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    public void addUser(User user) {
        users.add(user);
        usernames.add(user.getName());
        saveUsers();
    }

    public int login(String phoneNumber, String email) {
        int found = -1;
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getEmail().equals(email)) {
                found = users.indexOf(user);
                break;
            }
        }
        return found;
    }

    public User getUser(int n) {
        return users.get(n);
    }

    public void AddBook(Book book) {
        books.add(book);
        bookNames.add(book.getName());
        saveBooks();
    }

    private void getUsers() {
        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(usersfile));
            String str;
            while ((str = br.readLine()) != null) {
                text += str + "\n";
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.matches("") || !text.isEmpty()) {
            String[] str = text.split("<NewUser/>");
            for (String s : str) {
                String[] str2 = s.split("<N/>");
                System.out.println(s);
                System.out.println("Split Data Length: " + str2.length);
                if (str.length <= 4) {
                    System.err.println("Data format error! There is not enough data for this user!");
                    continue;
                }
                if (str2[3].matches("Admin")) {
                    User user = new Admin(str2[0], str2[1], str2[2]);
                    addUser(user);
                } else {
                    User user = new NormalUser(str2[0], str2[1], str2[2]);
                    addUser(user);
                }
            }
        }
    }

    private void saveUsers() {
        String text = "";
        for (User user : users) {
            text += user.toString() + "<NewUser/>\n";
        }
        try {
            PrintWriter printWriter = new PrintWriter(usersfile);
            printWriter.print(text);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void saveBooks() {
        String text = "";
        for (Book book : books) {
            text += book.toString() + "<NewBook/>\n";
        }
        try {
            PrintWriter printWriter = new PrintWriter(booksfile);
            printWriter.print(text);
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBooks() {
        String text = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(booksfile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text += str + "\n";
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        if (!text.matches("") || !text.isEmpty()) {
            String[] str = text.split("<NewBook/>");
            for (String s : str) {
                Book book = parseBook(s);
                books.add(book);
                bookNames.add(book.getName());
            }
        }
    }

    public Book parseBook(String s) {
        String[] a = s.split("<N/>");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setAddress(a[3]);
        book.setQty(Integer.parseInt(a[4]));
        book.setPrice(Double.parseDouble(a[5]));
        book.setBrwCopies(Integer.parseInt(a[6]));
        return book;
    }

    public ArrayList<Book> getAllBook() {
        return books;
    }

    public int getBook(String bookName) {
        int found = -1;
        for (Book book : books) {
            if (book.getName().matches(bookName)) {
                found = books.indexOf(book);
                break;
            }
        }
        return found;
    }
    public Book getBook(int i ){
        return books.get(i);
    }
//    public int getBook(String bookName) {
//        int found = -1;
//        for (int i = 0; i < books.size(); i++) {
//            Book book = books.get(i);
//            if (book.getName().equals(bookName)) {
//                found = i;
//                break;
//            }
//        }
//        return found;
//    }


    public Book searchBook(int i) {
        return books.get(i);
    }

    public void deleteBook(int bookId) {
        books.remove(bookId);
        bookNames.remove(bookId);
    }

    public void deleteAllData() {
        if (!usersfile.exists()) {
            try {
                usersfile.delete();
            } catch (Exception e) {
            }
        }
        if (!booksfile.exists()) {
            try {
                booksfile.delete();
            } catch (Exception e) {
            }
        }
        if (!ordersFile.exists()) {
            try {
                ordersFile.delete();
            } catch (Exception e) {
            }
            if (!borrowingsFile.exists()) {
                try {
                    borrowingsFile.delete();
                } catch (Exception e) {
                }
            }
        }
    }

    public void AddOrder(Order order, Book book, int bookIndex) {
        orders.add(order);
        books.set(bookIndex, book);
        saveOrders();
        saveBooks();
    }

    private void saveOrders() {
        String text = "";
        for (Order order : orders) {
            text += order.toString() + "<NewOrder/>\n";
        }
        try {
            PrintWriter printWriter = new PrintWriter(ordersFile);
            printWriter.println(text);
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
    }

    private void getOrders() {
        String text = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ordersFile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text += str + "\n";
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        if (!text.matches("") || !text.isEmpty()) {
            String[] str = text.split("<NewOrder/>");
            for (String s : str) {
                Order order = parseOrder(s);
                orders.add(order);
            }
        }
    }

    public boolean userExists(String name){
        boolean f = false;
        for (User user: users){
            if (user.getName().toLowerCase().matches(name)){
                f = true;
                break;
            }
        }
        return f;
    }

    private User getUserByName(String name) {
        User user1 = new NormalUser("");
        for (User user : users) {
            if (user.getName().equals(name)) {
                user1 = user;
                break;
            }
        }
        return user1;
    }

    private Order parseOrder(String s) {
        String[] a = s.split("<N/>");
        Order order = new Order(books.get(getBook(a[0])), getUserByName(a[1]), Double.parseDouble(a[2]), Integer.parseInt(a[3]));
        return order;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    private void saveBorrowing(){
        String text = "";
        for (Borrowing book : borrowings) {
            text += book.toString() + "<NewBorrowing/>\n";
        }
        try {
            PrintWriter printWriter = new PrintWriter(borrowingsFile);
            printWriter.println(text);
            printWriter.close();
        }catch (Exception e) {
            System.err.println(e.toString());
        }

    }

    private void getBorrowings() {
        String text = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(borrowingsFile));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text += str + "\n";
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text.matches("") || !text.isEmpty()) {
            String[] str = text.split("<NewBorrowing/>");
            for (String s : str) {
                Borrowing borrowing = parseBorrowing(s);
                borrowings.add(borrowing);
            }
        }
    }
    public ArrayList<Borrowing> getBrw(){
        if (borrowings.isEmpty()){
            getBorrowings();
        }
        return borrowings;
    }

    private Borrowing parseBorrowing(String s) {
        String[] a = s.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(a[0], formatter);
        LocalDate endDate = LocalDate.parse(a[1], formatter);
        Book book = getBook(getBook(a[3]));
        User user = getUserByName(a[4]);
        Borrowing borrowing = new Borrowing(startDate,endDate,book,user);
        return borrowing;

    }

    public void borrowBook(Borrowing borrowing, Book book, int bookIndex) {
        borrowings.add(borrowing);
        books.set(bookIndex,book);
        saveBorrowing();
        saveBooks();
    }
    public ArrayList<Borrowing> getAllBorrowing(){
        return borrowings;
    }

    public void returnBook(Borrowing borrowing, Book book, int i) {
        borrowings.remove(borrowing);
        books.set(i,book);
        saveBorrowing();
        saveBooks();
    }
}

