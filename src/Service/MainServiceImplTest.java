package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.BookRepoImpl;
import Repo.UserRepo;
import Repo.UserRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MainServiceImplTest {

    private BookRepo bookRepo;
    private UserRepo userRepo ;
    private MainServiceImpl mainService;
    private User adminUser;
    private User regularUser;

    @BeforeEach
    void setUp() {
        bookRepo = new BookRepoImpl(1000);
        userRepo = new UserRepoImpl(100);
        mainService = new MainServiceImpl(bookRepo, userRepo);


        adminUser = userRepo.addUser("admin@example.com", "adminPassword");
        adminUser.setRole(Role.ADMIN);
        regularUser = userRepo.addUser("user@example.com", "userPassword");
    }


    @Test
    void testAddBookAsAdmin() {
        mainService.loginUser("admin@example.com", "adminPassword");
        mainService.addBook("Effective Java", "Joshua Bloch", 2008);

        Book addedBook = bookRepo.findBookById(1); // Предполагаем, что ID - 1
        assertNotNull(addedBook);
        assertEquals("Effective Java", addedBook.getName());
        assertEquals("Joshua Bloch", addedBook.getAuthor());
        assertEquals(2008, addedBook.getYear());
    }

    @Test
    void testAddBookAsNonAdmin() {
        mainService.loginUser("user@example.com", "userPassword");
        mainService.addBook("Effective Java", "Joshua Bloch", 2008);

        assertNull(bookRepo.findBookById(1)); // Проверяем, что книга не добавлена
    }

    @Test
    void testBorrowBookAsLoggedInUser() {
        mainService.loginUser("user@example.com", "userPassword");

        Book book = new Book("Clean Code", "Robert C. Martin", 2008);
        bookRepo.addBook(book);

        boolean result = mainService.borrowBook(1);

        assertTrue(result);
        assertTrue(regularUser.getUserBooks().contains(book));
    }

    @Test
    void testBorrowBookWhenNotLoggedIn() {
        boolean result = mainService.borrowBook(1);

        assertFalse(result);
    }

    @Test
    void testReturnBook() {
        mainService.loginUser("user@example.com", "userPassword");

        Book book = new Book("Design Patterns", "Erich Gamma", 1994);
        book.setBusy(true);
        bookRepo.addBook(book);

        mainService.returnBook(1);

        assertFalse(book.isBusy());
    }

    @Test
    void testEditBookAsAdmin() {
        mainService.loginUser("admin@example.com", "adminPassword");

        Book book = new Book("Old Title", "Old Author", 1999);
        bookRepo.addBook(book);

        mainService.editBook(1, "New Title", "New Author", 2020);

        Book updatedBook = bookRepo.findBookById(1);
        assertEquals("New Title", updatedBook.getName());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals(2020, updatedBook.getYear());
    }

    @Test
    void testEditBookAsNonAdmin() {
        mainService.loginUser("user@example.com", "userPassword");

        mainService.editBook(1, "New Title", "New Author", 2020);

        Book book = bookRepo.findBookById(1);
        assertNotEquals("New Title", book.getName());
    }
}