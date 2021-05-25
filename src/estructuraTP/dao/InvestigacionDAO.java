package estructuraTP.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;



public class InvestigacionDAO implements DAOGenerico {

	  public void Alta (Investigacion unaInvestigacion) {
	       
	    	ConexionJDBC con =new ConexionJDBC();
	    	Connection conn= null;
	    	conn=con.conectar();
	    	
	    	 int  categoria= unaInvestigacion.getCategoria();
	         String tema = unaInvestigacion.getTema();
	         String palabraclave=unaInvestigacion.getPalabras_clave();
	         String contenido=unaInvestigacion.getContenido();
	         String epigrafe=unaInvestigacion.getEpigrafe();
	         Date fecha = Date.valueOf(unaInvestigacion.getFechaCreacion());	         
	         String titulo=unaInvestigacion.getTitulo();
	    	
	    	try {
	   
	        
	         
	            
	           PreparedStatement myPs =(PreparedStatement) conn.prepareStatement("INSERT INTO investigaciones (categoria, palabra_clave, contenido, tema, titulo, epigrafe, fecha) VALUES (?,?,?,?,?,?,?)");
	           myPs.setInt(1, categoria );
	           myPs.setString(2,palabraclave);
	           myPs.setString(3, contenido);
	           myPs.setString(4, tema);
	           myPs.setString(5, titulo);
	           myPs.setString(6, epigrafe);
	           myPs.setDate(7, fecha);
	           
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
	    
	    public void Baja (int id) {
	    	ConexionJDBC con =new ConexionJDBC();
	    	Connection conn= null;
	    	conn=con.conectar();
	    	

	        try {
	 
	           PreparedStatement myPs =(PreparedStatement) conn.prepareStatement("DELETE FROM `investigaciones` WHERE id_investigacion = ?");
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
	     	    
	    public ArrayList<Investigacion> consultar_tabla(){
			ConexionJDBC con = new ConexionJDBC();
	    	Connection conn = null;
	    	
	    	
	    	ArrayList<Investigacion> investigaciones = new ArrayList<>();
	    	conn = con.conectar();
	    	
	    	
	    	
	    	try {
	    		   
	            Statement stmt = conn.createStatement();

	            ResultSet rs = stmt.executeQuery("SELECT * FROM investigaciones");

	            while(rs.next())
	            {
	            	Investigacion i = new Investigacion();
	            	
	            	String contenido = rs.getString("contenido");
	            	String tema = rs.getString("tema");          	
	            	String titulo = rs.getString("titulo");
	            	String epigrafe = rs.getString("epigrafe");	
	            	int categoria = rs.getInt("categoria");
	            	String palabra = rs.getString("palabra_clave");
	            	
	            	i.setPalabras_clave(palabra);
	            	i.setCategoria(categoria);
	            	i.setContenido(contenido);
	            	i.setTema(tema);
	            	i.setTitulo(titulo);
	            	i.setEpigrafe(epigrafe);
	            	
	            	investigaciones.add(i);
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
	    	return investigaciones;
		}
	  	    
	    public void Actualizar(Investigacion inv, int id) {
	    	ConexionJDBC con =new ConexionJDBC();
	    	Connection conn= null;
	    	conn=con.conectar();
	    	
   	
	        try {
	  
	        	


	        	PreparedStatement stmt= conn.prepareStatement("UPDATE investigaciones SET categoria = ?, palabra_clave = ?, contenido = ?, tema = ?, titulo = ?, epigrafe = ? WHERE id_investigacion = ?");
	        	stmt.setInt(1, inv.getCategoria());
	        	stmt.setString(2, inv.getPalabras_clave());
	        	stmt.setString(3, inv.getContenido());
	        	stmt.setString(4, inv.getTema());
	        	stmt.setString(5, inv.getTitulo());
	        	stmt.setString(6, inv.getEpigrafe());
	        	//stmt.setDate(7, fe);
	        	stmt.setInt(7, id);
	        	stmt.executeUpdate();
	        	
	        	
	        } catch(SQLException e){
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
	        try {
	   
	           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM investigaciones WHERE contenido = ? and tema = ? and titulo = ?");
	          
	           stmt.setString(1, a);
	           stmt.setString(2, b);
	           stmt.setString(3, c);
	           
	         
	           
	           ResultSet rs = stmt.executeQuery();
	           
	           while(rs.next()) {
	        	   
	        	   id = rs.getInt("id_investigacion");
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
	        System.out.println(id);
	        return id;
		}

	    public Investigacion obtenerInvestigacion(int id) {
			ConexionJDBC con = new ConexionJDBC();
	    	Connection conn = null;

	    	conn = con.conectar();
	    	
	    	Investigacion i = new Investigacion();
	    	
	        try {
	   
	           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM investigaciones WHERE id_investigacion = ?");
	          
	           stmt.setInt(1, id);
	         
	           
	           ResultSet rs = stmt.executeQuery();
	           
	           while(rs.next()) {
	        	   String titulo = rs.getString("titulo");
	        	   String epigrafe = rs.getString("epigrafe");
	        	   String contenido = rs.getString("contenido");
	        	   int categoria = rs.getInt("categoria");
	        	   String tema = rs.getString("tema");
	        	   String palabra_clave = rs.getString("palabra_clave");
	        	   
	        	   i.setTitulo(titulo);
	        	   i.setEpigrafe(epigrafe);
	        	   i.setContenido(contenido);
	        	   i.setCategoria(categoria);
	        	   i.setTema(tema);
	        	   i.setPalabras_clave(palabra_clave);
	        	   
	        	   
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
	       
	        return i;
		}
}