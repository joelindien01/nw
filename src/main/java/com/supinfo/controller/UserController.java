package com.supinfo.controller;

import com.supinfo.entitty.Notebook;
import com.supinfo.entitty.User;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by axelito on 21/08/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NotebookService notebookService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User input) {

        if(userService.findByMail(input.getMail()) ==null){

            User result = userService.createUser(input);


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getId()).toUri());
            return new ResponseEntity<User>(null, httpHeaders, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);

    }

}
