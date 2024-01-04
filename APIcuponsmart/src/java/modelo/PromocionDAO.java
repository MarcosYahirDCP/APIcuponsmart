package modelo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.pojo.CanjeoCupon;
import modelo.pojo.Categoria;
import modelo.pojo.Cliente;
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
    
    public static List<Promocion> listarPromocionesPorEmpresa(int idEmpresa){
        List<Promocion> promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                promocion = conexionBD.selectList("promocion.promocionesPorEmpresa", idEmpresa);
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return promocion;
    }
    
    public static Mensaje promocionPorSucursal(PromocionSucursal promocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               Promocion promocionExiste = conexionBD.selectOne("promocion.verificarCodigo", promocion.getCodigoPromocion());
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
    
    public static Mensaje eliminarPromocionPorSucursal(PromocionSucursal promocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               Promocion promocionExiste = conexionBD.selectOne("promocion.verificarCodigo", promocion.getCodigoPromocion());
               Sucursal sucursalExiste = conexionBD.selectOne("sucursal.sucursalPorId",promocion.getIdSucursal());
               if(promocionExiste != null && sucursalExiste != null){
                   int numFilasAfectadas = conexionBD.delete("eliminarPromocionPorSucursal", promocion);
                   conexionBD.commit();
                   if(numFilasAfectadas > 0){
                       msj.setError(false);
                       msj.setMensaje("Promocion Eliminada con exito");
                   }else{
                       msj.setMensaje("Error al eliminar");
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
    
    public static Mensaje verPromocionPorSucursal(PromocionSucursal promocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               Promocion promocionExiste = conexionBD.selectOne("promocion.verificarCodigo", promocion.getCodigoPromocion());
               Sucursal sucursalExiste = conexionBD.selectOne("sucursal.sucursalPorId",promocion.getIdSucursal());
               if(promocionExiste != null && sucursalExiste != null){
                   PromocionSucursal promo = conexionBD.selectOne("verPromocionPorSucursal", promocion);
                   conexionBD.commit();
                   if(promo != null){
                       msj.setError(false);
                       msj.setMensaje("La sucursal ya está asignada a esta promoción");
                   }else{
                       msj.setMensaje("La sucursal no está asignada a esta promoción");
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
    
    public static Boolean cuponDisponible(String codigoPromocion){
        SqlSession conexionBD = MyBatisUtil.getSession();
        Boolean disponible = false;
        if(conexionBD != null){
         try{
             Promocion estatus = conexionBD.selectOne("promocion.verificarCodigo", codigoPromocion);
             if(estatus.getEstatus().equals("Activo")){
                 disponible = true;
             }
         }catch(Exception e){
             e.printStackTrace();
         }   
        }
        return disponible;
    }
    
    public static Mensaje inhabilitarCupon(String codigoPromocion){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
         try{
             Promocion cupones = conexionBD.selectOne("promocion.verificarCodigo", codigoPromocion);
             if(cupones.getNumeroCupones() == 0){
                 msj.setError(false);
                 cupones.setEstatus("Inactivo");
                 editarPromocion(cupones);
             }
             else{
                 msj.setMensaje("Aun quedan " +cupones.getNumeroCupones() + " disponibles");
             }
         }catch(Exception e){
             e.printStackTrace();
         }   
        }
        return msj;
    }
    
    public static Boolean cuponPorUsuario(CanjeoCupon canjeo){
        SqlSession conexionBD = MyBatisUtil.getSession();
        Boolean disponible = true;
        if(conexionBD != null){
         try{
             HashMap<String, String> parametros = new HashMap<>();
                parametros.put("correo",canjeo.getCorreo());
                parametros.put("codigoPromocion",canjeo.getCodigoPromocion());
                CanjeoCupon usuario = conexionBD.selectOne("promocion.clientePromocion", parametros);
                if(usuario!=null){
                 disponible = false;
                }
             
         }catch(Exception e){
             e.printStackTrace();
         }   
        }
        return disponible;
    }
    
    public static Boolean promocionEnSucursal(String codigoPromocion, Integer idSucursal){
        SqlSession conexionBD = MyBatisUtil.getSession();
        Boolean disponible = false;
        if(conexionBD != null){
         try{
             HashMap<String, String> parametros = new HashMap<>();
             parametros.put("codigoPromocion", codigoPromocion);
             parametros.put("idSucursal", idSucursal.toString());
             PromocionSucursal existe = conexionBD.selectOne("promocion.promocionEnSucursal", parametros);
                if(existe!=null){
                 disponible = true;
                }
         }catch(Exception e){
             e.printStackTrace();
         }   
        }
        return disponible;
    }
    
    public static Mensaje canjeoCupon (CanjeoCupon canjeo){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Boolean correoExiste = cuponPorUsuario(canjeo);
                if(correoExiste.equals(true)){
                    Boolean disponible = cuponDisponible(canjeo.getCodigoPromocion());
                    if(disponible.equals(true)){
                        Cliente clienteExiste = conexionBD.selectOne("cliente.verificarCorreo", canjeo.getCorreo());
                        if(clienteExiste != null){
                            Boolean promocionSucursal = promocionEnSucursal(canjeo.getCodigoPromocion(), canjeo.getIdSucursal());
                            //if (promocionSucursal.equals(true)){
                                int numFilasAfectadas = conexionBD.insert("promocion.canjeoCupon", canjeo);
                                conexionBD.commit();
                                if(numFilasAfectadas > 0){
                                    msj.setError(false);
                                    msj.setMensaje("Promocion canjada con exitó");
                                    Promocion cupones = conexionBD.selectOne("promocion.verificarCodigo", canjeo.getCodigoPromocion());
                                    cupones.setNumeroCupones(cupones.getNumeroCupones()-1);
                                    editarPromocion(cupones);
                                    inhabilitarCupon(canjeo.getCodigoPromocion());
                                }else{
                                    msj.setMensaje("Error al canjear el cupon");
                                }
                            //}else{
                                //msj.setMensaje("La promocion no se encuentra disponible en la sucursal");
                            //}
                        }else{
                            msj.setMensaje("El correo del cliente no se encuentra registrado");
                        }
                    }else{
                        msj.setMensaje("El cupon no se encuentra activo");
                    }
                }else{
                    msj.setMensaje("El usuario ya canjeo el cupon");
                }
            }catch (Exception e){
                msj.setMensaje("Error "+ e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
    
    public static List<Categoria> listaCategorias(){
        List<Categoria> categorias = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                categorias = conexionBD.selectList("promocion.categorias");
                conexionBD.commit();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
            
        }
        return categorias;
    }
    
    //------------------------------------- Registrar foto Empresa ----------------------------------------------------------\\ 
    public static Mensaje registrarImgPromo(int idPromocion, byte[] imagen){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idPromocion", idPromocion);
        parametros.put("imagen", imagen);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("promocion.guardarImagen", parametros);
                conexionBD.commit();

                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Imagen de la promocion guardada correctamente.");
                } else {
                    msj.setMensaje("Lo sentimos, hubo un error al intentar guardadar la imagen, por favor intentelo más tarde.");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            msj.setMensaje("Error de conexión, por el momento no se úede registrar La imagen de la promoción");
        }
        
        return msj;
    }
    
    //------------------------------------- Obtener Logo empresa ----------------------------------------------------------\\ 
    public static Promocion obtenerImgPromo(int idPromocion){
        Promocion promocion = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                promocion = conexionBD.selectOne("promocion.obtenerImagen", idPromocion);
                System.out.println(promocion);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return promocion;
    
    }
    
}
