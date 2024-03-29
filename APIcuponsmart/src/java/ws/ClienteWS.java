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
import modelo.ClienteDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;

@Path("cliente")
public class ClienteWS {
    
    @Context
    private UriInfo context;
    public ClienteWS(){
        
    }
    
    @POST
    @Path("agregarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarCliente(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonParam, Cliente.class);
            if(cliente != null){   
                msj = ClienteDAO.agregarCliente(cliente);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos " + e);
        }
        return msj;
    }
    
    @PUT
    @Path("editarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String jsonParam){
        Mensaje msj = new Mensaje();
        try{
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonParam, Cliente.class);
            if(cliente != null){   
                msj = ClienteDAO.editarCliente(cliente);
            }else{
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
        }catch(Exception e){
            msj.setMensaje("Error al enviar los datos " + e);
        }
        return msj;
    }
    
    @GET
    @Path("clientePorId/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente clientePorId(@PathParam("idCliente") Integer idCliente){
        Cliente cliente = null;
        if(idCliente!= null && idCliente > 0 ){
            cliente = ClienteDAO.clientePorId(idCliente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return cliente;
    }
}
