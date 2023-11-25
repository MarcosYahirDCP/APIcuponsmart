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
            Empresa empresa = gson.fromJson(jsonParam, Empresa.class);
            if(empresa != null){
                msj = EmpresaDAO.agregarEmpresa(empresa);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error "+ e.getMessage());
        }
        return msj;
    }
    
    @PUT
    @Path("editarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarEmpresa(String jsonParam){
     Mensaje msj = new Mensaje();
         try{
            Gson gson = new Gson();
            Empresa empresa = gson.fromJson(jsonParam, Empresa.class);
            if(empresa != null){
                msj = EmpresaDAO.editarEmpresa(empresa, empresa.getRFC());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error "+ e.getMessage());
        }
         return msj;
    }
    
    @DELETE
    @Path("eliminarEmpresa")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEmpresa(String jsonParam){
     Mensaje msj = new Mensaje();
         try{
            Gson gson = new Gson();
            Empresa empresa = gson.fromJson(jsonParam, Empresa.class);
            if(empresa.getRFC() != null && !empresa.getRFC().isEmpty()){
                msj = EmpresaDAO.eliminarEmpresa(empresa.getRFC());
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje("Error "+ e.getMessage());
        }
         return msj;
    }
    
    @GET
    @Path("empresaRFC/{RFC}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empresa empresaPorRFC(@PathParam("RFC") String RFC){
        Empresa empresa = null;
        if(RFC != null){
            empresa =  EmpresaDAO.empresaPorRFC(RFC);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }
    
    @GET
    @Path("empresaPorId/{idEmpresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empresa empresaPorId(@PathParam("idEmpresa") Integer idEmpresa){
        Empresa empresa = null;
        if(idEmpresa != null && idEmpresa > 0){
            empresa =  EmpresaDAO.empresaPorId(idEmpresa);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }
    
    @GET
    @Path("empresaNombreComercial/{nombreComercial}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empresa empresaPorNombreComercial(@PathParam("nombreComercial") String nombreComercial){
        Empresa empresa = null;
        if(nombreComercial != null){
            empresa =  EmpresaDAO.empresaPorNombreComercial(nombreComercial);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }
    
    @GET
    @Path("empresaRepresentante/{representante}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Empresa empresaPorRepresentante(@PathParam("representante") String representante){
        Empresa empresa = null;
        if(representante != null){
            empresa =  EmpresaDAO.empresaPorRepresentante(representante);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return empresa;
    }
    
    @GET
    @Path("listaEmpresa")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Empresa> listaEmpresa(){
        List<Empresa> empresa = null;
            empresa =  EmpresaDAO.listaEmpresa();
        return empresa;
    }
}
