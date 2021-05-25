package estructuraTP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.ScrollPane;

public class Nueva_Explicacion extends JPanel {
	private JTextField textFieldTitulo;
	private JTextField textFieldEpigrafe;
	private JTable table_chequeos;
	private JTextArea textAreaContenido;
	private JTable table_ChequeosAsociados;
	


	public Nueva_Explicacion(JFrame frame, boolean actualizar, int ID) {
		setSize(920, 640);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 939, 640);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(147, 53, 763, 350);
		add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setLayout(null);
		panel_1.setVisible(false);	
		
		JLabel lblChequeos = new JLabel("Chequeos");
		lblChequeos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChequeos.setBounds(163, 11, 89, 19);
		panel_1.add(lblChequeos);
		
		
		String[] columnas = {"Enlace", "Sitio"};
		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		Object[] nombresColumnas = columnas;
		model.addRow(nombresColumnas);

		ChequeoDAO  cd = new ChequeoDAO();
			
		ArrayList<Chequeo> chequeos = cd.consultar_tabla();
		ArrayList<Chequeo> potenExp = potencialesExplicaciones(chequeos);
				
		for(Chequeo c: potenExp) {
			String enlace = c.getEnlace_frase();
		    String sitio = c.getMedio_frase();          	
		   
		    Object[] registro = {enlace, sitio};
		    model.addRow(registro);
		}
		
		
		table_chequeos = new JTable();
		table_chequeos.setModel(model);
		table_chequeos.setBounds(24, 46, 343, 264);
		panel_1.add(table_chequeos);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component[] com = panel.getComponents();
				
				for(int a = 0; a < com.length; a++) {
					com[a].setEnabled(true);
				}
				
