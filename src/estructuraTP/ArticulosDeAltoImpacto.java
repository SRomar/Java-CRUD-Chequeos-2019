package estructuraTP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ExplicacionDAO;
import estructuraTP.dao.InvestigacionDAO;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ArticulosDeAltoImpacto extends JPanel {
	private JTable table;

	public ArticulosDeAltoImpacto(JFrame frame) {
		setLayout(null);
		
		String[] nombresColumnas = {"Contenido", "Titulo", "Epigrafe", "Articulo"};
		DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 57, 630, 485);
		add(scrollPane);
		
		
		InvestigacionDAO ind = new InvestigacionDAO();
		
		ArrayList<Investigacion> investigaciones = ind.consultar_tabla();
		
		for(Investigacion i: investigaciones) {
			String contenido = i.getContenido();
			String titulo = i.getTitulo();
			String epigrafe = i.getEpigrafe();
			
			
			Object[] registro = {contenido, titulo, epigrafe, "Investigacion"};
			model.addRow(registro);
		}
		
		ExplicacionDAO ed = new ExplicacionDAO(); 
		
		ArrayList<Explicacion> explicaciones = ed.explicacionesDeAltoImpacto();
		
		for(Explicacion exp: explicaciones) {
			String contenido = exp.getContenido();
			String titulo = exp.getTitulo();
			String epigrafe = exp.getEpigrafe();
			
			Object[] registro = {contenido, titulo, epigrafe, "Explicacion"};
			model.addRow(registro);
		}
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal panel = new Principal(frame);
				setVisible(false);
				frame.setContentPane(panel);
			}
		});
		btnVolver.setBounds(753, 420, 89, 23);
		add(btnVolver);

	}

}
