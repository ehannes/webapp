package com.adde.webbapp.frontend.rest;

import com.adde.webbapp.model.entity.Article;
import com.adde.webbapp.model.entity.ArticleEdit;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Translation from XML to JSON
 * @author hannes
 */
@XmlRootElement(name = "article")
@XmlType(name = "ArticleType")
public class ArticleProxy {
        private Article article;

    protected ArticleProxy() { 
    }

    public ArticleProxy(Article project) {
        this.article = project;
    }
    
    @XmlElement(required = true)
    public String getTitle() {
        return article.getTitle();
    }

    @XmlElement(required = true)
    public List<ArticleEdit> getArticleEditions() {
        return article.getArticleEditions();
    }

    @XmlElement(required = true)
    public String getContent() {
        return article.getContent();
    }
    
    @XmlElement(required = true)
    public Long getId() {
        return article.getId();
    }
}