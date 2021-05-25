
package estructuraTP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.ChequeoDAO;
import estructuraTP.modelo.Chequeo;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DescartarChequeos extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnVolver;
	
	public DescartarChequeos(JFrame frame) {
		setLayout(null);
	
		String[] nombresColumnas = {"Frase", "Enlace", "Sitio", "Fecha"};
		DefaultTableModel model = new DefaultTableModel(nombresColumnas, 0);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 62, 444, 467);
		add(scrollPane);
		
		ChequeoDAO cd = new ChequeoDAO();
		
		ArrayList<Chequeo> chequeos = cd.consultar_repositorio();
		
		for(Chequeo c: chequeos) {
			String frase = c.getFrase();
			String enlace = c.getEnlace_frase();
			String sitio = c.getMedio_frase();
			String fecha = c.getFecha_frase().toString();
			
			Object[] registro = {frase, enlace, sitio, fecha};
			model.addRow(registro);			
		}

		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		
		JButton btnDescartarChequeo = new JButton("Descartar Chequeo");
		btnDescartarChequeo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();

				if(fila != -1) {
				String frase = model.getValueAt(fila, 0).toString();
				String enlace = model.getValueAt(fila, 1).toString();
				String sitio = model.getValueAt(fila, 2).toString();
				
				int id = cd.buscarIdRepositorio(frase, enlace, sitio);
				
				cd.eliminarRepos(id);
		
				model.setRowCount(0);
				
				ArrayList<Chequeo> chequeos = cd.consultar_repositorio();
				
				for(Chequeo c: chequeos) {
					String f = c.getFrase();
					String en = c.getEnlace_frase();
					String s = c.getMedio_frase();
					String fecha = c.getFecha_frase().toString();
					
					Object[] registro = {f, en, s, fecha};
					model.addRow(registro);			
				}
			}
			}
		});
		btnDescartarChequeo.setBounds(607, 225, 150, 23);
		add(btnDescartarChequeo);
		
		btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal panel = new Principal(frame);
				setVisible(false);
				frame.setContentPane(panel);
			}
		});
		btnVolver.setBounds(641, 439, 89, 23);
		add(btnVolver);

	}
}
