package modelo;

import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {
    
    public static Mensaje agregarEmpresa(Empresa empresa){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empresa rfc = conexionBD.selectOne("empresa.empresaPorRFC", empresa.getRFC());
                if(rfc == null){
                    int filasAfectadas = conexionBD.insert("empresa.agregarEmpresa", empresa);
                    conexionBD.commit();
                    if(filasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje("Empresa registrada exitosamente");
                    }else{
                        msj.setMensaje("Error al guardar los datos, pruebe mas tarde.");
                    }
                }else{
                    msj.setMensaje("El rfc ya se encuentra registrado en una empresa");
                }
            }catch(Exception e){
                e.printStackTrace();
                msj.setMensaje("Error al ejecutar la operacion." + e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("No hay conexion a la BD, prueba mas tarde.");
        }
        return msj;
    }
    
    public static Mensaje editarEmpresa(Empresa empresa, String RFC){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empresa empresaExiste = conexionBD.selectOne("empresa.empresaPorRFC",RFC);
                if(empresaExiste != null){
                   int filasAfectadas = conexionBD.update("empresa.editarEmpresa", empresa);
                    conexionBD.commit();
                    if(filasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje("Empresa editada exitosamente, " + filasAfectadas + " filas afectadas.");
                    }else{
                        msj.setMensaje("Error al guardar los datos, pruebe mas tarde.");
                    } 
                }else{
                    msj.setMensaje("La empresa con el RFC: "+RFC + " no existe");
                }
            }catch(Exception e){
                msj.setMensaje("Error al ejecutar la operacion." + e);
            }finally{
                conexionBD.close();
            }
        }else{
          msj.setMensaje("No hay conexion a la BD, prueba mas tarde."); 
        }
        return msj;
    }
    
    public static Mensaje eliminarEmpresa(String RFC){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empresa empresaExiste = conexionBD.selectOne("empresa.empresaPorRFC",RFC);
                if(empresaExiste != null){
                   int filasAfectadas = conexionBD.delete("empresa.eliminarEmpresa", RFC);
                    conexionBD.commit();
                    if(filasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje("Empresa eliminada exitosamente, " + filasAfectadas + " filas afectadas.");
                    }else{
                        msj.setMensaje("Error al procesae los datos, pruebe mas tarde.");
                    } 
                }else{
                    msj.setMensaje("La empresa con el RFC: "+RFC + " no existe");
                }
            }catch(Exception e){
                msj.setMensaje("Error al ejecutar la operacion." + e);
            }finally{
                conexionBD.close();
            }
        }else{
          msj.setMensaje("No hay conexion a la BD, prueba mas tarde."); 
        }
        return msj;
    }
    
    public static Empresa empresaPorRFC(String RFC){
        Empresa empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectOne("empresa.empresaPorRFC", RFC);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return empresa;
    }
    
    public static Empresa empresaPorId(Integer idEmpresa){
        Empresa empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectOne("empresa.empresaPorId", idEmpresa);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return empresa;
    }
    
    public static Empresa empresaPorNombreComercial(String nombreComercial){
        Empresa empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectOne("empresa.empresaPorNombreComercial", nombreComercial);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return empresa;
    }
    
    public static Empresa empresaPorRepresentante(String representante){
        Empresa empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectOne("empresa.empresaPorRepresentante", representante);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return empresa;
    }
    
    public static List<Empresa> listaEmpresa(){
        List<Empresa> empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectList("empresa.listaEmpresa");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return empresa;
    }
    
    //------------------------------------- Registrar foto Empresa ----------------------------------------------------------\\ 
    public static Mensaje registrarLogoEmpresa(int idEmpresa, byte[] logo){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idEmpresa", idEmpresa);
        parametros.put("logo", logo);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("empresa.guardarLogo", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Logo de la empresa guardada correctamente.");
                } else {
                    msj.setMensaje("Lo sentimos, hubo un error al intentar guardadar el logo, por favor intentelo más tarde.");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            msj.setMensaje("Error de conexión, por el momento no se úede registrar el logo de la empresa");
        }
        
        return msj;
    }
    
    //------------------------------------- Obtener Logo empresa ----------------------------------------------------------\\ 
    public static Empresa obtenerLogoEmpresa(int idEmpresa){
        Empresa empresa = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                empresa = conexionBD.selectOne("empresa.obtenerLogo", idEmpresa);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return empresa;
    
    }

}
