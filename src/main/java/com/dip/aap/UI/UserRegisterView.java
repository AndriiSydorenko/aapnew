package com.dip.aap.UI;

import com.dip.aap.controller.ArticleController;
import com.dip.aap.controller.LoginController;
import com.dip.aap.model.Person;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * Created by andrz on 10/09/2017.
 */
public class UserRegisterView extends UserRegisterDesign {


    ArticleController articleController;
    LoginController loginController;
    Person currentPerson = new Person();

    public UserRegisterView() {
        getControllersFromUI();
        signUpbutton.addListener(event -> signUpPerson());
        backbutton.addListener(event -> ((AapUI) UI.getCurrent()).gotoListView());
    }

    private void getControllersFromUI() {
        articleController = ((AapUI) UI.getCurrent()).getArticleController();
        loginController = ((AapUI) UI.getCurrent()).getLoginController();
    }

    public void signUpPerson() {
        if (validateInput()) {
            currentPerson.setName(nameField.getValue());
            currentPerson.setLogin(loginField.getValue());
            currentPerson.setPassword(passwordField.getValue());
            loginController.savePerson(currentPerson);
            ((AapUI) UI.getCurrent()).gotoListView();
        }
    }

    private boolean validateInput() {
        if (loginField.getValue().isEmpty() || passwordField.getValue().isEmpty()) {
            Notification notification = new Notification("Error", "Fill in all rquired fields", Notification.Type.HUMANIZED_MESSAGE, true);
            notification.show(Page.getCurrent());
            return false;
        }
        if (!loginController.getPersonByLogin(loginField.getValue()).isEmpty()) {
            Notification notification = new Notification("Error", "User " + loginField.getValue() + " already exists", Notification.Type.HUMANIZED_MESSAGE, true);
            notification.show(Page.getCurrent());
            loginField.setValue("");
            passwordField.setValue("");
            repeatPasswordField.setValue("");
            return false;
        }
        if (!passwordField.getValue().equals(repeatPasswordField.getValue())) {
            Notification notification = new Notification("Error", "Passwords don't match", Notification.Type.HUMANIZED_MESSAGE, true);
            notification.show(Page.getCurrent());
            passwordField.setValue("");
            repeatPasswordField.setValue("");
            return false;
        }
        return true;
    }

}
