package Repo;

import java.util.Comparator;
import java.time.LocalDate;
import Model.Book;
import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

import java.time.LocalDate;

public class BookRepoImpl implements BookRepo {

    private final MyList<Book> books;

    public BookRepoImpl(MyList<Book> books) {
        this.books = books;
    }

    @Override
    //добавление новой книги в коллекцию
    public void addBook(String author, String name, int year) {
        int bookId = books.size() + 1; // Присваиваем уникальный ID
        Book newBook = new Book(author, name, year, bookId);
        books.add(newBook);
    }

    @Override
    // Получить список всех книг
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    //получить книгу по полному названию
    public Book getByName(String name) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }
        return (Book) result;
    }

    @Override
    //получить книгу по полному имени автора
    public MyList<Book> getByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
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


    //Дополнительные и опциональные (предлагаю):

   /* @Override
    // Поиск книги по полному или частичному названию
    public MyList<Book> findByNamePart(String namePart) {
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(namePart.toLowerCase())) {
                return (MyList<Book>) book; // Возвращаем первую найденную книгу
            }
        }
        return null; // Если книга не найдена, возвращаем null
    }
*/


     /*@Override
    //Поиск книги по полному или частичному имени автора
    public MyList<Book> findByAuthor(String authorPart) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(authorPart.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
    */

    //Опционально 2
    /*
    //Список всех книг, отсортированный по автору
    @Override
    public MyList<Book> getBooksSortedByAuthor() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        sortedBooks.sort(Comparator.comparing(Book::getAuthor)); // Сортируем по автору
        return sortedBooks; // Возвращаем отсортированный список
    }*/

    // Список всех книг, отсортированный по названию книги
    /* @Override
    public MyList<Book> getBooksSortedByName() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        sortedBooks.sort(Comparator.comparing(Book::getName)); // Сортируем по имени
        return sortedBooks; // Возвращаем отсортированный список
    }
    */

/*
     @Override
     //Взятие книги (изменяет статус на занятый и устанавливает пользователя)
     public boolean borrowBook(String name, User user) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                 if (book.isBusy()) {
                   System.out.println("Book is already borrowed by another user.");
                   return false;
                 } else {
                     book.setBusy(true);
                    book.setHolder(user);
                    return true;
               }
           }
        }
        System.out.println("Book not found.");
         return false;
     }
*/


    /* @Override
     // Возврат книги в библиотеку (освобождает книгу)
     public boolean returnBook(String name) {
         for (Book book : books) {
         if (book.getName().equalsIgnoreCase(name) && book.isBusy()) {
               book.setBusy(false);
                 book.setHolder(null);
                return true;
            }
        }
        System.out.println("Book is either not found or not currently borrowed.");
         return false;
    }
    */



}