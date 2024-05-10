
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
            
            //Creando objeto de PREPAREDSTATEMENT para sql2  //Invocando a EXECUTEUPDATE para ps2
            int filas2=pStat(conexion, sql2).executeUpdate();
             if(filas2 >0){
                 JOptionPane.showMessageDialog(null, "herramienta/s agregada/s exitosamente!");             
             }
             
            String sql3 = "SELECT * FROM herramienta WHERE stock > 10";
            
            //Creando objeto de PREPAREDSTATEMENT para sql3
//            PreparedStatement ps3 = conexion.prepareStatement(sql3);
            
            //Creando objeto ResultSet para ejecutar la consulta sql3         
            ResultSet rs = pStat(conexion, sql3).executeQuery();
               
            //ResultSet de la consulta
            System.out.println("Herramientas con stock superior a 10:");
            while (rs.next()) {
                System.out.println("ID Herramienta: " + rs.getInt("id_herramienta")+ ", Nombre: " + rs.getString("nombre")+ ", Descripcion: " 
                        +rs.getString("descripcion") + ", Stock: " + rs.getInt("stock") + ", Estado: " + rs.getInt("estado"));
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
