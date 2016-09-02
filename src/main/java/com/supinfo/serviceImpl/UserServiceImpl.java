package com.supinfo.serviceImpl;

import com.supinfo.NeverWriteApplication;
import com.supinfo.config.ApplicationProperties;
import com.supinfo.entitty.Notebook;
import com.supinfo.entitty.User;
import com.supinfo.repository.UserRepository;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    ApplicationProperties applicationProperties;

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
        notebookService.saveNotebook(new Notebook(result, applicationProperties.getDefaultNotebookName()));
        createUserSpace(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        try {
            deleteUserSpace(user);
            userRepository.delete(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void createUserSpace(User user) {
        String userSpacePath = NeverWriteApplication.getProjectFolder()+File.separator+applicationProperties.getUserSpaceRoot()+File.separator+user.getId();

        File dir = new File(userSpacePath);
        dir.mkdir();

    }

    @Override
    public void deleteUserSpace(User user) throws IOException {

        String userSpacePath = NeverWriteApplication.getProjectFolder()+File.separator+applicationProperties.getUserSpaceRoot()+File.separator+user.getId();

        FileUtils.deleteDirectory(new File(userSpacePath));

    }
}
