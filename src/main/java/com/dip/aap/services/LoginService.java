package com.dip.aap.services;

import com.dip.aap.model.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

/**
 * Created by andrz on 01/08/2017.
 */


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginService {

    public Person loggedPerson;

}
