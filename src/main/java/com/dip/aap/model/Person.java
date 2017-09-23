package com.dip.aap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by andrz on 01/08/2017.
 */
@Entity
@Table(name="person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    private String password;
    private String login;
    private String name;
    private String discription;
    private String image_url;

    @OneToMany(mappedBy="author")
    private List<Article> articleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin(){
        return  login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword(){
        return  password;
    }
    public void setPassword(String password) {
        this.password =password;
    }

    public String getDiscription(){
        return  discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage_url(){
        return  image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString(){
        return "id="+id+", name="+name+", login="+login+", password="+password+", discription="+discription+", image_url="+image_url;
    }
}
