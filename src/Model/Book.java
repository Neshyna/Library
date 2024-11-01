package Model;

import java.util.Objects;

public class Book {

    private String author;
    private String name;
    private int year;
    private boolean isBusy;
    private User holder;//data type user
    private int bookId;

    public Book(String author, String name, int year, int bookId) {
        this.author = author;
        this.name = name;
        this.year = year;
        this.bookId = bookId;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && isBusy == book.isBusy && bookId == book.bookId && Objects.equals(author, book.author) && Objects.equals(name, book.name) && Objects.equals(holder, book.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, name, year, isBusy, holder, bookId);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", isBusy=" + isBusy +
                ", holder=" + holder +
                ", bookId=" + bookId +
                '}';
    }
}