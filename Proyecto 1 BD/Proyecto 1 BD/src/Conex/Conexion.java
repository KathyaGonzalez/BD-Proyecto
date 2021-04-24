//InserCorrecta
package Conex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Conexion {
    public Connection con;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root"; //esto no lo toquen, todo va a ser en root supongo, si alguien le colocó otro usuario pues cambienlo
    private static final String pass="Tr4c30n"; //aquí tiene que ir su contra personal de la bd
    private static final String url="jdbc:mysql://localhost:3306/bd_contable";
    public ArrayList<String> lista= new ArrayList();
    public ArrayList<String> listaS= new ArrayList();
    public void conector() {
        con=null;
        try{
            Class.forName(driver);
            con= (Connection) DriverManager.getConnection(url, user, pass);
            if (con!=null){
                //JOptionPane.showMessageDialog(null, "Si enlazó");//esto pueden volverlo comentario
            }
        }
        catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "¡¡No enlazó!!");//pueden volverlo comentario
        }
    }
    //############buscar valores
    public boolean Buscar(String tabla, String valor) 
    {
        String comparacion="";
        String sql1="SELECT Id FROM "+tabla+" WHERE Id="+valor+"";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql1);
            while(rs.next())
            {
                comparacion=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!"".equals(comparacion))
        {
            return true;
        }
        return false;
    }
    //buscar 2
    public String BuscarC(String tabla, String valor) 
    {
        String comparacion="";
        String sql1="SELECT Nombre_Asignado FROM "+tabla+" WHERE Id="+valor+"";
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql1);
            while(rs.next())
            {
                comparacion=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comparacion;
    }
    
    //LOGIN
    
    public int Login(String User, String Pass){
        Integer resultado = 0;
        try{
            Statement ejecutar = con.createStatement();
            ResultSet rs = ejecutar.executeQuery("Select * from usuario where Name = '"+User+"' and Password = '"+Pass+"'");
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Bienvenido");
                resultado =1;
            }
            else {
                JOptionPane.showMessageDialog(null, "Problemas con Ususario y/o Contraseña");
                resultado =0;
            }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Error al conectar" + e.getMessage(), e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    //Consultas
    
    //Recibe consultas
    public ResultSet consulta (String sql){
        ResultSet res = null;
        try{
            PreparedStatement pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            
        } catch(SQLException e){
            System.err.println("Error consulta:" + e.getMessage());
        }
        return res;
    }
    
    //Coloca las consultas en la combobox
    public DefaultComboBoxModel Obt_Codigo(){
        DefaultComboBoxModel ListarCodigo = new DefaultComboBoxModel();
        ListarCodigo.addElement("Seleccione una clave");
        ResultSet res = this.consulta("Select * from cuenta order By Id");
        
        try{
            while(res.next()){
                ListarCodigo.addElement(res.getString("Id"));             
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListarCodigo;
    }
    
    public DefaultComboBoxModel Obt_Doc(){
        DefaultComboBoxModel ListarDoc = new DefaultComboBoxModel();
        ListarDoc.addElement("Seleccione un documento");
        ResultSet res = this.consulta("Select * from documento order By Id");
        
        try{
            while(res.next()){
                ListarDoc.addElement(res.getString("Nombre"));
                lista.add(res.getString("Id"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListarDoc;
    }
    
    public DefaultComboBoxModel Obt_Serv(){
        DefaultComboBoxModel ListarServ = new DefaultComboBoxModel();
        ListarServ.addElement("Seleccione un servicio");
        ResultSet res = this.consulta("Select * from servicio order By Id");
        
        try{
            while(res.next()){
                ListarServ.addElement(res.getString("Tipo"));
                listaS.add(res.getString("Id"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListarServ;
    }
    
    //Coloca las consultas en la Lista
    
    public DefaultListModel MostrarDoc(){
        DefaultListModel ListarDoc = new DefaultListModel();
        ResultSet res = this.consulta("Select * from documento order By Id");
        
        try{
            while(res.next()){
                ListarDoc.addElement(res.getString("Nombre"));             
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListarDoc;
    }
    
    public DefaultListModel MostrarServ(){
        DefaultListModel ListarServ = new DefaultListModel();
        ResultSet res = this.consulta("Select * from servicio order By Id");
        
        try{
            while(res.next()){
                ListarServ.addElement(res.getString("Tipo"));             
            }
            res.close();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return ListarServ;
    }
            
    
}
