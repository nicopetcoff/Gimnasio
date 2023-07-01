package usuarios.vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.usuarios.Administrativo;
import controlador.*;
import modelo.productos.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class VistaAdministrativoNueva {

	private JFrame vistaAdminsitrativo;

	private DefaultTableModel tablaModelo;
	private JTable tablaSedes;

	public VistaAdministrativoNueva() {
		vistaAdminsitrativo = new JFrame("Vista Administrativo");

		ControladorAdministrativo controlador = new ControladorAdministrativo();

		vistaAdminsitrativo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vistaAdminsitrativo.setLayout(new BorderLayout());

		JButton botonAgendarClase = new JButton("Agendar clase");
		JButton cambiarEstadoClase = new JButton("Cambiar estado de clase");
		JButton asignarArticuloASede = new JButton("Asignar Articulo a Sede");
		JButton BDStreaming = new JButton("BDStreaming");
		JButton consultarStockSede = new JButton("Consultar Stock de Sede");
		JButton abmCliente = new JButton("ABM Cliente");

		// --------------------------------------------------------------------

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

		asignarArticuloASede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				asignarArticulosASede(controlador);
			}
		});
		BDStreaming.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// llamar a BDStream Admin
			}
		});

		consultarStockSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				consultarStockSede(controlador);
			}
		});

		tablaModelo = new DefaultTableModel();
		tablaModelo.addColumn("Localidad");
		tablaModelo.addColumn("Nivel");
		tablaModelo.addColumn("Descripción");
		tablaSedes = new JTable(tablaModelo);

		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setLayout(new GridLayout(6, 1));
		panelIzquierdo.add(botonAgendarClase);
		panelIzquierdo.add(cambiarEstadoClase);
		panelIzquierdo.add(asignarArticuloASede);
		panelIzquierdo.add(BDStreaming);
		panelIzquierdo.add(consultarStockSede);
		panelIzquierdo.add(abmCliente);

		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.add(new JScrollPane(tablaSedes), BorderLayout.CENTER);

		vistaAdminsitrativo.add(panelIzquierdo, BorderLayout.WEST);
		vistaAdminsitrativo.add(panelDerecho, BorderLayout.CENTER);

		configurarTabla(controlador);

		vistaAdminsitrativo.pack();
		vistaAdminsitrativo.setVisible(true);

	}

	// -----------------------------------------------------------------------------------------------------------------

	private void consultarStockSede(ControladorAdministrativo controlador) {
		JFrame ventana = new JFrame("Artículos de la Sede");
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setSize(300, 200);
		ventana.setLocationRelativeTo(null);

		JLabel ingreseSede = new JLabel("Ingrese el nombre de la sede:");
		JTextField campoSede = new JTextField();

		JButton btnConsultar = new JButton("Consultar");

		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreSede = campoSede.getText();

				ArrayList<Articulo> articulosSede = null;
				try {
					articulosSede = controlador.getArticulosSede(nombreSede);
				} catch (NoExisteUsuarioException | NoExisteSedeException ex) {
					ex.printStackTrace();
				}

				if (articulosSede != null) {
					DefaultListModel<Articulo> modeloLista = new DefaultListModel<>();
					for (Articulo articulo : articulosSede) {
						modeloLista.addElement(articulo);
					}

					JList<Articulo> listaArticulos = new JList<>(modeloLista);

					ventana.getContentPane().removeAll();
					ventana.setLayout(new BorderLayout());
					ventana.add(new JScrollPane(listaArticulos), BorderLayout.CENTER);
					ventana.revalidate();
				} else {

					JOptionPane.showMessageDialog(ventana, "No se encontraron artículos para la sede ingresada.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		ventana.setLayout(new BorderLayout());
		ventana.add(ingreseSede, BorderLayout.NORTH);
		ventana.add(campoSede, BorderLayout.CENTER);
		ventana.add(btnConsultar, BorderLayout.SOUTH);

		ventana.setVisible(true);
	}

	private void asignarArticulosASede(ControladorAdministrativo controlador) {
		JFrame frame = new JFrame("Asignar Artículos a Sede");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 250);
		frame.setLayout(new GridLayout(7, 2));

		JLabel labelArticulos = new JLabel("Artículos:");
		JComboBox<Articulo> comboBoxArticulos = new JComboBox<>();
		ArrayList<Articulo> articulos = controlador.getArticulos();
		for (Articulo articulo : articulos) {
			comboBoxArticulos.addItem(articulo);
		}

		JLabel labelMarca = new JLabel("Marca:");
		JTextField textFieldMarca = new JTextField();

		JLabel labelNombreArticulo = new JLabel("Nombre Artículo:");
		JTextField textCampoNombreArticulo = new JTextField();

		JLabel labelAtributos = new JLabel("Atributos:");
		JTextField textCampoAtributos = new JTextField();

		JLabel labelCantidad = new JLabel("Cantidad:");
		JTextField textCampoCantidad = new JTextField();

		JLabel labelSede = new JLabel("Sede:");
		JTextField textCampoSede = new JTextField();

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Articulo articuloSeleccionado = (Articulo) comboBoxArticulos.getSelectedItem();
				String marca = textFieldMarca.getText();
				String nombreArticulo = textCampoNombreArticulo.getText();
				String atributos = textCampoAtributos.getText();
				int cantidad = Integer.parseInt(textCampoCantidad.getText());
				String sede = textCampoSede.getText();

				try {
					controlador.asignarStockSede(marca, nombreArticulo, atributos, cantidad, sede);
				} catch (NoExisteArticuloEnCatalogoException | NoExisteUsuarioException | NoExisteSedeException e1) {
					JOptionPane.showMessageDialog(vistaAdminsitrativo, "No se pudo asginar stock.", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				frame.dispose();
			}
		});

		frame.add(labelArticulos);
		frame.add(comboBoxArticulos);
		frame.add(labelMarca);
		frame.add(textFieldMarca);
		frame.add(labelNombreArticulo);
		frame.add(textCampoNombreArticulo);
		frame.add(labelAtributos);
		frame.add(textCampoAtributos);
		frame.add(labelCantidad);
		frame.add(textCampoCantidad);
		frame.add(labelSede);
		frame.add(textCampoSede);
		frame.add(botonAceptar);

		frame.setVisible(true);
	}

	// ------------------------------------------------------------------------------------------------------------

	private void cambiarEstadoClase(ControladorAdministrativo controlador) {
		JFrame cambiarEstadoClase = new JFrame("Clases Agendadas");
		cambiarEstadoClase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cambiarEstadoClase.setLayout(new BorderLayout());

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

		cambiarEstadoClase.add(panelCentral, BorderLayout.CENTER);
		cambiarEstadoClase.add(panelInferior, BorderLayout.SOUTH);

		configurarTablaClases(controlador, tablaClases, sedeCombo);

		confirmarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaClases.getSelectedRow();
				if (filaSeleccionada != -1) {
					cambiarEstadoClase(filaSeleccionada, "CONFIRMADA", tablaClases);
				} else {
					JOptionPane.showMessageDialog(vistaAdminsitrativo, "Debe seleccionar una clase.", "Error",
							JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(vistaAdminsitrativo, "Debe seleccionar una clase.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		sedeCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				configurarTablaClases(controlador, tablaClases, sedeCombo);
			}
		});

		cambiarEstadoClase.pack();
		cambiarEstadoClase.setVisible(true);
	}

	private void configurarTablaClases(ControladorAdministrativo controlador, JTable tablaClases,
			JComboBox<String> sedeCombo) {
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
			Object[] rowData = { clase.getnombre(), clase.getFecha(), clase.getEstado() };
			tablaModelo.addRow(rowData);
		}
	}

	private void cambiarEstadoClase(int fila, String estado, JTable tablaClases) {
		DefaultTableModel tablaModelo = (DefaultTableModel) tablaClases.getModel();
		tablaModelo.setValueAt(estado, fila, 2);
	}

	// ------------------------------------------------------------------------------------------

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
		DefaultComboBoxModel<String> comboActividad = new DefaultComboBoxModel<>();

		for (String actividad : controlador.getActividades()) {
			comboActividad.addElement(actividad);
		}
		JComboBox<String> comboActiviadBox = new JComboBox<>(comboActividad);

		JComboBox<String> comboEmplazamiento = new JComboBox<>();

		// ------------------------------------------------------------------------------------------

		DefaultComboBoxModel<String> comboEmplazamiento1 = new DefaultComboBoxModel<>();

		for (String emplazamiento : controlador.getEmplazamientos()) {
			comboEmplazamiento1.addElement(emplazamiento);
		}

		JComboBox comboEmplazamientoBox = new JComboBox<>(comboEmplazamiento1);

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
				String actividad = (String) comboActiviadBox.getSelectedItem();
				String emplazamiento = (String) comboEmplazamientoBox.getSelectedItem();
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
		ventanaAgendarClase.add(comboActiviadBox);
		ventanaAgendarClase.add(etiquetaEmplazamiento);
		ventanaAgendarClase.add(comboEmplazamientoBox);
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
