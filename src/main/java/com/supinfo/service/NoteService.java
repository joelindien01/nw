package com.supinfo.service;

import com.supinfo.entitty.Note;

/**
 * Created by axelito on 21/08/16.
 */
public interface NoteService {

    void createNote(Note note);

    void deleteNote(Note note);

    void getNote(Long id);

    void updateNote(Note note);
}
