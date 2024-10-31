package Repo;

import Model.Book;
import Utils.MyList;

public interface BookRepo {

    void addBook(String author, String name,int year);

    MyList<Book> getAllBooks();

    Book getByName(String name);

    MyList<Book> getByAuthor(String author);

    MyList<Book> getAllBusyBooks();

    MyList<Book>getAllFreeBooks();













<<<<<<< HEAD
    void deleteBook(Book book);     // Удалить книгу
=======


>>>>>>> e619e8fba515a7343edf1f64065a721f81af4c4c
}
