package com.supinfo.service;

import com.supinfo.entitty.User;

import java.util.Optional;

/**
 * Created by axelito on 21/08/16.
 */
public interface UserService {

    Optional<User> findByMail(String mail);

    User findByUserId(Long id);

    User createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
