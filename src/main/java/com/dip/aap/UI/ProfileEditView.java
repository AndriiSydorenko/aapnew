package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.vaadin.ui.UI;

/**
 * Created by andrz on 12/09/2017.
 */
public class ProfileEditView extends  ProfileEditDesign {

    ArticleController articleController;
    LoginController loginController;


    public ProfileEditView() {
        getControllersFromUI();
    }

    private void getControllersFromUI() {
        articleController = ((AapUI) UI.getCurrent()).getArticleController();
        loginController = ((AapUI) UI.getCurrent()).getLoginController();
    }
}
