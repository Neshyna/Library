package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyList;

public class MainServiceImpl {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private User currentUser;

    public MainServiceImpl(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        initializeBooks(); // Инициализация списка книг
    }

    // Метод для инициализации списка книг
    private void initializeBooks() {
        bookRepo.addBook("Война и мир", "Лев Толстой", 1869, 1);
        bookRepo.addBook("Преступление и наказание", "Фёдор Достоевский", 1866, 2);
        bookRepo.addBook("Мастер и Маргарита", "Михаил Булгаков", 1967, 3);
        bookRepo.addBook("Анна Каренина", "Лев Толстой", 1877, 4);
        bookRepo.addBook("1984", "Джордж Оруэлл", 1949, 5);
        bookRepo.addBook("Убить пересмешника", "Харпер Ли", 1960, 6);
        bookRepo.addBook("Гарри Поттер и философский камень", "Дж.К. Роулинг", 1997, 7);
        bookRepo.addBook("Алхимик", "Пауло Коэльо", 1988, 8);
        bookRepo.addBook("Тихий Дон", "Шолохов", 1928, 9);
        bookRepo.addBook("Три товарища", "Эрих Мария Ремарк", 1936, 10);
        bookRepo.addBook("Собачье сердце", "Михаил Булгаков", 1925, 11);
        bookRepo.addBook("Золотой телёнок", "Илья Ильф и Евгений Петров", 1931, 12);
        bookRepo.addBook("Мартин Иден", "Джек Лондон", 1909, 13);
        bookRepo.addBook("Похождения Тома Сойера", "Марк Твен", 1876, 14);
        bookRepo.addBook("451 градус по Фаренгейту", "Рэй Брэдбери", 1953, 15);
        bookRepo.addBook("Слепой музыкант", "Антон Чехов", 1904, 16);
        bookRepo.addBook("Дети подземелья", "Григорий Горин", 1967, 17);
        bookRepo.addBook("Невский проспект", "Николай Гоголь", 1835, 18);
        bookRepo.addBook("Старик и море", "Эрнест Хемингуэй", 1952, 19);
        bookRepo.addBook("Шинель", "Николай Гоголь", 1842, 20);
        bookRepo.addBook("Затмение", "Людмила Улицкая", 1999, 21);
        bookRepo.addBook("Лолита", "Владимир Набоков", 1955, 22);
        bookRepo.addBook("Левша", "Николай Лесков", 1881, 23);
        bookRepo.addBook("Человек в футляре", "Антон Чехов", 1898, 24);
        bookRepo.addBook("Тень горы", "Гарри Поттер и философский камень", 1997, 25);
        bookRepo.addBook("Братья Карамазовы", "Фёдор Достоевский", 1880, 26);
        bookRepo.addBook("Служебный роман", "Эмиль Брагинский", 1977, 27);
        bookRepo.addBook("Идиот", "Фёдор Достоевский", 1869, 28);
        bookRepo.addBook("Маленький принц", "Антуан де Сент-Экзюпери", 1943, 29);
        bookRepo.addBook("Гордость и предубеждение", "Джейн Остин", 1813, 30);
        bookRepo.addBook("Сияние", "Стивен Кинг", 1977, 31);
        bookRepo.addBook("Декамерон", "Джованни Боккаччо", 1353, 32);
        bookRepo.addBook("Собрание сочинений", "Сергей Есенин", 1960, 33);
        bookRepo.addBook("Судьба человека", "Михаил Шолохов", 1956, 34);
        bookRepo.addBook("Бесы", "Фёдор Достоевский", 1872, 35);
        bookRepo.addBook("Краткая история времени", "Стивен Хокинг", 1988, 36);
        bookRepo.addBook("На дне", "Максим Горький", 1902, 37);
        bookRepo.addBook("Достоевский и Шекспир", "Лев Шестов", 1935, 38);
        bookRepo.addBook("Давид Копперфилд", "Чарльз Диккенс", 1850, 39);
        bookRepo.addBook("Бесплодные земли", "Т. С. Элиот", 1922, 40);
        bookRepo.addBook("Красивая незнакомка", "Майк Ньютон", 2020, 41);
        bookRepo.addBook("Тайна третьей планеты", "Кира Булычёв", 1981, 42);
        bookRepo.addBook("Убить пересмешника", "Харпер Ли", 1960, 43);
        bookRepo.addBook("Как закалялась сталь", "Николай Островский", 1932, 44);
        bookRepo.addBook("Тихий Дон", "Михаил Шолохов", 1940, 45);
        bookRepo.addBook("На берегу", "Павел Санаев", 2006, 46);
        bookRepo.addBook("Место под солнцем", "Федор Достоевский", 1999, 47);
        bookRepo.addBook("Стратегия и тактика", "Андрей Игоревич", 1999, 48);
        bookRepo.addBook("Невский проспект", "Николай Гоголь", 1835, 49);
        bookRepo.addBook("Вечера на хуторе близ Диканьки", "Николай Гоголь", 1831, 50);
    }

    // Метод для логина пользователя
    public boolean loginUser(String email, String password) {
        currentUser = userRepo.getUserByEmail(email);
        return currentUser != null && currentUser.getPassword().equals(password);
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == Role.ADMIN;
    }

    // Метод для регистрации нового пользователя
    public User registerUser(String email, String password) {
        if (userRepo.isMailExist(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        User newUser = userRepo.addUser(email, password);
        currentUser = newUser; // Устанавливаем текущего пользователя на зарегистрированного
        return newUser;
    }

    // Метод для добавления новой книги
    public void addBook(String name, String author, int year, int bookId) {
        if (currentUser != null && currentUser.getRole() == Role.ADMIN) {
            bookRepo.addBook(name, author, year, bookId);
        } else {
            throw new IllegalStateException("Only admin can add books.");
        }
    }

    public void addSampleBooks() {
        for (int i = 1; i <= 50; i++) {
            String bookName = "Книга " + i;
            String author = "Автор " + i;
            int year = 2000 + (i % 21); // Годы от 2000 до 2020
            int bookId = i; // Используем i как уникальный ID книги
            bookRepo.addBook(bookName, author, year, bookId);
        }
    }


    // Метод для заимствования книги
    public boolean borrowBook(int bookId) {
        if (currentUser == null) {
            return false; // Пользователь не залогинен
        }
        Book book = bookRepo.findBookById(bookId);
        if (book != null && book.isAvailable()) {
            book.setBusy(true);
            currentUser.getUserBooks().add(book); // Добавляем книгу пользователю
            return true; // Успешно занята книга
        }
        return false; // Книга недоступна
    }

    // Метод для возврата книги
    public void returnBook(int bookId) {
        if (currentUser == null) {
            return; // Пользователь не залогинен, возврат невозможен
        }
        Book book = bookRepo.findBookById(bookId);
        if (book != null && currentUser.getUserBooks().contains(book)) {
            book.setBusy(false); // Книга теперь свободна
            currentUser.getUserBooks().remove(book); // Удаляем книгу из списка книг пользователя
        }
    }

    // Метод для получения всех книг
    public MyList<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    // Метод для получения книги по ID
    public Book getBookById(int bookId) {
        return bookRepo.findBookById(bookId);
    }

    // Метод для получения всех пользователей
    public MyList<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    // Метод для получения текущего пользователя
    public User getCurrentUser() {
        return currentUser;
    }
}




