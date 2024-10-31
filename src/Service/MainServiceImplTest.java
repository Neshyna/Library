package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepo;
import Repo.BookRepoImpl;
import Repo.UserRepo;
import Repo.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainServiceImplTest {

    private BookRepo bookRepo;
    private UserRepo userRepo ;
    private MainServiceImpl mainService;
    private User adminUser;
    private User regularUser;
    private Book bookId;
    String startEmail = "Neshyna@test.com";
    String startPassword = "Neshyna100%";

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

        Book book = new Book("Clean Code", "Robert C. Martin", 2008, bookId);
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

        Book book = new Book("Design Patterns", "Erich Gamma", 1994, bookId);
        book.setBusy(true);
        bookRepo.addBook(book);

        mainService.returnBook(1);

        assertFalse(book.isBusy());
    }

    @Test
    void testEditBookAsAdmin() {
        mainService.loginUser("admin@example.com", "adminPassword");

        Book book = new Book("Old Title", "Old Author", 1999, bookId);
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

    @Test
    void testValidEmailSet() {
        String validEmail = "Valid123@test.com";
        regularUser.setEmail(validEmail);
        System.out.println("getEamil: " + regularUser.getEmail());
        Assertions.assertEquals(validEmail, regularUser.getEmail());
    }

    @ParameterizedTest
    @MethodSource("invalidEmailData")
    void testInvalidEmailSet(String invalidEmail) {
        regularUser.setEmail(invalidEmail);
        Assertions.assertNotEquals(invalidEmail,regularUser.getEmail());
        Assertions.assertEquals(startEmail,regularUser.getEmail());
    }
    static Stream<String> invalidEmailData(){
        return Stream.of(
                "testmail.net",
                "test@@mail.net",
                "test@mai@l.net",
                "test@mailnet",
                "test@mail.ne.t",
                "test@mail.net.",
                "test@mailne.t",
                "test@ mail.net",
                "test@ma!il.net",
                "t#est@mail.net",
                "test@mail.?net",
                "@testmail.net",
                "1test@mail.net",
                "_test@mail.net",
                "-t@mail.net",
                ".est@mail.net",
                "test+1@mail.net"
                //add upper case
        );
    }

    @Test
    void testValidPasswordSet(){
        String validPassword = "Test_123";
        //i.e. String validPassword = "Test123"; with incorrect pass will fail
        regularUser.setPassword(validPassword);
        System.out.println("validPassword: " + regularUser.getPassword());
        Assertions.assertEquals(validPassword,regularUser.getPassword());
    }

    @ParameterizedTest
    @MethodSource("invalidPassword")
    void testInvalidPassword(String invalidPassword){
        regularUser.setPassword(invalidPassword);
        Assertions.assertEquals(startPassword,regularUser.getPassword());
        Assertions.assertNotEquals(invalidPassword,regularUser.getPassword());
    }
    static Stream<String> invalidPassword(){
        return Stream.of(
                "Test_1",//length less than 8
                "Test_test",//no numbers
                "TEST_123",//no lower case
                "test_123",//no upper case
                "Test123456"//no special symbol
                //"Test_123" test will fail as well as email passed gut
        );
    }
}