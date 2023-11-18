package modelo;

import modelo.pojo.Cliente;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ClienteDAO {
    public static Mensaje agregarCliente(Cliente cliente){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Cliente correo = conexionBD.selectOne("cliente.verificarCorreo", cliente.getCorreo());
                if (correo == null){
                int numeroFilasAfectadas = conexionBD.insert("cliente.agregarCliente", cliente);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0 ){
                    msj.setError(false);
                    msj.setMensaje(cliente.getNombre()+" se ha registrado correctamente");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
                }else{
                    msj.setMensaje("El correo ya se encuentra registrado");
                }
            }catch(Exception e){
                msj.setMensaje("Error " +e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Sin conexion a la base de datos, intentelo más tarde");
        }
        return msj;
    }
}
