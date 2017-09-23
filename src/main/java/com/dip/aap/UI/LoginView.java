package com.dip.aap.UI;

import com.dip.aap.controller.LoginController;
import com.dip.aap.dao.PersonDAO;
import com.dip.aap.model.Person;
import com.dip.aap.services.LoginService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andrz on 19/08/2017.
 */
public class LoginView extends LoginDesign implements View {

    public static final String VIEW_NAME = "login";

    LoginController loginController;

    public LoginView(){
        loginController = ((AapUI)UI.getCurrent()).getLoginController();
        logInButton.addClickListener(event -> confirmLogin());
        signUpbutton.addListener(event ->((AapUI) UI.getCurrent()).gotoUserRegisterView());
        logInButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void confirmLogin(){
        if(loginController.confirmLogin(loginField.getValue(), passwordField.getValue())){
            ((AapUI) UI.getCurrent()).loadLoggedInPanel();
            ((AapUI) UI.getCurrent()).gotoListView();
        } else {
            loginField.setValue("");
            passwordField.setValue("");
            loginField.setRequiredIndicatorVisible(true);
            passwordField.setRequiredIndicatorVisible(true);
            Notification notification = new Notification("Error", "Incalid Credentials", Notification.Type.HUMANIZED_MESSAGE,true);
            notification.show(Page.getCurrent());
        }
    }
}
