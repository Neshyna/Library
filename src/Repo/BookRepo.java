package Repo;

import Model.Book;
import Utils.MyList;

public interface BookRepo {

    Book addBook(int year);

    MyList<Book> getAllBooks();

    Book getByName(String name);

    MyList<Book> getByAuthor(String author);

    MyList<Book> getAllBusyBooks();

    MyList<Book>getAllFreeBooks();


    void addBook(Book newBook);

    Book findBookById(int bookId);

    void updateBook(Book book);
}
