
package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Empleado;
import modelo.pojo.RespuestaLoginEscritorio;
import modelo.pojo.RespuestaLoginMovil;

@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    public AutenticacionWS() {
    }

    @POST
    @Path("iniciarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio loginEscritorio(String jsonParam){
        RespuestaLoginEscritorio respuestaLogin = null;
        try{
            Gson gson = new Gson();
            Empleado empleado = gson.fromJson(jsonParam, Empleado.class);
            if(empleado.getNombreUsuario() != null && !empleado.getNombreUsuario().isEmpty() && empleado.getContraseña() !=null &&!empleado.getContraseña().isEmpty()){
                respuestaLogin = AutenticacionDAO.verificarInicioSesionEscritorio(empleado.getNombreUsuario(), empleado.getContraseña());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return respuestaLogin;
    }

    @POST
    @Path("iniciarSesionMobil")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaLoginMovil loginMovil(String jsonParam){
        RespuestaLoginMovil respuestaLogin = null;
        try{
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonParam, Cliente.class);
            if(cliente.getCorreo() != null && !cliente.getCorreo().isEmpty() && cliente.getContraseña() !=null &&!cliente.getContraseña().isEmpty()){
                respuestaLogin = AutenticacionDAO.verificarInicioSesionMovil(cliente.getCorreo(), cliente.getContraseña());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return respuestaLogin;
    }
    
}
