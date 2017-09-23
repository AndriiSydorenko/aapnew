package com.dip.aap.services;

/**
 * Created by andrz on 27/08/2017.
 */
public enum ArticleCategory {
    ALL("All"),
    JAVA("Java"),
    PHP("PHP"),
    OTHER("Other");

    public String categoryName;

    private ArticleCategory(String category){
        this.categoryName = category;
    }
}
