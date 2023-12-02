package modelo.pojo;

public class CanjeoCupon {
    private Integer idCanjeo;
    private String correo;
    private Integer codigoPromocion;
    private String fechaCanjeo;

    public CanjeoCupon() {
    }

    public CanjeoCupon(Integer idCanjeo, String correo, Integer codigoPromocion, String fechaCanjeo) {
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

    public Integer getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(Integer codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getFechaCanjeo() {
        return fechaCanjeo;
    }

    public void setFechaCanjeo(String fechaCanjeo) {
        this.fechaCanjeo = fechaCanjeo;
    }
    
    
}
