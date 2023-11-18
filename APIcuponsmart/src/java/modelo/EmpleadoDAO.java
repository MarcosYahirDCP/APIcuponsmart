
package modelo;

import modelo.pojo.Empleado;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpleadoDAO {
   
    public static Mensaje agregarEmpleado(Empleado empleado){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empleado curp = conexionBD.selectOne("empleado.verificarCurp", empleado.getCURP());
                Empleado correo = conexionBD.selectOne("empleado.verificarCorreo", empleado.getCorreo());
                if(curp ==null && correo == null){
                   int filasAfectadas = conexionBD.insert("empleado.agregarEmpleado", empleado);
                    conexionBD.commit();
                    if(filasAfectadas > 0){
                        msj.setError(false);
                        msj.setMensaje("Empleado registrado exitosamente");
                    }else{
                        msj.setMensaje("Error al guardar los datos, pruebe mas tarde.");
                    }
                }else{
                    msj.setMensaje("correo y/o curp se encuentran registrados");
                }
            }catch(Exception e){
                msj.setMensaje("Error "+ e);
            }finally{
                conexionBD.close();
            }
        }else{
         msj.setMensaje("Error con la conexion, intentelo más tarde");
        }
        return msj;
    }
    
    public static Mensaje editarEmpleado(Empleado empleado){
        Mensaje msj =  new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numeroFilasAfectadas = conexionBD.update("empleado.editarEmpleado", empleado);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Usuario editado con exito");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
            }catch(Exception e){
                msj.setMensaje("Error al encair los datos " + e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error al conectarse con la base de datos");
        }
        return msj;
    }
    
    public static Mensaje eliminarEmpleado(int idEmpleado){
        Mensaje msj =  new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int numeroFilasAfectadas = conexionBD.delete("empleado.eliminarEmpleado", idEmpleado);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0){
                  msj.setError(false);
                    msj.setMensaje("Usuario eliminado con exito");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
            }catch(Exception e){
                msj.setMensaje("Error al encair los datos " + e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error al conectarse con la base de datos");
        }
        return msj;
    }
    
    public static Empleado empleadoPorRol(Integer idRol){
        Empleado empleado = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empleado = conexionBD.selectOne("empleado.empleadoPorRol", idRol);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
        }
        return empleado;
    }
    
    public static Empleado empleadoPorNombre(String nombre){
        Empleado empleado = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empleado = conexionBD.selectOne("empleado.empleadoPorNombre", nombre);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
        }
        return empleado;
    }
    
    public static Empleado empleadoPorNombreUsuario(String nombreUsuario){
        Empleado empleado = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                empleado = conexionBD.selectOne("empleado.empleadoPorNombreUsuario", nombreUsuario);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }else{
        }
        return empleado;
    }
}