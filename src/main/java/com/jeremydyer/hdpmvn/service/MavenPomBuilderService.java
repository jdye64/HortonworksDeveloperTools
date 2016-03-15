package com.jeremydyer.hdpmvn.service;

import com.jeremydyer.hdpmvn.core.Component;
import com.jeremydyer.hdpmvn.core.HDPRelease;
import com.jeremydyer.hdpmvn.core.Service;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Service for building Maven pom.xml files.
 */
public class MavenPomBuilderService {

    private HortonworksReleaseDBService DB = null;
    private String _mavenTemplate = null;

    XStream xStream = new XStream();

    public MavenPomBuilderService(HortonworksReleaseDBService DB) throws IOException {
        this.DB = DB;
        _mavenTemplate = FileUtils.readFileToString(new File("src/main/resources/pom_template.xml"));

        xStream.alias("dependency", Component.class);
        xStream.aliasField("groupId", Component.class, "mvnGroupId");
        xStream.aliasField("artifactId", Component.class, "mvnArtifactId");
        xStream.aliasField("version", Component.class, "mvnVersion");
    }


    public String buildPomForVersion(String version) {

        //Load the Maven Template
        String pom = _mavenTemplate;

        //Build the dependencies xml
        HDPRelease release = DB.hdpReleaseForVersion(version);
        String dependenciesXML = buildDependencyXML(release);
        System.out.println(dependenciesXML);

        pom = pom.replace("${{PROPERTIES}}", buildPropertiesXML(release));
        pom = pom.replace("${{DEPENDENCIES}}", dependenciesXML);

        return pom;
    }

    private String buildPropertiesXML(HDPRelease release) {
        StringBuilder properties = new StringBuilder();


        Set<String> vs = new HashSet<String>();
        for (Service s : release.getSubProjServices()) {
            for (Component c : s.getServiceComponents()) {
                vs.add(c.getMvnPropertiesVersion());
            }
        }

        return properties.toString();
    }

    private String buildDependencyXML(HDPRelease release) {

        StringBuilder dependencies = new StringBuilder();

        for (Service s : release.getSubProjServices()) {
            dependencies.append(xStream.toXML(s.getServiceComponents()).replace("<list>", "").replace("<list/>", "").replace("</list>", ""));
        }

        return dependencies.toString();
    }
}
