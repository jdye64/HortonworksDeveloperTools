package com.jeremydyer.hdpmvn.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.hdpmvn.HDPMVNConfiguration;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by jdyer on 3/1/16.
 */
@Path("/v1/mvn/hdp")
@Produces(MediaType.APPLICATION_JSON)
public class HDPMVNResource {

    private static final Logger logger = LoggerFactory.getLogger(HDPMVNResource.class);

//    private HDPMVNService service = null;
//    private HDPMVNConfiguration configuration = null;
//
//    public HDPMVNResource(HDPMVNConfiguration configuration) {
//        this.configuration = configuration;
//        service = new HDPMVNService(configuration);
//    }
//
//    @POST
//    @Timed
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response createReceiptEntry(@FormDataParam("receiptImage") InputStream fileInputStream,
//                                       @FormDataParam("receiptImage") FormDataContentDisposition contentDispositionHeader,
//                                       @FormParam("SKU") long sku,
//                                       @FormParam("date") Date date,
//                                       @FormParam("price") double price) {
//
//        logger.info("SKU: " + sku + " Date: " + date + " Price: " + price);
//        return Response.ok().build();
//    }
//
//    @GET
//    @Timed
//    public Response getProjects() {
//        logger.info("Retriving Hortonworks Projects");
//        return Response.ok(service.getProjects(), MediaType.APPLICATION_JSON_TYPE).build();
//    }
//
////    @GET
////    @Timed
////    @Path("/{transactionID}/image")
////    public Response downloadReceiptImage(@PathParam("transactionID") long transactionID) {
////
////        String fileName = transactionID + "_receiptimage.jpg";
////        String receiptImage = rService.getReceiptImage(transactionID);
////        return Response
////                .ok(receiptImage, MediaType.APPLICATION_OCTET_STREAM)
////                .header("content-disposition", "attachment; filename = " + fileName)
////                .build();
////    }

}
