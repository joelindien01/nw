package com.supinfo.serviceImpl;

import com.supinfo.entitty.Note;
import com.supinfo.repository.NoteRepository;
import com.supinfo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by axelito on 01/09/16.
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note note) {

        noteRepository.delete(note);
    }

    @Override
    public Note getNote(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }
}
