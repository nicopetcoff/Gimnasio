package vista.BdStreaming;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorBdStreaming;
import modelo.baseDeDatos.LimiteClasesException;
import modelo.sedes.Actividad;

public class VistaBdStreamingActividades extends JFrame {

	private ControladorBdStreaming controlador;
	private JTable tablaActividades;

	public VistaBdStreamingActividades() {
		super();
		controlador = new ControladorBdStreaming();

		// Configurar la ventana principal
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setTitle("BdStreaming - Admin - Actividades");
		setMinimumSize(new Dimension(400, 600));

		// Crear el panel para los botones de administrador
		JPanel panelAdmin = new JPanel(new FlowLayout());

		JButton btnCambiarLimite = new JButton("Cambiar Limite");
		btnCambiarLimite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaActividades.getSelectedRow();
				if (filaSeleccionada != -1) {
					Actividad actividad = (Actividad) tablaActividades.getValueAt(filaSeleccionada, 0);
					String limite = JOptionPane.showInputDialog("Cambiar Limite", "Ingrese el nuevo valor");

					try {
						int limiteInt = Integer.parseInt(limite);
						controlador.cambiarLimite(actividad, limiteInt);
						mostrarMensaje("Limite cambiado con exito");
						cargarActividades();
					} catch (LimiteClasesException e1) {
						mostrarMensaje("No se pudo cambiar el Limite");
					}

				} else {
					mostrarMensaje("Debe seleccionar una actividad para cambiar el limite.");
				}
			}
		});
		panelAdmin.add(btnCambiarLimite);

		add(panelAdmin, BorderLayout.NORTH);

		// Crear el modelo de tabla para las clases
		DefaultTableModel modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Actividad");
		modeloTabla.addColumn("Limite");

		tablaActividades = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaActividades);
		add(scrollPane, BorderLayout.CENTER);

		// Cargar las actividades en la tabla
		cargarActividades();
	}

	private void cargarActividades() {
		Set<Actividad> actividades = controlador.getLimitePorActividad().keySet();
		DefaultTableModel modeloTabla = (DefaultTableModel) tablaActividades.getModel();
		Map<Actividad, Integer> limites = controlador.getLimitePorActividad();
		modeloTabla.setRowCount(0);

		for (Actividad actividad : actividades) {
			Object[] fila = { actividad, limites.get(actividad).toString() };
			modeloTabla.addRow(fila);
		}

	}

	private void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

}
