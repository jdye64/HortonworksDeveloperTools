package com.jeremydyer.hdpmvn.conf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by jdyer on 3/22/16.
 */
@JsonTypeName("hwxdevtools")
public class HWXDevToolsFactory
    implements HWXDevTools {

    @JsonProperty
    private String dbPath;

    public String dbPath() {
        return null;
    }
}
