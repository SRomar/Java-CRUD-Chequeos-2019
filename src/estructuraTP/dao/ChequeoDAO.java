package estructuraTP.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


import estructuraTP.modelo.Chequeo;

public class ChequeoDAO implements DAOGenerico {

	public void Alta (Chequeo ch) {
		
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();

    	String frase = ch.getFrase();
    	String autor_frase = ch.getAutor_frase();
    	String medio_frase = ch.getMedio_frase();
    	int categoria_frase = ch.getCategoria_frase();
    	String enlace_frase = ch.getEnlace_frase();
    	LocalDate fecha_frase = ch.getFecha_frase();

    	int res_verif = ch.getRes_verif();
    	String palabras_clave = ch.getPalabras_clave();
    
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("INSERT INTO chequea2.chequeados (categoria, palabra_clave, estado, frase, enlace, sitio, fecha, autor) VALUES (?,?,?,?,?,?,?,?);");

           stmt.setInt(1, categoria_frase);
           stmt.setString(2, palabras_clave);
           stmt.setInt(3, res_verif);
           stmt.setString(4, frase);
           stmt.setString(5, enlace_frase);
           stmt.setString(6, medio_frase);
           stmt.setDate(7, java.sql.Date.valueOf(fecha_frase));
           stmt.setString(8, autor_frase);

           stmt.executeUpdate();

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
    }

