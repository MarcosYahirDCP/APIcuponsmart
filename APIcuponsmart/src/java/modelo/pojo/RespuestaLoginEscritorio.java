
package modelo.pojo;

public class RespuestaLoginEscritorio {
    
    private boolean error;
    private String mensaje;
    private Empleado empleadoSesion;

    public RespuestaLoginEscritorio() {
    }

    public RespuestaLoginEscritorio(boolean error, String mensaje, Empleado empleadoSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.empleadoSesion = empleadoSesion;
    }

    public boolean isError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Empleado getMedicoSesion() {
        return empleadoSesion;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setMedicoSesion(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }
            
            
}

