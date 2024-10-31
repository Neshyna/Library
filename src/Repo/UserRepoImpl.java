package Repo;

import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

public class UserRepoImpl implements UserRepo {

    private final MyList<User> users;

    public UserRepoImpl(MyList<User> users) {
        this.users = users;
    }

    @Override
    public User addUser(String email, String password) {
        // Проверяем, существует ли уже пользователь с таким email
        if (isMailExist(email)) {
            System.out.println("Email already exists.");
            return null; // Если email существует, возвращаем null
        }

        User newUser = new User(new MyArrayList<>(), password, email); // Создаем нового пользователя
        users.add(newUser); // Добавляем пользователя в список
        return newUser; // Возвращаем нового пользователя
    }

    @Override
    public boolean isMailExist(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true; // Если email найден, возвращаем true
            }
        }
        return false; // Если email не найден, возвращаем false
    }

    @Override
    public User getUserEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user; // Возвращаем пользователя с указанным email
            }
        }
        return null; // Если пользователь не найден, возвращаем null
    }

    @Override
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null; // если user не найден
    }

    @Override
    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user; // Возвращаем пользователя с указанным ID
            }
        }
        return null; // Если пользователь не найден, возвращаем null
    }
}