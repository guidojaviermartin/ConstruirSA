
package construirsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Javier
 */
public class ConstruirSA {
   
    
    public static void main(String[] args) {
        
        try {
            PreparedStatement ps; 
            //Cargando DRIVER
            Class.forName("org.mariadb.jdbc.Driver");
            
            //Creando atributos para la conexion
            String bd = "jdbc:mysql://localhost:3306/construirsa";
            String usr = "root";
            String pass = "";
            
            //Creando objeto conexion
            Connection conexion = DriverManager.getConnection(bd, usr, pass);
           
            //INSERT EMPLEADOS
            String sql = "INSERT INTO empleado(dni, apellido, nombre, acceso, estado)"
                       + "VALUES (2838847,'Argento','Pepe',1,1),"
                       + "(31543492,'Hetfield','James',1,1), "
                       + "(4574832,'Barilari','Adrian',1,1)";
                    
            //Creando objeto de PREPAREDSTATEMENT e Invocando a EXECUTEUPDATE para sql
             int filas=pStat(conexion, sql).executeUpdate();
             if(filas > 0){
                 JOptionPane.showMessageDialog(null, "Empleado/s agregado/s exitosamente!");             
             }
                       
            String sql2 = "INSERT INTO herramienta(nombre, descripcion, stock, estado)"
                        + "VALUES ('Pinza de punta', 'Pinza de 8 pulgadas con alicate',3,1),"
                        + "('Destornillador electrico','Makita',5,1)";
            
            int filas2=pStat(conexion, sql2).executeUpdate(); //llamando al metodo pStat de PREPAREDSTATEMENT con parametros sql2 y la conexion y ejecutando al mismo tiempo
             if(filas2 >0){
                 JOptionPane.showMessageDialog(null, "herramienta/s agregada/s exitosamente!");             
             }
             
            String sql5 = "SELECT * FROM herramienta WHERE stock > 10"; //QUERIE
                   
            ResultSet rs = pStat(conexion, sql5).executeQuery(); //Creando objeto rs del tipo ResultSet para almacenar la ejecucion de la consulta sql5  
           
            System.out.println("Herramientas con stock superior a 10:");
            while (rs.next()) {
                System.out.println("ID Herramienta: " + rs.getInt("id_herramienta")+ 
                        ", Nombre: " + rs.getString("nombre")+ 
                        ", Descripcion: " + rs.getString("descripcion")+ 
                        ", Stock: " + rs.getInt("stock") + 
                        ", Estado: " + rs.getInt("estado"));
            }
             
            String sql4 = "SELECT id_empleado AS pidEmp FROM empleado ORDER BY id_empleado"; //Buscando el ID del primer empleado
            ResultSet rs4 = pStat(conexion, sql4).executeQuery();
            
            if (rs4.next()) {
                int primer_idEmpleado =  rs4.getInt("pidEmp"); //guardo el valor del id del primer empleado de la BD    
                
                String sql4a = "SELECT estado AS estadoEmp FROM empleado WHERE id_empleado = ?";
                PreparedStatement ps4a = pStat(conexion, sql4a);
                ps4a.setInt(1, primer_idEmpleado); //reemplaza el "?" con el valor de "primer_idEmpleado"
                ResultSet rs4a = ps4a.executeQuery();//Resultado de la consulta de "estado" del primer empleado en la BD
                if(rs4a.next()){           
                    if(rs4a.getInt("estadoEmp")==1){
                       String sql6 = "UPDATE empleado SET estado=0 WHERE id_empleado = "+primer_idEmpleado;
                       int filas6 = pStat(conexion, sql6).executeUpdate();
                       if(filas6 > 0){
                            JOptionPane.showMessageDialog(null, "Baja de empleado exitoso!"); 
                       }
                       String sql7 = "SELECT * FROM empleado WHERE id_empleado = ?";
                       PreparedStatement ps7 = pStat(conexion, sql7);
                       ps7.setInt(1, primer_idEmpleado);
                       ResultSet rs7 = ps7.executeQuery();
                            if (rs7.next()) {
                                System.out.println("Datos del empleado dado de baja: ");
                                System.out.println("id_empleado: " + rs7.getInt("id_empleado")+ 
                                      ", DNI: " + rs7.getInt("dni")+
                                      ", Apellido: " + rs7.getString("apellido")+ 
                                      ", Nombre: " + rs7.getString("nombre") + 
                                      ", Acceso: " + rs7.getInt("acceso") + 
                                      ", Estado: " + rs7.getInt("estado"));
                            }
                    }else{
                        JOptionPane.showMessageDialog(null, "Solicitud invalida!\nEl estado actual es: \nBAJA");
                    }
                }
            }
                
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el Driver"+ex.getMessage());
            
        } catch (SQLException ex) {
            int codigoError = ex.getErrorCode();
            //System.out.println(codigoError);
            switch (codigoError) {
                case 1062:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Registro/s duplicado/s "+ex.getMessage());
                    break;
                case 1049:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: BD No existe o no se encuentra "+ex.getMessage());
                    break;
                case 1045:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Nombre de usuario y/o contraseña incorrecto/s: "+ex.getMessage());
                    break;
                case 1064:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Error de sintaxis: "+ex.getMessage());
                    break;
                case 1054:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Error de sintaxis en Columna: "+ex.getMessage());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: "+ex.getMessage());
            }     
        }
    }//Fin Main   
    public static PreparedStatement pStat(Connection conexion, String sql) throws SQLException {
       
    return conexion.prepareStatement(sql);
    }
}
