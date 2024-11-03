package Service;

import Model.Book;
import Model.User;
import Utils.MyList;

public interface MainService {

    void addBook(String name, String author, int year, int bookId);

    MyList<Book> getAllBooks();

    MyList<Book> getBookByName(String name);

    MyList<Book> getByAuthor(String author);

    MyList<Book> getAllBusyBooks();

    MyList<Book> getAllFreeBooks();

    boolean borrowBook(int bookId);

    public void returnBook(int bookId);

    void editBook(int bookId, String newName, String newAuthor, int newYear);

    User registerUser(String email, String password);

    boolean loginUser(String email, String password);

    void logout();

    Book getBookById(int bookId);

    MyList<Book> getBooksSortedByName();

    MyList<Book> getBooksSortedByAuthor();

    User findUserById(int userId);

    MyList<User> getAllUsers();
}