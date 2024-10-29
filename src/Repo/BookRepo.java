package Repo;

import Model.Book;
import Model.User;
import Utils.MyList;

public interface BookRepo {

    void addBook(String author, String name,int year);

    Book getByName(String name);  //получить книгу по названию

    MyList<Book> getAllBooks();  // получить список всех книг

    MyList<Book> findByName(String namePart);  // Поиск книги по полному или частичному названию

    MyList<Book> findByAuthor(String authorPart);  // Поиск книги по полному или частичному имени автора

    MyList<Book> getAllBusyBooks(); // Получение всех книг, находящихся у читателей

    MyList<Book>getAllFreeBooks(); // Получить список свободных книг

    MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги

    boolean borrowBook(String name, User user);  // Взятие книги (изменяет статус на занятый и устанавливает пользователя)

    boolean returnBook(String name);// Возврат книги в библиотеку (освобождает книгу)

    void setBorrowDate(String name); //Опционально 2.  Дата, когда была книга взята на прокат

    long daysHeld(String name); // Опционально 2. Получить информацию сколько дней книга находится у пользователя

    void deleteBook(Book book);     // Удалить книгу


}
