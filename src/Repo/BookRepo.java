package Repo;

import Model.Book;
import Utils.MyList;

import java.time.LocalDate;


public interface BookRepo {

    void addBook(String author, String name,int year);//добавление новой книги в коллекцию

    MyList<Book> getAllBooks();


    Book getByName(String name);


    MyList<Book> getByAuthor(String author); //получить книгу по полному имени автора

    MyList<Book> getAllBusyBooks();


    MyList<Book>getAllFreeBooks();





   //Дополнительные и опциональные (предлагаю):

    //MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    //MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги



    Book findBookById(int bookId);



}
