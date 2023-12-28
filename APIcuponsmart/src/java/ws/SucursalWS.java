package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.SucursalDAO;
import modelo.pojo.Sucursal;
import modelo.pojo.Mensaje;

@Path("sucursal")
public class SucursalWS {
    
    @Context
    private UriInfo context;
    public SucursalWS (){
        
    }
    
    @POST
    @Path("agregarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        try{
            Gson gson = new Gson();
            Sucursal sucursal = gson.fromJson(jsonParam, Sucursal.class);
            if(sucursal != null && sucursal.getIdEmpresa() != null && sucursal.getIdEmpresa() > 0 && 
                    sucursal.getNombre() != null && !sucursal.getNombre().isEmpty() &&
                    sucursal.getTelefono() != null && !sucursal.getTelefono().isEmpty() &&
                    sucursal.getEncargado() != null && !sucursal.getEncargado().isEmpty() &&
                    sucursal.getIdUbicacion() != null){
                msj = SucursalDAO.agregarSucursal(sucursal);
            }else{
                 throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos" + e);
        }
        return msj;
    }
    
    @PUT
    @Path("editarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        try {
            Gson gson = new Gson();
            Sucursal sucursal = gson.fromJson(jsonParam, Sucursal.class);
            if(sucursal != null && sucursal.getIdSucursal() != null && sucursal.getIdSucursal() >0 &&
                    sucursal.getNombre() != null && !sucursal.getNombre().isEmpty() &&
                    sucursal.getTelefono() != null && !sucursal.getTelefono().isEmpty() &&
                    sucursal.getEncargado() != null && !sucursal.getEncargado().isEmpty()){
                msj = SucursalDAO.editarSucursal(sucursal, sucursal.getIdSucursal());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos" + e);
        }
        return msj;
    }
    
    @DELETE
    @Path("eliminarSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        try{
            Gson gson = new Gson();
            Sucursal sucursal = gson.fromJson(jsonParam, Sucursal.class);
            if(sucursal.getIdSucursal() != null && sucursal.getIdSucursal() > 0){
                msj = SucursalDAO.eliminarSucursal(sucursal.getIdSucursal());
            }else{
                msj.setMensaje("El id de la sucursal no existe");
            }
        }catch(Exception e){
            
        }
        return msj;
    }
    
    @GET
    @Path("sucursalNombre/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Sucursal sucursalPorNombre(@PathParam("nombre") String nombre){
        Sucursal sucursal = null;
        if(nombre != null){
            sucursal = SucursalDAO.sucursalPorNombre(nombre);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return sucursal;
    }
    
    @GET
    @Path("sucursalUbicacion/{ubicacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Sucursal sucursalPorUbicacion(@PathParam("ubicacion") Integer idUbicacion){
        Sucursal sucursal = null;
        if(idUbicacion != null){
            sucursal = SucursalDAO.sucursalPorUbicacion(idUbicacion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return sucursal;
    }
    
    @GET
    @Path("sucursalEmpresa/{idEmpresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Sucursal> sucursalPorEmpresa(@PathParam("idEmpresa") Integer idEmpresa){
        List<Sucursal> sucursal = null;
        if(idEmpresa != null && idEmpresa > 0){
            sucursal = SucursalDAO.sucursalPorEmpresa(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return sucursal;
    }
    
    @GET
    @Path("sucursalPorId/{idSucursal}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Sucursal sucursalPorId(@PathParam("idSucursal") Integer idSucursal){
        Sucursal sucursal = null;
        if(idSucursal != null && idSucursal > 0){
            sucursal = SucursalDAO.sucursalPorId(idSucursal);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return sucursal;
    }
}
