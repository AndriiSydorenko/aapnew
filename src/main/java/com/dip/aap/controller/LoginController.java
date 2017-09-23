package com.dip.aap.controller;

import com.dip.aap.dao.PersonDAO;
import com.dip.aap.model.Article;
import com.dip.aap.model.Person;
import com.dip.aap.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by andrz on 01/08/2017.
 */

@Controller
//@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private LoginService loginService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String ShowLogin(){
//        return "loginUser";
//    }

    public boolean isLoggedIn() {
        return loginService.loggedPerson!=null;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/confirm")
    public boolean confirmLogin(String login_text,
                               String password_text) {
        try {
            Person person = personDAO.findByLogin(login_text).get(0);
            if (person.getPassword().equals(password_text)) {
                loginService.loggedPerson = person;
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public void savePerson(Person person){
           personDAO.persist(person);
    }

    public void confirmLogout(){
        loginService.loggedPerson = null;
    }

    public Person getLoggedPerson(){
        return loginService.loggedPerson;
    }

    public List<Person> getPersonByLogin(String login){
        return personDAO.findByLogin(login);
    }

    public void UpdatePerson(Person person){
            personDAO.merge(person);

    }

}
