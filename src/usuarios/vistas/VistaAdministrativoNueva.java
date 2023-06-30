package usuarios.vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.usuarios.Administrativo;
import controlador.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VistaAdministrativoNueva {
	private JFrame frame;
	private JButton botonAgendarClase;
	private JButton cambiarEstadoClase;
	private JButton abmCliente;
	private JTable tablaSedes;

	public VistaAdministrativoNueva() {
		frame = new JFrame("Vista Administrativo");

		ControladorAdministrativo controlador = new ControladorAdministrativo();

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		botonAgendarClase = new JButton("Agendar clase");
		cambiarEstadoClase = new JButton("Cambiar estado de clase");
		abmCliente = new JButton("ABM Cliente");

		//--------------------------------------------------------------------
		
		botonAgendarClase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				agendarClase(controlador);
			}
		});
		
		cambiarEstadoClase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarEstadoClase(controlador);
			}
		});

		DefaultTableModel tablaModelo = new DefaultTableModel();
		tablaModelo.addColumn("Localidad");
		tablaModelo.addColumn("Nivel");
		tablaModelo.addColumn("Descripción");
		tablaSedes = new JTable(tablaModelo);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(3, 1));
		panelIzquierdo.add(botonAgendarClase);
		panelIzquierdo.add(cambiarEstadoClase);
		panelIzquierdo.add(abmCliente);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.add(new JScrollPane(tablaSedes), BorderLayout.CENTER);

		frame.add(panelIzquierdo, BorderLayout.WEST);
		frame.add(panelDerecho, BorderLayout.CENTER);

		configurarTabla(controlador);

		frame.pack();
		frame.setVisible(true);

	}

	private void cambiarEstadoClase(ControladorAdministrativo controlador) {
	    frame = new JFrame("Clases Agendadas");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setLayout(new BorderLayout());

	    // Crear tabla
	    DefaultTableModel tablaModelo = new DefaultTableModel();
	    tablaModelo.addColumn("Nombre");
	    tablaModelo.addColumn("Fecha");
	    tablaModelo.addColumn("Estado");
	    JTable tablaClases = new JTable(tablaModelo);

	    JPanel panelCentral = new JPanel(new BorderLayout());
	    panelCentral.add(new JScrollPane(tablaClases), BorderLayout.CENTER);

	    ArrayList<String> sedes = controlador.getNombreSedes();
	    JComboBox<String> sedeCombo = new JComboBox<>(sedes.toArray(new String[0]));
	    panelCentral.add(sedeCombo, BorderLayout.NORTH);

	    JButton confirmarButton = new JButton("Confirmar");
	    JButton finalizarButton = new JButton("Finalizar");

	    JPanel panelInferior = new JPanel();
	    panelInferior.add(confirmarButton);
	    panelInferior.add(finalizarButton);

	    frame.add(panelCentral, BorderLayout.CENTER);
	    frame.add(panelInferior, BorderLayout.SOUTH);

	    configurarTablaClases(controlador, tablaClases, sedeCombo);

	    confirmarButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int filaSeleccionada = tablaClases.getSelectedRow();
	            if (filaSeleccionada != -1) {
	                cambiarEstadoClase(filaSeleccionada, "CONFIRMADA", tablaClases);
	            } else {
	                JOptionPane.showMessageDialog(frame, "Debe seleccionar una clase.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });

	    finalizarButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int filaSeleccionada = tablaClases.getSelectedRow();
	            if (filaSeleccionada != -1) {
	                cambiarEstadoClase(filaSeleccionada, "FINALIZADA", tablaClases);
	            } else {
	                JOptionPane.showMessageDialog(frame, "Debe seleccionar una clase.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });

	    sedeCombo.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            configurarTablaClases(controlador, tablaClases, sedeCombo);
	        }
	    });

	    frame.pack();
	    frame.setVisible(true);
	}

	private void configurarTablaClases(ControladorAdministrativo controlador, JTable tablaClases, JComboBox<String> sedeCombo) {
	    DefaultTableModel tablaModelo = (DefaultTableModel) tablaClases.getModel();
	    tablaModelo.setRowCount(0); // Limpiar la tabla antes de actualizarla

	    ArrayList<Clase> clases = new ArrayList<>();
	    try {
	        String sedeSeleccionada = (String) sedeCombo.getSelectedItem();
	        clases = controlador.getClasesDisponibles(sedeSeleccionada);
	    } catch (NoExisteSedeException e) {
	        e.printStackTrace();
	    }

	    for (Clase clase : clases) {
	        Object[] rowData = {clase.getnombre(), clase.getFecha(), clase.getEstado()};
	        tablaModelo.addRow(rowData);
	    }
	}

	private void cambiarEstadoClase(int fila, String estado, JTable tablaClases) {
	    DefaultTableModel tablaModelo = (DefaultTableModel) tablaClases.getModel();
	    tablaModelo.setValueAt(estado, fila, 2);
	}



	//------------------------------------------------------------------------------------------

	private void agendarClase(ControladorAdministrativo controlador) {

		JFrame ventanaAgendarClase = new JFrame("Agendar clase");
		ventanaAgendarClase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaAgendarClase.setLayout(new GridLayout(8, 2));

		JLabel etiquetaDNI = new JLabel("DNI del profesor:");
		JLabel etiquetaSede = new JLabel("Sede:");
		JLabel etiquetaNombreClase = new JLabel("Nombre de la clase:");
		JLabel etiquetaActividad = new JLabel("Actividad:");
		JLabel etiquetaEmplazamiento = new JLabel("Emplazamiento:");
		JLabel etiquetaFechaHora = new JLabel("Fecha y hora (YYYY-MM-DD HH:MM):");
		JLabel etiquetaDuracion = new JLabel("Duración (minutos):");

		JTextField campoDNI = new JTextField();
		JTextField campoSede = new JTextField();
		JTextField campoNombreClase = new JTextField();
		JComboBox<String> comboActividad = new JComboBox<>();

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(
				controlador.getActividades().toArray(new String[0]));
		comboActividad.setModel(model);

		JComboBox<String> comboEmplazamiento = new JComboBox<>();

		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(
				controlador.getEmplazamientos().toArray(new String[0]));

		comboActividad.setModel(model1);

		JTextField campoFechaHora = new JTextField();
		JTextField campoDuracion = new JTextField();

		JButton botonAceptar = new JButton("Aceptar");
		JButton botonCancelar = new JButton("Cancelar");

		botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dniProfesor = campoDNI.getText();
				String sede = campoSede.getText();
				String nombreClase = campoNombreClase.getText();
				String actividad = (String) comboActividad.getSelectedItem();
				String emplazamiento = (String) comboEmplazamiento.getSelectedItem();
				String horario = campoFechaHora.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime fechaHora = LocalDateTime.parse(horario, formatter);

				int duracion = Integer.parseInt(campoDuracion.getText());

				try {
					controlador.agendarClase(dniProfesor, sede, nombreClase, actividad, emplazamiento, fechaHora,
							duracion);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No se pudo agendar la clase", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				ventanaAgendarClase.dispose();
			}
		});

		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAgendarClase.dispose();
			}
		});

		ventanaAgendarClase.add(etiquetaDNI);
		ventanaAgendarClase.add(campoDNI);
		ventanaAgendarClase.add(etiquetaSede);
		ventanaAgendarClase.add(campoSede);
		ventanaAgendarClase.add(etiquetaNombreClase);
		ventanaAgendarClase.add(campoNombreClase);
		ventanaAgendarClase.add(etiquetaActividad);
		ventanaAgendarClase.add(comboActividad);
		ventanaAgendarClase.add(etiquetaEmplazamiento);
		ventanaAgendarClase.add(comboEmplazamiento);
		ventanaAgendarClase.add(etiquetaFechaHora);
		ventanaAgendarClase.add(campoFechaHora);
		ventanaAgendarClase.add(etiquetaDuracion);
		ventanaAgendarClase.add(campoDuracion);
		ventanaAgendarClase.add(botonAceptar);
		ventanaAgendarClase.add(botonCancelar);

		ventanaAgendarClase.pack();
		ventanaAgendarClase.setVisible(true);
	}

	private void configurarTabla(ControladorAdministrativo controlador) {

		ArrayList<Sede> sedes = controlador.getSedes();
		DefaultTableModel tablaModelo = (DefaultTableModel) tablaSedes.getModel();
		for (Sede sede : sedes) {
			Object[] rowData = { sede.getLocalidad(), sede.getNivel(), sede.getDescripcion() };
			tablaModelo.addRow(rowData);
		}

	}
}
