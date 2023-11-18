package modelo.pojo;

public class RespuestaLoginMovil {
private boolean error;
    private String mensaje;
    private Cliente clienteSesion;

    public RespuestaLoginMovil() {
    }

    public RespuestaLoginMovil(boolean error, String mensaje, Cliente clienteSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.clienteSesion = clienteSesion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Cliente getClienteSesion() {
        return clienteSesion;
    }

    public void setClienteSesion(Cliente clienteSesion) {
        this.clienteSesion = clienteSesion;
    }

    
}
