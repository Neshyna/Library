package Repo;

import Model.Book;
import Utils.MyList;

public interface BookRepo {
    void addBook(String author, String name, int year, int bookId);
    Book getByName(String name);
    MyList<Book> getByAuthor(String author);
    MyList<Book> getAllBooks();
    MyList<Book> getAllFreeBooks();
    MyList<Book> getAllBusyBooks();
    Book findBookById(int bookId);
}
