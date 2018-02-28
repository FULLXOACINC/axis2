package by.zhuk.aipos.service;


import by.zhuk.aipos.model.Article;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {



    private final static String PATH = "articles/";

    @Override
    public void saveArticle(Article article) {
        try {
            ArticleServiceStub stub = new ArticleServiceStub();
           // stub.g
            //ArticleServiceServiceStub.GetArticlesNameReturn res =stub.getArticlesName();

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        File file = new File(PATH + article.getName());
        file.mkdir();
        try (PrintWriter out = new PrintWriter(PATH + article.getName() + "/intro")) {
            out.println(article.getInto());
        } catch (FileNotFoundException e) {
            // throw new TException(e);
        }
        try (PrintWriter out = new PrintWriter(PATH + article.getName() + "/body")) {
            out.println(article.getBody());
        } catch (FileNotFoundException e) {
            // throw new TException(e);
        }
        try (PrintWriter out = new PrintWriter(PATH + article.getName() + "/exemples")) {
            out.println(article.getCodeExample());
        } catch (FileNotFoundException e) {
            // throw new TException(e);
        }
    }

    @Override
    public Article findArticle(String name) {
        Article article = new Article();
        String intro = null;
        String body = null;
        String codeExample = null;
        try {
            intro = readFile(PATH + name + "/intro");
            body = readFile(PATH + name + "/body");
            codeExample = readFile(PATH + name + "/exemples");
        } catch (IOException e) {
            // throw new TException(e);
        }
        article.setName(name);
        article.setInto(intro);
        article.setBody(body);
        article.setCodeExample(codeExample);

        return article;
    }

    @Override
    public void deleteArticle(String name) {
        File directory = new File(PATH + name);
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException e) {
            // throw new TException(e);
        }
    }

    @Override
    public List<String> getArticlesName() {
        File[] directories = new File(PATH).listFiles(File::isDirectory);
        List<String> result = new ArrayList<>();
        for (File directory : directories) {
            result.add(directory.getName());
        }
        Collections.sort(result);
        return result;
    }

    private String readFile(String file) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

        }
        return stringBuilder.toString();
    }
}
