package com.dip.aap.UI;

import com.dip.aap.controller.LoginController;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;

/**
 * Created by andrz on 22/08/2017.
 */
public class LoggedInView extends LoggedInDesign implements View {

    public static final String VIEW_NAME = "logout";
    LoginController loginController;

    public LoggedInView(){
        loginController = ((AapUI)UI.getCurrent()).getLoginController();
        if(loginController.getLoggedPerson()!=null) {
            nicknameLabel.setValue(loginController.getLoggedPerson().getLogin());
            buttonEdit.addListener(event -> ((AapUI)UI.getCurrent()).gotoEditLoggedInView());
        }
        logoutButton.addClickListener(event -> confirmLogout());
    }
    private void confirmLogout(){
        loginController.confirmLogout();
        ((AapUI) UI.getCurrent()).loadLoginPanel();
        ((AapUI) UI.getCurrent()).gotoListView();
    }
}
