package com.jeremydyer.hdpmvn.core.old;

import java.util.*;

/**
 * Creates an InMemory index of HDP versions mapped to the appropriate Open Source project Maven Dependencies
 */
public class HDPVersionDependencyDB {

    private Map<String, ArrayList<MavenDependency>> hdpDepsMap = new HashMap<String, ArrayList<MavenDependency>>();

    /**
     * Builds the internal InMemory indexed database from the Projects that were scraped from the Hortonworks
     * public repository
     *
     * @param projects
     *  Web Scraped projects from the public Hortonworks repository
     */
    public void buildInMemoryDBFromWebScrapedProjects(ArrayList<Project> projects) {
        Set<String> hdpReleases = determineAllHDPReleaseVersions(projects);

        for (Project p : projects) {
            for (Dependency d : p.getDependencies()) {
                for (Version v : d.getVersions()) {
                    System.out.println(v);
                }
            }
        }
    }

    private Set<String> determineAllHDPReleaseVersions(ArrayList<Project> projects) {
        Set<String> hdpReleases = new HashSet<String>();

        for (Project p : projects) {
            for (Dependency d : p.getDependencies()) {
                for (Version v : d.getVersions()) {
                    hdpReleases.add(v.getHdpRelease());
                }
            }
        }

        return hdpReleases;
    }
}
