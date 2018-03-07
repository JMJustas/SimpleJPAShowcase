package lt.vu.entities;

import java.io.Serializable;

public class Author implements Serializable {

    public Author() {}
    public Author(String name) {
       this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
