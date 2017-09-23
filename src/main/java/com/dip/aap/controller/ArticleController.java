package com.dip.aap.controller;

import com.dip.aap.dao.ArticleDAO;
import com.dip.aap.model.Article;
import com.dip.aap.model.Person;
import com.dip.aap.services.ArticleCategory;
import com.dip.aap.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by andrz on 6/25/2017.
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleDAO articleDao;

    @Autowired
    private LoginService loginService;

    List<Article> articlesLocal;
    int rowsNumShort = 3;
    int rowsNumLong = 15;

    public List<Article> findAll() {
        articlesLocal = articleDao.findAll();
        return articlesLocal;
    }

    public List<Article> findAllCategory(String category) {
        if(category.equals(ArticleCategory.ALL.categoryName.toLowerCase())){
            return articleDao.findAll();
        }
        return articleDao.findAllWithCategory(category);
    }

    public List<Article> findAllForPerson(Person person) {
        return articleDao.findAllForPerson(person);
    }


    public void deleteArticle(int articleId) {
        articleDao.DeleteArticle(articleId);
        articlesLocal = findAll();
    }


    public void saveArticle(Article article) {
        if(article.getId()!=0) {
            articleDao.merge(article);
        } else {
            articleDao.persist(article);
        }
        articlesLocal = findAll();
    }

    //Common

//    @RequestMapping(method = RequestMethod.GET, value = "/all")
//    public String getAllArticles(Model model) {
//        getArticlesCommon(model);
//        model.addAttribute("articles", articlesLocal);
//        return "articlesListView";
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/java")
//    public String getAllJavaArticles(Model model) {
//        getArticlesCommon(model);
//        model.addAttribute("articles", findAllCategory("java"));
//        return "articlesListView";
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/new")
//    public String getAddAtricle() {
//        return "addArticle";
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
//    public String editArticle(@PathVariable("id") int articleId, Model model) {
//        Article editedArticle = articleDao.findById(articleId);
//        model.addAttribute("article", editedArticle);
//        return "editArticle";
//    }


//    @RequestMapping(method = RequestMethod.GET, value = "/new/add")
//    public String AddNewArticle(@RequestParam String name_text,
//                                @RequestParam String content_text,
//                                @RequestParam String category_text,
//                                Model model) {
//        Article article = new Article();
//        article.setName(name_text);
//        article.setContent(content_text);
//        article.setCategory(category_text);
//        articleDao.persist(article);
//        articlesLocal = findAll();
//        return "redirect:/articles/all";
//    }



//    private void getArticlesCommon(Model model)
//    {
//        model.addAttribute("isRendered", (loginService.loggedPerson == null));
//        if (articlesLocal == null) {
//            articlesLocal = findAll();
//        }
//    }
}
