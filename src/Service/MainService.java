package Service;

import Model.Book;
import Model.User;
import Utils.MyList;

public interface MainService {
    boolean loginUser(String email, String password);
    User registerUser(String email, String password);
    MyList<Book> getAllBooks();
    void addBook(String name, String author, int year, int bookId);
    void borrowBook(int bookId);
    void returnBook(int bookId);
    MyList<User> getAllUsers();
}