				panel_1.setVisible(false);
				
			}
		});
		btnFinalizar.setBounds(24, 321, 89, 23);
		panel_1.add(btnFinalizar);
		
		String[] columnasAsociados = {"Id", "Frase", "Estado", "Autor"};

		DefaultTableModel model2 = new DefaultTableModel(columnasAsociados, 0);
		Object[] nombresCol = columnasAsociados;
		model2.addRow(nombresCol);
		
		
		
		table_ChequeosAsociados = new JTable();
		table_ChequeosAsociados.setModel(model2);
		table_ChequeosAsociados.setBounds(397, 46, 343, 264);
		panel_1.add(table_ChequeosAsociados);
		
		JLabel lblChequeosAsociados = new JLabel("Chequeos Asociados");
		lblChequeosAsociados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChequeosAsociados.setBounds(501, 11, 143, 19);
		panel_1.add(lblChequeosAsociados);
		
		JButton btnOk = new JButton("Agregar");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fila = table_chequeos.getSelectedRow();
				
				model2.setRowCount(1);
				
				ArrayList<Chequeo> chequeos = cd.mismoEnlace(model.getValueAt(fila, 0).toString());
				
				for(Chequeo ch: chequeos) {		
					String id = new Integer(ch.getId()).toString();
					String frase = ch.getFrase();
					String estado = new Integer(ch.getRes_verif()).toString();
					String autor = ch.getAutor_frase();
								
					Object[] regis = {id, frase, estado, autor};
					model2.addRow(regis);
				}

				
				if(fila<=0) {
					JOptionPane.showMessageDialog(null, "Seleccione los chequeos que desea asociar a la Explicación");
				}
				
			}
		});
		btnOk.setBounds(278, 321, 89, 23);
		panel_1.add(btnOk);
		
		
		
		add(panel);
		panel.setLayout(null);
		panel.setSize(920, 640);
		
		JTextArea textAreaContenido = new JTextArea();
		textAreaContenido.setBounds(33, 127, 861, 284);
		panel.add(textAreaContenido);
		
		JLabel lblChequea = new JLabel("Chequea2");
		lblChequea.setBounds(417, 18, 162, 34);
		panel.add(lblChequea);
		lblChequea.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setBounds(803, 30, 91, 23);
		panel.add(btnInicio);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(38, 102, 104, 14);
		panel.add(lblContenido);
		lblContenido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(38, 450, 46, 14);
		panel.add(lblTitulo);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(38, 467, 405, 20);
		panel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblEpigrafe = new JLabel("Epigrafe");
		lblEpigrafe.setBounds(38, 506, 71, 23);
		panel.add(lblEpigrafe);
		lblEpigrafe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldEpigrafe = new JTextField();
		textFieldEpigrafe.setBounds(38, 532, 405, 20);
		panel.add(textFieldEpigrafe);
		textFieldEpigrafe.setColumns(10);
		
		JButton btnSeleccionarChequeos = new JButton("Seleccionar Chequeos");
		btnSeleccionarChequeos.setBounds(605, 466, 181, 23);
		panel.add(btnSeleccionarChequeos);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(613, 529, 162, 23);
		panel.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplicacionDAO EDAO = new ExplicacionDAO();				
				LocalDate fecha = LocalDate.now();
				String contenido = textAreaContenido.getText();
				String titulo = textFieldTitulo.getText();
				String epigrafe = textFieldEpigrafe.getText();
				int a1 = 0;			
				int a = 0;
				
				if(contenido.equals("") || titulo.equals("") || epigrafe.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos Incompletos");
					a = 1;
				}
				
				
			
				 if(a==0) {
					 if(actualizar == false) {			
						Explicacion ex = new Explicacion();
					 	ex.setContenido(contenido);					 	
					 	ex.setEpigrafe(epigrafe);					 	
					 	ex.setTitulo(titulo);
					 	
					 	
					 	EDAO.Alta(ex);
					 						 
					 	textAreaContenido.setText(null);
					 	textFieldTitulo.setText(null);
					 	textFieldEpigrafe.setText(null);
					 	
					 	if(table_ChequeosAsociados.getRowCount() == 1) {
					 		JOptionPane.showMessageDialog(null, "Seleccione los chequeos que desea asociar a la Explicación");
					 		a1=1;
					 	}
					 	if(a1 == 0) {
					 		
					 		for (int i = 1; i < table_ChequeosAsociados.getRowCount(); i++) {
			                       
					 			if (table_ChequeosAsociados.getValueAt(i, 0)!=null) {
					 				String idee = table_ChequeosAsociados.getValueAt(i, 0).toString();
					 				int id = new Integer(idee);
					 				EDAO.AltaExplicaciones_chequeos(id);
					 				                        	 
					 			} 
					 		                        	                         
					 		}
					 	
					 		model.setRowCount(1);
					 	}
					 }
					 else if(actualizar == true) {
						 Explicacion unaExplicacion=new Explicacion();
						 unaExplicacion.setContenido(contenido);
						 unaExplicacion.setTitulo(titulo);
						 unaExplicacion.setEpigrafe(epigrafe);
						 unaExplicacion.setFechaCreacion(fecha);
						 
						 EDAO.Actualizar(unaExplicacion, ID);
						 EDAO.bajaExplicaciones_chequeos(EDAO.buscarId(contenido, titulo, epigrafe));
						 
						 if(table_ChequeosAsociados.getRowCount() == 1) {
						 		JOptionPane.showMessageDialog(null, "Seleccione los chequeos que desea asociar a la Explicación");
						 		a1=1;
						 	}
						 	if(a1 == 0) {
						 		
						 		for (int i = 1; i < table_ChequeosAsociados.getRowCount(); i++) {
				                       
						 			if (table_ChequeosAsociados.getValueAt(i, 0)!=null) {
						 				String idee = table_ChequeosAsociados.getValueAt(i, 0).toString();
						 				int id = new Integer(idee);
						 				EDAO.AltaExplicaciones_chequeos(id);
						 				                        	 
						 			} 
						 		                        	                         
						 		}
						 	
						 		model.setRowCount(1);
						 	}
						 
						 
						 textAreaContenido.setText(null);
						 textFieldTitulo.setText(null);
						 textFieldEpigrafe.setText(null);
					
					 }
				 }
			}
		});
		btnSeleccionarChequeos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Component[] com = panel.getComponents();
				
				for(int a = 0; a < com.length; a++) {
					com[a].setEnabled(false);
				}
				
				panel_1.setVisible(true);
			}
		});
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal panel = new Principal(frame);
				setVisible(false);
				frame.setContentPane(panel);
			}
		});
		
		if(actualizar==true) {
			ExplicacionDAO ed = new ExplicacionDAO();
			Explicacion exp = ed.obtenerExplicacion(ID);
			textFieldTitulo.setText(exp.getTitulo());
			textFieldEpigrafe.setText(exp.getEpigrafe());
			textAreaContenido.setText(exp.getContenido());
			ArrayList<Chequeo> ch = ed.consultar_tabla_chequeosAsociados(ID);
			
			for(Chequeo c: ch) {
				String frase = c.getFrase();
				String estado = new Integer(c.getRes_verif()).toString();
				String autor = c.getAutor_frase();
				
				Object[] registro = {frase, estado, autor};
				model2.addRow(registro);
			}
		}
		
	}
	
	
	public ArrayList<Chequeo> potencialesExplicaciones(ArrayList<Chequeo> chequeos){
		ArrayList<Chequeo> pe = new ArrayList<>();
		String enlace = "";
		int cont=0;
		boolean yaEstaAgregado = false;
		for(int i = 0; i<chequeos.size(); i++) {
			enlace = chequeos.get(i).getEnlace_frase();
			for(int j = 0; j<chequeos.size();j++) {
				if(j != i) {
					if(chequeos.get(j).getEnlace_frase().equals(enlace)) {
						cont++;
					}
				}
			}
			if(cont > 2) {
				for(Chequeo c: pe) {
					if(c.getEnlace_frase().equals(chequeos.get(i).getEnlace_frase())) {
						yaEstaAgregado = true;
					}
				}
				if(yaEstaAgregado==false) {
					pe.add(chequeos.get(i));
				}
			}
		}
		
		return pe;
	}
}
