package Repo;


    import Model.Book;
import Repo.BookRepoImpl;
import Utils.MyArrayList;
import Utils.MyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class BookRepoImplTest {

        private BookRepoImpl bookRepo;
        private MyList<Book> books;

        @BeforeEach
        void setUp() {//инициализирует начальное состояние перед каждым тестом
            books = new MyArrayList<>();
            bookRepo = new BookRepoImpl(10);

            // Adding sample books to initialize repository
            bookRepo.addBook("Author One", "Book One", 2020, 1);
            bookRepo.addBook("Author Two", "Book Two", 2019,2);
            bookRepo.addBook("Author Three", "Book Three", 2018,3);
        }

        @Test
        void testAddBook() {//проверяет, что книга добавляется корректно.
            int initialSize = books.size();
            bookRepo.addBook("Author Four", "Book Four", 2021,4);

            assertEquals(initialSize + 1, books.size());
            assertEquals("Author Four", books.get(books.size() - 1).getAuthor());
            assertEquals("Book Four", books.get(books.size() - 1).getName());
        }

        @Test
        void testGetAllBooks() {//проверяет получение всех книг.
            MyList<Book> allBooks = bookRepo.getAllBooks();
            assertEquals(3, allBooks.size());  // Should have 3 books initially
        }

        @Test
        void testGetByNamePart_Found() {//проверяет получение книги по названию.
            MyList<Book> result = bookRepo.getByNamePart("Book One");
            assertNotNull(result);
            assertEquals("Book One", result.get(0).getName());
        }

        @Test
        void testGetByNamePart_NotFound() {
            MyList<Book> result = bookRepo.getByNamePart("Non-Existent Book");
            assertNull(result); // Should return null if not found
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
            // Set one book as busy
            books.get(0).setBusy(true);
            MyList<Book> busyBooks = bookRepo.getAllBusyBooks();

            assertEquals(1, busyBooks.size());
            assertTrue(busyBooks.get(0).isBusy());
        }

        @Test
        void testGetAllFreeBooks() {//проверяет фильтрацию свободных книг.
            // Set one book as busy
            books.get(0).setBusy(true);
            MyList<Book> freeBooks = bookRepo.getAllFreeBooks();

            assertEquals(2, freeBooks.size()); // There should be 2 free books left
            assertFalse(freeBooks.get(0).isBusy());
        }

        @Test
        void testGetBooksSortedByAuthor() {//проверяет сортировку по автору.
            MyList<Book> sortedByAuthor = bookRepo.getBooksSortedByAuthor();
            assertEquals(3, sortedByAuthor.size());
            assertTrue(sortedByAuthor.get(0).getAuthor().compareTo(sortedByAuthor.get(1).getAuthor()) < 0);
        }

        @Test
        void testGetBooksSortedByName() {//проверяет сортировку по названию.
            MyList<Book> sortedByName = bookRepo.getBooksSortedByName();
            assertEquals(3, sortedByName.size());
            assertTrue(sortedByName.get(0).getName().compareTo(sortedByName.get(1).getName()) < 0);
        }
    }

