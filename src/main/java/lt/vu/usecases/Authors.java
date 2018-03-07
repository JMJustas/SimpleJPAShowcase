package lt.vu.usecases;

import lt.vu.entities.Author;
import lt.vu.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Authors {
    @Inject
    private AuthorsDAO authorsDAO;
    private List<Author> allAuthors;

    @PostConstruct
    public void init() {
        this.loadAuthors();
    }

    private void loadAuthors() {
        this.allAuthors = authorsDAO.loadAll();
    }


    public List<Author> getAllAuthors() {
        return allAuthors;
    }
}