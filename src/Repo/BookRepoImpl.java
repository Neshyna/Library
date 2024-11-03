package Repo;

import Model.Book;
import Utils.MyArrayList;
import Utils.MyList;

public class BookRepoImpl implements BookRepo {
    private MyList<Book> books;

    public BookRepoImpl() {
        this.books = new MyArrayList<>();
    }

    @Override
    public void addBook(String author, String name, int year, int bookId) {
        Book newBook = new Book(name, author, year, bookId);
        books.add(newBook);
    }

    @Override
    public Book getByName(String name) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public MyList<Book> getByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> freeBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        MyList<Book> busyBooks = new MyArrayList<>();
        for (Book book : books) {
            if (!book.isAvailable()) {
                busyBooks.add(book);
            }
        }
        return busyBooks;
    }

    @Override
    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

