
package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.AutenticacionDAO;
import modelo.pojo.RespuestaLoginEscritorio;

@Path("autenticacion")
public class AutenticacionWS {

    @Context
    private UriInfo context;

    public AutenticacionWS() {
    }

    @POST
    @Path("iniciarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLoginEscritorio loginEscritorio(@FormParam("nombreUsuario") String nombreUsuario,
                                  @FormParam("contraseña") String contraseña){
        RespuestaLoginEscritorio respuestaLogin = null;
        if(nombreUsuario!= null && !nombreUsuario.isEmpty() && contraseña!=null &&!contraseña.isEmpty()){
            respuestaLogin = AutenticacionDAO.verificarInicioSesionEscritorio(nombreUsuario, contraseña);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return respuestaLogin;
    }

    
}
