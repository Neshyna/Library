package Service;

import Model.Book;
import Model.User;
import Repo.BookRepo;
import Repo.UserRepo;
import Utils.MyList;

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
    public boolean BorrowBook(int bookId) {
        return false;
    }

    @Override
    public void returnBook(int bookId) {

    }

    @Override
    public void editBook(int bookId) {

    }


    @Override
    public User registerUser(String email, String password) {
        return null;
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logout() {

    }
}
