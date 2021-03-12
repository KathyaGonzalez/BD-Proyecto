
package Conex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    private static Connection con;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root"; //esto no lo toquen, todo va a ser en root supongo, si alguien le colocó otro usuario pues cambienlo
    private static final String pass=""; //aquí tiene que ir su contra personal de la bd
    private static final String url="jdbc:mysql://localhost:3306/bd_contable";
    public void conector() {
        con=null;
        try{
            Class.forName(driver);
            con= (Connection) DriverManager.getConnection(url, user, pass);
            if (con!=null){
                JOptionPane.showMessageDialog(null, "Si enlazó");//esto pueden volverlo comentario
            }
        }
        catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "¡¡No enlazó!!");//pueden volverlo comentario
        }
    }
}
