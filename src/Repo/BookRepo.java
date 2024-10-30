package Repo;

import Model.Book;
import Model.User;
import Utils.MyList;

import java.time.LocalDate;

public interface BookRepo {

    void addBook(String author, String name,int year);//добавление новой книги в коллекцию

    MyList<Book> getAllBooks();  // получить список всех книг

    Book getByName(String name);  //получить книгу по полному названию

    MyList<Book> getByAuthor(String author); //получить книгу по полному имени автора

    MyList<Book> getAllBusyBooks(); // Получение всех книг, находящихся у читателей

    MyList<Book>getAllFreeBooks(); // Получить список свободных книг



//Дополнительные и опциональные (предлагаю):
    // MyList<Book> findByNamePart(String namePart);  // Поиск книги по полному или частичному названию

   //MyList<Book> findByAuthor(String authorPart);  // Поиск книги по полному или частичному имени автора

    //MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    //MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги

    // boolean borrowBook(String name, User user);  // Взятие книги (изменяет статус на занятый и устанавливает пользователя)

   //boolean returnBook(String name);// Возврат книги в библиотеку (освобождает книгу)



}
