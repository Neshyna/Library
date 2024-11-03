package Model;

import Utils.MyArrayList;
import Utils.MyList;

public class User {

    private static int userIdCounter = 0; // Статический счетчик для генерации уникальных ID
    private final int id; // Уникальный идентификатор
    private String email;
    private String password;
    private Role role;
    boolean isBlocked;
    boolean isAdmin;

    private final MyList<Book> userBooks;

    public User( String email, String password) {

        this.userBooks = new MyArrayList<>();
        this.role = Role.USER;
        this.password = password;
        this.email = email;
        this.id = ++userIdCounter; // Генерируем уникальный ID
        this.isBlocked = isBlocked;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id; // Метод для получения ID
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getUserBooks() {
        return userBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isBlocked=" + isBlocked +
                ", userBooks=" + userBooks +
                '}';
    }


}
