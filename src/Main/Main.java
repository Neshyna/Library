package Main;

import Service.MainServiceImpl;
import Repo.BookRepoImpl;
import Repo.UserRepoImpl;
import View.Menu;

public class Main {
    public static void main(String[] args) {
        BookRepoImpl bookRepo = new BookRepoImpl();
        UserRepoImpl userRepo = new UserRepoImpl();
        MainServiceImpl mainService = new MainServiceImpl(bookRepo, userRepo);
        Menu menu = new Menu(mainService);

        menu.showMainMenu(); // Запуск меню
    }
}







