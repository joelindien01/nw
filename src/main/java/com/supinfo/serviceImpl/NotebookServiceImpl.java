package com.supinfo.serviceImpl;

import com.supinfo.entitty.Notebook;
import com.supinfo.repository.NotebookRepository;
import com.supinfo.service.NotebookService;
import com.supinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by axelito on 21/08/16.
 */
@Service
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    NotebookRepository notebookRepository;
    @Autowired
    UserService userService;

    @Override
    public Notebook saveNotebook(Notebook notebook) {
        if(notebook.getNotes()==null){
            notebook.setNotes(new ArrayList());
        }
        return notebookRepository.save(notebook);
    }

    @Override
    public Page<Notebook> findByUserMail(String mail,Pageable pageable) {
        return notebookRepository.findByUserMail(mail, pageable);
    }


    @Override
    public void deleteNotebook(Notebook notebook) {
        notebookRepository.delete(notebook);
    }

    @Override
    public Notebook getNotebook(Long id) {
        return notebookRepository.findOne(id);
    }

    @Override
    public Notebook findByUserMailAndId(String mail, Long id) {
        return notebookRepository.findByUserMailAndId(mail,id);
    }
}
