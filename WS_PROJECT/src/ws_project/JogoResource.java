package ws_project;
/**
 *
 * @author GabrielAugustoSouzaS
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

@Path("/jogo")
@Produces(MediaType.APPLICATION_JSON)
public class JogoResource {
    
    private JogoDAO dao;
    
    public JogoResource(JogoDAO dao){
        this.dao = dao;
    }
    
    @POST
    public Jogo create(Jogo jogo){
        return dao.create(jogo);      
    }
    
    @GET
    public List<Jogo> read(){
        return dao.read();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") IntParam id, Jogo jogo) {
        jogo.setId(id.get());
        if (dao.update(jogo)) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Jogo com id=" + id.get()
                                          + " não encontrado!", 404);
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") IntParam id) {
        
        if ( dao.delete(id.get())) {
            return Response.ok().build();
        }
        
        throw new WebApplicationException("Jogo com id=" + id 
                                          + " não encontrado!", 404);
    }
    
}
    