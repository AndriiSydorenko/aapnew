package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.dip.aap.model.Article;
import com.dip.aap.services.ArticleCategory;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
@Theme("valo")
public class AapUI extends UI {

    @Autowired
    LoginController loginController;

    @Autowired
    ArticleController articleController;

    private VerticalLayout layout;

    private Component loggingPanelView;
    private Component contentPanelView;

    private ArticleCategory currentCategory;

    @Override
    protected void init(VaadinRequest vaadinRequest){
        setupLayout();
        if(loginController.isLoggedIn()) {
            loggingPanelView = new LoggedInView();
        } else {
            loggingPanelView = new LoginView();
        }
        gotoListView();
        getPage().setTitle("AAP");
    }

    public void loadLoginPanel() {
        loggingPanelView = new LoginView();
        refresh();
    }

    public void loadLoggedInPanel() {
        loggingPanelView = new LoggedInView();
        refresh();
    }

    public void gotoNewArticleView(){
        contentPanelView = new ArticleEditView();
        refresh();
    }

    public void gotoArticleView(Article article){
        contentPanelView = new ArticleView(article);
        refresh();
    }

    public void gotoListView(){
        currentCategory = ArticleCategory.ALL;
        gotoLastListView();
    }

    public void gotoListView(ArticleCategory category){
        currentCategory = category;
        gotoLastListView();
    }

    public void gotoLastListView( ){
        contentPanelView = new ListView();
        if(currentCategory != null) {
            ((ListView)contentPanelView).fetchArticleCategory(currentCategory.categoryName.toLowerCase());
        } else {
            ((ListView)contentPanelView).fetchMyArticles();
        }
        refresh();
    }

    public void gotoUserRegisterView(){
        contentPanelView = new UserRegisterView();
        refresh();
    }

    public void gotoListMyView(){
        currentCategory = null;
        contentPanelView = new ListView();
        ((ListView)contentPanelView).fetchMyArticles();
        refresh();
    }

    public void gotoArticleEditView(Article article){
        contentPanelView = new ArticleEditView(article);
        refresh();
    }

    public void refresh(){
        setupLayout();
        addLoginForm();
        addContentForm();
    }

    public void gotoEditLoggedInView(){
    contentPanelView = new ProfileEditView();
    refresh();
    }

    public void gotoProfileView(){
        contentPanelView = new ProfileView();
        refresh();
    }

    private void addLoginForm(){
        layout.addComponent(loggingPanelView);
    }

    private void addContentForm(){
        layout.addComponent(contentPanelView);
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    public ArticleController getArticleController(){
        return articleController;
    }
    public LoginController getLoginController(){
        return loginController;
    }

    private void loadListView(){

    }
}
