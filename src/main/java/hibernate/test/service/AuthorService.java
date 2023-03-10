package hibernate.test.service;

import hibernate.test.repository.AuthorRepository;
import hibernate.test.entity.Author;
import hibernate.test.entity.Book;

import java.util.List;

public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService() {
        authorRepository = new AuthorRepository();
    }

    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    public Author getById(long id) {
        return authorRepository.getById(id);
    }

    public Author getByName(String name) {
        return authorRepository.getByName(name);
    }

    public List<Book> getBooks(long id) {
        Author author = authorRepository.getById(id);
        if (author != null) return authorRepository.getBooks(author);
        return null;
    }

    public String save(String name) {
        try {
            Author author = authorRepository.save(new Author(name));
            return "Author has been been successfully saved to the database:\n" + author;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public String update(long id, String name) {
        Author author = authorRepository.getById(id);
        if (author == null) {
            return "Updating failed. Author with the given ID number doesn't exist";
        } else {
            author.setName(name);
            try {
                author = authorRepository.update(author);
                return "Author's details have been successfully updated:\n" + author;
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        }
    }

    public String delete(long id) {
        Author author = authorRepository.getById(id);
        if (author != null) {
            authorRepository.delete(author);
            return "Author has been successfully deleted from the database";
        } else return "Deletion error. Author with the given ID number doesn't exist";
    }

    public String delete(String name) {
        Author author = authorRepository.getByName(name);
        if (author != null) {
            authorRepository.delete(author);
            return "Author has been successfully deleted from the database";
        } else return "Deletion error. Author with the same name doesn't exist";
    }
}
