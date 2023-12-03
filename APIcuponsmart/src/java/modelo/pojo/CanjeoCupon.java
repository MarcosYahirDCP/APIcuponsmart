package modelo.pojo;

public class CanjeoCupon {
    private String correo;
    private String codigoPromocion;
    private String fechaCanjeo;
    private Integer idSucursal;
    
    public CanjeoCupon() {
    }

    public CanjeoCupon(Integer idSucursal, String correo, String codigoPromocion, String fechaCanjeo) {
        this.idSucursal = idSucursal;
        this.correo = correo;
        this.codigoPromocion = codigoPromocion;
        this.fechaCanjeo = fechaCanjeo;   
    }
    
    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
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
