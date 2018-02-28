package by.zhuk.aipos.service;


import by.zhuk.aipos.model.Article;


import java.util.List;

public interface ArticleService {

    void saveArticle(Article article);

    void deleteArticle(String name);

    Article findArticle(String name);

    List<String> getArticlesName();

}