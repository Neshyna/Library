package Repo;

import Model.Book;
import Model.User;
import Utils.MyList;

public interface UserRepo {

    User addUser(String email, String password);

    boolean isMailExist(String email);

    User getUserEmail(String email);

    User findUserById(int userId);

    MyList<User> getAllUsers();

    MyList<Book> getUserBooksByUserId(int userId);
}
