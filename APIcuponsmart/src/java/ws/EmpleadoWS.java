package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import modelo.EmpleadoDAO;
import modelo.pojo.Empleado;
import modelo.pojo.Mensaje;

@Path("empleado")
public class EmpleadoWS {
    
    @Context
    private UriInfo context;
    public EmpleadoWS(){
    
}
    @POST
    @Path("agregarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarEmpleado(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if(empleado!= null){
                msj = EmpleadoDAO.agregarEmpleado(empleado);
            }else{
                 throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos " + e);
        }
        return msj;
    }
    
    @PUT
    @Path("editarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpleado(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if(empleado != null){
                msj = EmpleadoDAO.editarEmpleado(empleado);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Errpr al enviar los datos "+e);
        }
        return msj;
    }
    
    @DELETE
    @Path("eliminarEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpleado(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if(empleado.getIdEmpleado() != null && empleado.getIdEmpleado() > 0){
                msj = EmpleadoDAO.eliminarEmpleado(empleado.getIdEmpleado());
            }else{
                msj.setMensaje("El idEmpleado debe ser mayor a 0");
            }
        }catch(Exception e){
            msj.setMensaje("Errpr al enviar los datos "+e);
        }
        return msj;
    }
    
    @GET
    @Path("empleadoPorRol/{idRol}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empleado empleadoPorRol(@PathParam("idRol") Integer idRol){
        Empleado empleado = null;
        if(idRol!= null && idRol>0 ){
            empleado = EmpleadoDAO.empleadoPorRol(idRol);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleado;
    }
    
    @GET
    @Path("empleadoPorNombre/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empleado empleadoPorNombre(@PathParam("nombre") String nombre){
        Empleado empleado = null;
        if(nombre!= null ){
            empleado = EmpleadoDAO.empleadoPorNombre(nombre);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleado;
    }
    
    @GET
    @Path("empleadoPorNombreUsuario/{nombreUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empleado empleadoPorNombreUsuario(@PathParam("nombreUsuario") String nombreUsuario){
        Empleado empleado = null;
        if(nombreUsuario!= null ){
            empleado = EmpleadoDAO.empleadoPorNombreUsuario(nombreUsuario);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleado;
    }
}
