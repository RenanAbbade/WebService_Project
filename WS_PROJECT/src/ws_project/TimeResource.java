/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_project;

/**
 *
 * @author Dell
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
        Time t = dao.create(time);
        return t;
    }
    
    @GET
    public List<Time> read(){
        return dao.lerTodos();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") IntParam idParam, Time time) {
        time.setId(idParam.get());
        if (dao.atualizar(time)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Time com id=" + idParam.get()
                                          + " não encontrado!", 404);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam idParam) {
        int id = idParam.get();
        
        if ( dao.apagar(id)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Professor com id=" + id 
                                          + " não encontrado!", 404);
    }
    


}
    
  
