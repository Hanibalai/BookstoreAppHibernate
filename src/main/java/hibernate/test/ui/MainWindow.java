package hibernate.test.ui;

import hibernate.test.services.AuthorService;
import hibernate.test.services.BookService;
import hibernate.test.services.GenreService;

public class MainWindow {
    public void mainMenu() {
        int category;
        while (true) {
            Menu.showCategories();
            if ((category = Console.getOption()) == 0) {
                Console.close();
                break;
            }
            switch (category) {
                case 1 -> authorCategory();
                case 2 -> bookCategory();
                case 3 -> genreCategory();
                default -> System.out.println("Wrong category. Try again");
            }
        }
    }

    private void authorCategory() {
        AuthorService service = new AuthorService();
        int option;
        String entity = "author";
        Menu.showOptions(entity);
        while ((option = Console.getOption()) != 0) {
            switch (option) {
                case 1 -> Console.showResultList(service.getAll());
                case 2 -> Console.showResult(service.getById(Console.getId(entity)));
                case 3 -> Console.showResult(service.getByName(Console.getName(entity)));
                case 4 -> Console.showResultList(service.getBooks(Console.getId(entity)));
                case 5 -> Console.showResult(service.save(Console.getName(entity)));
                case 6 -> Console.showResult(service.update(Console.getId(entity), Console.getName(entity)));
                case 7 -> Console.showResult(service.delete(Console.getId(entity)));
                case 8 -> Console.showResult(service.delete(Console.getName(entity)));
                case 9 -> Menu.showOptions(entity);
                default -> System.out.println("Wrong option. Try again");
            }
            if (option != 9) Menu.showTips(9);
        }
    }

    private void bookCategory() {
        BookService service = new BookService();
        int option;
        String entity = "book";
        Menu.showOptions(entity);
        while ((option = Console.getOption()) != 0) {
            switch (option) {
                case 1 -> Console.showResultList(service.getAll());
                case 2 -> Console.showResult(service.getById(Console.getId(entity)));
                case 3 -> Console.showResultList(service.getByTitle(Console.getName(entity)));
                case 4 -> {
                    String title = Console.getName(entity);
                    float price = Console.getBookPrice();
                    int amount = Console.getBookAmount();
                    String authorName = Console.getName("author");
                    String genreName = Console.getName("genre");
                    Console.showResult(service.save(title, price, amount, authorName, genreName));
                }
                case 5 -> {
                    long id = Console.getId(entity);
                    String title = Console.updateBookData("title");
                    float price = Console.updateBookPrice();
                    int amount = Console.updateBookAmount();
                    String authorName = Console.updateBookData("author");
                    String genreName = Console.updateBookData("genre");
                    Console.showResult(service.update(id, title, price, amount, authorName, genreName));
                }
                case 6 -> Console.showResult(service.deleteById(Console.getId(entity)));
                case 7 -> Console.showResult(service.deleteByTitle(Console.getName(entity)));
                case 8 -> Menu.showOptions(entity);
                default -> System.out.println("Wrong option. Try again");
            }
            if (option != 8) Menu.showTips(8);
        }
    }

    private void genreCategory() {
        GenreService service = new GenreService();
        int option;
        String entity = "genre";
        Menu.showOptions(entity);
        while ((option = Console.getOption()) != 0) {
            switch (option) {
                case 1 -> Console.showResultList(service.getAll());
                case 2 -> Console.showResult(service.getById(Console.getId(entity)));
                case 3 -> Console.showResult(service.getByName(Console.getName(entity)));
                case 4 -> Console.showResultList(service.getBooks(Console.getId(entity)));
                case 5 -> Console.showResult(service.save(Console.getName(entity)));
                case 6 -> Console.showResult(service.update(Console.getId(entity), Console.getName(entity)));
                case 7 -> Console.showResult(service.delete(Console.getId(entity)));
                case 8 -> Console.showResult(service.delete(Console.getName(entity)));
                case 9 -> Menu.showOptions(entity);
                default -> System.out.println("Wrong option. Try again");
            }
            if (option != 9) Menu.showTips(9);
        }
    }
}
