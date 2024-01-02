package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Sucursal;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class SucursalDAO {
    
    public static Mensaje agregarSucursal(Sucursal sucursal){
        Mensaje msj = new Mensaje ();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numeroFilasAfectadas = conexionBD.insert("sucursal.agregarSucursal", sucursal);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Sucursal registrada correctamente ");
                }else{
                    msj.setMensaje("Error al procesar los datos");
                }
            }catch(Exception e){
                msj.setMensaje("Error: "+ e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("No hay conexión con la base de datos");
        }
        return msj;
    }
    
    public static Mensaje editarSucursal(Sucursal sucursal, int idSucursal){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Sucursal sucursalExiste = conexionBD.selectOne("sucursal.sucursalPorId", idSucursal);
                if(sucursalExiste != null){
                int numeroFilasAfectadas = conexionBD.update("sucursal.editarSucursal", sucursal);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Sucursal editada correctamente");
                }else{
                    msj.setMensaje("Error al editar la sucursal");
                }
                }else{
                    msj.setMensaje("La sucursal con " + idSucursal + " no existe");
                }
            }catch(Exception e){
                msj.setMensaje("Error al procesar los datos " + e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("No hay conexión con la base de datos");
        }
        return msj;
    }
    
    public static Mensaje eliminarSucursal( int idSucursal){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numeroFilasAfectadas = conexionBD.delete("sucursal.eliminarSucursal",idSucursal);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Sucursal eliminada correctamente");
                }else{
                    msj.setMensaje("Error al eliminar la Sucursal");
                }
            }catch(Exception e){
                msj.setMensaje("Error "+e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("No hay conexión con la base de datos"); 
        }
        return msj;
    }
    
    public static Sucursal sucursalPorNombre(String nombre){
        Sucursal sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
              sucursal = conexionBD.selectOne("sucursal.sucursalPorNombre",nombre);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return sucursal;
    }
    
    public static Sucursal sucursalPorUbicacion(Integer idUbicacion){
        Sucursal sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
              sucursal = conexionBD.selectOne("sucursal.sucursalPorUbicacion",idUbicacion);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return sucursal;
    }
    
    public static Sucursal sucursalPorId(Integer idSucursal){
        Sucursal sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
              sucursal = conexionBD.selectOne("sucursal.sucursalPorId",idSucursal);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return sucursal;
    }
    
    public static List<Sucursal> sucursalPorEmpresa(Integer idEmpresa){
        List<Sucursal> sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
              sucursal = conexionBD.selectList("sucursal.sucursalPorEmpresa",idEmpresa);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return sucursal;
    }
    
    public static List<Sucursal> sucursales(){
        List<Sucursal> sucursal = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
              sucursal = conexionBD.selectList("sucursal.sucursales");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return sucursal;
    }
}
