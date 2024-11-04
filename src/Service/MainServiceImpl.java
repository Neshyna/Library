package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyArrayList;
import Utils.MyList;
import Utils.PersonValidator;

public class MainServiceImpl implements MainService {

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private User activUser;

    public MainServiceImpl(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addBook(String name, String author, int year, int bookId) {
        if (activUser == null || activUser.getRole() != Role.ADMIN) {
            System.out.println("Adding a new book is only available to administrators");
            return;
        }
        bookRepo.addNewBook(new Book(author, name, year, bookId));
        System.out.println("The book: " + name + " by " + author + " is added to the library");
    }

    @Override
    public MyList<Book> getAllBooks() {
        MyList<Book> books = bookRepo.getAllBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("No books found.");
            return new MyArrayList<>(); // Возвращаем пустой список
        } else {
            return books;
        }
    }

    @Override
    public MyList<Book> getBookByName(String name) {
        MyList<Book> books = bookRepo.getBookByName(name);
        if (books == null || books.isEmpty()) {
            System.out.println("No books found with the name: " + name);
            return new MyArrayList<>(); // Возвращаем пустой список
        } else {
            System.out.println("Books found successfully with the name: " + name);
            return books;
        }
    }

    @Override
    public MyList<Book> getByAuthor(String author) {
        MyList<Book> books = bookRepo.getByAuthor(author);
        if (books == null || books.isEmpty()) {
            System.out.println("No books found by author: " + author);
            return new MyArrayList<>(); // Возвращаем пустой список
        } else {
            System.out.println("Books retrieved successfully by author: " + author);
            return books;
        }
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        MyList<Book> busyBooks = bookRepo.getAllBooks();
        MyList<Book> result = new MyArrayList<>();
        for (Book book : busyBooks) {
            if (busyBooks.isEmpty()) {
                System.out.println("No available books found.");
            }
            if (book.isBusy()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> allBooks = bookRepo.getAllBooks();
        MyList<Book> freeBooks = new MyArrayList<>();
        for (Book book : allBooks) {
            if (!book.isBusy()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    @Override
    public boolean borrowBook(int bookId) {
        if (getActiveUser() == null) {
            System.out.println("User not logged in.");
            return false;
        }
        if (getActiveUser().isBlocked()) {
            System.out.println("User is blocked and cannot borrow books.");
            return false;
        }

        Book book = bookRepo.getBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return false;
        }
        if (book.isBusy()) {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
            return false;
        }
        activUser.getUserBooks().add(book);
        book.setBusy(true);
        System.out.println("Book with ID " + bookId + " successfully borrowed.");
        return true;
    }

    private User getActiveUser() {
        return null;
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
        Book book = bookRepo.getBookById(bookId);
        if (getActiveUser() == null) {
            System.out.println("User not logged in.");
            return;
        }
        if (activUser.getRole() != Role.ADMIN) {
            System.out.println("Editing a book is only available to administrators.");
            return;
        }
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

    @Override
    public User registerUser(String email, String password) {
        if (!PersonValidator.isEmailValid(email)) {
            System.out.println("Please check email");
            return null;
        }
        if (!PersonValidator.isPasswordValid(password)) {
            System.out.println("Please check password");
            return null;
        }
        if (userRepo.isMailExist(email)) {
            System.out.println("Email already exists");
            return null;
        }
        return userRepo.addUser(email, password);
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = userRepo.getUserEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid email or password");
            return false;
        }
        activUser = user; // Исправлено
        System.out.println("User successfully logged in ");
        return true;
    }

    @Override
    public void logout() {
        activUser = null;
        System.out.println("User logged out successfully");
    }

    @Override
    public Book getBookById(int bookId) {
        Book bookById = bookRepo.getBookById(bookId);
        if (bookById == null) {
            System.out.println("Book with ID " + bookId + " not found.");
        }
        return bookById;
    }

    @Override
    public MyList<Book> getBooksSortedByName() {
        MyList<Book> booksSortedByName = bookRepo.getBooksSortedByName();
        return booksSortedByName;
    }

    @Override
    public MyList<Book> getBooksSortedByAuthor() {
        MyList<Book> booksSortedByAuthor = bookRepo.getBooksSortedByAuthor();
        return booksSortedByAuthor;
    }

    @Override
    public User findUserById(int userId) {
        User userById = userRepo.findUserById(userId);
        if (userById == null) {
            System.out.println("Book with ID " + userById + " not found.");
        }
        return userById;
    }

    @Override
    public MyList<User> getAllUsers() {
        MyList<User> allUsers = userRepo.getAllUsers();
        if (allUsers == null || allUsers.isEmpty()) {
            System.out.println("No users found.");
            return new MyArrayList<>(); // Возвращаем пустой список
        } else {
            System.out.println("Show all users: ");
            return allUsers;
        }
    }


    @Override
    public MyList<Book> findBooksByNamePart(String namePart) {
        return bookRepo.getBookByName(namePart);
    }

    @Override
    public MyList<Book> findBooksByAuthor(String authorPart) {
        return bookRepo.getByAuthor(authorPart);
    }


    @Override
    public void getUserBooksByUserId(int userId) {
        User user = userRepo.getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        MyList<Book> userBooks = bookRepo.getUserBooksByUserId(user);
        if (userBooks.isEmpty()) {
            System.out.println("No books found for this user.");
        } else {
            System.out.println("Books owned by user " + user.getId() + ":");
            for (Book book : userBooks) {
                System.out.println(book);
            }
        }


    }
}