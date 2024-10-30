package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyList;
import Utils.PersonValidator;

import java.util.concurrent.atomic.AtomicReference;

public class MainServiceImpl implements MainService{

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private User activUser;

    public MainServiceImpl(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addBook(String name, String author, int year) {
        if (activUser == null || activUser.getRole() != Role.ADMIN) {
            System.out.println("Adding a new book is only available to administrators");
            return;
        }
        Book newBook = new Book(name, author, year);
        bookRepo.addBook(newBook);
        System.out.println("Book added successfully");
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBookByName(String name) {
        return null;
    }

    @Override
    public MyList<Book> getByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

    @Override
    public boolean BorrowBook(int bookId) {
        return false;
    }

    public boolean borrowBook(int bookId) {
        Book book = bookRepo.addBook(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return false;
        }
        if (book.isBusy()) {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
            return false;
        }
        book.setBusy(true);
        bookRepo.addBook(book);
        System.out.println("Book with ID " + bookId + " successfully borrowed.");
        return true;
    }
    @Override
    public void returnBook(int bookId) {
        Book book = bookRepo.addBook(bookId);
        if (book != null && book.isBusy()) {
            book.setBusy(false);
            bookRepo.addBook(book);
            System.out.println("Book with ID " + bookId + " successfully returned.");
        } else {
            System.out.println("Book with ID " + bookId + " is not currently borrowed or does not exist.");
        }
    }


    @Override
    public void editBook(int bookId) {
        Book book = bookRepo.findBookById(bookId);
        if (book != null) {
            book.setName("New Name");
            book.setAuthor("New Author");
            book.setYear(2023);
            bookRepo.updateBook(book);
            System.out.println("Book with ID " + bookId + " updated successfully.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }


    //author neshyna
    @Override
    public User registerUser(String email, String password) {

        if(!PersonValidator.isEmailValid(email)){
            System.out.println("Please check email");
            return  null;
        }

        if(!PersonValidator.isPasswordValid(password)){
            System.out.println("Please check password");
            return null;
        }

        if (userRepo.isMailExist(email)){
            System.out.println("Email already exists");
            return null;
        }

        User user = userRepo.addUser(email,password);

        return user;

    }


    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepo.getUserEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid email or password");
            return false;
        }
        activUser = user;
        System.out.println("User logged in successfully");
        return true;
    }

    @Override
    public void logout() {
        activUser = null;
        System.out.println("User logged out successfully");
    }
    public User getActiveUser(){
        return activUser;
    }
}

