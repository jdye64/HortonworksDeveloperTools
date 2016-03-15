package com.jeremydyer.hdpmvn.core.old;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jdyer on 2/26/16.
 */
public class Version {

    private String rawVersion;
    private String apacheVersion;
    private String hdpRelease;
    private String hdpVersion;

    public Version(String rawVersion) {
        this.rawVersion = rawVersion.replace("/", "");

        if (rawVersion != null) {
            String[] verParts = StringUtils.split(this.rawVersion, ".");
            StringBuilder rsb = new StringBuilder();
            StringBuilder hdpsb = new StringBuilder();
            for (int i = 0; i < verParts.length; i++) {
                if (i < 2) {
                    rsb.append(verParts[i] + ".");
                }

                if (i == 2) {
                    rsb.append(verParts[i]);
                }

                if (i > 2 && i < verParts.length - 1) {
                    hdpsb.append(verParts[i] + ".");
                }

                if (i == verParts.length - 1) {
                    hdpsb.append(verParts[i]);
                }
            }

            this.apacheVersion = rsb.toString();
            this.hdpVersion = hdpsb.toString();

            String[] hdpParts = StringUtils.split(this.hdpVersion, ".");
            StringBuilder hdpRel = new StringBuilder();
            try {
                for (int i = 0; i < 2; i++) {
                    hdpRel.append(hdpParts[i] + ".");
                }
                hdpRel.append(hdpParts[2]);
            } catch (Exception ex) {
                //ex.printStackTrace();
            }

            this.hdpRelease = hdpRel.toString();
        }
    }

    public String getRawVersion() {
        return rawVersion;
    }

    public void setRawVersion(String rawVersion) {
        this.rawVersion = rawVersion;
    }

    public String getApacheVersion() {
        return apacheVersion;
    }

    public void setApacheVersion(String apacheVersion) {
        this.apacheVersion = apacheVersion;
    }

    public String getHdpVersion() {
        return hdpVersion;
    }

    public void setHdpVersion(String hdpVersion) {
        this.hdpVersion = hdpVersion;
    }

    public String getHdpRelease() {
        return hdpRelease;
    }

    public void setHdpRelease(String hdpRelease) {
        this.hdpRelease = hdpRelease;
    }
}
