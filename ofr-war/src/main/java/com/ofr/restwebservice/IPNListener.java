package com.ofr.restwebservice;

/**
 * Created with IntelliJ IDEA.
 * User: imon
 * Date: 2/26/13
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("/ipn")
public class IPNListener {
    private static Logger log = Logger.getLogger(IPNListener.class.toString());

    @Context
    private UriInfo context;

    /**
     * Default constructor.
     */
    public IPNListener() {
    }

    /**
     * POST method for updating or creating an instance of IPNListener * @param content representation for the resource * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postIPN(MultivaluedMap formParams) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource("https://www.sandbox.paypal.com/cgi-bin/webscr");
        formParams.add("cmd", "_notify-validate");
        ClientResponse response = service.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formParams);
        String validation = response.getEntity(String.class);
        System.out.println(String.format("IPN: Validation='%s'", validation));
        if ("VERIFIED".equals(validation)) {


            for (Object o : formParams.entrySet()) {
                Map.Entry thisEntry = (Map.Entry) o;
                String key = (String) thisEntry.getKey();
                List value = (List) thisEntry.getValue();

                if (value != null && value.size() > 0) {
                    System.out.println(String.format("IPN: Key='%s', Value='%s'", key, value.get(0)));
                    // TODO: Persist the IPN details
                }
            }
            // TODO: Alert handler of received IPN
        }
        return Response.status(Status.OK).entity(validation).build();
    }

    @GET
    public Response getIPN() {
        return Response.status(Status.OK).entity("You must POST here").build();
    }
}
