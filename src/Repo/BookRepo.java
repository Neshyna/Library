package Repo;

import Model.Book;
import Model.User;
import Utils.MyList;

public interface BookRepo {

    MyList<Book> getAllBooks();

    void addBook(String authorPart, String namePart, int year, int bookId);//добавление новой книги в коллекцию

    MyList<Book> getBookByName(String namePart);  // Поиск книги по полному или частичному названию

    MyList<Book> getByAuthor(String authorPart);

    MyList<Book> getAllBusyBooks(); // Получение всех книг, находящихся у читателей

    MyList<Book> getAllFreeBooks(); // Получить список свободных книг

    MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги

    // Список всех книг, отсортированный по ID
    MyList<Book> getBooksSortedById();

    // Этот метод создает новую книгу и добавляет ее в репозиторий bookRepo. Реализуется в MainServiceImpl
    void addNewBook(Book newBook);

    void updateBook(Book book);

    public static void addDefaultBooks(){};

    Book getBookById(int bookId);

}

