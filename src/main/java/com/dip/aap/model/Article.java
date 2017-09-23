package com.dip.aap.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="article")
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String content;

    private String category;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person author;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    @Override
    public String toString(){
        return "id="+id+", name="+name+", contetnt="+content+", category="+category;
    }


    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}