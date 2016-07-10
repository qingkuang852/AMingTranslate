package com.aming.translate.db;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by wenming on 2016/6/30.
 */
public class Favorite extends DataSupport{

    @Column(unique = true)
    private String query;

    private String phonetic;

    private String translation;

    private String webTranslation;

    private String explain;

    private String date;

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setWebTranslation(String webTranslation) {
        this.webTranslation = webTranslation;
    }

    public String getWebTranslation() {
        return webTranslation;
    }
}
