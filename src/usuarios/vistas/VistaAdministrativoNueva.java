package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAdministrativo;
import controlador.ControladorBdStreaming;
import modelo.productos.Articulo;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.usuarios.Profesor;
import modelo.usuarios.Excepciones.ProfesorNoDisponibleException;
import modelo.usuarios.Excepciones.ProfesorNoRegistradoException;
import modelo.supertlon.Excepciones.*;
import modelo.utilidad.EstadoClase;

public class VistaAdministrativoNueva {
	private ControladorAdministrativo controlador;
	private JFrame vistaAdminsitrativo;
	private JTable tablaArticulos;
	private DefaultTableModel tablaModelo;
	private JTable tablaSedes;

	public VistaAdministrativoNueva() {
		vistaAdminsitrativo = new JFrame("Vista Administrativo");

		controlador = new ControladorAdministrativo();

		vistaAdminsitrativo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vistaAdminsitrativo.setLayout(new BorderLayout());
		vistaAdminsitrativo.setLocationRelativeTo(null);

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

				ControladorBdStreaming instancia = new ControladorBdStreaming();
				instancia.abrirVistaBdStreamingAdmin();
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

	// -----------------------------------------------------------------------------------------------------------------

	private void asignarArticulosASede(ControladorAdministrativo controlador) {

		JFrame ventanaAsignarArticulosASede = new JFrame("Asignar Artículos a Sede");
	    ventanaAsignarArticulosASede.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaAsignarArticulosASede.setLayout(new BorderLayout());
	    ventanaAsignarArticulosASede.setLocationRelativeTo(null);

	    DefaultTableModel tablaModelo = new DefaultTableModel();
	    tablaModelo.addColumn("Articulo");
	    tablaModelo.addColumn("Marca");
	    tablaModelo.addColumn("Atributos");
	    tablaModelo.addColumn("Amortizacion");
	    tablaModelo.addColumn("Precio");
	    tablaArticulos = new JTable(tablaModelo);
	    configurarTabla(controlador.getCatalogo());

	    JScrollPane tablaScroll = new JScrollPane(tablaArticulos);
	    tablaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	    JPanel panelInferior = new JPanel(new GridLayout(3, 2));

	    JLabel labelCantidad = new JLabel("Cantidad:");
	    JTextField campoCantidad = new JTextField();

	    JLabel labelSede = new JLabel("Sede:");
	    JComboBox<String> comboSede = new JComboBox<>();
	   
	    for(Sede s:controlador.getSedes()) {
	    	comboSede.addItem(s.getLocalidad());
	    }

	    JButton botonAceptar = new JButton("Aceptar");
	    botonAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtener el artículo seleccionado de la tabla
	            int filaSeleccionada = tablaArticulos.getSelectedRow();
	            
                Articulo a=null;
				try {
					a = controlador.getArticuloSeleccionado();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Falta seleccionar articulo que desea agregar.", "Campo incompleto",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
                // Obtener la cantidad y la sede ingresadas
                int cantidad = Integer.parseInt(campoCantidad.getText());
                String sede = (String) comboSede.getSelectedItem();

                // Realizar la asignación de stock
                try {
                    controlador.asignarStockSede(a.getMarca(), a.getArticulo(), a.getAtributos(), cantidad, sede);
                    JOptionPane.showMessageDialog(ventanaAsignarArticulosASede, "Stock asignado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NoExisteArticuloEnCatalogoException | NoExisteUsuarioException | NoExisteSedeException e1) {
                    JOptionPane.showMessageDialog(ventanaAsignarArticulosASede, "No se pudo asignar el stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
	            

	            ventanaAsignarArticulosASede.dispose();
	        }
	    });

	    panelInferior.add(labelCantidad);
	    panelInferior.add(campoCantidad);
	    panelInferior.add(labelSede);
	    panelInferior.add(comboSede);
	    panelInferior.add(new JLabel()); // Espacio vacío para alinear el botón en la misma fila
	    panelInferior.add(botonAceptar);

	    ventanaAsignarArticulosASede.add(tablaScroll, BorderLayout.CENTER);
	    ventanaAsignarArticulosASede.add(panelInferior, BorderLayout.SOUTH);

	    ventanaAsignarArticulosASede.pack();
	    ventanaAsignarArticulosASede.setVisible(true);
	}
	
	public int getArticuloSeleccionado() {
		return tablaArticulos.getSelectedRow();
	}
	
	public void configurarTabla(ArrayList<Articulo> catalogo) {
		for (Articulo articulo : catalogo) {
			Object[] rowData = { articulo.getArticulo(), articulo.getMarca(), articulo.getAtributos(),
					articulo.getTipoAmortizacion(), articulo.getPrecio() };
			tablaModelo.addRow(rowData);
		}
	}
	// ------------------------------------------------------------------------------------------------------------

	private void cambiarEstadoClase(ControladorAdministrativo controlador) {
		JFrame cambiarEstadoClase = new JFrame("Clases Agendadas");
		cambiarEstadoClase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cambiarEstadoClase.setLayout(new BorderLayout());
		cambiarEstadoClase.setLocationRelativeTo(null);

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
				String localidad = (String) sedeCombo.getSelectedItem();
				if (filaSeleccionada != -1) {
					cambiarEstadoClase(filaSeleccionada, EstadoClase.CONFIRMADA, localidad);
					cerrarVentana(e);
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
				String localidad = (String) sedeCombo.getSelectedItem();
				if (filaSeleccionada != -1) {
					cambiarEstadoClase(filaSeleccionada, EstadoClase.FINALIZADA, localidad);
					cerrarVentana(e);
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

	private void cambiarEstadoClase(int fila, EstadoClase estado, String localidad) {
		Clase clase = controlador.getSede(localidad).getClases().get(fila);
		clase.setEstado(estado);

		/*
		 * DefaultTableModel tablaModelo = (DefaultTableModel) tablaClases.getModel();
		 * tablaModelo.setValueAt(estado, fila, 2);
		 */
	}

	private void cerrarVentana(ActionEvent e) {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
		frame.dispose();
	}
	// ------------------------------------------------------------------------------------------
	private void agendarClase(ControladorAdministrativo controlador) {

	    JFrame ventanaAgendarClase = new JFrame("Agendar clase");
	    ventanaAgendarClase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaAgendarClase.setLayout(new GridLayout(9, 2));
	    ventanaAgendarClase.setLocationRelativeTo(null);

	    JLabel etiquetaDNI = new JLabel("Profesor");
	    JLabel etiquetaSede = new JLabel("Sede:");
	    JLabel etiquetaNombreClase = new JLabel("Nombre de la clase:");
	    JLabel etiquetaActividad = new JLabel("Actividad:");
	    JLabel etiquetaEmplazamiento = new JLabel("Emplazamiento:");
	    JLabel etiquetaFechaHora = new JLabel("Fecha y hora (YYYY-MM-DD HH:MM):");
	    JLabel etiquetaDuracion = new JLabel("Duración (minutos):");
	    JLabel etiquetaEnviarNotificaciones = new JLabel("Es online?:");


	    ArrayList<Profesor> profesores = controlador.getProfesores();
	    DefaultComboBoxModel<Profesor> comboProfesor = new DefaultComboBoxModel<>(profesores.toArray(new Profesor[0]));
	    JComboBox<Profesor> comboProfesorBox = new JComboBox<>(comboProfesor);


	    ArrayList<Sede> sedes = controlador.getSedes();
	    DefaultComboBoxModel<Sede> comboSede = new DefaultComboBoxModel<>(sedes.toArray(new Sede[0]));
	    JComboBox<Sede> comboSedeBox = new JComboBox<>(comboSede);

	    JTextField campoNombreClase = new JTextField();
	    DefaultComboBoxModel<String> comboActividad = new DefaultComboBoxModel<>();

	    for (String actividad : controlador.getActividades()) {
	        comboActividad.addElement(actividad);
	    }
	    JComboBox<String> comboActiviadBox = new JComboBox<>(comboActividad);

	    JComboBox<String> comboEmplazamiento = new JComboBox<>();

	    DefaultComboBoxModel<String> comboEmplazamiento1 = new DefaultComboBoxModel<>();

	    for (String emplazamiento : controlador.getEmplazamientos()) {
	        comboEmplazamiento1.addElement(emplazamiento);
	    }

	    JComboBox<String> comboEmplazamientoBox = new JComboBox<>(comboEmplazamiento1);

	    JTextField campoFechaHora = new JTextField();
	    JTextField campoDuracion = new JTextField();

	    JCheckBox checkBoxNotificaciones = new JCheckBox();

	    JButton botonAceptar = new JButton("Aceptar");
	    JButton botonCancelar = new JButton("Cancelar");

	    botonAceptar.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Profesor profesor = (Profesor) comboProfesorBox.getSelectedItem();
	            Sede sede = (Sede) comboSedeBox.getSelectedItem();
	            String nombreClase = campoNombreClase.getText();
	            String actividad = (String) comboActiviadBox.getSelectedItem();
	            String emplazamiento = (String) comboEmplazamientoBox.getSelectedItem();
	            int duracion = Integer.parseInt(campoDuracion.getText());
	            boolean enviarNotificaciones = checkBoxNotificaciones.isSelected();

	            try {
	                String horario = campoFechaHora.getText();
	                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	                LocalDateTime fechaHora = LocalDateTime.parse(horario, formato);

	                controlador.agendarClase(profesor.getDNI(), sede.getLocalidad(), nombreClase, actividad, emplazamiento, fechaHora, duracion, enviarNotificaciones);
	            } catch (DateTimeParseException e1) {
	                JOptionPane.showMessageDialog(null, "Fecha erronea. (yyyy/MM/dd HH:mm)", "Error", JOptionPane.ERROR_MESSAGE);
	                e1.printStackTrace();
	            } catch (ProfesorNoRegistradoException e1) {
	                JOptionPane.showMessageDialog(null, "Profesor ingresado no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
	                e1.printStackTrace();
	            } catch (NoExisteSedeException e1) {
	                JOptionPane.showMessageDialog(null, "No hay sede en esa localidad", "Error", JOptionPane.ERROR_MESSAGE);
	                e1.printStackTrace();
	            } catch (ProfesorNoDisponibleException e1) {
	                JOptionPane.showMessageDialog(null, "Profesor no disponible.", "Error", JOptionPane.ERROR_MESSAGE);
	                e1.printStackTrace();
	            } catch (Exception e1) {
	                JOptionPane.showMessageDialog(null, "Error al agendar la clase. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
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
	    ventanaAgendarClase.add(comboProfesorBox);
	    ventanaAgendarClase.add(etiquetaSede);
	    ventanaAgendarClase.add(comboSedeBox);
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
	    ventanaAgendarClase.add(etiquetaEnviarNotificaciones);
	    ventanaAgendarClase.add(checkBoxNotificaciones);
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
