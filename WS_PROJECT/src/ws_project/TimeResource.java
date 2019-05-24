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
import io.dropwizard.jersey.params.IntParam;
import io.dropwizard.jersey.params.LongParam;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    
  
