package lt.vu.persistence;

import lt.vu.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class AuthorsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Author> loadAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
