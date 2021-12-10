package lab9p2_gabrielgiron_12051024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dba {
    private String base_datos;
    private Connection dbcon;
    public Statement query;
    
    public Dba(String base_datos){
        this.base_datos=base_datos;
    }

    public void setBase_datos(String base_datos) {
        this.base_datos = base_datos;
    }        
    
   public void conectar(){
        try {
            String driver = "jdbc:ucanaccess://";
            dbcon = DriverManager.getConnection(driver+base_datos,"","");
            query = dbcon.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public void desconectar() {
        try {
            query.close();
            dbcon.close();
        } catch (Exception e) {
        }
    }
   
   public void commit(){
       try{
           dbcon.commit();
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public boolean verificar(String user, String contra)
   {
       int V = 0;
       String sql = "Select Usuario,Contraseña,Tipo"
                   +"FROM User";
       try
       {
           PreparedStatement pstm = dbcon.prepareStatement(sql);
           ResultSet res = pstm.executeQuery();
           while(res.next())
           {
               System.out.println(res.getString("Usuario"));
               System.out.println(res.getString("Contraseña"));
               System.out.println(res.getString("Tipo"));
               V = 1;
           }
       }
       catch(SQLException ex)
       {
           ex.printStackTrace(); 
       }
       if(V == 1)
       {
           return true;
       }
       else 
       {
           return false;
       }
   }
}
