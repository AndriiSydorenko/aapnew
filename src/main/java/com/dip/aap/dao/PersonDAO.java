package com.dip.aap.dao;

import com.dip.aap.model.Article;
import com.dip.aap.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by andrz on 01/08/2017.
 */
@Component
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void merge(Person person){
        entityManager.merge(person);
    }

    @Transactional
    public List<Person> findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.login = :login");
        query.setParameter("login", login);
        return query.getResultList();
    }

    @Transactional
    public Person findById(int personId) {
        Query query = entityManager.createQuery("SELECT p FROM Person p WHERE p.id = :id ORDER BY p.id");
        query.setParameter("id", personId);
        return (Person) query.getSingleResult();
    }


}
