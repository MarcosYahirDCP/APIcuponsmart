package ws;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import modelo.pojo.Mensaje;

@Path("promocion")
public class PromocionWS {
    
    @Context
    private UriInfo context;
    public PromocionWS(){
        
    }
    
    public Mensaje agregarPromocion(String jsonParam){
        Mensaje msj = new Mensaje();
        return msj;
    }
}
