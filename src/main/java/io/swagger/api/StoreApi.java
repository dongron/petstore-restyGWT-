package io.swagger.api;

import io.swagger.model.Body2;
import io.swagger.model.InlineResponse2003;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.PATCH;

@Path("/")
@Api(value = "/", description = "")
public interface StoreApi  {

    @DELETE
    @Path("/store/order/{orderId}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Delete purchase order by ID", tags={  })
    public  deleteOrder(@PathParam("orderId") Long orderId);

    @GET
    @Path("/store/inventory")
    @Produces({ "application/json" })
    @ApiOperation(value = "Returns pet inventories by status", tags={  })
    public Map<String, Map<String, Integer>> getInventory();

    @GET
    @Path("/store/order/{orderId}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Find purchase order by ID", tags={  })
    public InlineResponse2003 getOrderById(@PathParam("orderId") Long orderId);

    @POST
    @Path("/store/order")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Place an order for a pet", tags={  })
    public InlineResponse2003 placeOrder(Body2 body);
}

