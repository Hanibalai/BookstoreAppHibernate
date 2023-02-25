package hibernate.test.repository;

import hibernate.test.entities.Author;
import hibernate.test.entities.Book;
import hibernate.test.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import java.util.List;

public class AuthorRepository {

    public List<Author> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Author order by id", Author.class).list();
        }
    }

    public Author getById(long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        }
    }

    public Author getByName(String name) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Author> query = session.createQuery("from Author where name = :name", Author.class);
            Author author = query.setParameter("name", name).uniqueResult();
            transaction.commit();
            return author;
        }
    }

    public List<Book> getBooks(Author author) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books =  session.get(Author.class, author.getId()).getBooks();
        transaction.commit();
        closeSession();
        return books;
    }

    public Author save(Author author) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
            return author;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Author with the same name already exists in the database");
        }
    }

    public Author update(Author author) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            author = session.merge(author);
            transaction.commit();
            return author;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Author with the same name already exists in the database");
        }
    }

    public void delete(Author author) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(author);
            transaction.commit();
        }
    }

    private void closeSession() {
        HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().close();
    }
}
