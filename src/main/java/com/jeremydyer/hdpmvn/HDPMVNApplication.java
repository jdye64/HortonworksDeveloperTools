package com.jeremydyer.hdpmvn;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.jeremydyer.hdpmvn.resource.HDPMVNResource;
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

        //final HDPMVNResource receiptResource = new HDPMVNResource(configuration);

        //Register the resources with Jersey
        //environment.jersey().register(receiptResource);

        //Enable the JMX metrics.
        MetricRegistry metricsRegistry = new MetricRegistry();
        final JmxReporter reporter = JmxReporter.forRegistry(metricsRegistry).build();
        reporter.start();
    }
}