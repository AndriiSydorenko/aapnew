package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.dip.aap.model.Article;
import com.dip.aap.services.ArticleCategory;
import com.vaadin.navigator.View;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by andrz on 05/09/2017.
 */
public class ArticleEditView extends ArticleEditDesign implements View {

//    public static final String VIEW_NAME = "articleEdit";

    ArticleController articleController;
    LoginController loginController;
    Article currentArticle = new Article();

    public ArticleEditView(){
        getControllersFromUI();
        List<String> articleCategoryList = new ArrayList<>();
        Arrays.stream(ArticleCategory.values()).
                filter(i -> !i.categoryName.equalsIgnoreCase("all")).
                forEach(i -> articleCategoryList.add(i.categoryName));
        artcileCategoryCombobox.setItems(articleCategoryList);
        artcileCategoryCombobox.setSelectedItem(ArticleCategory.OTHER.categoryName);
        backButton.addListener(event ->((AapUI) UI.getCurrent()).gotoListMyView());
        saveButton.addListener(event ->saveArticle());
    }

    public ArticleEditView(Article article){
        this();
        currentArticle = article;
        atricleName.setValue(currentArticle.getName());
        artcileCategoryCombobox.setSelectedItem(currentArticle.getCategory());
        textField.setValue(currentArticle.getContent());
    }

    private void getControllersFromUI(){
        articleController = ((AapUI)UI.getCurrent()).getArticleController();
        loginController = ((AapUI)UI.getCurrent()).getLoginController();
    }

    private void saveArticle(){
        currentArticle.setName(atricleName.getValue());
        currentArticle.setContent(textField.getValue());
        currentArticle.setAuthor(loginController.getLoggedPerson());
        currentArticle.setCategory(artcileCategoryCombobox.getValue());
        articleController.saveArticle(currentArticle);
        ((AapUI)UI.getCurrent()).gotoListMyView();
    }




}
