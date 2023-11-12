package modelo;

import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {
    
    public static Mensaje registrarEmpresa(Empresa empresa){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empresa rfc = conexionBD.selectOne("empresa.empresaPorRFC", empresa.getRFC());
                if(rfc != null){
                    msj.setMensaje("El rfc ya se encuentra registrado en una empresa");
                }
                int filasAfectadas = conexionBD.insert("empresa.agregarEmpresa", empresa);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Empresa registrada exitosamente");
                }else{
                    msj.setMensaje("Error al guardar los datos, pruebe mas tarde.");
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
}
