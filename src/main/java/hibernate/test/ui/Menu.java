package hibernate.test.ui;

import java.util.ArrayList;
import java.util.List;

class Menu {
    private final static List<String> CATEGORIES = new ArrayList<>();
    private final static List<String> AUTHOR_OPTIONS = new ArrayList<>();
    private final static List<String> BOOK_OPTIONS = new ArrayList<>();
    private final static List<String> GENRE_OPTIONS = new ArrayList<>();

    static {
        initialize();
    }

    private static void initialize() {
        CATEGORIES.add("1. Author");
        CATEGORIES.add("2. Book");
        CATEGORIES.add("3. Genre");
        CATEGORIES.add("0. Exit");

        AUTHOR_OPTIONS.add("1. Show list of authors");
        AUTHOR_OPTIONS.add("2. Get author by ID");
        AUTHOR_OPTIONS.add("3. Get author by name");
        AUTHOR_OPTIONS.add("4. Get all books by the author's ID");
        AUTHOR_OPTIONS.add("5. Create and save a new author");
        AUTHOR_OPTIONS.add("6. Update author's details");
        AUTHOR_OPTIONS.add("7. Delete author by ID");
        AUTHOR_OPTIONS.add("8. Delete author by name");
        AUTHOR_OPTIONS.add("0. Exit");

        BOOK_OPTIONS.add("1. Show list of books");
        BOOK_OPTIONS.add("2. Get book by ID");
        BOOK_OPTIONS.add("3. Get book by title");
        BOOK_OPTIONS.add("4. Create and save a new book");
        BOOK_OPTIONS.add("5. Update book details");
        BOOK_OPTIONS.add("6. Delete book by ID number");
        BOOK_OPTIONS.add("7. Delete book by title");
        BOOK_OPTIONS.add("0. Exit");

        GENRE_OPTIONS.add("1. Show list of genres");
        GENRE_OPTIONS.add("2. Get genre by ID number");
        GENRE_OPTIONS.add("3. Get genre by name");
        GENRE_OPTIONS.add("4. Get all books by the genre ID");
        GENRE_OPTIONS.add("5. Create and save a new genre");
        GENRE_OPTIONS.add("6. Update genre details");
        GENRE_OPTIONS.add("7. Delete genre by ID number");
        GENRE_OPTIONS.add("8. Delete genre by name");
        GENRE_OPTIONS.add("0. Exit");
    }

    static void showCategories() {
        CATEGORIES.forEach(System.out::println);
        System.out.println("Please select a category");
    }

    static void showOptions(String entity) {
        switch (entity) {
            case "author" -> AUTHOR_OPTIONS.forEach(System.out::println);
            case "book" -> BOOK_OPTIONS.forEach(System.out::println);
            case "genre" -> GENRE_OPTIONS.forEach(System.out::println);
        }
        System.out.println("Please select an option");
    }

    static void showTips(int lastOption) {
        System.out.printf("%d. Show options\n", lastOption);
        System.out.println("0. Exit");
    }
}
