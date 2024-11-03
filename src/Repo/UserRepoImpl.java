package Repo;

import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

public class UserRepoImpl implements UserRepo {
    private MyList<User> users;

    public UserRepoImpl() {
        this.users = new MyArrayList<>();
    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user; // Возвращаем первого найденного пользователя
            }
        }
        return null; // Если пользователь не найден
    }

    @Override
    public MyList<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean isMailExist(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true; // Почта существует
            }
        }
        return false; // Почта не существует
    }
}

