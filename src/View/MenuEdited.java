package View;

import Model.Book;
import Model.User;
import Service.MainService;
import Utils.MyList;

import java.util.Scanner;

public class MenuEdited {
    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_WHITE = "\u001B[37m";

    public MenuEdited(MainService service) {
        this.service = service;
    }

    public void run() {
        showLoginPage();
    }

    private void waitRead() {
        System.out.println("\nPress enter to proceed");
        scanner.nextLine();
    }

    private void printBooks(MyList<Book> books) {
        StringBuilder result = new StringBuilder(String.format("\u001B[4m%-5s %-25s %-37s %-5s\u001B[0m\n",
                "ID:", "Author:", "Title:", "Year:"));

        // Добавляем строки с данными книг
        for (Book book : books) {
            result.append(String.format("%-5s %-25s %-37s %-5s\n",
                    book.getBookId(), book.getAuthor(), book.getName(), book.getYear()));
        }
        // Теперь выводим всю таблицу на консоль
        System.out.println(result.toString());
    }

    private void showLoginPage() {
        while (true) {
            System.out.println(COLOR_BLUE);
            System.out.println("Welcome");
            System.out.println(COLOR_WHITE);
            System.out.println("1. Login");
            System.out.println("2. Register new user");
            System.out.println("0. Exit");
            System.out.println("\nSelect an option");

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                System.out.println("Good bye");
                System.exit(0);//end app work
            }
            handleLoginPageChoice(input);
        }
    }

    private void handleLoginPageChoice(int input) {
        switch (input) {
            case 1:
                //authorization
                System.out.println(COLOR_GREEN);
                System.out.println("User authorization");
                System.out.println(COLOR_YELLOW);
                System.out.println("Type your email");
                String email2 = scanner.nextLine();
                System.out.println("Type your password");
                String password1 = scanner.nextLine();

                boolean user1 = service.loginUser(email2, password1);

                if (user1 == true) {
                   // System.out.println("User successfully logged in");
                    showHomePage();
                }
                waitRead();
                break;
            case 2:
                //registration
                System.out.println(COLOR_GREEN);
                System.out.println("New user registration");
                System.out.println(COLOR_YELLOW);
                System.out.println("Insert email");
                String email = scanner.nextLine();
                System.out.println("Insert password");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null) {
                    System.out.println("Registered successfully");
                    showHomePage();
                } else {
                    System.out.println(COLOR_RED);
                    System.out.println("Registration failed");
                    showLoginPage();
                }

                waitRead();
                break;

            default:
                System.out.println(COLOR_RED);
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void showHomePage() {
        while (true) {
            System.out.println(COLOR_BLUE);
            System.out.println("Menu");
            System.out.println(COLOR_WHITE);
            System.out.println("1. Book menu");
            System.out.println("2. Admin menu");
            System.out.println("0. Logout");

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                break;
            }
            handleShowHomePageChoice(input);
        }
    }

    private void handleShowHomePageChoice(int input) {
        switch (input) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showAdminMenu();
                break;
            default:
                System.out.println("Select an option");
        }
    }

    private void showBookMenu() {
        while (true) {
            System.out.println(COLOR_BLUE);
            System.out.println("Book menu");
            System.out.println(COLOR_WHITE);
            System.out.println("1. Find a book by title");
            System.out.println("2. Find a book by author");
            System.out.println("3. Show all books");
            System.out.println("4. Available books");
            System.out.println("5. Not available books");
            System.out.println("6. Take a book");
            System.out.println("7. Return a book");
            System.out.println("8. Sort books by title");
            System.out.println("9. Sort books by author");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleBookMenuChoice(input);
        }
    }

    private void handleBookMenuChoice(int input) {
        switch (input) {
            case 1:
                System.out.println(COLOR_BLUE);
                System.out.println("Find a book by title");
                System.out.println(COLOR_YELLOW);
                System.out.println("Insert book's title");
                String name = scanner.nextLine();
                MyList<Book> bookByName = service.getBookByName(name);
                printBooks(bookByName);
                waitRead();
                break;
            case 2:
                System.out.println(COLOR_BLUE);
                System.out.println("Find a book by author");
                System.out.println(COLOR_YELLOW);
                System.out.println("Insert book's author");
                String author = scanner.nextLine();
                MyList<Book> bookByAuthor = service.getByAuthor(author);
                printBooks(bookByAuthor);
                waitRead();
                break;
            case 3:
                MyList<Book> booksAll = service.getAllBooks();
                printBooks(booksAll);
                waitRead();
                break;
            case 4:
                MyList<Book> booksAvailable = service.getAllFreeBooks();
                printBooks(booksAvailable);
                waitRead();
                break;
            case 5:
                MyList<Book> booksBusy = service.getAllBusyBooks();
                printBooks(booksBusy);
                waitRead();
                break;
            case 6:
                System.out.println(COLOR_BLUE);
                System.out.println("Borrow a book");
                System.out.println(COLOR_YELLOW);
                System.out.println("Insert book id");
                int bookId = scanner.nextInt();
                service.borrowBook(bookId);
                waitRead();
                break;
            case 7:
                System.out.println(COLOR_BLUE);
                System.out.println("Return a book");
                System.out.println(COLOR_YELLOW);
                System.out.println("Insert book id");
                bookId = scanner.nextInt();
                service.returnBook(bookId);
                waitRead();
                break;
            case 8:
                MyList<Book> booksSortTitle = service.getBooksSortedByName();
                printBooks(booksSortTitle);
                waitRead();
                break;
            case 9:
                MyList<Book> booksSortAuthor = service.getBooksSortedByAuthor();
                printBooks(booksSortAuthor);
                waitRead();
                break;
            default:
                System.out.println(COLOR_RED);
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void showAdminMenu () {
            while (true) {
                System.out.println(COLOR_BLUE);
                System.out.println("Admin menu");
                System.out.println(COLOR_WHITE);
                System.out.println("1. Add a book");
                System.out.println("2. Find a book by id");
                System.out.println("3. Edit a book");
                System.out.println("4. Find a user by id");
                System.out.println("5. View all books by user ID");
                System.out.println("6. Show all users");
                System.out.println("0. Back");

                System.out.println("\n Select an option");
                int input = scanner.nextInt();
                scanner.nextLine();

                if (input == 0) break;

                handleAdminMenuChoice(input);
            }
        }

    private void handleAdminMenuChoice(int input) {
        switch (input) {
            case 1:
                //add a book
                System.out.println(COLOR_BLUE);
                System.out.println("Add a book");
                System.out.println(COLOR_WHITE);
                System.out.println("Insert book's title");
                String name = scanner.nextLine();
                System.out.println("Insert book's author");
                String author = scanner.nextLine();
                System.out.println("Insert book's year");
                int year = scanner.nextInt();

                System.out.println("Insert book's id");
                int bookId = scanner.nextInt();

                service.addBook(name, author, year, bookId);
                waitRead();
                break;
            case 2:
                System.out.println(COLOR_BLUE);
                System.out.println("Find a book by id");
                System.out.println(COLOR_WHITE);
                System.out.println("Insert book id");
                bookId = scanner.nextInt();
                Book bookById = service.getBookById(bookId);
                System.out.println(bookById);
                waitRead();
                break;
            case 3:
                System.out.println(COLOR_BLUE);
                System.out.println("Edit a book");
                System.out.println(COLOR_WHITE);
                System.out.println("Insert book id");
                bookId = scanner.nextInt();
                service.getBookById(bookId);

                System.out.println("Insert new title");
                String newName = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Insert new author");
                String newAuthor = scanner.nextLine();

                System.out.println("Insert book's publish year");
                int newYear = scanner.nextInt();

                service.editBook(bookId, newName, newAuthor, newYear);
                waitRead();
                break;
            case 4:
                System.out.println(COLOR_BLUE);
                System.out.println("Find a user by id");
                System.out.println(COLOR_WHITE);
                System.out.println("Insert user id");
                int userId = scanner.nextInt();
                User userById = service.findUserById(userId);
                System.out.println(userById);
                waitRead();
                break;
            case 5:
                System.out.println(COLOR_BLUE);
                System.out.println("View all books by user ID");
                System.out.println(COLOR_WHITE);
                System.out.println("Insert user id");
                userId = scanner.nextInt();
                scanner.nextLine();
                service.getUserBooksByUserId(Integer.valueOf(userId));
                break;

            case 6:
                System.out.println(COLOR_BLUE);
                System.out.println("Show all users: ");
                System.out.println(COLOR_WHITE);
                MyList<User> allUsers = service.getAllUsers();
                printUsers(allUsers);
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input, please enter a number");

        }
    }





    private void printUsers(MyList<User> users) {
        for (User user : users){
            System.out.println("Id: " + user.getId() + "; " + user.getEmail() + "; "
                    + "Role: " + user.getRole() + "; " + "Hold books:  " + user.getUserBooks());
        }
    }
}

