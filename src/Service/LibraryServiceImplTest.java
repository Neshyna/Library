package Service;

import Model.Book;
import Model.Role;
import Model.User;
import Repo.BookRepoImpl;
import Repo.UserRepoImpl;
import Utils.MyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LibraryServiceImplTest {

    private BookRepoImpl bookRepo;
    private UserRepoImpl userRepo;
    private MainServiceImpl mainService;
    private User adminUser;
    private User regularUser;
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

        bookRepo.addBook("Original Author", "Original Title", 2019, 1);
        bookRepo.addBook("Book One", "Author One", 2020, 1);
        bookRepo.addBook("Book Two", "Author Two", 2019, 2);
        bookRepo.addBook("Book Three", "Author Three", 2018, 3);


        for (Book book : bookRepo.getAllBooks()) {
            System.out.println(book.getName());
        }
    }


    @Test
    void testAddBook() {//проверяет, что книга добавляется корректно.
        int initialSize = bookRepo.getAllBooks().size();
        bookRepo.addBook("Author Four", "Book Four", 2021,4);

        assertEquals(initialSize + 1, bookRepo.getAllBooks().size());
        assertEquals("Author Four", bookRepo.getAllBooks().get(initialSize).getAuthor());
        assertEquals("Book Four", bookRepo.getAllBooks().get(initialSize).getName());
    }

    @Test
    void testGetAllBooks() {//проверяет получение всех книг.
        MyList<Book> allBooks = bookRepo.getAllBooks();
        System.out.println("Total books: " + allBooks.size());
        assertEquals(3, allBooks.size());
    }
    @Test
    void testGetByNamePart_Found() {
        bookRepo.addBook("Book One", "Author One", 2020, 1);

        for (Book book : bookRepo.getAllBooks()) {
            System.out.println(book.getName()); // Вывод имени каждой книги
        }
        MyList<Book> result = bookRepo.getByNamePart("Book One");

        assertFalse(result.isEmpty(), "The result list should not be empty");
        assertEquals("Book One", result.get(0).getName(), "The first book name should be 'Book One'");
    }

    @Test
    void testGetByNamePart_NotFound() {
        MyList<Book> result = bookRepo.getByNamePart("Non-Existent Book");
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetByAuthor_Found() {//проверяет получение книг по автору.
        MyList<Book> result = bookRepo.getByAuthor("Author Two");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Author Two", result.get(0).getAuthor());
    }

    @Test
    void testGetByAuthor_NotFound() {//проверяет фильтрацию занятых книг.
        MyList<Book> result = bookRepo.getByAuthor("Non-Existent Author");
        assertTrue(result.isEmpty()); // Should return an empty list if no author matches
    }

    @Test
    void testGetAllBusyBooks() {
        bookRepo.getAllBooks().get(0).setBusy(true);
        MyList<Book> busyBooks = bookRepo.getAllBusyBooks();
        assertEquals(1, busyBooks.size());
        assertTrue(busyBooks.get(0).isBusy());
    }

    @Test
    void testGetAllFreeBooks() { //проверяет фильтрацию свободных книг.
        bookRepo.getAllBooks().get(0).setBusy(true);
        MyList<Book> freeBooks = bookRepo.getAllFreeBooks();
        assertEquals(2, freeBooks.size());
        assertFalse(freeBooks.get(0).isBusy());
    }

    @Test
    void testGetBooksSortedByAuthor() {
        MyList<Book> sortedByAuthor = bookRepo.getBooksSortedByAuthor();

        // Проверяем, что список не null
        assertNotNull(sortedByAuthor, "The sorted list should not be null");

        // Проверяем, что все книги в списке не null и их авторы тоже не null
        for (Book book : sortedByAuthor) {
            assertNotNull(book, "Book should not be null");
            assertNotNull(book.getAuthor(), "Book author should not be null");
        }

        assertEquals(3, sortedByAuthor.size());
        assertTrue(sortedByAuthor.get(0).getAuthor().compareTo(sortedByAuthor.get(1).getAuthor()) < 0);
    }

    @Test
    void testGetBooksSortedByName() {//проверяет сортировку по названию.
        MyList<Book> sortedByName = bookRepo.getBooksSortedByName();

        // Проверяем, что список не null
        assertNotNull(sortedByName, "The sorted list should not be null");

        for (Book book : sortedByName) {
            assertNotNull(book, "Book should not be null");
            assertNotNull(book.getName(), "Book name should not be null");
        }

        assertEquals(3, sortedByName.size());
        assertTrue(sortedByName.get(0).getName().compareTo(sortedByName.get(1).getName()) < 0);
    }

@Test
    void testAddBookAsAdmin() {
        mainService.loginUser("admin@example.com", "adminPassword");
        mainService.addBook("Effective Java", "Joshua Bloch", 2008, 82);

        Book addedBook = bookRepo.getBookById(82);
        assertNotNull(addedBook);
        assertEquals("Effective Java", addedBook.getName());
        assertEquals("Joshua Bloch", addedBook.getAuthor());
        assertEquals(2008, addedBook.getYear());
    }

    @Test
    void testAddBookAsNonAdmin() {
        mainService.loginUser("user@example.com", "userPassword");
        mainService.addBook("Effective Java", "Joshua Bloch", 2008, 33);

        Book addedBook = bookRepo.getBookById(33);
        assertNull(addedBook); // Проверяем, что книга не добавлена
    }

    @Test
    void testBorrowBookAsLoggedInUser() {
        mainService.loginUser("user@example.com", "userPassword");

        Book book = new Book("Clean Code", "Robert C. Martin", 2008, 86);
        bookRepo.addNewBook(book);

        boolean result = mainService.borrowBook(86);

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

        Book book = new Book("Design Patterns", "Erich Gamma", 1994, 7);
        book.setBusy(true);
        bookRepo.addNewBook(book);

        mainService.returnBook(7);

        assertFalse(book.isBusy());
    }

    @Test
    void testEditBookAsAdmin() {
        mainService.loginUser("admin@example.com", "adminPassword");

        bookRepo.addNewBook(new Book("Old Title", "Old Author", 1999, 8));

        mainService.editBook(8, "New Title", "New Author", 2020);

        Book editedBook = bookRepo.getBookById(8);
        assertEquals("New Title", editedBook.getName());
        assertEquals("New Author", editedBook.getAuthor());
    }

    @Test
    void testEditBookAsNonAdmin() {
        mainService.loginUser("user@example.com", "userPassword");

        mainService.editBook(1, "New Title", "New Author", 2020);

        Book book = bookRepo.getBookById(1);
        assertNotEquals("New Title", book.getName());
    }

    @Test
    void testValidEmailSet() {
        String validEmail = "Valid123@test.com";
        regularUser.setEmail(validEmail);
        assertEquals(validEmail, regularUser.getEmail());
    }

    @ParameterizedTest
    @MethodSource("invalidEmailData")
    void testInvalidEmailSet(String invalidEmail) {
        regularUser.setEmail(invalidEmail);
        assertNotEquals(invalidEmail, regularUser.getEmail());
        assertEquals(startEmail, regularUser.getEmail());
    }

    static Stream<String> invalidEmailData() {
        return Stream.of(
                "testmail.net",
                "test@@mail.net",
                "test@mai@l.net",
                "test@mailnet",
                "test@mail.ne.t",
                "test@mail.net.",
                "test@ mail.net",
                "test@ma!il.net"
        );
    }

    @Test
    void testValidPasswordSet() {
        String validPassword = "Test_123";
        regularUser.setPassword(validPassword);
        assertEquals(validPassword, regularUser.getPassword());
    }

    @ParameterizedTest
    @MethodSource("invalidPassword")
    void testInvalidPassword(String invalidPassword) {
        regularUser.setPassword(invalidPassword);
        assertEquals(startPassword, regularUser.getPassword());
        assertNotEquals(invalidPassword, regularUser.getPassword());
    }

    static Stream<String> invalidPassword() {
        return Stream.of(
                "Test_1",
                "Test_test",
                "TEST_123",
                "test_123",
                "Test123456"
        );
    }
}