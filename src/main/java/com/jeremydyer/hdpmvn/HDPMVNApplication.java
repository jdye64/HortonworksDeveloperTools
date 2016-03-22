package com.jeremydyer.hdpmvn;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.jeremydyer.hdpmvn.resource.HDPMVNResource;
import com.jeremydyer.hdpmvn.service.HortonworksReleaseDBService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by jdyer on 3/1/16.
 */
public class HDPMVNApplication
        extends Application<HDPMVNConfiguration> {

    public static void main(String[] args) throws Exception {
        new HDPMVNApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HDPMVNConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(HDPMVNConfiguration configuration,
                    Environment environment) {

        //Non-static content requests should be accepted through "/api/*" URL formats
        environment.jersey().setUrlPattern("/api/*");

        //Create the HDP Database instance.
        HortonworksReleaseDBService DB = new HortonworksReleaseDBService(configuration.getHwxDevTools().dbPath());
        DB.load();

        HDPMVNResource hdpmvnResource = new HDPMVNResource(DB, configuration);
        environment.jersey().register(hdpmvnResource);

        //Enable the JMX metrics.
        MetricRegistry metricsRegistry = new MetricRegistry();
        final JmxReporter reporter = JmxReporter.forRegistry(metricsRegistry).build();
        reporter.start();
    }
}