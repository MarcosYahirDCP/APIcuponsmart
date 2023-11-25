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
    
    public static Mensaje editarCliente(Cliente cliente){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Cliente clienteExiste = conexionBD.selectOne("clientePorId", cliente.getIdCliente());
                if(clienteExiste != null){
                int numeroFilasAfectadas = conexionBD.update("cliente.editarCliente",cliente);
                conexionBD.commit();
                if(numeroFilasAfectadas > 0 ){
                    msj.setError(false);
                    msj.setMensaje(cliente.getNombre()+" se ha editado correctamente la información");
                }else{
                    msj.setMensaje("Error al enviar los datos");
                }
                }else{
                    msj.setMensaje("El cliente con id " +cliente.getIdCliente() + " No existe");
                }
            }catch(Exception e){
                msj.setMensaje("Error: "+ e);
            }finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("Error en la conexión, intentelo más tarde");
        }
        return msj;
    }
    
    public static Cliente clientePorId(Integer idCliente){
        Cliente cliente = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
         try{
             cliente = conexionBD.selectOne("cliente.clientePorId", idCliente);
         }catch (Exception e){
             e.printStackTrace();
         }finally{
             conexionBD.close();
         }   
        }
        return cliente;
    }
}
