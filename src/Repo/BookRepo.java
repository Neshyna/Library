package Repo;

import Model.Book;
import Utils.MyList;

import java.time.LocalDate;


public interface BookRepo {

    void addBook(String authorPart, String namePart,int year, int bookId);//добавление новой книги в коллекцию

    MyList<Book> getAllBooks();  // получить список всех книг


    MyList<Book> getByNamePart(String namePart);  // Поиск книги по полному или частичному названию


    MyList<Book> getByAuthor(String authorPart);  // Поиск книги по полному или частичному имени автора

    MyList<Book> getAllBusyBooks(); // Получение всех книг, находящихся у читателей

    MyList<Book>getAllFreeBooks(); // Получить список свободных книг



    MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги



    Book findBookById(int bookId);


    //добавление новой книги в коллекцию
    void addBook(String author, String name, int year);
}
