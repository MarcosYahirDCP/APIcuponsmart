package ws;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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
import modelo.PromocionDAO;
import modelo.pojo.CanjeoCupon;
import modelo.pojo.Categoria;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.PromocionSucursal;

@Path("promocion")
public class PromocionWS {
    
    @Context
    private UriInfo context;
    public PromocionWS(){
        
    }
    
    @POST
    @Path("agregarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarPromocion(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Promocion promocion = gson.fromJson(jsonParam, Promocion.class);
            if (promocion != null){
                msj = PromocionDAO.agregarPromocion(promocion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error "+e);
        }
        return msj;
    }
    
    @PUT
    @Path("editarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarPromocion(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Promocion promocion = gson.fromJson(jsonParam, Promocion.class);
            if (promocion != null){
                msj = PromocionDAO.editarPromocion(promocion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error "+e);
        }
        return msj;
    }
    
    @DELETE
    @Path("eliminarPromocion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPromocion(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Promocion promocion = gson.fromJson(jsonParam, Promocion.class);
            if (promocion != null){
                msj = PromocionDAO.eliminarPromocion(promocion.getIdPromocion());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error "+e);
        }
        return msj;
    }
    
    @GET
    @Path("promocionNombre/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Promocion promocionPorNombre(@PathParam("nombre") String nombre){
        Promocion promocion = null;
        if(nombre != null && !nombre.isEmpty()){
            promocion = PromocionDAO.promocionPorNombre(nombre);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    @GET
    @Path("detallesPromocion/{idPromocion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Promocion detallesPromocion(@PathParam("idPromocion") Integer idPromocion){
        Promocion promocion = null;
        if(idPromocion != null && idPromocion > 0){
            promocion = PromocionDAO.detallesPromocion(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    @GET
    @Path("promocionCategoria/{idCategoria}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Promocion> promocionPorCategoria(@PathParam("idCategoria") Integer idCategoria){
        List<Promocion> promocion = null;
        if(idCategoria != null && idCategoria > 0){
            promocion = PromocionDAO.promocionPorCategoria(idCategoria);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    @GET
    @Path("promocionFechaInicio/{inicioPromocion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Promocion> promocionFechaInicio(@PathParam("inicioPromocion") String inicioPromocion){
        List<Promocion> promocion = null;
        if(inicioPromocion != null && !inicioPromocion.isEmpty()){
            promocion = PromocionDAO.promocionFechaInicio(inicioPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    @GET
    @Path("promocionPorId/{idPromocion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Promocion promocionPorId(@PathParam("idPromocion") Integer idPromocion){
        Promocion promocion = null;
        if(idPromocion != null && idPromocion > 0){
            promocion = PromocionDAO.promocionPorId(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return promocion;
    }
    
    @GET
    @Path("promociones")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Promocion> listarPromociones(){
        List<Promocion> promocion =  PromocionDAO.listarPromociones();
        return promocion;
    }
    
    @GET
    @Path("promocionesPorEmpresa/{idEmpresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Promocion> listarPromocionesPorEmpresa(@PathParam("idEmpresa") Integer idEmpresa){
        List<Promocion> promocion =  PromocionDAO.listarPromocionesPorEmpresa(idEmpresa);
        return promocion;
    }
    
    @POST
    @Path("promocionPorSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje promocionPorSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            PromocionSucursal promocion = gson.fromJson(jsonParam, PromocionSucursal.class);
            if(promocion != null){
                msj = PromocionDAO.promocionPorSucursal(promocion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error: " + e);
        }
        return msj;
    }
    
    @DELETE
    @Path("eliminarPromocionPorSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPromocionPorSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            PromocionSucursal promocion = gson.fromJson(jsonParam, PromocionSucursal.class);
            if(promocion != null){
                msj = PromocionDAO.eliminarPromocionPorSucursal(promocion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error: " + e);
        }
        return msj;
    }
    
    @POST
    @Path("verPromocionPorSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje verPromocionPorSucursal(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            PromocionSucursal promocion = gson.fromJson(jsonParam, PromocionSucursal.class);
            if(promocion != null){
                msj = PromocionDAO.verPromocionPorSucursal(promocion);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error: " + e);
        }
        return msj;
    }
    
    @POST
    @Path("canjeoCupon")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje canjeoCupon(String jsonParam){
        Mensaje msj = new Mensaje();
        Gson gson = new Gson();
        try{
            CanjeoCupon canjeo = gson.fromJson(jsonParam, CanjeoCupon.class);
            if(canjeo != null){
                msj = PromocionDAO.canjeoCupon(canjeo);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error: " + e);
        }
        return msj;
    }
    
    @GET
    @Path("categorias")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Categoria> listaCategorias(){
        List<Categoria> categoria =  PromocionDAO.listaCategorias();
        return categoria;
    }
    
     //-------------------------------------------------------------Subir IMG promocion -------------------------------------------------------------\\
    @PUT
    @Path("subirImg/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirImgPromo(@PathParam("idPromocion") Integer idPromocion, byte[] img){
        Mensaje msj = new Mensaje();
        if(idPromocion >0 && img!=null){
            msj = PromocionDAO.registrarImgPromo(idPromocion, img);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    //-------------------------------------------------------------Recuperar imagen de la promocion -------------------------------------------------------------\\
    @GET
    @Path("obtenerImg/{idPromocion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerImgPromo(@PathParam("idPromocion") Integer idPromocion){
        Promocion promocion  = null;
        if(idPromocion != null && idPromocion > 0){
            promocion = PromocionDAO.obtenerImgPromo(idPromocion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }        
        return promocion;
    }
}
