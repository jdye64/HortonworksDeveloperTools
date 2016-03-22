package com.jeremydyer.hdpmvn.core.old;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Apache Project present in the HDP public reposi
 */
public class Project {

    private String name;
    private String baseURL;
    private ArrayList<Dependency> dependencies;

    public Project(Element element) {
        this.name = element.html().replace("/", "");
        this.baseURL = element.attr("href");
        this.dependencies = getDependencies(element);
    }

    private ArrayList<Dependency> getDependencies(Element element) {
        ArrayList<Dependency> deps = new ArrayList<Dependency>();
        try {
            Document doc = Jsoup.connect(this.baseURL).get();
            Elements eles = doc.select("tr > td > a");
            for (Element ele : eles) {
                if (!ele.text().equalsIgnoreCase("Parent Directory")) {
                    Dependency dep = new Dependency(ele);
                    deps.add(dep);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deps;
    }

    public ArrayList<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ArrayList<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
