package com.jeremydyer.hdpmvn.core;

import java.util.List;

/**
 * Represents a single Apache or Hortonworks Service that is available as part of a larger Hortonworks release.
 * Ex: HDFS, Hive, Pig, etc
 */
public class Service {

    private String serviceName;                     //Name of the service
    private List<Component> serviceComponents;      //Represents all of the individual components that compromise a service.

    public List<Component> getServiceComponents() {
        return serviceComponents;
    }

    public void setServiceComponents(List<Component> serviceComponents) {
        this.serviceComponents = serviceComponents;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
