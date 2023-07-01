package usuarios.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import modelo.sedes.Clase;
import modelo.sedes.Sede;

import javax.swing.JButton;

public class ClienteVista extends JFrame {
	private JButton botonReservarClase = new JButton("Reservar clase");
	private JButton botonCancelarReserva = new JButton("Cancelar reserva");

	private DefaultTableModel tablaModelo = new DefaultTableModel();
	private JTable tablaClases = new JTable(tablaModelo);

	private ClienteControlador controlador;

	public ClienteVista(ClienteControlador controlador) {
		this.controlador = controlador;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setBackground(Color.WHITE);
		this.setTitle("Panel de Control - Cliente");

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(botonReservarClase);
		panelBotones.add(botonCancelarReserva);

		tablaModelo.addColumn("Nombre");
		tablaModelo.addColumn("Actividad");
		tablaModelo.addColumn("Sede");
		tablaModelo.addColumn("Fecha");


		JScrollPane tablaScroll = new JScrollPane(tablaClases, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tablaModelo.addColumn("Precio");
		
		//JScrollPane tablaScroll = new JScrollPane(tablaClases, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaClases.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
		panelTabla.add(new JLabel("Mis clases"));
		panelTabla.add(tablaScroll);

		this.getContentPane().add(panelBotones);
		this.getContentPane().add(panelTabla);

		pack();

		botonReservarClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Acción para el rol de Soporte Técnico
				try {
					controlador.getClaseSeleccionada();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No hay ninguna clase seleccionada");
				}
			}
		});
	}

	public void configurarTabla(ArrayList<Clase> clases) {

		for (Clase clase : clases) {
			Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
					clase.getFecha() };
		}

		/*for(Clase clase: clases) {
			Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(), clase.getFecha(),clase.getCosto() };
			tablaModelo.addRow(rowData);
		}
		*/
	}

	public JTable getTablaClases() {
		return this.tablaClases;
	}

	public int getFilaSeleccionada() {
		return tablaClases.getSelectedRow();
	}
}
