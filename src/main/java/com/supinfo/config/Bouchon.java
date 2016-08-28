package com.supinfo.config;

import com.supinfo.entitty.Notebook;
import com.supinfo.entitty.User;
import com.supinfo.repository.NotebookRepository;
import com.supinfo.repository.UserRepository;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by axelito on 28/08/16.
 */
@Configuration
public class Bouchon {

    private int i =0;

    @Bean
    CommandLineRunner init(UserService userService,
                           NotebookService notebookService) {
        return (evt) -> Arrays.asList(
                "axel,alex".split(","))
                .forEach(
                        a -> {

                            User user = userService.createUser(new User(a,
                                    "mail_"+ i++ +"@mail.com"));
                        });
    }
}
