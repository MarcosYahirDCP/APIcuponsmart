package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.UbicacionDAO;
import modelo.pojo.Coordenada;
import modelo.pojo.Mensaje;
import modelo.pojo.Ubicacion;

@Path("ubicacion")
public class UbicacionWS {
    
    @Context
    private UriInfo context;
    public UbicacionWS(){
        
    }
    
    @POST
    @Path("agregarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarUbicacion(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            Ubicacion ubicacion = gson.fromJson(jsonParam, Ubicacion.class);
            if(ubicacion != null){
                msj = UbicacionDAO.agregarUbicacion(ubicacion);
            }
            else{
                msj.setMensaje("Error al procesar los datos");
            }
        }catch(Exception e){
            msj.setMensaje("Error "+ e);
        }
        return msj;
    }
    
    @PUT
    @Path("editarUbicacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarUbicacion(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            Ubicacion ubicacion = gson.fromJson(jsonParam, Ubicacion.class);
            if(ubicacion != null){
                msj = UbicacionDAO.editarUbicacion(ubicacion);
            }
            else{
                msj.setMensaje("Error al procesar los datos");
            }
        }catch(Exception e){
            msj.setMensaje("Error "+ e);
        }
        return msj;
    }
    
    @GET
    @Path("obtenerUbicacion/{idUbicacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Ubicacion obtenerUbicacion(@PathParam("idUbicacion")Integer idUbicacion){
        Ubicacion ubicacion = null;
            if(idUbicacion != null && idUbicacion > 0){
                ubicacion = UbicacionDAO.obtenerUbicacion(idUbicacion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        return ubicacion;
    }
    
    @GET
    @Path("obtenerUbicacionRegistro")
    @Consumes(MediaType.APPLICATION_JSON)
    public int obtenerUbicacionRegistro(String jsonparams){
        int idUbicacion = 0;
        Gson gson = new Gson();
        Coordenada coordenada = gson.fromJson(jsonparams, Coordenada.class);
        if (coordenada.getLatitud() != null && coordenada.getLongitud() != null) {
            idUbicacion = UbicacionDAO.obtenerUbicacionRegistro(coordenada.getLatitud(), coordenada.getLongitud());
        }
        return idUbicacion;
    }
}
