package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Chequeo;

public class CategoriaDAO {
	
	public String[] consultar_tabla(){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
	
    	conn = con.conectar();
    	
    	String[] categorias = new String[3];
    	
    	try {
    		   
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            int c = 0;
            while(rs.next())
            {
            	    String cat = rs.getString("categoria");
            		categorias[c] = cat;
                       c++;	
            }
            
            
         } catch (Exception e) {

             e.printStackTrace();

         }
         finally {
        	 
        	 
         	try {
 				conn.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
         }
    	return categorias;
	}


public int buscarId(String categoria) {
	ConexionJDBC con = new ConexionJDBC();
	Connection conn = null;

	conn = con.conectar();
	
	int numero=0;
	
	try {
		   
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias WHERE categoria = ?");

        stmt.setString(1,categoria);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next())
        {
        	int id = rs.getInt("id_categoria");
        	numero = id;
        }
        
        
     } catch (Exception e) {

         e.printStackTrace();

     }
     finally {
    	 
    	 
     	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
     }
	return numero;
}
	
}