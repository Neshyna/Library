package Repo;

import Model.Book;
import Utils.MyList;


public interface BookRepo {

    void  addBook (String author, String name, int year, Book bookId);//добавление новой книги в коллекцию

    MyList<Book> getAllBooks();


    Book getByName(String name);


    MyList<Book> getByAuthor(String author); //получить книгу по полному имени автора

    MyList<Book> getAllBusyBooks();


    MyList<Book>getAllFreeBooks();





   //Дополнительные и опциональные (предлагаю):

    //MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    //MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги



    Book findBookById(int bookId);

    // Этот метод создает новую книгу и добавляет ее в репозиторий bookRepo.Реализовуется в MainServiceImpl
    void addBook(Book newBook);
 // Этот метод обновляет информацию о книге в репозитории bookRepo. Реализовуется в MainServiceImpl
    void updateBook(Book book);
  // Этот метод используется для поиска книги в репозитории по её уникальному идентификатору bookId. Реализовуется в MainServiceImpl
    Book getBookById(int bookId);
}
