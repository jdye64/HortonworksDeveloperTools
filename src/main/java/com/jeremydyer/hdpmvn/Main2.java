package com.jeremydyer.hdpmvn;

import com.jeremydyer.hdpmvn.service.HortonworksReleaseDBService;
import com.jeremydyer.hdpmvn.service.MavenPomBuilderService;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jdyer on 3/4/16.
 */
public class Main2 {

    public static void main(String[] args) throws IOException {
        HortonworksReleaseDBService db = new HortonworksReleaseDBService();
        db.load();
        System.out.println(db);

        MavenPomBuilderService mvn = new MavenPomBuilderService(db);
        String pom = mvn.buildPomForVersion("2.3.4");

        IOUtils.write(pom.getBytes(), new FileOutputStream(new File("src/main/resources/sample-output/2.3.4-pom.xml")));

//        XStream xStream = new XStream();
//        xStream.toXML(pom, new FileOutputStream(new File("src/main/resources/sample-output/2.3.4-pom.xml")));

    }
}
