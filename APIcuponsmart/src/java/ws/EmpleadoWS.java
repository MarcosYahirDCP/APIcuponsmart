package ws;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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
import modelo.pojo.Empresa;
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
            msj.setMensaje("Error al enviar los datos "+e);
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
            msj.setMensaje("Error al enviar los datos "+e);
        }
        return msj;
    }
    
    @DELETE
    @Path("eliminarEmpleadosEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpleadosEmpresa(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Empresa empresa = gson.fromJson(jsonParam, Empresa.class);
            if(empresa.getIdEmpresa() != null && empresa.getIdEmpresa()> 0){
                msj = EmpleadoDAO.eliminarEmpleadosEmpresa(empresa.getIdEmpresa());
            }else{
                msj.setMensaje("El idEmpresa debe ser mayor a 0");
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos "+e);
        }
        return msj;
    }
    
    @GET
    @Path("empleadoPorRol/{idRol}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Empleado> empleadosPorRol(@PathParam("idRol") Integer idRol){
        List<Empleado> empleados = new ArrayList<>();
        if(idRol!= null && idRol>0 ){
            empleados = EmpleadoDAO.empleadosPorRol(idRol);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleados;
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
    
    @GET
    @Path("listaEmpleados/{idEmpresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Empleado> ListaEmpleados(@PathParam("idEmpresa") Integer idEmpresa){
        List<Empleado> empleado = null;
        if(idEmpresa!= null && idEmpresa > 0 ){
            empleado = EmpleadoDAO.ListaEmpleados(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleado;
    }
    
    @GET
    @Path("empleados")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Empleado> empleados(){
        List<Empleado> empleado = EmpleadoDAO.empleados();
        return empleado;
    }
    
    @GET
    @Path("empleadoPorId/{idEmpleado}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empleado empleadoPorId(@PathParam("idEmpleado") Integer idEmpleado){
        Empleado empleado = null;
        if(idEmpleado!= null && idEmpleado > 0 ){
            empleado = EmpleadoDAO.empleadoPorId(idEmpleado);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empleado;
    }
}
