
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.CatalogoDAO;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;

@Path ("catalogo")
public class CatalogoWS {
    
    
    @Path("obtenerEstados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> obtenerEstados(){
        return CatalogoDAO.obtenerEstados();
    }
    
    @Path("obtenerMunicipiosEstados/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Municipio> obtenerMunicipiosEstados(@PathParam("idEstado") Integer idEstado){
        
        List<Municipio> municipios;
        if(idEstado != null && idEstado > 0){
            municipios = CatalogoDAO.obtenerMunicipiosEstados(idEstado);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return municipios;
    }
    
    @Path("obtenerMunicipioPorId/{idMunicipio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Municipio obtenerEstadoPorId(@PathParam("idMunicipio") Integer idMunicipio){
        Municipio municipio = new Municipio();
        if(idMunicipio != null && idMunicipio > 0){
            municipio = CatalogoDAO.obtenerMunicipioPorID(idMunicipio);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return municipio;
    }
}
