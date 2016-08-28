package com.supinfo.repository;

import com.supinfo.entitty.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by axelito on 21/08/16.
 */
@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {
}
