package com.supinfo.serviceImpl;

import com.supinfo.entitty.Notebook;
import com.supinfo.entitty.User;
import com.supinfo.repository.UserRepository;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by axelito on 21/08/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotebookService notebookService;

    @Override
    public Optional<User> findByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        User result = userRepository.save(user);
        notebookService.saveNotebook(new Notebook(result, "default notebook"));
      return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {

    }


    @Override
    public User updateUser(User user) {

        return null;
    }
}
