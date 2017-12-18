package com.jeremydyer.hdpmvn;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jeremydyer.hdpmvn.core.Component;
import com.jeremydyer.hdpmvn.core.HDPRelease;
import com.jeremydyer.hdpmvn.core.Service;
import com.jeremydyer.hdpmvn.core.old.Dependency;
import com.jeremydyer.hdpmvn.core.old.Project;
import com.jeremydyer.hdpmvn.core.old.Version;
import com.jeremydyer.hdpmvn.service.HortonworksReleaseDBService;

/**
 * Created by jdyer on 2/26/16.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.run();
    }

    public Main() {

    }

    public void run() throws IOException {

        HortonworksReleaseDBService DB = new HortonworksReleaseDBService("/Users/jdyer/Development/github/HortonworksDeveloperTools/src/main/resources/hdp-snapshot");
        DB.load();

        //Connect JSoup to HDP repository and pull down the complete list of Projects
        Document doc = Jsoup.connect("http://repo.hortonworks.com/content/repositories/releases/org/apache/").get();
        Elements projs = doc.select("tr > td > a");
        ArrayList<Project> projects = getProjects(projs);
        System.out.println(projects.size() + " projects were found");

        String interestedVersion = "2.4.3.0-227";
        ArrayList<Service> services = new ArrayList<Service>();
        for (Project p : projects) {
            Service service = new Service();
            service.setServiceName(p.getName());

            ArrayList<Component> components = new ArrayList<Component>();
            for (Dependency d : p.getDependencies()) {
                Component component = new Component();
                for (Version v : d.getVersions()) {
                    if (v.getRawVersion().contains(interestedVersion)) {
                        component.setMvnGroupId(d.getGroupId());
                        component.setMvnArtifactId(d.getArtifactId());
                        component.setMvnVersion(v.getRawVersion());
                        //component.setMvnPropertiesVersion("<" + d.getArtifactId() + ".version>" + v.getRawVersion() + "</" + d.getArtifactId() + ".version>");
                    }
                }
                if (component.getMvnArtifactId() != null) {
                    components.add(component);
                } else {
                    //This component doesn't have a maven artifcat for this version of the HDP Release so do not add it!
                    System.out.println("No component");
                }
            }
            service.setServiceComponents(components);

            services.add(service);
        }

        System.out.println("Services: " + services);
        HDPRelease release = new HDPRelease("2.4.3", services);
        DB.addHDPRelease(release);
        DB.save();


//        XStream xStream = new XStream();
//        File f = new File("/Users/jdyer/Development/github/HortonworksDeveloperTools/src/main/resources/hdp-snapshot/hdp.xml");
//        if (!f.exists()) {
//            f.createNewFile();
//        }
//        xStream.toXML(projects, new FileOutputStream(f));
    }

    private ArrayList<Project> getProjects(Elements elements) {
        ArrayList<Project> projects = new ArrayList<Project>();
        for (Element ele : elements) {
            if (!ele.text().equalsIgnoreCase("Parent Directory")) {
                Project p = new Project(ele);
                projects.add(p);
            }
        }
        return projects;
    }
}
