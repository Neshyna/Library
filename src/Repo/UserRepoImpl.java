package Repo;

import Model.User;
import Utils.MyArrayList;
import Utils.MyList;

import java.util.Random;

public class UserRepoImpl implements UserRepo {

    private final MyList<User> users;

    public UserRepoImpl(int initialCapacity) {
            this.users = new MyArrayList<>();

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
            if (user.getEmail().equals(email)) {
                return true; // Если email найден, возвращаем true
            }
        }
        return false; // Если email не найден, возвращаем false
    }

    @Override
    public User getUserEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user; // Возвращаем пользователя с указанным email
            }
        }
        return null; // Если пользователь не найден, возвращаем null
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

    // Метод для генерации случайного пользователя
    /* Метод создает случайные значения для email и password.
    Это делается с помощью Random и добавляет случайное число к базовым строкам, например "user1234@example.com" */
    public User generateRandomUser() {
        String randomEmail = "user" + new Random().nextInt(10000) + "@example.com";
        String randomPassword = "pass" + new Random().nextInt(10000);

        // Создаем пользователя и добавляем его в репозиторий
        return addUser(randomEmail, randomPassword);
    }
}