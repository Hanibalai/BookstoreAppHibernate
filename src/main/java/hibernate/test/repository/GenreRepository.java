package hibernate.test.repository;

import hibernate.test.entity.Book;
import hibernate.test.entity.Genre;
import hibernate.test.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

public class GenreRepository {

    public List<Genre> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Genre order by id", Genre.class).list();
        }
    }

    public Genre getById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Genre.class, id);
        }
    }

    public Genre getByName(String genreName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Genre> query = session.createQuery("from Genre where name = :name", Genre.class);
            Genre genre = query.setParameter("name", genreName).uniqueResult();
            transaction.commit();
            return genre;
        }
    }

    public List<Book> getBooks(Genre genre) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.get(Genre.class, genre.getId()).getBooks();
        transaction.commit();
        closeSession();
        return books;
    }

    public Genre save(Genre genre) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(genre);
            transaction.commit();
            return genre;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Genre with the same name already exists in the database");
        }
    }

    public Genre update(Genre genre) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            genre = session.merge(genre);
            transaction.commit();
            return genre;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Genre with the same name already exists in the database");
        }
    }

    public void delete(Genre genre) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(genre);
            transaction.commit();
        }
    }

    private void closeSession() {
        HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().close();
    }
}
