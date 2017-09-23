/**
 * Created by andrz on 6/25/2017.
 */
package com.dip.aap.dao;

import com.dip.aap.model.Article;
import com.dip.aap.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ArticleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Article article) {
        entityManager.persist(article);
    }

    @Transactional
    public Article findById(int articleId) {
        Query query = entityManager.createQuery("SELECT a FROM Article a WHERE a.id = :id ORDER BY a.id");
        query.setParameter("id", articleId);
        return (Article) query.getSingleResult();
    }



    @Transactional
    public void merge(Article article){
        entityManager.merge(article);
    }



    //    public void addArticle(com.dip.aap.model.Article article);
//    public void updateArticle(com.dip.aap.model.Article article);
    @Transactional
    public List<Article> findAll(){
        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY a.id");
        return query.getResultList();
    }

    @Transactional
    public List<Article> findAllWithCategory(String category){
        Query query = entityManager.createQuery("SELECT a FROM Article a where a.category = :category ORDER BY a.id");
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Transactional
    public List<Article> findAllForPerson(Person person){
        Query query = entityManager.createQuery("SELECT a FROM Article a where a.author = :person ORDER BY a.id");
        query.setParameter("person", person);
        return query.getResultList();
    }

    @Transactional
    public void DeleteArticle(int articleId) {
        Query query = entityManager.createQuery("DELETE FROM Article AS a WHERE a.id = :id");
        query.setParameter("id", articleId);
        query.executeUpdate();
    }

//    public com.dip.aap.model.Article getArticleById(int id);
//    public void removeArticle(int id);

}
