package hibernate.test.services;

import hibernate.test.entities.Author;
import hibernate.test.entities.Genre;
import hibernate.test.repository.AuthorRepository;
import hibernate.test.repository.BookRepository;
import hibernate.test.entities.Book;
import hibernate.test.repository.GenreRepository;
import java.math.BigDecimal;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService() {
        bookRepository = new BookRepository();
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    public List<Book> getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    public String save(String title, BigDecimal price, int quantity, String authorName, String genreName) {
        AuthorRepository authorRepository = new AuthorRepository();
        GenreRepository genreRepository = new GenreRepository();
        Author author = authorRepository.getByName(authorName);
        Genre genre = genreRepository.getByName(genreName);
        if (author == null) {
            author = new Author(authorName);
            authorRepository.save(author);
        }
        if (genre == null) {
            genre = new Genre(genreName);
            genreRepository.save(genre);
        }
        Book book = new Book(title, price, quantity);
        book.setAuthor(author);
        book.setGenre(genre);
        try {
            bookRepository.save(book);
            return "Book has been been successfully saved to the database";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public String update(long id, String title, BigDecimal price, int quantity, String authorName, String genreName) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            if (!title.isEmpty()) book.setTitle(title);
            if (price.intValue() != -1) book.setPrice(price);
            if (quantity != -1) book.setQuantity(quantity);
            if (!authorName.isEmpty() && !authorName.equals(book.getAuthor().getName())) {
                Author author = new AuthorRepository().getByName(authorName);
                if (author == null) book.setAuthor(new Author(authorName));
                else book.setAuthor(author);
            }
            if (!genreName.isEmpty() && !genreName.equals(book.getGenre().getName())) {
                Genre genre = new GenreRepository().getByName(genreName);
                if (genre == null) book.setGenre(new Genre(genreName));
                else book.setGenre(genre);
            }
            try {
                bookRepository.update(book);
                return "Book details have been successfully updated";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else return "Updating failed. Book with the given ID number doesn't exist";
    }

    public String deleteById(long id) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            bookRepository.delete(book);
            return "Book has been successfully deleted from the database";
        } else return "Deletion failed. Book with the given ID number doesn't exist";
    }

    public String deleteByTitle(String title) {
        List<Book> books = bookRepository.getByTitle(title);
        if (books != null && !books.isEmpty()) {
            bookRepository.deleteByTitle(books);
            return "Book has been successfully deleted from the database";
        } else return "Deletion failed. Book with the given title doesn't exist";
    }
}
