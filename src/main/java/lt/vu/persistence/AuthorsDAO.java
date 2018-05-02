package lt.vu.persistence;

import lt.vu.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AuthorsDAO {
    @Inject
    private EntityManager em;

    @PersistenceUnit(name = "BooksPU")
    EntityManagerFactory emf;

    public List<Author> loadAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    public Author findOne(Integer id) {
        return em.find(Author.class, id);
    }

    public void save(Author author) {
        this.em.persist(author);
    }

    public void update(Author author) {
        em.merge(author);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void overwriteAfterConflict(Author author) {
        // construct a new EntityManager, because old persistence context was destroyed
        EntityManager em = emf.createEntityManager(SynchronizationType.SYNCHRONIZED);

        Author conflictingAuthor = em.find(Author.class, author.getId());
        author.setVersion(conflictingAuthor.getVersion());
        em.merge(author);
        em.flush();
    }
}
