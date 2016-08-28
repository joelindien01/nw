package com.supinfo.service;

import com.supinfo.entitty.Notebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by axelito on 21/08/16.
 */
public interface NotebookService {

    Notebook saveNotebook(Notebook notebook);

    Page<Notebook> findByUserMail(String mail,Pageable pageable);

    void deleteNotebook(Notebook notebook);

    Notebook getNotebook(Long id);

    Notebook findByUserMailAndId(String mail, Long id);
}
