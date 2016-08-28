package com.supinfo.entitty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by axelito on 21/08/16.
 */
@Entity
public class Notebook {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Notebook() {

    }

    public Notebook(User user, String title) {
        this.user = user;
        this.title = title;
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

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

