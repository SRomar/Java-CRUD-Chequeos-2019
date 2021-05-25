package estructuraTP;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Text;

import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.DAOGenerico;
import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

public class Principal extends JPanel {
	private JTextField txtquDeseaBuscar;
	private JTable table_1;

	


	public Principal(JFrame frame) {
		setSize(920, 640);
		setLayout(null);
			
		CategoriaDAO catdao = new CategoriaDAO();
		
		JComboBox combo_categoria2 = new JComboBox();
		combo_categoria2.setBounds(191, 48, 152, 22);
		add(combo_categoria2);
		combo_categoria2.setModel(new DefaultComboBoxModel(catdao.consultar_tabla()));
			
		JButton btnRegistrar = new JButton("Nuevo Chequeo");
		btnRegistrar.setBounds(730, 48, 152, 23);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnRegistrar.getText().equals("Nuevo Chequeo")) {
					Nuevo_Chequeo panel = new Nuevo_Chequeo(frame, false, 0);
					setVisible(false);
					frame.setContentPane(panel);
				}
				else if(btnRegistrar.getText().equals("Nueva Explicacion")) {
					Nueva_Explicacion panel = new Nueva_Explicacion(frame, false, 0);
					setVisible(false);
					frame.setContentPane(panel);
				}
				else if(btnRegistrar.getText().equals("Nueva Investigacion")) {
					Nueva_Investigacion panel = new Nueva_Investigacion(frame, false, 0);
					setVisible(false);
					frame.setContentPane(panel);
				}
				
			}
		});
		add(btnRegistrar);
		
		JComboBox combo_categoria1 = new JComboBox();
		combo_categoria1.setBounds(27, 48, 154, 22);
		combo_categoria1.setModel(new DefaultComboBoxModel(new String[] {"Chequeos", "Explicaciones", "Investigaciones", "Potenciales Explicaciones"}));
		add(combo_categoria1);
		CategoriaDAO catd = new CategoriaDAO();
		
		
		if(combo_categoria1.getSelectedItem().toString().contentEquals("Chequeos")) {
        
        	btnRegistrar.setText("Nuevo Chequeo");
      
        }
	
		JLabel lblChequea = new JLabel("Chequea2");
		lblChequea.setBounds(332, 11, 141, 29);
		lblChequea.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblChequea);
		
		txtquDeseaBuscar = new JTextField();
		txtquDeseaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtquDeseaBuscar.getText().contentEquals("\u00BFQu\u00E9 desea buscar?")) {
					txtquDeseaBuscar.setText(null);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (txtquDeseaBuscar.getText().isBlank()) {
					txtquDeseaBuscar.setText("\u00BFQu\u00E9 desea buscar?");
				}
				
			}
		});
		txtquDeseaBuscar.setBounds(368, 49, 127, 20);
		txtquDeseaBuscar.setText("\u00BFQu\u00E9 desea buscar?");
		add(txtquDeseaBuscar);
		txtquDeseaBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		

		 String col1 = "frase";
		 String col2 = "estado";
		 String col3 = "autor";
		
		 String col[] = {col1 , col2, col3, "articulo"};
	
		 
		
		
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(27, 82, 855, 534);
		  scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		  add(scrollPane);
		  JTable table_1 = new JTable();
		  JPopupMenu popupMenu = new JPopupMenu();
		  JMenuItem a = new JMenuItem("Actualizar");
		  JMenuItem e = new JMenuItem("Eliminar");
		 
		  DefaultTableModel model = new DefaultTableModel(col, 0);
		 
		  table_1.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mouseClicked(MouseEvent e) {
		  		
		  		if (table_1.getSelectionModel().isSelectionEmpty()!=true) {
					
					
					addPopup(table_1, popupMenu);
				
		  		}
		  		
		  	}
		  });
	
		  popupMenu.setBounds(0, 0, 200, 50);
		  table_1.setPreferredSize(new Dimension(850, 900));
		  table_1.setPreferredScrollableViewportSize(new Dimension(900, 400));	  
		 
	
		
		  
		  table_1.setModel(model);
		  table_1.getColumnModel().getColumn(0).setPreferredWidth(509);		  
		  table_1.setRowHeight(150);
		  scrollPane.setViewportView(table_1);
		  table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		  
		  e.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DAOGenerico d = null;
				
				int i = table_1.getSelectedRow();
				String aaa = (String) model.getValueAt(i, 0);
				String b = (String) model.getValueAt(i, 2);
				String c = (String) model.getValueAt(i, 1);
				if (JOptionPane.showConfirmDialog(null, "¿Eliminar?", "Aviso",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					
				
					if (model.getValueAt(i, 3).toString().equals("Chequeo")) {
						d = new ChequeoDAO();
					}
									
					if (model.getValueAt(i, 3).toString().equals("Investigacion")) {
						d = new InvestigacionDAO();
				
					}	
					
					if(model.getValueAt(i, 3).toString().equals("Explicacion")) {
						ExplicacionDAO ed = new ExplicacionDAO();
						int id = ed.buscarId(aaa, b, c);
						ed.bajaExplicaciones_chequeos(id);
						ed.Baja(id);
						
					}
					
					int id = d.buscarId(aaa, b, c);					
					d.Baja(id);
					
				    
				} 
				
			}
		});
		
		 
	
		  
		  a.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InvestigacionDAO ind = new InvestigacionDAO(); 
				ExplicacionDAO ed = new ExplicacionDAO();
				ChequeoDAO cd = new ChequeoDAO();
				int i = table_1.getSelectedRow();
				String aaa = (String) model.getValueAt(i, 0);
				String b = (String) model.getValueAt(i, 2);
				String c = (String) model.getValueAt(i, 1);
				if(btnRegistrar.getText().equals("Nuevo Chequeo")) {
					if (JOptionPane.showConfirmDialog(null, "¿Actualizar chequeo?", "Aviso",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
											
						int id = cd.buscarId(aaa, b, c);			
						Nuevo_Chequeo panel = new Nuevo_Chequeo(frame, true, id);
						
						//panel.Deshabilitar(1);
						setVisible(false);
						frame.setContentPane(panel);}
					} 
				else if(btnRegistrar.getText().equals("Nueva Explicacion")) {
					if (JOptionPane.showConfirmDialog(null, "¿Actualizar explicacion?", "Aviso",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							
						int id = ed.buscarId(aaa, c, b);							
						Nueva_Explicacion panel = new Nueva_Explicacion(frame, true, id);
							
						//panel.Deshabilitar(1);
						setVisible(false);
						frame.setContentPane(panel);
				}
				}
					else if(btnRegistrar.getText().equals("Nueva Investigacion")) {
						
					if (JOptionPane.showConfirmDialog(null, "¿Actualizar investigacion?", "Aviso",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			
						int id = ind.buscarId(aaa, c, b);									
						Nueva_Investigacion panel = new Nueva_Investigacion(frame, true, id);
												
						setVisible(false);
						frame.setContentPane(panel);
				}
				
			}
			}	
		});
				 
		  
		  popupMenu.add(a);
		  popupMenu.add(e);
		  
		  btnBuscar.setBounds(505, 48, 80, 23);
			btnBuscar.addActionListener(new ActionListener() {
				ChequeoDAO cd = new ChequeoDAO();
				public void actionPerformed(ActionEvent e) {
				
					 String columnas[] = new String[4];
						String col1 = "frase";
					  	String col2 = "estado";
					  	String col3 = "autor";
						 
					if (combo_categoria1.getSelectedItem().toString().contentEquals("Chequeos"))  {
						ArrayList<Chequeo> ch = cd.consultar_tabla();
						  
					
						columnas[0] = col1;
						columnas[1] = col2;
						columnas[2] = col3;
						columnas[3] = "articulo";
							 
						model.setColumnIdentifiers(columnas);
						 
						model.setRowCount(0);
						
						  for(Chequeo c: ch) {
							  String frase = c.getFrase();
							  String estado = new Integer(c.getRes_verif()).toString();
							  String autor = c.getAutor_frase();
							  String articulo = "Chequeo";
							  String palabra = c.getPalabra_clave();
							  
							  Object[] registro = {frase, estado, autor, articulo};
							  
							  if (frase.matches(txtquDeseaBuscar.getText()) || estado.contentEquals(txtquDeseaBuscar.getText()) || autor.contentEquals(txtquDeseaBuscar.getText())
									  || articulo.contentEquals(txtquDeseaBuscar.getText()) || palabra.contains(txtquDeseaBuscar.getText())) {
								
								  model.addRow(registro);
								  
								  
							}
							  
							  
			  
						  }
						  if (model.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "No se encontro nada.", ":(",
										JOptionPane.WARNING_MESSAGE);

							  
								ChequeoDAO cd = new ChequeoDAO();
								  combo_categoria2.setEnabled(true);
								col1 = "frase";
							  	 col2 = "estado";
							  	 col3 = "autor";
								 
								columnas[0] = col1;
								columnas[1] = col2;
								columnas[2] = col3;
								columnas[3] = "articulo";
									 
								model.setColumnIdentifiers(columnas);
								 
								model.setRowCount(0);
								  
								  for(Chequeo c: ch) {
									  String frase = c.getFrase();
									  String estado = new Integer(c.getRes_verif()).toString();
									  String autor = c.getAutor_frase();
									  String articulo = "Chequeo";
									  
									  Object[] registro = {frase, estado, autor, articulo};
									  
									 
									  
									  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && c.getCategoria_frase() == 1) {
										  model.addRow(registro);
									}
									  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && c.getCategoria_frase() == 2) {
										  model.addRow(registro);
									}
									  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && c.getCategoria_frase() == 3) {
										  model.addRow(registro);
									}
									  
									  
								
							}
						  }  	
					}
					if (combo_categoria1.getSelectedItem().toString().contentEquals("Investigaciones"))  {
						InvestigacionDAO ind = new InvestigacionDAO(); 
						ArrayList<Investigacion> in = ind.consultar_tabla();
					  	columnas[0] = col1;
						 columnas[1] = col2;
						 columnas[2] = col3;
						 columnas[3] = "articulo";
						 
						 model.setColumnIdentifiers(columnas);
						  
						  
						 model.setRowCount(0);
						  
						 for(Investigacion i: in) {
							  table_1.revalidate();
							  String contenido = i.getContenido();
							  String tema = i.getTema();
							  String titulo = i.getTitulo();
							  String articulo = "Investigacion";
							  
							  String palabra_clave = i.getPalabras_clave();
							  Object[] registro = {contenido, tema, titulo, articulo};
							  
							  if (contenido.matches(txtquDeseaBuscar.getText()) || tema.contentEquals(txtquDeseaBuscar.getText()) || titulo.contentEquals(txtquDeseaBuscar.getText())
									 || palabra_clave.contains(txtquDeseaBuscar.getText()) || articulo.contentEquals(txtquDeseaBuscar.getText())) {
								  model.addRow(registro);
								  
							  		}
							 
								
							  }
						 if (model.getRowCount()==0) {
							 JOptionPane.showMessageDialog(null, "No se encontro nada.", ":(",
										JOptionPane.WARNING_MESSAGE);

							  col1 = "contenido";
							  col2 = "tema";
							  col3 = "titulo";
							  combo_categoria2.setVisible(true);

							 columnas[0] = col1;
							 columnas[1] = col2;
							 columnas[2] = col3;
							 columnas[3] = "articulo";
							 
							 model.setColumnIdentifiers(columnas);
							  
							  
							 model.setRowCount(0);
							  
							  for(Investigacion i: in) {
								  table_1.revalidate();
								  String contenido = i.getContenido();
								  String tema = i.getTema();
								  String titulo = i.getTitulo();
								  String articulo = "Investigacion";
								  
								  Object[] registro = {contenido, tema, titulo, articulo};
								  
								  
								  
								  
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && i.getCategoria() == 1) {
									  model.addRow(registro);
								}
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && i.getCategoria() == 2) {
									  model.addRow(registro);
								}
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && i.getCategoria() == 3) {
									  model.addRow(registro);
								}
							  }

						}
						
						  
						  
					}
					if (combo_categoria1.getSelectedItem().toString().contentEquals("Explicaciones"))  {

			        	 ExplicacionDAO ed = new ExplicacionDAO();
						  ArrayList<Explicacion> exp = ed.consultar_tabla();
						  combo_categoria2.setVisible(false);
						  
							 col1 = "contenido";
						  	 col2 = "titulo";
						  	 col3 = "epigrafe";
							 
							columnas[0] = col1;
							columnas[1] = col2;
							columnas[2] = col3;
							columnas[3] = "articulo";
								 
							model.setColumnIdentifiers(columnas);
							 
							model.setRowCount(0);
							  
							  for(Explicacion ex: exp) {
								  String contenido = ex.getContenido();
								  String titulo = ex.getTitulo();
								  String epigrafe = ex.getEpigrafe();
								  String articulo = "Explicacion";
								  
								  Object[] registro = {contenido, titulo, epigrafe, articulo};
								  
								
								  model.addRow(registro);
								  
							  
							  }
						  
						  if (model.getRowCount()==0) {
							  JOptionPane.showMessageDialog(null, "No se encontro nada.", ":(",
										JOptionPane.WARNING_MESSAGE);
							  for(Explicacion ex: exp) {
								  String contenido = ex.getContenido();
								  String titulo = ex.getTitulo();
								  String epigrafe = ex.getEpigrafe();
								  String articulo = "Explicacion";
								  
								  Object[] registro = {contenido, titulo, epigrafe, articulo};
								  
								  model.addRow(registro);
								  
							  
							  }
						  
						}
						  

						
					}	
					  
					  
					  
					
					
				}
			});
			add(btnBuscar);
			
			JButton btnDescartarChequeos = new JButton("Descartar Chequeos");
			btnDescartarChequeos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DescartarChequeos panel = new DescartarChequeos(frame);
					setVisible(false);
					frame.setContentPane(panel);
				}
			});
			btnDescartarChequeos.setBounds(595, 14, 287, 23);
			add(btnDescartarChequeos);
			
			JButton btnArtAltoImpacto = new JButton("Art. Alto Impacto");
			btnArtAltoImpacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArticulosDeAltoImpacto panel = new ArticulosDeAltoImpacto(frame);
					setVisible(false);
					frame.setContentPane(panel);
				}
			});
			btnArtAltoImpacto.setBounds(595, 48, 125, 23);
			add(btnArtAltoImpacto);
			
			

			
			 ItemListener itemListener2 = new ItemListener() {
			      public void itemStateChanged(ItemEvent itemEvent) {
			        String item = itemEvent.getItem().toString();
			        String columnas[] = new String[4];
			        
			        if(item.equals("Economia")) {
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Chequeos")) {
							
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
							model.setRowCount(0);
							  
							  for(Chequeo c: ch) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 						
								  if (c.getCategoria_frase() == 1) {
									  model.addRow(registro);
								}
								
								  								  
							  
							  }
			        		
			        		
						}
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Potenciales Explicaciones")) {
							
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
							 ArrayList<Chequeo> potenExp = potencialesExplicaciones(ch);
							model.setRowCount(0);
							  
							  for(Chequeo c: potenExp) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 						
								  if (c.getCategoria_frase() == 1) {
									  model.addRow(registro);
								}
								
								  								  
							  
							  }
			        		
			        		
						}
			        	 if (combo_categoria1.getSelectedItem().toString().contentEquals("Investigaciones")) {
				        	 if(btnRegistrar.getText().equals("Nueva Investigacion")) {
								  InvestigacionDAO ind = new InvestigacionDAO(); 
								  ArrayList<Investigacion> in = ind.consultar_tabla();
								
								  
								 model.setRowCount(0);
								  
								  for(Investigacion i: in) {
									  table_1.revalidate();
									  String contenido = i.getContenido();
									  String tema = i.getTema();
									  String titulo = i.getTitulo();
									  String articulo = "Investigacion";
									  
									  Object[] registro = {contenido, tema, titulo, articulo};
									  
									  
									  
									  
									  if (i.getCategoria() == 1) {
										  model.addRow(registro);
									}
									  							  
										
								  }
								  }
				        	 
							
						}
			        
			        
			        
			        }
			        else if(item.equals("Politica")) {
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Chequeos")) {
										        		
				        	btnRegistrar.setText("Nuevo Chequeo");
				        	
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
						
							model.setRowCount(0);
							  
							  for(Chequeo c: ch) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 
								  
								  
								  if (c.getCategoria_frase() == 2) {
									  model.addRow(registro);
								}
															  
							  
							  }
			        		
			        		
						}
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Potenciales Explicaciones")) {
							
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
							 ArrayList<Chequeo> potenExp = potencialesExplicaciones(ch);
							model.setRowCount(0);
							  
							  for(Chequeo c: potenExp) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 						
								  if (c.getCategoria_frase() == 2) {
									  model.addRow(registro);
								}
								
								  								  
							  
							  }
			        		
			        		
						}
			        	 if (combo_categoria1.getSelectedItem().toString().contentEquals("Investigaciones")) {
			        		
				        	 if(btnRegistrar.getText().equals("Nueva Investigacion")) {
								  InvestigacionDAO ind = new InvestigacionDAO(); 
								  ArrayList<Investigacion> in = ind.consultar_tabla();
								
								  
								 model.setRowCount(0);
								  
								  for(Investigacion i: in) {
									  table_1.revalidate();
									  String contenido = i.getContenido();
									  String tema = i.getTema();
									  String titulo = i.getTitulo();
									  String articulo = "Investigacion";
									  
									  Object[] registro = {contenido, tema, titulo, articulo};
									  
									  
									  
									  
									 
									  if (i.getCategoria() == 2) {
										  model.addRow(registro);
									}
														  
										
								  }
								  }
				        	 
							
						}
			        	
			        			        }
			        else if(item.equals("Sociedad")) {
			        	
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Chequeos")) {
							
			        	
				        	
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
							
							 
							model.setRowCount(0);
							  
							  for(Chequeo c: ch) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 
							
								  if ( c.getCategoria_frase() == 3) {
									  model.addRow(registro);
								}
								  								  
							  
							  }
			        		
			        		
						}
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Potenciales Explicaciones")) {
							
				        	ChequeoDAO cd = new ChequeoDAO();
							 ArrayList<Chequeo> ch = cd.consultar_tabla();
							 ArrayList<Chequeo> potenExp = potencialesExplicaciones(ch);
							model.setRowCount(0);
							
							  
							  for(Chequeo c: potenExp) {
								  String frase = c.getFrase();
								  String estado = new Integer(c.getRes_verif()).toString();
								  String autor = c.getAutor_frase();
								  String articulo = "Chequeo";
								  
								  Object[] registro = {frase, estado, autor, articulo};
								  
								 						
								  if (c.getCategoria_frase() == 3) {
									  model.addRow(registro);
								}
								
								  								  
							  
							  }
			        		
			        		
						}
			        	if (combo_categoria1.getSelectedItem().toString().contentEquals("Investigaciones")) {
			        	
			        		if(btnRegistrar.getText().equals("Nueva Investigacion")) {
								  InvestigacionDAO ind = new InvestigacionDAO(); 
								  ArrayList<Investigacion> in = ind.consultar_tabla();
								 
								  
								  
								 model.setRowCount(0);
								  
								  for(Investigacion i: in) {
									  table_1.revalidate();
									  String contenido = i.getContenido();
									  String tema = i.getTema();
									  String titulo = i.getTitulo();
									  String articulo = "Investigacion";
									  
									  Object[] registro = {contenido, tema, titulo, articulo};
									  
									 
									
									  if (i.getCategoria() == 3) {
										  model.addRow(registro);
									}
									  								  
										
								  }
								  }
				        	 
							
						}
			        
			        
			        }
			        
			        
			        
			        
			      }
			    };
			
			
			    combo_categoria2.addItemListener(itemListener2);
		
			    
			    
			    
			ItemListener itemListener = new ItemListener() {
			      public void itemStateChanged(ItemEvent itemEvent) {
			        String item = itemEvent.getItem().toString();
			        String columnas[] = new String[4];
			        
			        
			        if(item.equals("Chequeos")) {
			        	combo_categoria2.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			        	btnRegistrar.setText("Nuevo Chequeo");
			        	
			        	ChequeoDAO cd = new ChequeoDAO();
						 ArrayList<Chequeo> ch = cd.consultar_tabla();
						  combo_categoria2.setVisible(true);
						  txtquDeseaBuscar.setVisible(true);
						  btnBuscar.setVisible(true);

						String	col1 = "frase";
					  	String col2 = "estado";
					  	String col3 = "autor";
						 
						columnas[0] = col1;
						columnas[1] = col2;
						columnas[2] = col3;
						columnas[3] = "articulo";
							 
						model.setColumnIdentifiers(columnas);
						 
						model.setRowCount(0);
						  
						  for(Chequeo c: ch) {
							  String frase = c.getFrase();
							  String estado = new Integer(c.getRes_verif()).toString();
							  String autor = c.getAutor_frase();
							  String articulo = "Chequeo";
							  
							  Object[] registro = {frase, estado, autor, articulo};
							  
							 
							  
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && c.getCategoria_frase() == 1) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && c.getCategoria_frase() == 2) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && c.getCategoria_frase() == 3) {
								  model.addRow(registro);
							}
							  								  
						  
						  }
					  }
				
			         if(item.equals("Explicaciones")) {
			        	combo_categoria2.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			        	btnRegistrar.setText("Nueva Explicacion");
			        	
			        	 ExplicacionDAO ed = new ExplicacionDAO();
						  ArrayList<Explicacion> exp = ed.consultar_tabla();
						  combo_categoria2.setVisible(false);
						  txtquDeseaBuscar.setVisible(true);
						  btnBuscar.setVisible(true);
						  
							String col1 = "contenido";
						  	String col2 = "titulo";
						  	String col3 = "epigrafe";
							 
							columnas[0] = col1;
							columnas[1] = col2;
							columnas[2] = col3;
							columnas[3] = "articulo";
								 
							model.setColumnIdentifiers(columnas);
							 
							model.setRowCount(0);
							  
							  for(Explicacion ex: exp) {
								  String contenido = ex.getContenido();
								  String titulo = ex.getTitulo();
								  String epigrafe = ex.getEpigrafe();
								  String articulo = "Explicacion";
								  
								  Object[] registro = {contenido, titulo, epigrafe, articulo};
								  
								
								  model.addRow(registro);
								  
							  }
			        }
			         if(item.equals("Investigaciones")) {
			        	combo_categoria2.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			        	btnRegistrar.setText("Nueva Investigacion");
			        	 if(btnRegistrar.getText().equals("Nueva Investigacion")) {
							  InvestigacionDAO ind = new InvestigacionDAO(); 
							  ArrayList<Investigacion> in = ind.consultar_tabla();
							 String col1 = "contenido";
							 String col2 = "tema";
							 String col3 = "titulo";
							  combo_categoria2.setVisible(true);
							  txtquDeseaBuscar.setVisible(true);
							  btnBuscar.setVisible(true);

							 columnas[0] = col1;
							 columnas[1] = col2;
							 columnas[2] = col3;
							 columnas[3] = "articulo";
							 
							 model.setColumnIdentifiers(columnas);
							  
							  
							 model.setRowCount(0);
							  
							  for(Investigacion i: in) {
								  table_1.revalidate();
								  String contenido = i.getContenido();
								  String tema = i.getTema();
								  String titulo = i.getTitulo();
								  String articulo = "Investigacion";
								  
								  Object[] registro = {contenido, tema, titulo, articulo};
								  
								  
								  
								  
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && i.getCategoria() == 1) {
									  model.addRow(registro);
								}
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && i.getCategoria() == 2) {
									  model.addRow(registro);
								}
								  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && i.getCategoria() == 3) {
									  model.addRow(registro);
								}
								  								  
									
								  }
							  
						  }
			        }
			        if(item.equals("Potenciales Explicaciones")) {
			        	combo_categoria2.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			        	btnRegistrar.setText("Nuevo Chequeo");
			        	
			        	ChequeoDAO cd = new ChequeoDAO();
						 ArrayList<Chequeo> ch = cd.consultar_tabla();
						 ArrayList<Chequeo> potenExp = potencialesExplicaciones(ch);
						 
						  combo_categoria2.setVisible(true);
						  txtquDeseaBuscar.setVisible(false);
						  btnBuscar.setVisible(false);

						String	col1 = "frase";
					  	String col2 = "estado";
					  	String col3 = "autor";
						 
						columnas[0] = col1;
						columnas[1] = col2;
						columnas[2] = col3;
						columnas[3] = "articulo";
							 
						model.setColumnIdentifiers(columnas);
						 
						model.setRowCount(0);
						  
						  for(Chequeo c: potenExp) {
							  String frase = c.getFrase();
							  String estado = new Integer(c.getRes_verif()).toString();
							  String autor = c.getAutor_frase();
							  String articulo = "Chequeo";
							  
							  Object[] registro = {frase, estado, autor, articulo};
							  
							 
							  
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && c.getCategoria_frase() == 1) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && c.getCategoria_frase() == 2) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && c.getCategoria_frase() == 3) {
								  model.addRow(registro);
							}
							  								  
						  
						  }
			        }
			        
			        
			        
			        
			      }
			    };
			    combo_categoria1.addItemListener(itemListener);
		  
		  
		  
				  if (model.getRowCount()==0) {
						
					  combo_categoria2.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			        	btnRegistrar.setText("Nuevo Chequeo");
			        	
			        	ChequeoDAO cd = new ChequeoDAO();
						 ArrayList<Chequeo> ch = cd.consultar_tabla();
						  combo_categoria2.setEnabled(true);
						  String columnas[] = new String[4];
							col1 = "frase";
					  	 col2 = "estado";
					  	 col3 = "autor";
						 
						columnas[0] = col1;
						columnas[1] = col2;
						columnas[2] = col3;
						columnas[3] = "articulo";
							 
						model.setColumnIdentifiers(columnas);
						 
						model.setRowCount(0);
						  
						  for(Chequeo c: ch) {
							  String frase = c.getFrase();
							  String estado = new Integer(c.getRes_verif()).toString();
							  String autor = c.getAutor_frase();
							  String articulo = "Chequeo";
							  
							  Object[] registro = {frase, estado, autor, articulo};
							  
							 
							  
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Economia") && c.getCategoria_frase() == 1) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Politica") && c.getCategoria_frase() == 2) {
								  model.addRow(registro);
							}
							  if (combo_categoria2.getSelectedItem().toString().contentEquals("Sociedad") && c.getCategoria_frase() == 3) {
								  model.addRow(registro);
							}
							  								  
						  }
						  }
							  
						
					
		 
		
		}
	
		
			private static void addPopup(Component component, final JPopupMenu popup) {
				component.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (e.isPopupTrigger()) {
							showMenu(e);
						}
					}
					public void mouseReleased(MouseEvent e) {
						if (e.isPopupTrigger()) {
							showMenu(e);
						}
					}
					private void showMenu(MouseEvent e) {
						popup.show(e.getComponent(), e.getX(), e.getY());
					}
				});
			}
			
			
			public ArrayList<Chequeo> potencialesExplicaciones(ArrayList<Chequeo> chequeos){
				ArrayList<Chequeo> pe = new ArrayList<>();
				String enlace = "";
				int cont=0;
				
				for(int i = 0; i<chequeos.size(); i++) {
					enlace = chequeos.get(i).getEnlace_frase();
					for(int j = 0; j<chequeos.size();j++) {
						
							if(chequeos.get(j).getEnlace_frase().equals(enlace)) {
								
								cont++;
								
							}
							
					}
					if(cont > 2) {
						pe.add(chequeos.get(i));
						
					}
					cont = 0;
					
				}
				
				return pe;
			}
}

 