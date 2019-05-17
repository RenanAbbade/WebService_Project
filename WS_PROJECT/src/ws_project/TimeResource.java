/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;

/**
 *
 * @author Renan
 */
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/times")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {
    
    private timeDAO dao;
    
    public TimeResource(timeDAO dao){
        this.dao = dao;
    }
    
    @POST
    public Time create(Time time){
        return dao.create(time);      
    }
    
    @GET
    public List<Time> read(){
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") IntParam id, Time time) {
        time.setId(id.get());
        if (dao.atualizar(time)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Time com id=" + id.get()
                                          + " não encontrado!", 404);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        
        if ( dao.apagar(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Professor com id=" + id 
                                          + " não encontrado!", 404);
    }
    


}
    
  
