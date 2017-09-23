package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.dip.aap.model.Article;
import com.dip.aap.services.ArticleCategory;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import java.util.List;

/**
 * Created by andrz on 19/08/2017.
 */
public class ListView extends ListDesign implements View {

    public static final String VIEW_NAME = "main";

    ArticleController articleController;
    LoginController loginController;


    public ListView() {
        getControllersFromUI();
        for(ArticleCategory category: ArticleCategory.values()){
            Button categoryButton = new Button();
            categoryButton.setCaption(category.categoryName);
            categoryButton.addListener(event -> ((AapUI)UI.getCurrent()).gotoListView(category));
            categoryButtonsPanel.addComponent(categoryButton);
        }
        if(loginController.isLoggedIn()) {
            Button myCategoryButton = new Button();
            myCategoryButton.setCaption("My");
            myCategoryButton.addListener(event -> ((AapUI)UI.getCurrent()).gotoListMyView());
            categoryButtonsPanel.addComponent(myCategoryButton);
        }
        if(!loginController.isLoggedIn()){
            addNewButton.setVisible(false);
        }
        addNewButton.addListener(event -> ((AapUI)UI.getCurrent()).gotoNewArticleView());

    }

    private void getControllersFromUI(){
        articleController = ((AapUI)UI.getCurrent()).getArticleController();
        loginController = ((AapUI)UI.getCurrent()).getLoginController();
    }

    public void fetchArticleCategory(String category){
        List<Article> articleList = articleController.findAllCategory(category);
        articleListLayout.removeAllComponents();
        for(Article article : articleList ){
            ArticleInListDesign tempLayout = new ArticleInListDesign();
            tempLayout.articleLink.setCaption(article.getName());
            tempLayout.articleLink.addListener(event -> viewArticle(article));
            tempLayout.articleLink.setStyleName("link");
            tempLayout.categoryLabel.setValue(article.getCategory());
            tempLayout.deleteButton.setVisible(false);
            tempLayout.editButton.setVisible(false);
            articleListLayout.addComponent(tempLayout);
        }
    }

    public void fetchMyArticles(){
        List<Article> articleList = articleController.findAllForPerson(loginController.getLoggedPerson());
        articleListLayout.removeAllComponents();
        for(Article article : articleList ){
            ArticleInListDesign tempLayout = new ArticleInListDesign();
            tempLayout.articleLink.setCaption(article.getName());
            tempLayout.articleLink.setStyleName("link");
            tempLayout.articleLink.addListener(event -> viewArticle(article));
            tempLayout.categoryLabel.setValue(article.getCategory());
            tempLayout.deleteButton.addClickListener(event -> deleteArticle(article.getId()));
            tempLayout.editButton.addClickListener(event ->((AapUI)UI.getCurrent()).gotoArticleEditView(article));
            articleListLayout.addComponent(tempLayout);
        }
    }

    private void viewArticle(Article article){
        ((AapUI)UI.getCurrent()).gotoArticleView(article);
    }

    private void deleteArticle(int articleId){
        articleController.deleteArticle(articleId);
        ((AapUI)UI.getCurrent()).gotoListView();
    }

}