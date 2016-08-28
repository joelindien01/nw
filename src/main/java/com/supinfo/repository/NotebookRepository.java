package com.supinfo.repository;

import com.supinfo.entitty.Notebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by axelito on 21/08/16.
 */
@Repository
public interface NotebookRepository extends PagingAndSortingRepository<Notebook, Long> {

    Page<Notebook> findByUserMail(String mail,Pageable pageable);

    Notebook findByUserMailAndId(String mail, Long id);
}
