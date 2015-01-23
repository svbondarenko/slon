package com.scm.rest;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("prototype")

public class ScmPrototypeResource {

    public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";


    @GET
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        return Response.status(200).entity("Hello word").build();
    }

    @POST
    @Path("upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces(MediaType.APPLICATION_JSON)
    public Response pushCSV(@FormDataParam("file") InputStream fileInputStream) throws IOException {


        //Get the CSVReader instance with specifying the delimiter to be used
        List<List<String>> content = getCSVContent(fileInputStream);


        return Response.status(200).entity("Hello word").build();

    }

    protected List<List<String>> getCSVContent(InputStream fileInputStream) throws IOException {
        CSVReader reader = null;
        List<List<String>> content = new ArrayList<>();
        try {

            reader = new CSVReader(new InputStreamReader(fileInputStream), ',');
            String[] nextLine;
            //Read one line at a time

            while ((nextLine = reader.readNext()) != null) {
                List<String> line = new ArrayList<>();
                for (String token : nextLine) {
                    line.add(token);
                }
                content.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

}
