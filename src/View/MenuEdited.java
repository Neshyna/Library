package View;

import Model.Book;
import Model.User;
import Service.MainService;
import Utils.MyList;

import java.util.Scanner;

public class MenuEdited {
    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

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
        for (Book book : books) {
            System.out.println("Id " + book.getBookId() + ", " + "Title: " + book.getName() +
                    ", " + "Author: " + book.getAuthor() + ", " + "Published in " + book.getYear());
        }
    }

    private void showLoginPage() {
        while (true) {
            System.out.println("Welcome");
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

                System.out.println("User authorization");
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
                System.out.println("New user registration");
                System.out.println("Insert email");
                String email = scanner.nextLine();
                System.out.println("Insert password");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user != null) {
                    System.out.println("Registered successfully! Please Login!");
                    showLoginPage();
                } else {
                    System.out.println("Registration failed");
                    showLoginPage();
                }

                waitRead();
                break;

            default:
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void showHomePage() {
        while (true) {
            System.out.println("Menu");
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
                if (service.isUserAdmin()){
                showAdminMenu();
                }else{
                    System.out.println("Admin menu is available only for admin");
                }
                break;
            default:
                System.out.println("Select an option");
        }
    }

    private void showBookMenu() {
        while (true) {
            System.out.println("Book menu");
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
                System.out.println("Find a book by title");
                System.out.println("Insert book's title");
                String name = scanner.nextLine();
                MyList<Book> bookByName = service.getBookByName(name);
                printBooks(bookByName);
                waitRead();
                break;
            case 2:
                System.out.println("Find a book by author");
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
                System.out.println("Borrow a book");
                System.out.println("Insert book id");
                int bookId = scanner.nextInt();
                service.borrowBook(bookId);
                waitRead();
                break;
            case 7:
                System.out.println("Return a book");
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
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void showAdminMenu () {
            while (true) {
                System.out.println("Admin menu");

                System.out.println("1. Add a book");
                System.out.println("2. Find a book by id");
                System.out.println("3. Edit a book");
                System.out.println("4. Find a user by id");
                System.out.println("5. Show all users");
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
                System.out.println("Add a book");
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
                System.out.println("Find a book by id");
                System.out.println("Insert book id");
                bookId = scanner.nextInt();
                Book bookById = service.getBookById(bookId);
                System.out.println(bookById);
                waitRead();
                break;
            case 3:
                System.out.println("Edit a book");

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
                System.out.println("Find a user by id");
                System.out.println("Insert user id");
                int userId = scanner.nextInt();
                User userById = service.findUserById(userId);
                System.out.println(userById);
                waitRead();
                break;
            case 5:
                System.out.println("Show all users: ");
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

