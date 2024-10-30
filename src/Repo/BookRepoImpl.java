package Repo;

import Model.Book;
import Utils.MyArrayList;
import Utils.MyList;

public class BookRepoImpl implements BookRepo{

    private final MyList<Book> books;

    public BookRepoImpl(MyList<Book> books) {
        this.books = books;
    }

    public BookRepoImpl(int initialCapacity) {
        this.books = new MyArrayList<>();
    }

    @Override
    public Book addBook(int year) {

        return null;
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

    @Override
    public void addBook(Book newBook) {

    }

    @Override
    public Book findBookById(int bookId) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }
}
