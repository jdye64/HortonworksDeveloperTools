package com.jeremydyer.hdpmvn.core.old;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jdyer on 2/26/16.
 */
public class Dependency {

    private String repoURL;
    private String groupId;
    private String artifactId;
    private ArrayList<Version> versions;

    private static final String APACHE_BASE = "http://repo.hortonworks.com/content/repositories/releases/";

    public Dependency(Element element) {
        this.repoURL = element.attr("href");
        this.artifactId = element.html().replace("/", "");
        this.groupId = element.attr("href").replace(APACHE_BASE, "").replace("/" + this.artifactId + "/", "").replace("/", ".");
        this.versions = determineVersions(element);
    }

    public ArrayList<Version> determineVersions(Element element) {
        ArrayList<Version> vers = new ArrayList<Version>();
        try {
            Document doc = Jsoup.connect(this.repoURL).get();
            Elements eles = doc.select("tr > td > a");
            for (Element ele : eles) {
                if (!ele.text().equalsIgnoreCase("Parent Directory")
                        && !ele.text().contains(".xml")) {
                    Version ver = new Version(ele.text());
                    vers.add(ver);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }
}
