package Repo;

import Model.User;

public interface UserRepo {

     User addUser(String email, String password);

     boolean isMailExits(String email);

    User getUserEmail(String email);

    User findUserById(int userId);
}
