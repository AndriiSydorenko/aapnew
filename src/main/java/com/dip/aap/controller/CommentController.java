package com.dip.aap.controller;

import com.dip.aap.dao.ArticleDAO;
import com.dip.aap.dao.CommentDAO;
import com.dip.aap.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.stream.events.Comment;

/**
 * Created by andrz on 13/09/2017.
 */

@Controller
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    private CommentDAO commentDAO;



}
