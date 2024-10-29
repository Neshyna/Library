package Service;

import Model.Book;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyList;
import Utils.PersonValidator;

public class MainServiceImpl implements MainService{

    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private User activUser;

    public MainServiceImpl(BookRepo bookRepo, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void addBook(String name, String author, int year) {

    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBookByName(String name) {
        return null;
    }

    @Override
    public MyList<Book> getByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

    @Override
    public boolean borrowBook(int bookId) {
        return false;
    }

    @Override
    public void returnBook(int bookId) {

    }

    @Override
    public void editBook(int bookId) {

    }


    //author neshyna
    @Override
    public User registerUser(String email, String password) {

        if(!PersonValidator.isEmailValid(email)){
            System.out.println("Please check email");
            return  null;
        }

        if(!PersonValidator.isPasswordValid(password)){
            System.out.println("Please check password");
            return null;
        }

        if (userRepo.isMailExist(email)){
            System.out.println("Email already exists");
            return null;
        }

        User user = userRepo.addUser(email,password);

        return user;

    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logout() {

    }
}
