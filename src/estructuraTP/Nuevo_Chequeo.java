package estructuraTP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.ChequeoDAO;
import estructuraTP.modelo.Chequeo;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;

public class Nuevo_Chequeo extends JPanel {
	
	private JTextField textFieldAutor;
	private JTextField textFieldSitio;
	private JTextField textFieldEnlace;
	private JTextField textFieldPalabraClave;
	private JTable table;
	private JTextField textFieldContenido;
	private JTextField textFieldAno;
	private JTextField textFieldMes;
	private JTextField textFieldDia;
	private JButton btnSeleccionarDatosRepos;
	
	public Nuevo_Chequeo(JFrame frame, boolean actualizar, int id) {
		setLayout(null);
		setSize(920, 640);
				
			JPanel panelT = new JPanel();
			panelT.setVisible(false);
			panelT.setBorder(new LineBorder(new Color(51, 51, 51), 1, true));
			panelT.setBounds(152, 60, 530, 479);
			add(panelT);
			
			panelT.setLayout(null);
			
			String[] nombresColumnas = {"Frase", "Medio", "Enlace", "Fecha"};
			DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(12, 51, 506, 350);
			panelT.add(scrollPane);
			ChequeoDAO CD = new ChequeoDAO();
			ArrayList<Chequeo> repos = CD.consultar_repositorio();
			
			for(Chequeo ch: repos) {
				String frase = ch.getFrase();
				String enlace = ch.getEnlace_frase();
				String sitio = ch.getMedio_frase();
				String fecha = ch.getFecha_frase().toString();
				
				Object[] registro = {frase, enlace, sitio, fecha};
				model.addRow(registro);
			}
		
			
			table = new JTable();
			scrollPane.setViewportView(table);
			table.setModel(model);	
			table.setPreferredSize(new Dimension(300, 350));
			
			
			JRadioButton rdbtnVerificada = new JRadioButton("Vedadera");
			rdbtnVerificada.setBounds(488, 446, 94, 23);
			add(rdbtnVerificada);
			
			JRadioButton rdbtnSinVerificar = new JRadioButton("Falsa");
			rdbtnSinVerificar.setBounds(586, 446, 109, 23);
			add(rdbtnSinVerificar);
			 
			JComboBox comboBoxCategorias = new JComboBox();
			comboBoxCategorias.setBounds(10, 460, 169, 22);
			add(comboBoxCategorias);
			CategoriaDAO catd = new CategoriaDAO(); 
			comboBoxCategorias.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
			
			JButton btnSeleccionarDatos = new JButton("Seleccionar datos");
			btnSeleccionarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = table.getSelectedRow();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					
				
					String frase = table.getValueAt(fila, 0).toString();					
					String enlace = table.getValueAt(fila, 1).toString();
					String sitio = table.getValueAt(fila, 2).toString();
					String fechita = (String)table.getValueAt(fila, 3);
					Date fecha=null;
					try {
						fecha = (Date) df.parse(fechita);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					textFieldContenido.setText(frase);
					textFieldEnlace.setText(enlace);
					textFieldSitio.setText(sitio);
					textFieldAno.setText(new Integer(fecha.getYear()).toString());
					textFieldMes.setText(new Integer(fecha.getMonth()).toString());
					textFieldDia.setText(new Integer(fecha.getDay()).toString());
					
					int id_repos  = CD.buscarIdRepositorio(frase, enlace, sitio);
					
					
					Component[] com = getComponents();
					
					for(int a = 0; a < com.length; a++) {
						com[a].setEnabled(true);
					}
					
					if(actualizar==false) {
						textFieldContenido.setEditable(false);
						textFieldEnlace.setEditable(false);
						textFieldSitio.setEditable(false);
						textFieldAno.setEditable(false);
						textFieldMes.setEditable(false);
						textFieldDia.setEditable(false);
					}
					else if(actualizar==true) {
						textFieldContenido.setEditable(false);
						textFieldEnlace.setEditable(false);
						textFieldAutor.setEditable(false);
						textFieldAno.setEditable(false);
						textFieldMes.setEditable(false);
						textFieldDia.setEditable(false);
						textFieldSitio.setEditable(false);
						btnSeleccionarDatosRepos.setEnabled(false);
					}
					panelT.setVisible(false);				
				}
			});
			btnSeleccionarDatos.setBounds(188, 426, 166, 25);
			panelT.add(btnSeleccionarDatos);
			
			JButton btnCerrar = new JButton("Cerrar");
			btnCerrar.setBounds(441, 12, 77, 25);
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		
					Component[] com = getComponents();
					
					for(int a = 0; a < com.length; a++) {
						com[a].setEnabled(true);
					}
					panelT.setVisible(false);
					
				}
			});
			
			
			panelT.add(btnCerrar);
		
		

		
		
		
		JLabel lblNuevoChequeo = new JLabel("Chequea2");
		lblNuevoChequeo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNuevoChequeo.setBounds(361, 12, 221, 29);
		add(lblNuevoChequeo);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal panel = new Principal(frame);
				setVisible(false);
				frame.setContentPane(panel);
			}
		});
		btnInicio.setBounds(770, 11, 94, 23);
		add(btnInicio);
		
		
		btnInicio.setBounds(770, 11, 94, 23);
		add(btnInicio);
		
		JLabel lbquienlodijo = new JLabel("\u00BFQui\u00E9n lo dijo?");
		lbquienlodijo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbquienlodijo.setBounds(10, 322, 144, 23);
		add(lbquienlodijo);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(10, 348, 420, 20);
		add(textFieldAutor);
		
		textFieldAutor.setColumns(10);
		
		textFieldContenido = new JTextField();
		textFieldContenido.setBounds(21, 111, 487, 187);
		add(textFieldContenido);
		
		JLabel lblafirmacion = new JLabel("Afirmaci\u00F3n/Frase");
		lblafirmacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblafirmacion.setBounds(21, 82, 233, 23);
		add(lblafirmacion);
		
		textFieldSitio = new JTextField();
		textFieldSitio.setColumns(10);
		textFieldSitio.setBounds(10, 405, 420, 20);
		add(textFieldSitio);
		
		JLabel lblMedioEnLa = new JLabel("Medio en la que fue publicada o vista la frase");
		lblMedioEnLa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMedioEnLa.setBounds(10, 379, 360, 23);
		add(lblMedioEnLa);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategora.setBounds(10, 436, 360, 23);
		add(lblCategora);
		
		JLabel lblEnlaceALa = new JLabel("Enlace a la noticia");
		lblEnlaceALa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnlaceALa.setBounds(10, 493, 360, 23);
		add(lblEnlaceALa);
		
		textFieldEnlace = new JTextField();
		textFieldEnlace.setColumns(10);
		textFieldEnlace.setBounds(10, 519, 420, 20);
		add(textFieldEnlace);
		
		textFieldPalabraClave = new JTextField();
		textFieldPalabraClave.setColumns(10);
		textFieldPalabraClave.setBounds(491, 348, 160, 20);
		add(textFieldPalabraClave);
		
		JLabel lblEnlaceALfa = new JLabel("Palabras clave");
		lblEnlaceALfa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnlaceALfa.setBounds(491, 322, 360, 23);
		add(lblEnlaceALfa);
		
		JLabel lblverificada = new JLabel("¿Verdadera?");
		lblverificada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblverificada.setBounds(488, 419, 94, 23);
		add(lblverificada);
		
		
		
		JButton btnGuardar = new JButton("Guardar ");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ChequeoDAO CD = new ChequeoDAO();
				 int id_repos = 0;
			 
				 int dia = new Integer(textFieldDia.getText());
				 int mes = new Integer(textFieldMes.getText());
				 int ano = new Integer(textFieldAno.getText());
				 String frase = textFieldContenido.getText();
				 String sitio = textFieldSitio.getText();
				 String autor = textFieldAutor.getText();
				 String enlace = textFieldEnlace.getText();
				 LocalDate firstDate1 = LocalDate.of(new Integer(textFieldAno.getText()), new Integer(textFieldMes.getText()), new Integer(textFieldDia.getText()));
				
				 

				 if (textFieldContenido.isEditable()==false) {
					id_repos = CD.buscarIdRepositorio(frase, enlace, sitio);
				}
				 
				 
				 if(id_repos != 0) {
					 CD.eliminarRepos(id_repos);
				 }
		
				 String palabra_clave = textFieldPalabraClave.getText();
				 int estado;
				 
				 if(rdbtnVerificada.isSelected()) {
					 estado = 1;
				 }
				 else if(rdbtnSinVerificar.isSelected()) {
					 estado = 2;
				 }
				 else {
					 estado = 3;
				 }
				 
				 int categoria = catd.buscarId(comboBoxCategorias.getSelectedItem().toString());

				 
				if(actualizar==false) {

					 Chequeo c  = new Chequeo();
					 c.setFrase(frase);
					 c.setAutor_frase(autor);
					 c.setMedio_frase(sitio);
					 c.setCategoria_frase(categoria);
					 c.setEnlace_frase(enlace);
					 c.setPalabra_clave(palabra_clave);
					 c.setFecha_frase(firstDate1);
					 c.setRes_verif(estado);
					 c.setPalabras_clave(palabra_clave);
					 CD.Alta(c);
					
					
				}
				else if(actualizar==true) {
					Chequeo chequeo = CD.obtenerChequeo(id);
					 chequeo.setFrase(frase);
					 chequeo.setAutor_frase(autor);
					 chequeo.setMedio_frase(sitio);
					 chequeo.setCategoria_frase(categoria);
					 chequeo.setEnlace_frase(enlace);
					 chequeo.setPalabra_clave(palabra_clave);
					 chequeo.setFecha_frase(firstDate1);
					 chequeo.setRes_verif(estado);
					 chequeo.setPalabras_clave(palabra_clave);
					CD.Actualizar(chequeo, id);


				}
				 		
				
				 //categoria = catd.buscarId(comboBoxCategorias.getSelectedItem().toString());
				 textFieldContenido.setEditable(true);
				 textFieldEnlace.setEditable(true);
				 textFieldSitio.setEditable(true);
				 textFieldAno.setEditable(true);
				 textFieldMes.setEditable(true);
				 textFieldDia.setEditable(true);
				 textFieldContenido.setText(null);
				 textFieldSitio.setText(null);
	   			 textFieldEnlace.setText(null);
				 textFieldAno.setText(null);
				 textFieldMes.setText(null);
				 textFieldDia.setText(null);
				 textFieldPalabraClave.setText(null);
				 textFieldAutor.setText(null);
				 rdbtnVerificada.setSelected(false);
				 rdbtnSinVerificar.setSelected(false);
				 
				 textFieldEnlace.setEditable(true);
				 comboBoxCategorias.setEnabled(true);
				 textFieldPalabraClave.setEditable(true);
				 rdbtnVerificada.setEnabled(true);
			   	 rdbtnSinVerificar.setEnabled(true);
				 
				
			}
		});
		btnGuardar.setBounds(670, 575, 194, 23);
		add(btnGuardar);
		
		JLabel lblNoSeleccioneNada = new JLabel("(No seleccione nada si no esta verificada)");
		lblNoSeleccioneNada.setBounds(594, 423, 288, 15);
		add(lblNoSeleccioneNada);
		
		JLabel label = new JLabel("Fecha de la frase");
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		label.setBounds(10, 552, 360, 23);
		add(label);
		
		textFieldAno = new JTextField();
		textFieldAno.setBounds(36, 578, 46, 20);
		add(textFieldAno);
		textFieldAno.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(117, 578, 46, 20);
		add(textFieldMes);
		
		textFieldDia = new JTextField();
		textFieldDia.setColumns(10);
		textFieldDia.setBounds(197, 578, 46, 20);
		add(textFieldDia);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(10, 581, 26, 14);
		add(lblAo);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(92, 581, 26, 14);
		add(lblMes);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(171, 581, 26, 14);
		add(lblDia);
		
		btnSeleccionarDatosRepos = new JButton("Seleccionar datos del repositorio");
		
		
		
		btnSeleccionarDatosRepos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component[] com = getComponents();
				
				for(int a = 0; a < com.length; a++) {
					com[a].setEnabled(false);
				}
				panelT.setVisible(true);
			}
		});
		btnSeleccionarDatosRepos.setBounds(556, 106, 262, 25);
		add(btnSeleccionarDatosRepos);
		
		if(actualizar==true) {
		
			Chequeo chequeo = CD.obtenerChequeo(id);
			int estado;
			int categoria;
			textFieldAutor.setText(chequeo.getAutor_frase());
			textFieldSitio.setText(chequeo.getMedio_frase());
			textFieldEnlace.setText(chequeo.getEnlace_frase());
			textFieldPalabraClave.setText(chequeo.getPalabras_clave());			
			textFieldContenido.setText(chequeo.getFrase());
			textFieldAno.setText(new Integer(chequeo.getFecha_frase().getYear()).toString());
			textFieldMes.setText((new Integer(chequeo.getFecha_frase().getMonthValue())).toString());
			textFieldDia.setText(new Integer(chequeo.getFecha_frase().getDayOfYear()).toString());
			estado = chequeo.getRes_verif(); 
			if(estado == 1) {
				rdbtnVerificada.setSelected(true);
			}
			else if(estado == 2) {
				rdbtnSinVerificar.setSelected(true);
			}
			
			categoria = chequeo.getCategoria_frase();			
			comboBoxCategorias.setSelectedIndex(categoria-1);
			
			
			
			textFieldContenido.setEditable(false);
			textFieldEnlace.setEditable(false);
			textFieldAutor.setEditable(false);
			//textFieldAno.setEditable(false);
			//textFieldMes.setEditable(false);
			//textFieldDia.setEditable(false);
			textFieldSitio.setEditable(false);
			btnSeleccionarDatosRepos.setEnabled(false);
		}
		
		
		
		
		
		
		
	}
	
	public void Deshabilitar(int N) {
		if (N==1) {
			textFieldContenido.setEnabled(false);
			textFieldSitio.setEnabled(false);
			textFieldEnlace.setEnabled(false);
			textFieldAno.setEnabled(false);
			textFieldMes.setEnabled(false);
			textFieldDia.setEnabled(false);
			
	}
		
		}
}