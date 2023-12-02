
package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CatalogoDAO {
    
    public static List<Estado> obtenerEstados(){
        List<Estado> estados = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                estados = conexionBD.selectList("catalogo.obtenerEstados");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return estados;
    }
    
    public static List<Municipio> obtenerMunicipiosEstados(Integer idEstado){
        List<Municipio> municipios = new ArrayList<>();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                municipios = conexionBD.selectList("catalogo.obtenerMunicipiosEstados", idEstado);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return municipios;
    }
}
