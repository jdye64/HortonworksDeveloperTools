package com.jeremydyer.hdpmvn;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeremydyer.hdpmvn.conf.HWXDevTools;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by jdyer on 3/1/16.
 */
public class HDPMVNConfiguration
        extends Configuration {

        @JsonProperty
        @NotNull
        @Valid
        private HWXDevTools hwxDevTools;

        public HWXDevTools getHwxDevTools() {
                return hwxDevTools;
        }

        public void setHwxDevTools(HWXDevTools hwxDevTools) {
                this.hwxDevTools = hwxDevTools;
        }
}