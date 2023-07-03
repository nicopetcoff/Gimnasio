package vista.BdStreaming;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorBdStreaming;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;

public class VistaBdStreamingUser extends JFrame {

	private ControladorBdStreaming controlador;
	private JComboBox<Actividad> actividadComboBox;
	private JTable tablaClases;
	private JTextField txtFiltroId;
	private JTextField txtFechaDesde;
	private JTextField txtFechaHasta;

	public VistaBdStreamingUser() {
		super("Base de Datos de Streaming");
		controlador = new ControladorBdStreaming();

		// Configurar la ventana principal
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setTitle("BdStreaming - Usuario");
		setMinimumSize(new Dimension(1000, 600));

		// Crear el panel para los controles
		JPanel panelSuperior = new JPanel(new FlowLayout());

		// Crear el combo box para elegir la actividad
		actividadComboBox = new JComboBox<>();
		actividadComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarClasesPorActividad();
			}
		});

		actividadComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, actividadComboBox.getPreferredSize().height));

		panelSuperior.add(new JLabel("Actividad:"));
		panelSuperior.add(actividadComboBox);

		add(panelSuperior, BorderLayout.NORTH);

		// Crear el modelo de tabla para las clases
		DefaultTableModel modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Actividad");
		modeloTabla.addColumn("Fecha");
		modeloTabla.addColumn("URL Clase");

		tablaClases = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaClases);
		add(scrollPane, BorderLayout.CENTER);

		// Crear el panel para los campos de busqueda
		JPanel panelInferior = new JPanel(new FlowLayout());

		// Campo de texto para filtrar por ID
		JLabel lblFiltroId = new JLabel("Filtrar por ID:");
		txtFiltroId = new JTextField(10);
		JButton btnFiltrarId = new JButton("Filtrar");
		btnFiltrarId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPorId();
			}
		});
		panelInferior.add(lblFiltroId);
		panelInferior.add(txtFiltroId);
		panelInferior.add(btnFiltrarId);

		// Campos de texto para filtrar por fecha desde y hasta
		JLabel lblFechaDesde = new JLabel("Fecha Desde (yyyy-MM-dd):");
		txtFechaDesde = new JTextField(10);
		JLabel lblFechaHasta = new JLabel("Fecha Hasta (yyyy-MM-dd):");
		txtFechaHasta = new JTextField(10);
		JButton btnFiltrarFecha = new JButton("Filtrar por Fecha");
		btnFiltrarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarPorFecha();
			}
		});
		panelInferior.add(lblFechaDesde);
		panelInferior.add(txtFechaDesde);
		panelInferior.add(lblFechaHasta);
		panelInferior.add(txtFechaHasta);
		panelInferior.add(btnFiltrarFecha);

		add(panelInferior, BorderLayout.SOUTH);

		// Cargar las actividades en el combo box
		cargarActividades();
	}

	private void cargarActividades() {
		Set<Actividad> actividades = controlador.getLimitePorActividad().keySet();
		DefaultComboBoxModel<Actividad> comboBoxModel = new DefaultComboBoxModel<>();

		for (Actividad actividad : actividades) {
			comboBoxModel.addElement(actividad);
		}

		actividadComboBox.setModel(comboBoxModel);
	}

	private void cargarClasesPorActividad() {
		Actividad actividadSeleccionada = (Actividad) actividadComboBox.getSelectedItem();
		List<Clase> clases = controlador.buscarClasesPorActividad(actividadSeleccionada);

		DefaultTableModel modeloTabla = (DefaultTableModel) tablaClases.getModel();
		modeloTabla.setRowCount(0);

		for (Clase clase : clases) {
			Object[] fila = { clase.getId(), clase.getnombre(), clase.getActividad(), clase.getFecha().toLocalDate(),
					"https://www.youtube.com/watch?v=MY_gyv3ZDLE" };
			modeloTabla.addRow(fila);
		}
	}

	private void filtrarPorId() {
		String filtroId = txtFiltroId.getText();
		List<Clase> clasesFiltradas = controlador.filtrarClasesPorId(filtroId);
		mostrarClasesFiltradas(clasesFiltradas);
	}

	private void filtrarPorFecha() {
		String fechaDesdeStr = txtFechaDesde.getText();
		String fechaHastaStr = txtFechaHasta.getText();

		LocalDate fechaDesde = null;
		LocalDate fechaHasta = null;

		try {
			fechaDesde = controlador.fechaStrAFecha(fechaDesdeStr);
			fechaHasta = controlador.fechaStrAFecha(fechaHastaStr);
			List<Clase> clasesFiltradas = controlador.filtrarClasesPorFecha(fechaDesde, fechaHasta);
			mostrarMensaje("Se muestran todas las clases entre las fechas " + fechaDesde + " y " + fechaHasta);
			mostrarClasesFiltradas(clasesFiltradas);
		} catch (Exception ex) {
			mostrarMensaje(
					"Error al convertir las fechas. Asegurate de que estén en formato yyyy-MM-dd y que ningun campo este vacío.");
			return;
		}
	}

	private void mostrarClasesFiltradas(List<Clase> clasesFiltradas) {
		DefaultTableModel modeloTabla = (DefaultTableModel) tablaClases.getModel();
		modeloTabla.setRowCount(0);

		for (Clase clase : clasesFiltradas) {
			Object[] fila = { clase.getId(), clase.getnombre(), clase.getActividad().getTipoClase(),
					"https://www.youtube.com/watch?v=MY_gyv3ZDLE" };
			modeloTabla.addRow(fila);
		}
	}

	private void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

}
