package View;

import Model.User;
import Repo.UserRepo;
import Service.MainService;

import java.util.Scanner;

public class Menu {
    private final MainService service;
    private final UserRepo userRepo;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service, UserRepo userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println("Welcome to menu");
            System.out.println("1. Book menu");
            System.out.println("2. User menu");
            System.out.println("3. Admin menu");
            System.out.println("0. Exit");
            System.out.println("\nSelect an option");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Good bye");
                System.exit(0);//end app work
            }

            showSubMenu(choice);
        }

    }

    private void showSubMenu(int input) {
        switch (input) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                showAdminMenu();
                break;
            default:
                System.out.println("Select an option");
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("User menu");
            System.out.println("1. Login");
            System.out.println("2. Register new user");
            System.out.println("3. Logout");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleUserMenuChoice(input);

        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                //authorization

                System.out.println("User authorization");
                System.out.println("Type your email");
                String email2 = scanner.nextLine();

                System.out.println("Type your pass");
                String password1 = scanner.nextLine();

                boolean user1 = service.loginUser(email2, password1);

                if (user1 == true) {
                    System.out.println("User successfully logged in");
                }

                waitRead();
                break;

            case 2:
                //registration
                System.out.println("New user registration");
                System.out.println("Insert email");
                String email = scanner.nextLine();

                System.out.println("Insert password");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null) {
                    System.out.println("Registered successfully");
                } else {
                    System.out.println("Registration failed");
                }

                waitRead();

                break;

            case 3:
                //logout
                service.logout();
                System.out.println("You are logged out");
                waitRead();
                break;

            default:
                System.out.println("\nIncorrect input");
        }
    }

    private void waitRead() {
        System.out.println("\npress enter to proceed");
        scanner.nextLine();
    }

    private void showBookMenu() {
        while (true) {
            System.out.println("Book menu");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. All books");
            System.out.println("4. Find a book");
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
                System.out.println("Borrow a book");
                System.out.println("Insert book id");
                int bookId = scanner.nextInt();

                service.BorrowBook(bookId);
                waitRead();
                break;
            case 2:
                System.out.println("Return a book");
                System.out.println("Insert book id");
                bookId = scanner.nextInt();

                service.returnBook(bookId);
                waitRead();
                break;
            case 3:
                showAllBooks();
                waitRead();
                break;
            case 4:
                showFindBookMenu();
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input");
        }
    }

    private void showFindBookMenu(){
        while (true) {
            System.out.println("Find a book ");
            System.out.println("1. Find a book by name");
            System.out.println("2. Find a book by author");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleFindBookChoice(input);
        }
    }

    private void handleFindBookChoice(int input) {
        switch (input){
            case 1:
                System.out.println("Find a book by name");
                System.out.println("Insert book's name");
                String name = scanner.nextLine();

                service.getBookByName(name);
                waitRead();
                break;
            case 2:
                System.out.println("Find a book by author");
                System.out.println("Insert book's author");
                String author = scanner.nextLine();

                service.getByAuthor(author);
                break;
            default:
                System.out.println("\nIncorrect input");
                break;

        }
    }

    private void showAllBooks(){
        while (true) {
            System.out.println("All books menu");
            System.out.println("1. All books");
            System.out.println("2. Available books");
            System.out.println("3. Borrowed books");
            System.out.println("4. Sort books by name");
            System.out.println("5. Sort books by author");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleShowAllBooksChoice(input);
        }
    }

    private void handleShowAllBooksChoice(int input) {
        switch (input) {
            case 1:
                service.getAllBooks();
                waitRead();
                break;
            case 2:
                service.getAllFreeBooks();
                waitRead();
                break;
            case 3:
                service.getAllBusyBooks();
                waitRead();
                break;
            case 4:
                //Sort books by name
                waitRead();
                break;
            case 5:
                // Sort books by author
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input");
        }

    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("Admin menu");
            System.out.println("1. Login");
            System.out.println("2. Add a book");
            System.out.println("3. Find a user");//optional
            System.out.println("4. Find a book");
            System.out.println("5. Edit a book");
            System.out.println("6. Logout");
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
                //admin login

                System.out.println("User authorization");
                System.out.println("Type your email");
                String email2 = scanner.nextLine();

                System.out.println("Type your pass");
                String password1 = scanner.nextLine();

                boolean user1 = service.loginUser(email2, password1);

                if (user1 == true) {
                    System.out.println("User successfully logged in");
                }
                waitRead();
                break;
            case 2:
                //add a book
                System.out.println("Add a book");
                System.out.println("Insert book's name");
                String name = scanner.nextLine();

                System.out.println("Insert book's author");
                String author = scanner.nextLine();

                System.out.println("Insert book's year");
                int year = scanner.nextInt();

                service.addBook(name,author,year);
                waitRead();
                break;
            case 3:
                // find user
                System.out.println("Find user");
                System.out.println("Insert user id");
                int userId = scanner.nextInt();

                userRepo.findUserById(userId);
                waitRead();
                break;
            case 4:
                //find book
                //todo
                //create method to find a book
                showBookInfoAdmin();
                waitRead();
                break;
            case 5:
                System.out.println("Edit a book");
                System.out.println("Insert book id");
                int bookId = scanner.nextInt();

                service.editBook(bookId);
                waitRead();
                break;
            case 6:
                //logout
                service.logout();
                System.out.println("You are logged out");
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input");
        }

    }
    //Todo
    private void showBookInfoAdmin() {
        while (true) {
            System.out.println("Admin book info");
            System.out.println("1. Дата, когда была книга взята на прокат");
            System.out.println("2. Получить информацию сколько дней книга находится у пользователя");
            System.out.println("3. Взятие книги из библиотеки с фиксацией даты");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleBookInfoAdminChoice(input);

        }
    }
    //todo
    private void handleBookInfoAdminChoice(int input) {
    }
}