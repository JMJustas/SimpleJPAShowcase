package lt.vu.persistence;

import lt.vu.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class BooksDAO {
    @Inject
    private EntityManager em;

    public void save(Book book) {
        this.em.persist(book);
    }
}
