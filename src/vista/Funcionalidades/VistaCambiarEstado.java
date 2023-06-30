package vista.Funcionalidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import modelo.sedes.Clase;
import modelo.sedes.Sede;

import javax.swing.JPanel;

public class VistaCambiarEstado extends JFrame {

	private JComboBox<String> estadoClase;
	private DefaultTableModel tablaModelo = new DefaultTableModel();
	private JTable tablaSedes = new JTable(tablaModelo);
	private JButton aceptarButton;
	private JButton cancelarButton;
	private ControladorCambiarEstado controlador;

	public VistaCambiarEstado(ControladorCambiarEstado controlador) {
		this.controlador = controlador;
		setTitle("Formulario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Tabla de Sedes
		tablaModelo.addColumn("Clase");
		tablaModelo.addColumn("Estado");
		tablaModelo.addColumn("Capacidad");
		tablaModelo.addColumn("Inscriptos");
		tablaModelo.addColumn("Actividad");
		tablaModelo.addColumn("Fecha");
		tablaModelo.addColumn("Profesor");
		tablaModelo.addColumn("Costo");

		JScrollPane tablaScroll = new JScrollPane(tablaSedes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaSedes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		contentPanel.add(tablaScroll, gbc);

		// Estado Clase
		estadoClase = new JComboBox<>(new String[] { "AGENDADA", "CONFIRMADA", "FINALIZADA" });
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		contentPanel.add(estadoClase, gbc);

		gbc.gridx = 0;
		contentPanel.add(new JLabel("Estado: "), gbc);

		// Botones Aceptar y Cancelar
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
		cancelarButton = new JButton("Cancelar");
		buttonPanel.add(cancelarButton);
		aceptarButton = new JButton("Aceptar");
		buttonPanel.add(aceptarButton);

		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpiar los campos
				// nombreCampo.setText("");

			}
		});

		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Obtener el valor seleccionado
				String estado = (String) estadoClase.getSelectedItem();
				// Realizar la acción deseada con los datos

				// cierra la ventana

				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				frame.dispose();

			}

		});

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPanel.add(buttonPanel, gbc);

		setContentPane(contentPanel);
		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public void configurarTabla(ArrayList<Clase> clases) {
		for (Clase clase : controlador.getSede().getClases()) {
			Object[] rowData = { clase.getnombre(), clase.getEstado(), clase.getCapacidad(), clase.getInscriptos(),
					clase.getActividad(), clase.getFecha(), clase.getProfesor().getNombre(), clase.getCosto() };
			tablaModelo.addRow(rowData);
		}
	}

}
