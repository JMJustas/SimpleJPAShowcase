package lt.vu.usecases;

import lt.vu.entities.Author;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.AuthorsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
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
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer authorId = Integer.parseInt(requestParameters.get("authorId"));
        this.author = authorsDAO.findOne(authorId);
    }

    @Transactional
    @LoggedInvocation
    public String updateAuthorNickname() {
        try{
            authorsDAO.update(this.author);
        } catch (OptimisticLockException e) {
            return "/author.xhtml?faces-redirect=true&authorId=" + this.author.getId() + "&error=optimistic-lock-exception";
        }
        return "/index.xhtml";
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
