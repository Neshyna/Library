package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyList;
import Utils.PersonValidator;

public class MainServiceImpl implements MainService{

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private User activUser;
    private Book bookId;

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
        Book newBook = new Book(name, author, year, bookId);
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
    public boolean borrowBook(int bookId) {
        if (activUser == null) {
            System.out.println("User not logged in.");
            return false;
        }
        if (activUser.isBlocked()) {
            System.out.println("User is blocked and cannot borrow books.");
            return false;
        }

        Book book = bookRepo.findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return false;
        }
        if (book.isBusy()) {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
            return false;
        }
        activUser.getUserBooks().add(book);
        System.out.println("Book with ID " + bookId + " successfully borrowed.");
        return true;
    }

    @Override
    public void returnBook(int bookId) {

        Book book = bookRepo.getBookById(bookId);
        if (book != null && book.isBusy()) {
            book.setBusy(false);
            bookRepo.updateBook(book);
            System.out.println("Book with ID " + bookId + " successfully returned.");
        } else {
            System.out.println("Book with ID " + bookId + " is not currently borrowed or does not exist.");
        }
    }

    @Override
    public void editBook(int bookId, String newName, String newAuthor, int newYear) {
        Book book = bookRepo.findBookById(bookId);
        if (activUser == null) {
            System.out.println("User not logged in.");
            return;
        }
        if (activUser.getRole() != Role.ADMIN) {
            System.out.println("Editing a book is only available to administrators.");
            return;
        }
        Book book1 = bookRepo.findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }
        book.setName(newName);
        book.setAuthor(newAuthor);
        book.setYear(newYear);
        bookRepo.updateBook(book);
        System.out.println("Book with ID " + bookId + " successfully updated.");
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


