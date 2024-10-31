package Repo;

import Model.Book;
import Utils.MyArrayList;
import Utils.MyList;

import java.util.Comparator;

public class BookRepoImpl implements BookRepo {

    private final MyList<Book> books;

    public BookRepoImpl(MyList<Book> books) {
        this.books = books;
    }

    @Override
    public void addBook(String author, String name, int year) {
        int bookId = books.size() + 1; // Присваиваем уникальный ID
        Book newBook = new Book(author, name, year, bookId);
        books.add(newBook);
    }

    @Override
    public void addBook(String authorPart, String namePart, int year, int bookId) {

    }

    @Override
    // Получить список всех книг
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    // Поиск книги по полному или частичному названию
    public MyList<Book> getByNamePart(String namePart) {
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(namePart.toLowerCase())) {
                return (MyList<Book>) book; // Возвращаем первую найденную книгу
            }
        }
        return null; // Если книга не найдена, возвращаем null
    }

    @Override
    //Поиск книги по полному или частичному имени автора
    public MyList<Book> getByAuthor(String authorPart) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(authorPart.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    // Получение всех книг, находящихся у читателей
    public MyList<Book> getAllBusyBooks() {
        MyList<Book> busyBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.isBusy()) {
                busyBooks.add(book);
            }
        }
        return busyBooks;
    }


    @Override
    // Получить список свободных книг
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> freeBooks = new MyArrayList<>();
        for (Book book : books) {
            if (!book.isBusy()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    //Список всех книг, отсортированный по автору
    @Override
    public MyList<Book> getBooksSortedByAuthor() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        ((MyArrayList<Book>) sortedBooks).sort(Comparator.comparing(Book::getAuthor)); // Сортируем по автору
        return sortedBooks; // Возвращаем отсортированный список
    }

    // Список всех книг, отсортированный по названию книги
    @Override
    public MyList<Book> getBooksSortedByName() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        ((MyArrayList<Book>) sortedBooks).sort(Comparator.comparing(Book::getName)); // Сортируем по имени
        return sortedBooks; // Возвращаем отсортированный список
    }

    @Override
    public Book findBookById(int bookId) {
        return null;
    }

}

