package Model;

public class Book {
    private String name;
    private String author;
    private int year;
    private int bookId;
    private boolean isBusy; // Поле для хранения состояния книги (занята/свободна)

    public Book(String name, String author, int year, int bookId) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.bookId = bookId;
        this.isBusy = false; // По умолчанию книга свободна
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isBusy() {
        return isBusy; // Возвращает состояние книги
    }

    public void setBusy(boolean busy) {
        isBusy = busy; // Устанавливает состояние книги
    }

    public boolean isAvailable() {
        return !isBusy; // Проверяет, доступна ли книга
    }

    public void setAvailable(boolean available) {
        this.isBusy = !available; // Устанавливает доступность книги
    }
}




