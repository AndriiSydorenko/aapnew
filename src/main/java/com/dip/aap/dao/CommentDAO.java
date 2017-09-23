package com.dip.aap.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.stream.events.Comment;

/**
 * Created by andrz on 13/09/2017.
 */

@Component
public class CommentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Comment comment) {
        entityManager.persist(comment);
    }
}
