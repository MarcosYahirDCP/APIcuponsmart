package modelo.pojo;

public class CanjeoCupon {
    private Integer idCanjeo;
    private String correo;
    private String codigoPromocion;
    private String fechaCanjeo;

    public CanjeoCupon() {
    }

    public CanjeoCupon(Integer idCanjeo, String correo, String codigoPromocion, String fechaCanjeo) {
        this.idCanjeo = idCanjeo;
        this.correo = correo;
        this.codigoPromocion = codigoPromocion;
        this.fechaCanjeo = fechaCanjeo;   
    }
    
    public Integer getIdCanjeo() {
        return idCanjeo;
    }

    public void setIdCanjeo(Integer idCanjeo) {
        this.idCanjeo = idCanjeo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getFechaCanjeo() {
        return fechaCanjeo;
    }

    public void setFechaCanjeo(String fechaCanjeo) {
        this.fechaCanjeo = fechaCanjeo;
    }
    
    
}
