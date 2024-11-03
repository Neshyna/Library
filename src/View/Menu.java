package View;

import Service.MainServiceImpl;
import Model.User;

import java.util.Scanner;

public class Menu {
    private final MainServiceImpl mainService;
    private final Scanner scanner;

    public Menu(MainServiceImpl mainService) {
        this.mainService = mainService;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Посмотреть все книги");
            System.out.println("4. Добавить книгу");
            System.out.println("5. Взять книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Выйти");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки после ввода числа

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 3:
                    viewAllBooks();
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    System.out.println("Выход...");
                    return; // Выход из меню
                default:
                    System.out.println("Неверная опция. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private void handleLogin() {
        System.out.print("Электронная почта: ");
        String email = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        if (mainService.loginUser(email, password)) {
            System.out.println("Успешный вход!");
        } else {
            System.out.println("Неверная электронная почта или пароль.");
        }
    }

    private void handleRegistration() {
        System.out.print("Электронная почта: ");
        String email = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        try {
            User newUser = mainService.registerUser(email, password);
            System.out.println("Регистрация успешна! Ваш ID пользователя: " + newUser.getId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllBooks() {
        mainService.getAllBooks().forEach(book -> {
            System.out.println("ID: " + book.getBookId() + ", Название: " + book.getName() + ", Автор: " + book.getAuthor());
        });
    }

    private void addBook() {
        if (!mainService.isAdmin()) {
            System.out.println("Только администратор может добавлять книги.");
            return;
        }

        System.out.print("Название книги: ");
        String name = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        System.out.print("Год: ");
        int year = scanner.nextInt();
        System.out.print("ID книги: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Считывание новой строки после ввода числа

        mainService.addBook(name, author, year, bookId);
        System.out.println("Книга успешно добавлена!");
    }

    private void borrowBook() {
        System.out.print("Введите ID книги для заимствования: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Считывание новой строки после ввода числа

        if (mainService.borrowBook(bookId)) {
            System.out.println("Книга успешно заимствована!");
        } else {
            System.out.println("Книга недоступна или пользователь не залогинен.");
        }
    }

    private void returnBook() {
        System.out.print("Введите ID книги для возврата: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Считывание новой строки после ввода числа

        mainService.returnBook(bookId);
        System.out.println("Книга успешно возвращена!");
    }
}
