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
    // MyList<Book> findByNamePart(String namePart);  // Поиск книги по полному или частичному названию

   //MyList<Book> findByAuthor(String authorPart);  // Поиск книги по полному или частичному имени автора

    //MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    //MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги

    // boolean borrowBook(String name, User user);  // Взятие книги (изменяет статус на занятый и устанавливает пользователя)

   //boolean returnBook(String name);// Возврат книги в библиотеку (освобождает книгу)


    Book findBookById(int bookId);



    void editBook(Book book);

}