	public void Baja (int id) {
    	ConexionJDBC con =new ConexionJDBC();
    	Connection conn= null;
    	conn=con.conectar();
    	

        try {
 
           PreparedStatement myPs =(PreparedStatement) conn.prepareStatement("DELETE FROM `chequeados` WHERE id_chequeo = ?");
           myPs.setInt(1, id );

           
           myPs.executeUpdate();
          
               		     
            
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
	}

	public void Actualizar (Chequeo ch, int id) {
			
			ConexionJDBC con = new ConexionJDBC();
	    	Connection conn = null;
	    	
	    	conn = con.conectar();

	    	
	        try {
	   
	        	PreparedStatement stmt = conn.prepareStatement("UPDATE chequeados SET categoria = ?, palabra_clave = ?, estado = ?, frase = ?, enlace = ?, sitio = ?, fecha = ?, autor = ? WHERE id_chequeo = ?");
		        
		           stmt.setInt(1, ch.getCategoria_frase());
		           stmt.setString(2, ch.getPalabra_clave());
		           stmt.setInt(3, ch.getRes_verif());
		           stmt.setString(4, ch.getFrase());
		           stmt.setString(5, ch.getEnlace_frase());
		           stmt.setString(6, ch.getMedio_frase());
		           stmt.setDate(7, java.sql.Date.valueOf(ch.getFecha_frase()));
		           stmt.setString(8, ch.getAutor_frase());
		           stmt.setInt(9, id);
		           
		           
		           stmt.executeUpdate();
	           	 
	        } catch (Exception ex) {

	            ex.printStackTrace();

	        }
	        finally {
	        	try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
	        }
	    }

	public ArrayList<Chequeo> consultar_tabla(){
		
	ConexionJDBC con = new ConexionJDBC();
	Connection conn = null;
	
	
	ArrayList<Chequeo> chequeos = new ArrayList<>();
	conn = con.conectar();
	
	
	
	try {
		   
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM chequeados");

        while(rs.next())
        {
        	int id = rs.getInt("id_chequeo");
        	String frase = rs.getString("frase");
        	int estado = rs.getInt("estado");          	
        	String autor = rs.getString("autor");
        	int categoria = rs.getInt("categoria");
        	String palabra = rs.getString("palabra_clave");
        	String enlace = rs.getString("enlace");
        	String sitio = rs.getString("sitio");
        	
        	Chequeo c = new Chequeo();
        	c.setPalabra_clave(palabra);
        	c.setId(id);
        	c.setCategoria_frase(categoria);
        	c.setFrase(frase);
        	c.setAutor_frase(autor);
        	c.setRes_verif(estado);
        	c.setEnlace_frase(enlace);
        	c.setMedio_frase(sitio);
        	
        	chequeos.add(c);
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
	return chequeos;
}
	
	public Chequeo obtenerChequeo(int id) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    	
    	Chequeo c = new Chequeo();
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM chequeados WHERE id_chequeo = ?");
          
           stmt.setInt(1, id);
         
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   String frase = rs.getString("frase");
        	   String autor = rs.getString("autor");
        	   String sitio = rs.getString("sitio");
        	   int categoria = rs.getInt("categoria");
        	   String enlace = rs.getString("enlace");
        
        	   int estado = rs.getInt("estado");
        	   String palabra_clave = rs.getString("palabra_clave");
        	   LocalDate firstDate1 = rs.getDate("fecha").toLocalDate();
        	   
        	   c.setId(id);
        	   c.setFrase(frase);
			   c.setAutor_frase(autor);
			   c.setMedio_frase(sitio);
			   c.setCategoria_frase(categoria);
			   c.setEnlace_frase(enlace);
			   c.setFecha_frase(firstDate1);
			   c.setRes_verif(estado);
			   c.setPalabras_clave(palabra_clave);
        	 
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
       
        return c;
	}
	
	public ArrayList<Chequeo> consultar_repositorio(){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    	
    	ArrayList<Chequeo> repos = new ArrayList<>();
    	
    	
    	
        try {
   
           Statement stmt = conn.createStatement();
                    
           ResultSet rs = stmt.executeQuery("SELECT * FROM repositorio");
           
           while(rs.next()) {
        	   Chequeo c = new Chequeo();
        	   String frase = rs.getString("frase");
        	   String enlace = rs.getString("enlace");
        	   String sitio = rs.getString("sitio");
        	   LocalDate fecha = rs.getDate("fecha").toLocalDate();
        	   
        	   c.setFrase(frase);
        	   c.setEnlace_frase(enlace);
        	   c.setMedio_frase(sitio);
        	   c.setFecha_frase(fecha);
        	   
        	   repos.add(c);
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
       
        return repos;
	}

	public int buscarIdRepositorio(String frase, String enlace, String sitio) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    	int ID=0;
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM repositorio WHERE frase = ? and enlace = ? and sitio = ?");
                
           stmt.setString(1, frase);
           stmt.setString(2, enlace);
           stmt.setString(3, sitio);
          
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   int id = rs.getInt("id");
        	   ID = id;
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
       
        return ID;
	}

	public void eliminarRepos(int id) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    		
        try {
   
           PreparedStatement stmt = conn.prepareStatement("DELETE FROM repositorio WHERE id = ?");
                
           stmt.setInt(1, id);
           
           stmt.executeUpdate();

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
       
	}
	
	
	
	public int buscarId(String a, String b, String c) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
    	int id=0;
    	int est = new Integer(c);
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM chequeados WHERE frase = ? and autor = ? and estado = ?");
          
           stmt.setString(1, a);
           stmt.setString(2, b);
           stmt.setInt(3, est);
         
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   
        	   id = rs.getInt("id_chequeo");
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
        
        return id;
	}
	
	public ArrayList<Chequeo> potencialesExplicaciones(){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    		
    	ArrayList<Chequeo> expPotenciales = new ArrayList<>();
    	
        try {
   
           Statement stmt = conn.createStatement();
                
           ResultSet rs = stmt.executeQuery("SELECT id_chequeo, COUNT(enlace) AS total FROM chequeados GROUP BY enlace");
           
           while(rs.next()) {
        	   int id = rs.getInt("id_chequeo");
        	   int cant = rs.getInt("total");
        	   
        	   if(cant>2) {
        		   expPotenciales.add(obtenerChequeo(id));
        	   }
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
        
        return expPotenciales;
	}



    public ArrayList<Chequeo> mismoEnlace(String enlace){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    		
    	ArrayList<Chequeo> chequeos = new ArrayList<>();
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT id_chequeo FROM chequeados WHERE enlace = ?");
                
           stmt.setString(1, enlace);
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   Chequeo ch = new Chequeo();
        	   int id = rs.getInt("id_chequeo");
        	   ch = obtenerChequeo(id);
        	   chequeos.add(ch);
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
        
        return chequeos;
	}

	
	
	
	
}
