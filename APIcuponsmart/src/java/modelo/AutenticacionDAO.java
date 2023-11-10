
package modelo;

import java.util.HashMap;
import modelo.pojo.Empleado;
import modelo.pojo.RespuestaLoginEscritorio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AutenticacionDAO {
    public static RespuestaLoginEscritorio verificarInicioSesionEscritorio(String nombreUsuario, String contraseña){
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("nombreUsuario",nombreUsuario);
                parametros.put("contraseña",contraseña);
                Empleado empleadoSesion = conexionBD.selectOne("autenticacion.inicioSesion",parametros);
                if(empleadoSesion != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenid@ "+empleadoSesion.getNombreUsuario() + " al sistema de cuponSmart");
                    respuesta.setMedicoSesion(empleadoSesion);
                }else{
                    respuesta.setMensaje("correo y/o contraseña incorrectas, favor de verificar sus credenciales");
                }
            }catch(Exception e){
                respuesta.setMensaje("Error: " + e.getMessage());
            }
    }else{
            respuesta.setMensaje("Hubo un error con la conexion a la base de datos.");
        }
        return respuesta;
    }
 }
