package com.jeremydyer.hdpmvn.resource;

import com.codahale.metrics.annotation.Timed;
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

    public HDPMVNResource(HortonworksReleaseDBService bd) {
        DB = bd;
        try {
            mvnService = new MavenPomBuilderService(DB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
