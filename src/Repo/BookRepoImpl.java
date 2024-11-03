package Repo;

import Model.Book;
import Utils.MyArrayList;
import Utils.MyList;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepoImpl implements BookRepo {

    private final MyList<Book> books;

    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepoImpl(int i) {// метод, который используется для инициализации объекта BookRepoImpl с начальным значением количества книг.он нужен в MainServiceTest
        this.books = new MyArrayList<>();
        addDefaultBooks();
    }

    //Создадим DemoLibrary
    public void addDefaultBooks(){
        books.addAll(

        new Book("Neshyna1", "Masha1", 1234, 1),
        new Book("Neshyna2", "Masha2", 1234, 2),
        new Book("Neshyna3", "Masha3", 1234, 3),
        new Book("Leo Tolstoy", "War and Peace", 1869, 4),
        new Book("F. Scott Fitzgerald", "The Great Gatsby", 1925, 5),
        new Book("Jane Austen", "Pride and Prejudice", 1813, 6),
        new Book("George Orwell", "1984", 1949, 7),
        new Book("Mark Twain", "Adventures of Huckleberry Finn", 1884, 8),
        new Book("J.K. Rowling", "Harry Potter and the Sorcerer's Stone", 1997, 9),
        new Book("Harper Lee", "To Kill a Mockingbird", 1960, 10),
        new Book("Ernest Hemingway", "The Old Man and the Sea", 1952, 11),
        new Book("Charles Dickens", "Great Expectations", 1860, 12),
        new Book("Virginia Woolf", "Mrs. Dalloway", 1925, 13),
        new Book("Isaac Asimov", "Foundation", 1951, 13),
        new Book("Franz Kafka", "The Metamorphosis", 1915, 14),
        new Book("Margaret Atwood", "The Handmaid's Tale", 1985, 15),
        new Book("Ray Bradbury", "Fahrenheit 451", 1953, 16),
        new Book("Haruki Murakami", "Kafka on the Shore", 2002, 17)
        );
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override //добавление новой книги в коллекцию
    public void addBook(String author, String name, int year, int bookId) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
            int bookID = currentId.getAndIncrement();  // Присваиваем уникальный ID
        Book newBook = new Book(author, name, year, bookId);
        books.add(newBook);
    }

    @Override
    // Поиск книги по полному или частичному названию
    public MyList<Book> getByNamePart(String namePart) {
        MyList<Book> foundBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.getName().contains(namePart)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    @Override
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
        MyList<Book> allBooks = getAllBooks();
        // Проверяем, что список не null и не пустой
        if (allBooks == null || allBooks.isEmpty()) {
            return new MyArrayList<>();
        }
        allBooks.sort(Comparator.comparing(Book::getAuthor, Comparator.nullsLast(Comparator.naturalOrder())));
        return allBooks;
    }

    // Список всех книг, отсортированный по названию книги
    @Override
    public MyList<Book> getBooksSortedByName() {
        MyList<Book> allBooks = getAllBooks();
        // Проверяем, что список не null и не пустой
        if (allBooks == null || allBooks.size() == 0) {
            return new MyArrayList<>();
        }
        // Сортируем книги по имени
        allBooks.sort(Comparator.comparing(Book::getName, Comparator.nullsLast(Comparator.naturalOrder())));
        return allBooks;
    }

    @Override
    public void addNewBook(Book book) {
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == book.getBookId()) {
                books.set(i, book);
                System.out.println("Book with ID " + book.getBookId() + " successfully updated.");
                return;
            }
        }
        System.out.println("Book with ID " + book.getBookId() + " not found.");
    }

    @Override
    public Book getBookById(int bookId) {
        for(Book book : books){
            if (bookId == book.getBookId());
            return book;
        }
        return null;
    }
}

