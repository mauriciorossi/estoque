package com.targettrust.imob.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mauricionb
 */
public class ConexaoDB{ 
    public static Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
    Connection con = null;
       try {    
           //CONEXAO POSTGRES
              //  String url = "jdbc:postgresql://localhost:5432/Rossi";
               // String usuario = "postgres";
                //String senha = "chica";  
            
            //CONEXAO MYSQL OPENSHIFT
             String url = "jdbc:mysql://127.7.152.131:3306/rossi";
             String usuario = "adminxNK3DsA";
             String senha = "weUbwSnF7fvp";  
            
            //CONEXAO MYSQL LOCAL
            //   String url = "jdbc:mysql://localhost/rossi";
            //   String usuario = "root";
            //   String senha = "root"; 
            
             //POSTGRES DRIVER
                // Class.forName("org.postgresql.Driver").newInstance(); 
              //MYSQL DRIVER
                Class.forName("com.mysql.jdbc.Driver").newInstance();  
            con = (Connection) DriverManager.getConnection(url, usuario, senha); 

        } catch (SQLException e) {
            System.out.println(e);
        }
       return con;
    }
    
}
