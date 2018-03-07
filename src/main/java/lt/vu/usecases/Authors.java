package lt.vu.usecases;

import lt.vu.entities.Author;
import lt.vu.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Authors {
    @Inject
    private AuthorsDAO authorsDAO;
    private List<Author> allAuthors;
    //Default initial values could be set here or inside the Author model - which is better?
    private Author newAuthor = new Author();

    @PostConstruct
    public void init() {
        this.loadAuthors();
    }

    @Transactional
    public String createNewAuthor() {
        authorsDAO.save(newAuthor);
        return "success"; // Why this should not be here? How could this be solved?
    }

    private void loadAuthors() {
        this.allAuthors = authorsDAO.loadAll();
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public Author getNewAuthor() {
        return newAuthor;
    }

    public void setNewAuthor(Author newAuthor) {
        this.newAuthor = newAuthor;
    }
}