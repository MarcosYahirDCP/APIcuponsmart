package modelo;

import java.util.List;
import modelo.pojo.Mensaje;
import modelo.pojo.Promocion;
import modelo.pojo.PromocionSucursal;
import modelo.pojo.Sucursal;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class PromocionDAO {
    
    public static Mensaje agregarPromocion(Promocion promocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Promocion codigoPromocion = conexionBD.selectOne("promocion.verificarCodigo", promocion.getCodigoPromocion());
                if(codigoPromocion == null){
                    int numeroFilasAfectadas = conexionBD.insert("promocion.agregarPromocion", promocion);
                    conexionBD.commit();
                    if(numeroFilasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje(promocion.getNombre() + " Promocion agregada correctamente");
                    }else{
                        msj.setMensaje("Error al enviar los datos");
                    }
                }else{
                    msj.setMensaje("El codigo ingresado ya existe");
                }
            }catch(Exception e){
                msj.setMensaje("Error "+e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
    
    public static Mensaje editarPromocion(Promocion promocion){
        Mensaje msj =  new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Promocion promocionExiste = conexionBD.selectOne("promocion.promocionPorId", promocion.getIdPromocion());
                if(promocionExiste != null){
                    int numeroFilasAfectadas = conexionBD.update("promocion.editarPromocion", promocion);
                    conexionBD.commit();
                    if(numeroFilasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje("Promocion editada correctamente");
                    }else{
                        msj.setMensaje("Error al enviar los datos");
                    }
                }else{
                    msj.setMensaje("La promocion "+ promocion.getIdPromocion() +" no existe");
                }
            }catch(Exception e){
                msj.setMensaje("error "+e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
    
    public static Mensaje eliminarPromocion(int idPromocion){
        Mensaje msj =  new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numeroFilasAfectadas = conexionBD.delete("promocion.eliminarPromocion", idPromocion);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Promocion eliminada correctamente");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
            }catch(Exception e){
                msj.setMensaje("error "+e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
    public static Promocion promocionPorNombre(String nombre){
        Promocion promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectOne("promocion.promocionNombre", nombre);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static Promocion detallesPromocion(Integer idPromocion){
        Promocion promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectOne("promocion.promocionDetalle", idPromocion);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static Promocion promocionPorId(Integer idPromocion){
        Promocion promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectOne("promocion.promocionPorId", idPromocion);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static List<Promocion> promocionPorCategoria(Integer idCategoria){
        List<Promocion> promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectList("promocion.promocionCategoria", idCategoria);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static List<Promocion> promocionFechaInicio(String inicioPromocion){
        List<Promocion> promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectList("promocion.fechaInicio", inicioPromocion);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static List<Promocion> listarPromociones(){
        List<Promocion> promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectList("promocion.promociones");
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return promocion;
    }
    
    public static Mensaje promocionPorSucursal(PromocionSucursal promocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               Promocion promocionExiste = conexionBD.selectOne("promocion.promocionPorId", promocion.getIdPromocion());
               Sucursal sucursalExiste = conexionBD.selectOne("sucursal.sucursalPorId",promocion.getIdSucursal());
               if(promocionExiste != null && sucursalExiste != null){
                   int numFilasAfectadas = conexionBD.insert("promocion.promocionPorSucursal", promocion);
                   conexionBD.commit();
                   if(numFilasAfectadas > 0){
                       msj.setError(false);
                       msj.setMensaje("Promocion registrada con exito");
                   }else{
                       msj.setMensaje("Error al enviar los datos");
                   }
               }else{
                   msj.setMensaje("promocion y/o sucursal no existe");
               }
            }catch(Exception e){
                msj.setMensaje("Error "+e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
}
