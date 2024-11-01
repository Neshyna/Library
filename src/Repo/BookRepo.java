package Repo;

import Model.Book;
import Utils.MyList;


public interface BookRepo {


    void addBook(String authorPart, String namePart,int year, int bookId);//добавление новой книги в коллекцию

    MyList<Book> getAllBooks();  // получить список всех книг

    MyList<Book> getByNamePart(String namePart);  // Поиск книги по полному или частичному названию

    MyList<Book> getByAuthor(String authorPart);  // Поиск книги по полному или частичному имени автора

    MyList<Book> getAllBusyBooks(); // Получение всех книг, находящихся у читателей

    MyList<Book> getAllFreeBooks(); // Получить список свободных книг

    MyList<Book> getBooksSortedByAuthor();// Опционально 2  Список всех книг, отсортированный по автору

    MyList<Book> getBooksSortedByName(); //Опционально 2  Список всех книг, отсортированный по названию книги



    // Этот метод создает новую книгу и добавляет ее в репозиторий bookRepo.Реализовуется в MainServiceImpl
    void addNewBook(Book newBook);
    // Этот метод обновляет информацию о книге в репозитории bookRepo. Реализовуется в MainServiceImpl
    void updateBook(Book book);
    // Этот метод используется для поиска книги в репозитории по её уникальному идентификатору bookId. Реализовуется в MainServiceImpl
    Book getBookById(int bookId);

    public void addDefaultBooks();

}
