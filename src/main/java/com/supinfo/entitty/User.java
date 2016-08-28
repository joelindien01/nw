package com.supinfo.entitty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by axelito on 21/08/16.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userName;

    @Column(unique = true)
    private String mail;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    @JsonIgnore
    private List<Notebook> notebooks;

    public User() {

    }

    public User(String userName, String mail) {
        this.userName = userName;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }
}
