package modelo.pojo;

public class Sucursal {
    private Integer idSucursal;
    private String nombre;
    private String telefono;
    private String encargado;
    private Integer idUbicacion;
    private Integer idEmpresa;
    private String nombreEmpresa;
    private String calle;
    private String codigoPostal;
    private String numero;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String nombre, String telefono, String encargado, Integer idUbicacion, Integer idEmpresa,String calle, String codigoPostal, String numero ,String nombreEmpresa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.telefono = telefono;
        this.encargado = encargado;
        this.idUbicacion = idUbicacion;
        this.idEmpresa = idEmpresa;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.nombreEmpresa = nombreEmpresa;
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    
    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    
}
