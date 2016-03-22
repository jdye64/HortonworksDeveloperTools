package com.jeremydyer.hdpmvn.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.hdpmvn.HDPMVNConfiguration;
import com.jeremydyer.hdpmvn.service.HortonworksReleaseDBService;
import com.jeremydyer.hdpmvn.service.MavenPomBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jdyer on 3/1/16.
 */
@Path("/v1/mvn/hdp")
@Produces(MediaType.APPLICATION_JSON)
public class HDPMVNResource {

    private static final Logger logger = LoggerFactory.getLogger(HDPMVNResource.class);

    private HortonworksReleaseDBService DB = null;
    private MavenPomBuilderService mvnService = null;

    public HDPMVNResource(HortonworksReleaseDBService bd, HDPMVNConfiguration configuration) {
        DB = bd;
        try {
            mvnService = new MavenPomBuilderService(DB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GET
    @Timed
    public Response getHDPVersions() {
        logger.info("Retrieving HDP Versions");
        return Response.ok().build();
    }

    @GET
    @Timed
    @Path("/{hdpversion}")
    public Response mvnForVersion(@PathParam("hdpversion") String hdpVersion) {
        logger.info("Retrieving pom.xml for HDP -> " + hdpVersion);
        String pomXml = mvnService.buildPomForVersion(hdpVersion);
        return Response.ok(pomXml, MediaType.APPLICATION_XML).build();
    }
}
