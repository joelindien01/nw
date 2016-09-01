package com.supinfo.controller;

import com.supinfo.entitty.Notebook;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by axelito on 21/08/16.
 */
@RestController
@RequestMapping("/{mail}/notebooks")
public class NotebookController {

    @Autowired
    NotebookService notebookService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createNotebook(@PathVariable String mail, @RequestBody Notebook input) {

        return this.userService
                .findByMail(mail)
                .map(user -> {
                    Notebook result = notebookService.saveNotebook(new Notebook(user,
                            input.getTitle()));

                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri());
                    return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                }).get();

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateNotebook(@PathVariable String mail,@RequestBody Notebook input) {

        return  userService.findByMail(mail)
                .map(user -> {
                    if(user!=null){
                        Notebook notebook = notebookService.findByUserMailAndId(mail,input.getId());
                        if(notebook != null){
                            Notebook result = notebookService.saveNotebook(input);
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.setLocation(ServletUriComponentsBuilder
                                    .fromCurrentRequest().path("/{id}")
                                    .buildAndExpand(result.getId()).toUri());

                            return new ResponseEntity<Notebook>(result,httpHeaders,HttpStatus.CREATED);
                        }
                    }
                    return new ResponseEntity<Notebook>(null,null,HttpStatus.NOT_FOUND);
                }).get();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Notebook> getNoteBooks(@PathVariable String mail, Pageable pageable) {

        return notebookService.findByUserMail(mail,pageable);

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getNotebook(@PathVariable String mail, @PathVariable Long id) {

        return  userService.findByMail(mail)
                .map(user -> {
                    Notebook result = notebookService.findByUserMailAndId(mail,id);
                    if(result != null){
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.setLocation(ServletUriComponentsBuilder
                                .fromCurrentRequest().path("/{id}")
                                .buildAndExpand(result.getId()).toUri());

                        return new ResponseEntity<Notebook>(result,httpHeaders,HttpStatus.OK);
                    }
                    return new ResponseEntity<>(null,null,HttpStatus.NOT_FOUND);
                }).get();

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNotebook(@PathVariable String mail, @PathVariable Long id, Pageable pageable) {

        return  userService.findByMail(mail)
                .map(user -> {
                    Notebook result = notebookService.findByUserMailAndId(mail,id);
                    if(result != null){
                        notebookService.deleteNotebook(result);
                        return new ResponseEntity<>(null,null,HttpStatus.OK);
                    }
                    return new ResponseEntity<Notebook>(null,null,HttpStatus.NOT_FOUND);
                }).get();

    }
}
