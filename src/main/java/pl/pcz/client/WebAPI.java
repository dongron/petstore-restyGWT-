package pl.pcz.client;

import pl.pcz.shared.Pet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface WebAPI extends RestService {
    @GET
    @Path("http://petstore.swagger.io/v2/pet/{id}")
    public void getPet(@PathParam("id") int id, MethodCallback<Pet> m);
}
