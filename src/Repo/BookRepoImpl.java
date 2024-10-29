package Repo;

import java.util.Comparator;
import Model.Book;
import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

import java.time.LocalDate;
import java.util.Comparator;

public class BookRepoImpl implements BookRepo {

    private final MyList<Book> books;

    public BookRepoImpl(MyList<Book> books) {
        this.books = books;
    }

    @Override
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
    // Найти книгу по названию
    public MyList<Book> findByName(String namePart) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(namePart.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    // Получить книгу по названию
    public Book getByName(String namePart) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getName().toLowerCase().contains(namePart.toLowerCase())){
                result.add(book);
            }
        }
        return null;
    }

    @Override
    // Получить книгу по автору
    public MyList<Book> findByAuthor(String authorPart) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(authorPart.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    // Взять книгу
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

    @Override
    // Вернуть книгу в библиотеку
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

    @Override
    // Получить список занятых книг
    public MyList<Book> getAllBusyBooks() {
        MyList<Book> busyBooks = new MyArrayList<>();
        for (Book book : books) {
            if (book.isBusy()) {
                busyBooks.add(book);
            }
        }
        return busyBooks;
    }

    //Опциональный метод для получения списка книг, отсортированных по автору
    @Override
    public MyList<Book> getBooksSortedByAuthor() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        sortedBooks.sort(Comparator.comparing(Book::getAuthor)); // Сортируем по автору
        return sortedBooks; // Возвращаем отсортированный список
    }


    //Опциональный метод для получения списка книг, отсортированных по названию
    @Override
    public MyList<Book> getBooksSortedByName() {
        MyList<Book> sortedBooks = new MyArrayList<>(); // Создаем новый список для сортированных книг
        sortedBooks.addAll(books.toArray()); // Копируем книги из исходного списка в новый
        sortedBooks.sort(Comparator.comparing(Book::getName)); // Сортируем по имени
        return sortedBooks; // Возвращаем отсортированный список
    }

    // Опциональный метод. Установить дату взятия книги
    @Override
    public void setBorrowDate(String name) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                book.setBorrowDate(LocalDate.now()); // Установить текущую дату
                return;
            }
        }
    }

    //Дополнительный метод. Установить дату взятия книги по имени
    //После добавления метода вы можете вызывать его из других частей вашего кода,
    // передавая нужное имя книги и желаемую дату. Например:
    //bookRepo.setBorrowDateByName("Название книги", LocalDate.of(2024, 10, 29));
    //Это обновит дату взятия для книги с указанным названием на 29 октября 2024 года.


    public void setBorrowDateByName(String name, LocalDate borrowDate) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                book.setBorrowDate(borrowDate); // Установить новую дату взятия
                return;
            }
        }
    }

    // Опциональный метод. Получить количество дней, которые книга у пользователя
    @Override
    public long daysHeld(String name) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name) && book.isBusy()) {
                return book.daysHeld();
            }
        }
        return -1; // Если книга не найдена или не занята
    }



    @Override
    // Удалить книгу
    public void deleteBook(Book book) {
        books.remove(book);
    }
}