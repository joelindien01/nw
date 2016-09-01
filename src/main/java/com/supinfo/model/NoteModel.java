package com.supinfo.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by axelito on 01/09/16.
 */
public class NoteModel {

    private Long id;

    private String title;

    private String textualContent;

    private List<MultipartFile> files;

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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
