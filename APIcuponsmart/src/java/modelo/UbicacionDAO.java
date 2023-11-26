package modelo;

import modelo.pojo.Mensaje;
import modelo.pojo.Ubicacion;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UbicacionDAO {
    
    public static Mensaje agregarUbicacion(Ubicacion ubicacion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numFilasAfectadas = conexionBD.insert("ubicacion.agregarUbicacion", ubicacion);
                conexionBD.commit();
                if(numFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Ubicación agregada con exito");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
            }catch(Exception e){
                msj.setMensaje("Error: " + e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexion, intentelo más tarde");
        }
        return msj;
    }
    
    public static Mensaje editarUbicacion(Ubicacion ubicacion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
            Ubicacion ubicacionExiste = conexionBD.selectOne("ubicacion.obtenerUbicacion", ubicacion.getIdUbicacion());
            if(ubicacionExiste != null){
                int numFilasAfectadas = conexionBD.update("ubicacion.editarUbicacion", ubicacion);
                if(numFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Ubicacion editada correctamente");
                }
                else{
                    msj.setMensaje("Error al enviar los datos");
                }
            }else{
                msj.setMensaje("Ubicacion con id:"+ubicacion.getIdUbicacion()+" no existe");
            }
            }catch(Exception e){
                msj.setMensaje("Error: " + e);
            }finally{
                conexionBD.close();
            }
            
        }else{
            msj.setMensaje("Error con la conexión, intentelo más tarde");
        }
        return msj;
    }
    
    public static Ubicacion obtenerUbicacion(Integer idUbicacion){
        Ubicacion ubicacion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        try{
            ubicacion = conexionBD.selectOne("ubicacion.obtenerUbicacion", idUbicacion);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conexionBD.close();
        }
        return ubicacion;
    }
}
