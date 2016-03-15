package com.jeremydyer.hdpmvn.core;

import java.util.List;

/**
 * Created by jdyer on 3/4/16.
 */
public class HDPRelease
    extends HortonworksRelease {

    private String hdpVersion;                  //Official Hortonworks HDP release version. Ex: 2.2.0, 2.3.4, etc
    private List<Service> subProjServices;     //List of Apache Projects that are available in this release as services

    public HDPRelease(String hdpVersion) {
        this.hdpVersion = hdpVersion;
    }

    public HDPRelease(String hdpVersion, List<Service> subProjServices) {
        this.hdpVersion = hdpVersion;
        this.subProjServices = subProjServices;
    }

    public String getHdpVersion() {
        return hdpVersion;
    }

    public void setHdpVersion(String hdpVersion) {
        this.hdpVersion = hdpVersion;
    }

    public List<Service> getSubProjServices() {
        return subProjServices;
    }

    public void setSubProjServices(List<Service> subProjServices) {
        this.subProjServices = subProjServices;
    }
}
