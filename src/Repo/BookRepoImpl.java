package Repo;

import Model.Book;
import Utils.MyList;

public class BookRepoImpl implements BookRepo{

    private final MyList<Book> books;

    public BookRepoImpl(MyList<Book> books) {
        this.books = books;
    }

    @Override
    public void addBook(String author, String name, int year) {

    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getByName(String name) {
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
}
