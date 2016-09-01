package com.supinfo.entitty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by axelito on 21/08/16.
 */
@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String textualContent;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;

    @ManyToOne
    @JsonIgnore
    private Notebook notebook;

    public Note() {
    }

    public Note(String title, String textualContent, List<Image> images, Notebook notebook) {
        this.title = title;
        this.textualContent = textualContent;
        this.images = images;
        this.notebook = notebook;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextualContent() {
        return textualContent;
    }

    public void setTextualContent(String textualContent) {
        this.textualContent = textualContent;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
