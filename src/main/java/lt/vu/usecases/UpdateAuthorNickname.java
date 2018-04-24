package lt.vu.usecases;

import lt.vu.entities.Author;
import lt.vu.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
public class UpdateAuthorNickname implements Serializable {

    private Author author;

    @Inject AuthorsDAO authorsDAO;


    @PostConstruct
    private void init() {
        System.out.println("UpdateAuthorNickname INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    @Transactional
    public String updateAuthorNickname() {
        authorsDAO.update(this.author);
        return "/author.xhtml?faces-redirect=true&authorId=" + this.author.getId();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
