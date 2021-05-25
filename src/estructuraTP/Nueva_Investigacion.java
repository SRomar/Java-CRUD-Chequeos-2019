package estructuraTP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Investigacion;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Nueva_Investigacion extends JPanel {
	private JTextField textFieldTitulo;
	private JTextField textFieldEpigrafe;
	private JTextField textFieldTema;
	private JTextField textFieldPalabraClave;
	
	


	public Nueva_Investigacion(JFrame frame, boolean actualizar, int ID) {
		
		setSize(920, 640);
		setLayout(null);
		
		
		JLabel lblChequea = new JLabel("Chequea2");
		lblChequea.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChequea.setBounds(399, 11, 161, 34);
		add(lblChequea);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal panel = new Principal(frame);
				setVisible(false);
				frame.setContentPane(panel);
			}
		});
		btnInicio.setBounds(808, 11, 91, 23);
		add(btnInicio);
		
		JLabel lblContenido = new JLabel("Contenido de la Investigaci\u00F3n");
		lblContenido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContenido.setBounds(30, 100, 252, 23);
		add(lblContenido);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(23, 371, 46, 14);
		add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(23, 387, 405, 20);
		add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblEpigrafe = new JLabel("Ep\u00EDgrafe");
		lblEpigrafe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEpigrafe.setBounds(23, 424, 99, 23);
		add(lblEpigrafe);
		
		textFieldEpigrafe = new JTextField();
		textFieldEpigrafe.setColumns(10);
		textFieldEpigrafe.setBounds(23, 445, 405, 20);
		add(textFieldEpigrafe);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTema.setBounds(23, 542, 99, 14);
		add(lblTema);
		
		textFieldTema = new JTextField();
		textFieldTema.setColumns(10);
		textFieldTema.setBounds(23, 557, 405, 20);
		add(textFieldTema);
		
		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(23, 482, 83, 23);
		add(lblCategoria);
		
		JLabel lblPalabraClave = new JLabel("Palabra Clave");
		lblPalabraClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPalabraClave.setBounds(494, 371, 120, 14);
		add(lblPalabraClave);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(23, 502, 405, 22);
		add(comboBoxCategoria);
		CategoriaDAO catd = new CategoriaDAO();
		comboBoxCategoria.setModel(new DefaultComboBoxModel(catd.consultar_tabla()));
		
		textFieldPalabraClave = new JTextField();
		textFieldPalabraClave.setColumns(10);
		textFieldPalabraClave.setBounds(494, 387, 405, 20);
		add(textFieldPalabraClave);
		
		JTextArea textAreaContenido = new JTextArea();
		textAreaContenido.setBounds(23, 125, 876, 215);
		add(textAreaContenido);
		textAreaContenido.setLineWrap(true);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InvestigacionDAO IDAO = new InvestigacionDAO();				

				LocalDate fecha = LocalDate.now();
				String contenido = textAreaContenido.getText();
				String titulo = textFieldTitulo.getText();
				String epigrafe = textFieldEpigrafe.getText();

				int categoria = catd.buscarId(comboBoxCategoria.getSelectedItem().toString());
				
				String tema = textFieldTema.getText();
				String palabraClave = textFieldPalabraClave.getText();
				int a = 0;
				
				if(contenido.equals("") || titulo.equals("") || epigrafe.equals("") || tema.equals("") || palabraClave.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos Incompletos");
					a = 1;
				}
			
				
					 if(actualizar == false) {						 
						Investigacion i = new Investigacion();
					 	i.setContenido(contenido);
					 	i.setCategoria(categoria);
					 	i.setEpigrafe(epigrafe);
					 	i.setTema(tema);
					 	i.setTitulo(titulo);
					 	i.setPalabras_clave(palabraClave);
					 	IDAO.Alta(i);
					 						 
					 	textAreaContenido.setText(null);
					 	textFieldTitulo.setText(null);
					 	textFieldEpigrafe.setText(null);
					 	comboBoxCategoria.setSelectedItem("Economía");
					 	textFieldTema.setText(null);
					 	textFieldPalabraClave.setText(null);
					 }
					 else if(actualizar==true) {
			
						 Investigacion inv=new Investigacion();
							inv.setCategoria(categoria);
							inv.setPalabras_clave(palabraClave);
							inv.setContenido(contenido);
							inv.setTema(tema);
							inv.setTitulo(titulo);
							inv.setEpigrafe(epigrafe);
							inv.setFechaCreacion(fecha);
							IDAO.Actualizar(inv, ID);
							textAreaContenido.setText(null);
							textFieldTitulo.setText(null);
						    textFieldEpigrafe.setText(null);
						    comboBoxCategoria.setSelectedItem("Economía");
							textFieldTema.setText(null);
							textFieldPalabraClave.setText(null);
					 }
					 
				}
				
				
			
		});
		btnGuardar.setBounds(572, 540, 182, 23);
		add(btnGuardar);
		
		if(actualizar==true) {
			int categoria;
			InvestigacionDAO ind = new InvestigacionDAO();
			Investigacion i = ind.obtenerInvestigacion(ID);
			textFieldTitulo.setText(i.getTitulo());
			textFieldEpigrafe.setText(i.getEpigrafe());
			textFieldTema.setText(i.getTema());
			textFieldPalabraClave.setText(i.getPalabras_clave());
			textAreaContenido.setText(i.getContenido());
			categoria = i.getCategoria();
			comboBoxCategoria.setSelectedIndex(categoria-1);
			
		}
		
		
		
	}
}
