import Repo.BookRepo;
import Repo.BookRepoImpl;
import Repo.UserRepo;
import Repo.UserRepoImpl;
import Service.MainService;
import Service.MainServiceImpl;
import View.Menu;
import View.MenuEdited;

public class LibraryExecute {
    public static void main(String[] args) {

        BookRepo bookRepo = new BookRepoImpl(10);
        UserRepo userRepo = new UserRepoImpl(100);
        MainService mainService = new MainServiceImpl(bookRepo,userRepo);

        MenuEdited menu = new MenuEdited(mainService);

        menu.run();


    }


}
