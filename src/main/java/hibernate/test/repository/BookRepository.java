package hibernate.test.repository;

import hibernate.test.entities.Book;
import hibernate.test.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import java.util.List;

public class BookRepository {

    public List<Book> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public Book getById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public List<Book> getByTitle(String title) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Book> query = session.createQuery("from Book where title = :title", Book.class);
            List<Book> books = query.setParameter("title", title).list();
            transaction.commit();
            return books;
        }
    }

    public Book save(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Book saving error " + e.getMessage());
        }
    }

    public Book update(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            book = session.merge(book);
            transaction.commit();
            return book;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Book updating error " + e.getMessage());
        }
    }

    public void delete(Book book) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
        }
    }

    public void deleteByTitle(List<Book> books) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            books.forEach(session::remove);
            transaction.commit();
        }
    }
}
