package com.jeremydyer.hdpmvn.core;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Represents an individual component that makes up a larger service. EX: HDFS -> hdfs-core, hdfs-client, etc
 */
public class Component {

    private String mvnGroupId;
    private String mvnArtifactId;
    private String mvnVersion;

    @XStreamOmitField
    private String mvnPropertiesVersion;

    public String getMvnGroupId() {
        return mvnGroupId;
    }

    public void setMvnGroupId(String mvnGroupId) {
        this.mvnGroupId = mvnGroupId;
    }

    public String getMvnArtifactId() {
        return mvnArtifactId;
    }

    public void setMvnArtifactId(String mvnArtifactId) {
        this.mvnArtifactId = mvnArtifactId;
    }

    public String getMvnVersion() {
        return mvnVersion;
    }

    public void setMvnVersion(String mvnVersion) {
        this.mvnVersion = mvnVersion;
    }

    public String getMvnPropertiesVersion() {
        return mvnPropertiesVersion;
    }

    public void setMvnPropertiesVersion(String mvnPropertiesVersion) {
        this.mvnPropertiesVersion = mvnPropertiesVersion;
    }
}
