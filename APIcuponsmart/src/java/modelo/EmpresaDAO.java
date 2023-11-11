package modelo;

import modelo.pojo.Empresa;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class EmpresaDAO {
    
    public static Mensaje registrarEmpresa(Empresa empresa){
        Mensaje mensaje = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Empresa rfc = conexionBD.selectOne("empresa.empresaPorRFC", empresa.getRFC());
                if(rfc != null){
                    mensaje.setError(true);
                    mensaje.setMensaje("El rfc ya se encuentra registrado en una empresa");
                    conexionBD.close();
                    return mensaje;
                }
                int filasAfectadas = conexionBD.insert("empresa.registrarEmpresa", empresa);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    mensaje.setError(false);
                    mensaje.setMensaje("Empresa registrada exitosamente, " + filasAfectadas + " filas afectadas.");
                }else{
                    mensaje.setError(true);
                    mensaje.setMensaje("Error al guardar los datos, pruebe mas tarde.");
                }
            }catch(Exception e){
                e.printStackTrace();
                mensaje.setError(true);
                mensaje.setMensaje("Error al ejecutar la operacion." + e);
            }finally{
                conexionBD.close();
            }
        }else{
            mensaje.setError(true);
            mensaje.setMensaje("No hay conexion a la BD, prueba mas tarde.");
        }
        return mensaje;
    }
}
