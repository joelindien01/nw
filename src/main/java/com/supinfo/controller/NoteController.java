package com.supinfo.controller;

import com.supinfo.NeverWriteApplication;
import com.supinfo.entitty.Image;
import com.supinfo.entitty.Note;
import com.supinfo.entitty.Notebook;
import com.supinfo.entitty.User;
import com.supinfo.model.NoteModel;
import com.supinfo.service.NoteService;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by axelito on 21/08/16.
 */
@RestController
@RequestMapping("/{mail}/notebooks/{notebookId}/notes")
public class NoteController {

    @Autowired
    NotebookService notebookService;
    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> createNote(@PathVariable String mail,
                                        @PathVariable long notebookId,
                                        @RequestBody NoteModel input){
        return userService.findByMail(mail).
                map(user -> {
                    Notebook notebook = notebookService.findByUserMailAndId(mail, notebookId);
                    if (notebook != null) {

                        List<String> errorList = new ArrayList<String>();
                        List<Image> images  = new ArrayList<Image>();
                        for (MultipartFile file : input.getFiles()) {
                            String name = file.getName();

                            if (!file.isEmpty()) {
                                if (name.contains("/")) {

                                    errorList.add("Folder separators not allowed..file named " + name + " will be not add");
                                } else {

                                    String projectFolder = NeverWriteApplication.getProjectFolder();
                                    try {
                                        String original_name = file.getOriginalFilename()+"_"+(new Date()).getTime();
                                        images.add(new Image(original_name));
                                        file.transferTo(new File(projectFolder + NeverWriteApplication.ROOT + "/" + original_name));

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }

                        }
                        Note note = new Note(input.getTitle(),input.getTextualContent(),images,notebook);
                        Note result = noteService.createNote(note);

                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.setLocation(ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(result.getId()).toUri());
                        return new ResponseEntity<>(result, null, HttpStatus.CREATED);

                    }
                    return new ResponseEntity<Notebook>(null, null, HttpStatus.NOT_FOUND);

                }).get();
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    public ResponseEntity<?> getNote(@PathVariable String mail,@PathVariable long noteId,@PathVariable long notebookId){
        Notebook notebook = notebookService.findByUserMailAndId(mail,notebookId);
        if(notebook!=null){
            Note result = noteService.getNote(noteId);
            if(result!=null){
                return new ResponseEntity<>(result,null, HttpStatus.FOUND);
            }else {
                return new ResponseEntity<>(result,null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<Notebook>(null,null,HttpStatus.BAD_REQUEST);

    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getNotes(@PathVariable String mail, @PathVariable long notebookId){
        Notebook notebook = notebookService.findByUserMailAndId(mail,notebookId);

        if(notebook!=null){
            return new ResponseEntity<>(notebook.getNotes(),null, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null,null, HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/{noteId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNote(@PathVariable String mail,
                                        @PathVariable long noteId,
                                        @PathVariable long notebookId,
                                        @RequestBody Note input){

        Notebook notebook = notebookService.findByUserMailAndId(mail,notebookId);

        if(notebook!=null){
            Note note = noteService.getNote(noteId);
            if(note!=null){
                Note result = noteService.updateNote(input);
                return new ResponseEntity<>(result,null, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null,null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null,null, HttpStatus.BAD_REQUEST);

    }
    @RequestMapping(value = "/{noteId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNote(@PathVariable String mail,
                                        @PathVariable long noteId,
                                        @PathVariable long notebookId){
        Notebook notebook = notebookService.findByUserMailAndId(mail,notebookId);
        if(notebook!=null){
            Note note = noteService.getNote(noteId);
            if(note!=null){
                noteService.deleteNote(note);
                return new ResponseEntity<>(null,null, HttpStatus.OK);
            }
            return new ResponseEntity<>(null,null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null,null, HttpStatus.BAD_REQUEST);

    }


}
