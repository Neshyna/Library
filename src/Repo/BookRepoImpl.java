package Repo;


import Model.Book;
import Utils.MyArrayList;
import Utils.MyList;


public class BookRepoImpl implements BookRepo{


    private final int books;

    public BookRepoImpl(int books)  // метод, который используется для инициализации объекта BookRepoImpl с начальным значением количества книг.
    { this.books = books;           // он нужен в MainServiceTest
    }


    @Override //добавление новой книги в коллекцию
    public void addBook(String author, String name, int year, int bookId) {
        bookId = books.size() + 1; // Присваиваем уникальный ID
        Book newBook = new Book(author, name, year, bookId);
        books.add(newBook);


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
    public MyList<Book> getAllBooks() {
        return null;
    }

    

    @Override
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

    
    */


    @Override
    public Book findBookById(int bookId) {
        return null;
    }

}

