package estructuraTP.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

public class ExplicacionDAO implements DAOGenerico {

	public void Alta (Explicacion e) {
	       
    	ConexionJDBC con =new ConexionJDBC();
    	Connection conn= null;
    	conn=con.conectar();
    	  	
         String contenido=e.getContenido();
         String epigrafe=e.getEpigrafe();
         LocalDate fecha = e.getFechaCreacion();
    	 String titulo=e.getTitulo();
    	
    	try {
   
        
         
            
           PreparedStatement myPs =(PreparedStatement) conn.prepareStatement("INSERT INTO explicaciones(contenido, titulo, epigrafe, fecha) VALUES (?,?,?,?)");
          
         
           myPs.setString(1, contenido);         
           myPs.setString(2, titulo);
           myPs.setString(3, epigrafe);
           myPs.setDate(4, java.sql.Date.valueOf(fecha));

           
           myPs.executeUpdate();
          
      
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
  
    public void Baja (int id) {
    	ConexionJDBC con =new ConexionJDBC();
    	Connection conn= null;
    	conn=con.conectar();

        try {
 
           PreparedStatement myPs =(PreparedStatement) conn.prepareStatement("DELETE FROM explicaciones WHERE id_explicacion = ?");
           
           myPs.setInt(1, id);
           
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
    
    public void Actualizar(Explicacion e, int id) {
    	ConexionJDBC con =new ConexionJDBC();
    	Connection conn= null;
    	conn=con.conectar();
    	
    	
        try {
    	
        	PreparedStatement stmt= conn.prepareStatement("UPDATE explicaciones SET contenido = ?, titulo = ?, epigrafe = ?, fecha = ? WHERE id_explicacion = ?");
        	
        	stmt.setString(1, e.getContenido());
        	stmt.setString(2, e.getTitulo());
        	stmt.setString(3, e.getEpigrafe());
        	stmt.setDate(4, java.sql.Date.valueOf(e.getFechaCreacion()));
        	stmt.setInt(5, id);
        	
        	stmt.executeUpdate();
        	
        } catch(SQLException ex){
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
    
    public int buscarId(String a, String b, String c) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    	int id=0;    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM explicaciones WHERE contenido = ? and titulo = ? and epigrafe = ?");
          
           stmt.setString(1, a);
           stmt.setString(2, b);
           stmt.setString(3, c);
           
         
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   
        	   id = rs.getInt("id_explicacion");
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
    
    public ArrayList<Explicacion> consultar_tabla(){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	
    	ArrayList<Explicacion> explicaciones = new ArrayList<>();
    	conn = con.conectar();
    	
    	
    	
    	try {
    		   
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM explicaciones");

            while(rs.next())
            {
            	int id = rs.getInt("id_explicacion");
            	
            	ArrayList<Chequeo> chequeosAsociados = consultar_tabla_chequeosAsociados(id);
            	String contenido = rs.getString("contenido");
            	String titulo = rs.getString("titulo");          	
            	String epigrafe = rs.getString("epigrafe");
            	Explicacion ex = new Explicacion();
            	ex.setContenido(contenido);
            	ex.setTitulo(titulo);
            	ex.setEpigrafe(epigrafe);
            	//TODO asignar chequeos asociados
            	explicaciones.add(ex);
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
    	return explicaciones;
	}

    public int buscarIdExplicacion() {
		ConexionJDBC con = new ConexionJDBC();
		Connection conn = null;
		
		conn = con.conectar();
		int idExplicacion = 0;
		try {
			
			Statement stmt = conn.createStatement();
					
			ResultSet rs = stmt.executeQuery("SELECT * FROM explicaciones ORDER BY id_explicacion DESC LIMIT 1 ");
			
			while(rs.next()) {
				idExplicacion = rs.getInt("id_explicacion");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	
		return idExplicacion;
	}

    public void AltaExplicaciones_chequeos(int id_chequeo) {
		ConexionJDBC con = new ConexionJDBC();
		Connection conn = null;
		
		conn = con.conectar();
		int id_explicacion = buscarIdExplicacion();
		try {
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO explicaciones_chequeos(id_explicacion, id_chequeo) VALUES(?,?)");
			
			stmt.setInt(1, id_explicacion);
			stmt.setInt(2, id_chequeo);
			
			
			stmt.executeUpdate();
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

    public Explicacion obtenerExplicacion(int id) {
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;

    	conn = con.conectar();
    	
    	Explicacion exp = new Explicacion();
    	
        try {
   
           PreparedStatement stmt = conn.prepareStatement("SELECT * FROM explicaciones WHERE id_explicacion = ?");
          
           stmt.setInt(1, id);
         
           
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()) {
        	   String titulo = rs.getString("titulo");
        	   String epigrafe = rs.getString("epigrafe");
        	   String contenido = rs.getString("contenido");
        	         	   
        	   exp.setTitulo(titulo);
        	   exp.setEpigrafe(epigrafe);
        	   exp.setContenido(contenido);
        	   
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
       
        return exp;
	}

    public ArrayList<Chequeo> consultar_tabla_chequeosAsociados(int idExplicacion){
		ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
    	ArrayList<Chequeo> chequeos = new ArrayList<>();
    	ChequeoDAO cd = new ChequeoDAO();
    	
    	
    	try {
    		   
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM explicaciones_chequeos WHERE id_explicacion = ?");

            stmt.setInt(1, idExplicacion);
            
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
            	Chequeo c = new Chequeo();
            	int id = rs.getInt("id_chequeo");
            	c = cd.obtenerChequeo(id);
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

    public ArrayList<Integer> conseguirIds(int idExplicacion){
    	ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
    	ArrayList<Integer> ids = new ArrayList<>();
    
    	
    	try {
    		   
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM explicaciones_chequeos WHERE id_explicacion = ?");

            stmt.setInt(1, idExplicacion);
            
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
            	int id = rs.getInt("id");
            	System.out.println(id);
            	ids.add(id);
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
    	return ids;
    }

    public void bajaExplicaciones_chequeos(int idExplicacion) {
    	ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
  	
    	ArrayList<Integer> ids = conseguirIds(idExplicacion);
  
    	try {
    		   
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM explicaciones_chequeos WHERE id = ?");

            for(Integer id: ids) {
            	stmt.setInt(1, id);
            	stmt.executeUpdate();
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
    }

    public int cantChequeosAsociados(int id) {
    	ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
  
    	int cant=0;
    	
    	try {
    		   
            PreparedStatement stmt = conn.prepareStatement("SELECT id_explicacion, COUNT(*) AS total FROM explicaciones_chequeos WHERE id_explicacion = ?");         
        
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
            	cant = rs.getInt("total");
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
    	return cant;
    	
    }
   
    public ArrayList<Explicacion> explicacionesDeAltoImpacto(){
    	ConexionJDBC con = new ConexionJDBC();
    	Connection conn = null;
    	
    	conn = con.conectar();
    	
    	ArrayList<Explicacion> explicaciones = new ArrayList<>();
  
    	try {
    		   
            Statement stmt = conn.createStatement();         
        
            ResultSet rs = stmt.executeQuery("SELECT * FROM explicaciones");
            
            while(rs.next()) {
            	Explicacion exp = new Explicacion();
            	
            	int id = rs.getInt("id_explicacion");
            	exp = obtenerExplicacion(id);          	
            	exp.setCantChequeos(cantChequeosAsociados(id));
            	         
            	if(exp.Alto_Impacto()==true) {
            		explicaciones.add(exp);
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
    	return explicaciones;

    }
}
