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















}
