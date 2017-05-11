package io.swagger.api;

import io.swagger.model.Body3;
import io.swagger.model.Body4;
import io.swagger.model.Body5;

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
public interface UserApi  {

    @POST
    @Path("/user")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Create user", tags={  })
    public  createUser(Body3 body);

    @POST
    @Path("/user/createWithArray")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Creates list of users with given input array", tags={  })
    public  createUsersWithArrayInput(List<Body4> body);

    @POST
    @Path("/user/createWithList")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Creates list of users with given input array", tags={  })
    public  createUsersWithListInput(List<Body4> body);

    @DELETE
    @Path("/user/{username}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Delete user", tags={  })
    public  deleteUser(@PathParam("username") String username);

    @GET
    @Path("/user/{username}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Get user by user name", tags={  })
    public Body4 getUserByName(@PathParam("username") String username);

    @GET
    @Path("/user/login")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Logs user into the system", tags={  })
    public String loginUser(@QueryParam("username")String username, @QueryParam("password")String password);

    @GET
    @Path("/user/logout")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Logs out current logged in user session", tags={  })
    public  logoutUser();

    @PUT
    @Path("/user/{username}")
    @Produces({ "application/xml", "application/json" })
    @ApiOperation(value = "Updated user", tags={  })
    public  updateUser(@PathParam("username") String username, Body5 body);
}

