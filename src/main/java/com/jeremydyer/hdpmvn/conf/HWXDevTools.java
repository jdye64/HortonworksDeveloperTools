package com.jeremydyer.hdpmvn.conf;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;

/**
 * Created by jdyer on 3/22/16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface HWXDevTools
        extends Discoverable {

    String dbPath();
}
