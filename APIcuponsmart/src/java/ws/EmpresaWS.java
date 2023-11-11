package ws;

import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.EmpresaDAO;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;

@Path("empresa")
public class EmpresaWS {
    
    @Context
    private UriInfo context;
    public EmpresaWS (){
        
    }
    
    @POST
    @Path("agregarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarEmpresa(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Empresa domicilio = gson.fromJson(jsonParam, Empresa.class);
            if(domicilio != null){
                msj = EmpresaDAO.registrarEmpresa(domicilio);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error "+ e.getMessage());
        }
        return msj;
    }
}
