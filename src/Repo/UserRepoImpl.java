package Repo;

import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

public class UserRepoImpl implements UserRepo{

    private final MyList<User> users;

    public UserRepoImpl(int initialCapacity) {
            this.users = new MyArrayList<>();

    }
 @Override
    public User addUser(String email, String password) {

        return null;
    }

    @Override
    public boolean isMailExits(String email) {
        return false;
    }

    @Override
    public boolean isMailExist(String email) {
        return false;
    }

    @Override
    public User getUserEmail(String email) {
        return null;
    }

    @Override
    public User findUserById(int userId) {

        return null;
    }
}
