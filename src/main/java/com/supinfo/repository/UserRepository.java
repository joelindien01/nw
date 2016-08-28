package com.supinfo.repository;

import com.supinfo.entitty.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by axelito on 21/08/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByMail(String mail);

}
