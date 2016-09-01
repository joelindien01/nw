package com.supinfo.service;

import com.supinfo.entitty.Note;

/**
 * Created by axelito on 21/08/16.
 */
public interface NoteService {

    Note createNote(Note note);

    void deleteNote(Note note);

    Note getNote(Long id);

    Note updateNote(Note note);
}
