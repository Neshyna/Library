package Repo;

import Model.User;
import Utils.MyList;

public interface UserRepo {
    User addUser(String email, String password);
    User getUserByEmail(String email);
    MyList<User> getAllUsers();
    boolean isMailExist(String email); // Убедитесь, что этот метод объявлен только один раз
}
