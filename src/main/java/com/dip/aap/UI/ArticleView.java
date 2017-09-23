package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.dip.aap.model.Article;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

/**
 * Created by andrz on 30/08/2017.
 */
public class ArticleView extends ArticleDesign implements View {

    public static final String VIEW_NAME = "article";

    ArticleController articleController;
    LoginController loginController;

    public ArticleView(){
        getControllersFromUI();
        backButton.addListener(event ->((AapUI)UI.getCurrent()).gotoLastListView());
    }

    public ArticleView(Article article){
        this();
        articleNameLabel.setValue(article.getName());
        if(article.getAuthor()!= null){
            buttonAuthorName.setCaption(article.getAuthor().getLogin());
            buttonAuthorName.addListener(event ->((AapUI)UI.getCurrent()).gotoProfileView());
            buttonAuthorName.setStyleName("link");


        } else{
            buttonAuthorName.setVisible(false);
        }
        contentTextArea.setValue(article.getContent());
    }

    private void getControllersFromUI(){
        articleController = ((AapUI)UI.getCurrent()).getArticleController();
        loginController = ((AapUI)UI.getCurrent()).getLoginController();
    }

}
