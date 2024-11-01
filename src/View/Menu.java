package View;

import Model.Book;
import Model.User;
import Repo.UserRepo;
import Repo.BookRepo;
import Service.MainService;
import Utils.MyList;



import java.util.Scanner;

public class Menu {
    private final MainService service;
    private final UserRepo userRepo;
    private final BookRepo bookRepo;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service,BookRepo bookRepo, UserRepo userRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    public void run() {
        showStartPage();
    }

    private void showStartPage() {
        while (true) {
            System.out.println("Welcome");
            //System.out.println("User menu");
            System.out.println("1. Login");
            System.out.println("2. Register new user");
            System.out.println("3. Logout");
            System.out.println("0. Exit");
            System.out.println("\nSelect an option");

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                System.out.println("Good bye");
                System.exit(0);//end app work
            }
            handleUserMenuChoice(input);

        }
    }

    private void showHomePage(){
        while (true){
            System.out.println("Menu");
            System.out.println("1. Book menu");
            System.out.println("2. Admin menu");
            System.out.println("0. Back");

            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                break;
            }
            showSubMenu(input);
        }
    }

    private void showSubMenu(int input) {
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

   /* private void showUserMenu() {
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

       }*/


    private void handleUserMenuChoice(int input) {
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
                    System.out.println("User successfully logged in");
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
                    System.out.println("Registered successfully");
                    showHomePage();
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
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void waitRead() {
        System.out.println("\nPress enter to proceed");
        scanner.nextLine();
    }

    private void showBookMenu() {
        while (true) {
            System.out.println("Book menu");
            System.out.println("1. Find a book");
            System.out.println("2. All books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
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
                showFindBookMenu();
                waitRead();
                break;
            case 2:
                showAllBooks();
                waitRead();
                break;
            case 3:
                System.out.println("Borrow a book");
                System.out.println("Insert book id");
                int bookId = scanner.nextInt();

                service.borrowBook(bookId);
                waitRead();
                break;
            case 4:
                System.out.println("Return a book");
                System.out.println("Insert book id");
                bookId = scanner.nextInt();

                service.returnBook(bookId);
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input, please enter a number");
        }
    }

    private void showFindBookMenu(){
        while (true) {
            System.out.println("Find a book ");
            System.out.println("1. Find a book by title");
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
                System.out.println("Find a book by title");
                System.out.println("Insert book's title");
                String name = scanner.nextLine();

                bookRepo.getByNamePart(name);
                waitRead();
                break;
            case 2:
                System.out.println("Find a book by author");
                System.out.println("Insert book's author");
                String author = scanner.nextLine();

                bookRepo.getByAuthor(author);
                break;
            default:
                System.out.println("\nIncorrect input, please enter a number");
                break;

        }
    }

    private void showAllBooks(){
        while (true) {
            System.out.println("All books menu");
            System.out.println("1. All books");
            System.out.println("2. Available books");
            System.out.println("3. Borrowed books");
            System.out.println("4. Sort books by title");
            System.out.println("5. Sort books by author");
            System.out.println("0. Back");

            System.out.println("\n Select an option");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;

            handleShowAllBooksChoice(input);
        }
    }

    private void printBooks(MyList<Book> books){
        for (Book book : books){
            System.out.println(book.getBookId() + ", " + book.getName() +
                    ", "  + book.getAuthor());
        }
    }

    private void handleShowAllBooksChoice(int input) {
        switch (input) {
            case 1:
                MyList<Book> books = bookRepo.getAllBooks();
                printBooks(books);
                waitRead();
                break;
            case 2:
                MyList<Book> books1 = bookRepo.getAllFreeBooks();
                printBooks(books1);
                bookRepo.getAllFreeBooks();
                waitRead();
                break;
            case 3:
                bookRepo.getAllBusyBooks();
                waitRead();
                break;
            case 4:
                bookRepo.getBooksSortedByName();
                waitRead();
                break;
            case 5:
                bookRepo.getBooksSortedByAuthor();
                waitRead();
                break;
            default:
                System.out.println("\nIncorrect input, please enter a number");
        }

    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("Admin menu");

            System.out.println("1. Add a book");
            System.out.println("2. Find a user");//optional
            System.out.println("3. Find a book by id");
            System.out.println("4. Edit a book");
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

                service.addBook(name,author,year,bookId);
                waitRead();
                break;
            case 2:
                // find user
                System.out.println("Find user");
                System.out.println("Insert user id");
                int userId = scanner.nextInt();

                userRepo.findUserById(userId);
                waitRead();
                break;
            case 3:
                System.out.println("Find a book by id");
                System.out.println("Insert book id");
                bookId = scanner.nextInt();
                bookRepo.getBookById(bookId);

                waitRead();
                break;
            case 4:
                System.out.println("Edit a book");
                System.out.println("Insert book id");

                bookId = scanner.nextInt();
                bookRepo.getBookById(bookId);

                System.out.println("Insert new title");
                String newName = scanner.nextLine();

                System.out.println("Insert new author");
                String newAuthor = scanner.nextLine();

                System.out.println("Insert book's publish year");
                int newYear = scanner.nextInt();

                service.editBook(bookId,newName,newAuthor,newYear);
                waitRead();
                break;

            default:
                System.out.println("\nIncorrect input, please enter a number");
        }

    }

}