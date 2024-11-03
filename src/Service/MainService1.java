//package Service;
//
//import Model.Book;
//import Model.User;
//import Repo.BookRepo;
//import Repo.UserRepo;
//import Utils.MyList;
//
//public class MainService1 {
//    private final MyList<Book> books;
//    private final MyList<User> users;
//    private User currentUser;
//
//    public MainService(BookRepo bookRepo, UserRepo userRepo) {
//        this.books = bookRepo.getAllBooks();
//        this.users = userRepo.getAllUsers();
//    }
//
//    public boolean loginUser(String email, String password) {
//        for (User user : users) {
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                currentUser = user;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public User registerUser(String email, String password) {
//        User user = new User(email, password); // Создаем нового пользователя
//        users.add(user); // Добавляем пользователя в список
//        return user; // Возвращаем созданного пользователя
//    }
//
//
//    public void addBook(String name, String author, int year) {
//        int bookId = books.size() + 1; // Генерация ID
//        books.add(new Book(name, author, year, bookId));
//    }
//
//    public void borrowBook(int bookId) {
//        if (currentUser == null) {
//            System.out.println("You need to log in to borrow a book.");
//            return;
//        }
//
//        Book book = books.get(bookId - 1); // Предполагаем, что bookId начинается с 1
//        if (book != null && book.isAvailable()) {
//            book.setAvailable(false);
//            currentUser.addBook(book);
//            System.out.println("You borrowed the book: " + book.getName());
//        } else {
//            System.out.println("This book is not available.");
//        }
//    }
//
//    public void returnBook(int bookId) {
//        Book book = books.get(bookId - 1);
//        if (book != null && !book.isAvailable()) {
//            book.setAvailable(true);
//            System.out.println("You returned the book: " + book.getName());
//        } else {
//            System.out.println("This book was not borrowed.");
//        }
//    }
//
//    public MyList<Book> getAllBooks() {
//        return books;
//    }
//
//    public MyList<User> getAllUsers() {
//        return users;
//    }
//}
