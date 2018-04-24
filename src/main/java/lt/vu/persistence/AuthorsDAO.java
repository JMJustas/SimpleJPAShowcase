package lt.vu.persistence;

import lt.vu.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AuthorsDAO {
    @Inject
    private EntityManager em;

    public List<Author> loadAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public Author findOne(Integer id) {
        return em.find(Author.class, id);
    }

    public void save(Author author) {
        this.em.persist(author);
    }

    public Author update(Author author) {
        return em.merge(author);
    }
}
