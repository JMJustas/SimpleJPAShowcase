package lt.vu.usecases;

import lt.vu.entities.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

@Model
public class Authors {
    private List<Author> allAuthors;

    @PostConstruct
    public void init() {
        this.loadAuthors();
    }

    private void loadAuthors() {
        // TODO this is a mock implementation - later we will connect it to real data store
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author("Author1"));
        authors.add(new Author("Author2"));
        this.allAuthors = authors;
    }


    public List<Author> getAllAuthors() {
        return allAuthors;
    }
}