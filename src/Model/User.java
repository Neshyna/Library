package Model;

import Utils.MyArrayList;
import Utils.MyList; // Убедитесь, что ваш класс MyList поддерживает необходимые методы

public class User {
    private static int userCount = 0; // Счетчик для уникальных ID пользователей
    private int id; // Уникальный ID пользователя
    private String email;
    private String password;
    private Role role; // Роль пользователя
    private MyList<Book> userBooks; // Список книг, взятых пользователем

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = ++userCount; // Присваиваем уникальный ID при создании пользователя
        this.role = Role.USER; // Устанавливаем роль по умолчанию
        this.userBooks = new MyArrayList<>(); // Инициализируем список книг
    }

    // Метод для получения ID пользователя
    public int getId() {
        return id;
    }

    // Метод для получения email
    public String getEmail() {
        return email;
    }

    // Метод для получения пароля (по идее, лучше избегать предоставления этого метода)
    public String getPassword() {
        return password;
    }

    // Метод для установки роли пользователя
    public void setRole(Role role) {
        this.role = role;
    }

    // Метод для получения роли пользователя
    public Role getRole() {
        return role;
    }

    // Метод для получения списка книг пользователя
    public MyList<Book> getUserBooks() {
        return userBooks;
    }

    // Метод для добавления книги в список пользователя
    public void addBook(Book book) {
        userBooks.add(book);
    }

    // Метод для удаления книги из списка пользователя
    public void removeBook(Book book) {
        userBooks.remove(book);
    }
}



