package hibernate.test.services;

import hibernate.test.entities.Book;
import hibernate.test.repository.GenreRepository;
import hibernate.test.entities.Genre;
import java.util.List;

public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService() {
        genreRepository = new GenreRepository();
    }

    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    public Genre getById(long id) {
        return genreRepository.getById(id);
    }

    public Genre getByName(String name) {
        return genreRepository.getByName(name);
    }

    public List<Book> getBooks(long id) {
        Genre genre = getById(id);
        if (genre != null) return genreRepository.getBooks(genre);
        return null;
    }

    public String save(String name) {
        try {
            Genre genre = genreRepository.save(new Genre(name));
            return "Genre has been been successfully saved to the database:\n" + genre;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public String update(long id, String name) {
        Genre genre = genreRepository.getById(id);
        if (genre == null) {
            return "Updating failed. Genre with the given ID number doesn't exist";
        } else {
            genre.setName(name);
            try {
                genre = genreRepository.update(genre);
                return "Genre details have been successfully updated:\n" + genre;
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        }
    }

    public String delete(long id) {
        Genre genre = genreRepository.getById(id);
        if (genre != null) {
            genreRepository.delete(genre);
            return "Genre has been successfully deleted from the database";
        } else return "Deletion failed. Genre with the given ID number doesn't exist";
    }

    public String delete(String name) {
        Genre genre = genreRepository.getByName(name);
        if (genre != null) {
            genreRepository.delete(genre);
            return "Genre has been successfully deleted from the database";
        } else return "Deletion failed. Genre with the same name doesn't exist";
    }
}
