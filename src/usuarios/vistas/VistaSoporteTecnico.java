package usuarios.vistas;

import controlador.*;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.usuarios.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VistaSoporteTecnico extends JFrame {

	public VistaSoporteTecnico() {
		super("Soporte Técnico");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 300);
		setLayout(new FlowLayout());
		setVisible(true);

		ArrayList<Usuario> listaUsuariosSoporteTecnico = obtenerUsuariosSoporteTecnico();

		DefaultTableModel modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Apellido");
		modeloTabla.addColumn("DNI");
		modeloTabla.addColumn("ID");

		for (Usuario usuarioSoporteTecnico : listaUsuariosSoporteTecnico) {
			Object[] fila = { usuarioSoporteTecnico.getNombre(), usuarioSoporteTecnico.getApellido(),
					usuarioSoporteTecnico.getDni(), usuarioSoporteTecnico.getId() };
			modeloTabla.addRow(fila);
		}

		JTable tablaUsuariosSoporteTecnico = new JTable(modeloTabla);
		JScrollPane scrollPaneUsuariosSoporteTecnico = new JScrollPane(tablaUsuariosSoporteTecnico);

		JMenuBar menuBar = new JMenuBar();
		JMenu opcionesMenu = new JMenu("Opciones");

		
		JMenuItem crearSede = new JMenuItem("Crear Sede");
		crearSede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearSede();
			}
			
		});
		
		JMenuItem crearCliente = new JMenuItem("Crear Cliente");
		crearCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				crearCliente();
			}
		});
		
		
		JMenuItem crearSoporteTecnico = new JMenuItem("Crear Soporte Tecnico");
		crearSoporteTecnico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearSoporteTecnico();
			}
			
		});
		
		JMenuItem crearAdministrativo = new JMenuItem("Crear Administrativo");
		crearAdministrativo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearProfesor();
			}
			
		});
		
		JMenuItem crearProfesor = new JMenuItem("Crear Profesor");
		crearProfesor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearProfesor();
			}
			
		});
		
		JMenuItem crearActividad = new JMenuItem("Crear Actividad");
		crearActividad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearActividad();
			}
			
		});
		
		JMenuItem crearEmplazamiento = new JMenuItem("Crear Emplazamiento");
		crearEmplazamiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearEmplazamiento();
			}
			
		});
		
		JMenuItem asignarEmplazamientoASede = new JMenuItem("Asignar Emplazamiento a Sede");
		asignarEmplazamientoASede.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				asignarEmplazamientoASede();
			}
			
		});
		
		JMenuItem crearArticuloEnCatalogo = new JMenuItem("Crear Articulo en Catalogo");
		crearArticuloEnCatalogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearArticuloEnCatalogo();
			}
			
		});
		
		
		
		
		
		opcionesMenu.add(crearSede);
		opcionesMenu.add(crearCliente);
		opcionesMenu.add(crearSoporteTecnico);
		opcionesMenu.add(crearAdministrativo);
		opcionesMenu.add(crearProfesor);
		opcionesMenu.add(crearActividad);
		opcionesMenu.add(crearEmplazamiento);
		opcionesMenu.add(asignarEmplazamientoASede);
		opcionesMenu.add(crearArticuloEnCatalogo);
		
		
		menuBar.add(opcionesMenu);
		
		setJMenuBar(menuBar);
		
		this.add(scrollPaneUsuariosSoporteTecnico);

		

	}

	private void crearArticuloEnCatalogo() {
	    JFrame ventanaCrearArticulo = new JFrame("Crear Artículo en Catalogo");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    JLabel marcaLabel = new JLabel("Marca:");
	    JTextField marcaCampo = new JTextField(10);

	    JLabel articuloLabel = new JLabel("Artículo:");
	    JTextField articuloCampo = new JTextField(10);

	    JLabel fechaFabricacionLabel = new JLabel("Fecha de Fabricación (AAAA-MM-DD):");
	    JTextField fechaFabricacionCampo = new JTextField(10);

	    JLabel tipoAmortizacionLabel = new JLabel("Tipo de Amortización:");
	    JComboBox<String> tipoAmortizacionComboBox = new JComboBox<>(new String[] { "DIAS_DE_USO", "FECHA_DE_FABRICA" });

	    JLabel durabilidadLabel = new JLabel("Durabilidad:");
	    JTextField durabilidadCampo = new JTextField(10);

	    JLabel precioLabel = new JLabel("Precio:");
	    JTextField precioCampo = new JTextField(10);

	    JLabel atributosLabel = new JLabel("Atributos:");
	    JTextArea atributosCampo = new JTextArea(5, 20);

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String marca = marcaCampo.getText();
	            String articulo = articuloCampo.getText();
	            LocalDate fechaFabricacion = LocalDate.parse(fechaFabricacionCampo.getText());
	            String tipoAmortizacion = (String) tipoAmortizacionComboBox.getSelectedItem();
	            int durabilidad = Integer.parseInt(durabilidadCampo.getText());
	            double precio = Double.parseDouble(precioCampo.getText());
	            String atributos = atributosCampo.getText();

	            controlador.crearArticuloEnStock(idGestion, marca, articulo, fechaFabricacion, tipoAmortizacion, durabilidad, precio, atributos);

	            ventanaCrearArticulo.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(9, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	    panelPrincipal.add(idGestionCampo);
	    panelPrincipal.add(marcaLabel);
	    panelPrincipal.add(marcaCampo);
	    panelPrincipal.add(articuloLabel);
	    panelPrincipal.add(articuloCampo);
	    panelPrincipal.add(fechaFabricacionLabel);
	    panelPrincipal.add(fechaFabricacionCampo);
	    panelPrincipal.add(tipoAmortizacionLabel);
	    panelPrincipal.add(tipoAmortizacionComboBox);
	    panelPrincipal.add(durabilidadLabel);
	    panelPrincipal.add(durabilidadCampo);
	    panelPrincipal.add(precioLabel);
	    panelPrincipal.add(precioCampo);
	    panelPrincipal.add(atributosLabel);
	    panelPrincipal.add(new JScrollPane(atributosCampo));
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaCrearArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearArticulo.add(panelPrincipal);
	    ventanaCrearArticulo.pack();
	    ventanaCrearArticulo.setVisible(true);
	}



	private void asignarEmplazamientoASede() {
		
	    JFrame ventanaAsignarEmplazamiento = new JFrame("Asignar Emplazamiento a Sede");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    ArrayList<String> listaEmplazamientos = controlador.getEmplazamientos();

	    JLabel emplazamientoLabel = new JLabel("Seleccionar Emplazamiento:");
	    JComboBox<String> emplazamientoComboBox = new JComboBox<>(listaEmplazamientos.toArray(new String[0]));

	    ArrayList<String> listaSedes = controlador.getSedes();

	    JLabel sedeLabel = new JLabel("Seleccionar Sede:");
	    JComboBox<String> sedeComboBox = new JComboBox<>(listaSedes.toArray(new String[0]));

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String emplazamientoSeleccionado = (String) emplazamientoComboBox.getSelectedItem();
	            String sedeSeleccionada = (String) sedeComboBox.getSelectedItem();

	            controlador.asignarEmplazamientoASede(idGestion, sedeSeleccionada, emplazamientoSeleccionado);
	            
	            ventanaAsignarEmplazamiento.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(4, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	    panelPrincipal.add(idGestionCampo);
	    panelPrincipal.add(emplazamientoLabel);
	    panelPrincipal.add(emplazamientoComboBox);
	    panelPrincipal.add(sedeLabel);
	    panelPrincipal.add(sedeComboBox);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaAsignarEmplazamiento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaAsignarEmplazamiento.add(panelPrincipal);
	    ventanaAsignarEmplazamiento.pack();
	    ventanaAsignarEmplazamiento.setVisible(true);
	}


	private void crearEmplazamiento() {
	    JFrame ventanaCrearEmplazamiento = new JFrame("Crear Emplazamiento");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    JLabel nombreLabel = new JLabel("Nombre del Emplazamiento:");
	    JTextField nombreCampo = new JTextField(20);

	    JLabel factorLabel = new JLabel("Factor de Cálculo:");
	    JTextField factorCampo = new JTextField(10);

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String nombreEmplazamiento = nombreCampo.getText();
	            double factorCalculo = Double.parseDouble(factorCampo.getText());

	            controlador.crearEmplazamiento(idGestion, nombreEmplazamiento, factorCalculo);

	            ventanaCrearEmplazamiento.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(4, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	    panelPrincipal.add(idGestionCampo);
	    panelPrincipal.add(nombreLabel);
	    panelPrincipal.add(nombreCampo);
	    panelPrincipal.add(factorLabel);
	    panelPrincipal.add(factorCampo);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaCrearEmplazamiento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearEmplazamiento.add(panelPrincipal);
	    ventanaCrearEmplazamiento.pack();
	    ventanaCrearEmplazamiento.setVisible(true);
	}



	private void crearActividad() {
	    JFrame ventanaCrearActividad = new JFrame("Crear Actividad");
	    
	   ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    JLabel actividadLabel = new JLabel("Nombre de la Actividad:");
	    JTextField actividadCampo = new JTextField(20);

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String nombreActividad = actividadCampo.getText();

	            controlador.crearActividad(idGestion, nombreActividad);

	            ventanaCrearActividad.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(3, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	    panelPrincipal.add(idGestionCampo);
	    panelPrincipal.add(actividadLabel);
	    panelPrincipal.add(actividadCampo);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaCrearActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearActividad.add(panelPrincipal);
	    ventanaCrearActividad.pack();
	    ventanaCrearActividad.setVisible(true);
	}



	private void crearProfesor() {
	    JFrame ventanaCrearProfesor = new JFrame("Crear Profesor");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    JLabel nombreLabel = new JLabel("Nombre:");
	    JTextField nombreCampo = new JTextField(10);

	    JLabel apellidoLabel = new JLabel("Apellido:");
	    JTextField apellidoCampo = new JTextField(10);

	    JLabel dniLabel = new JLabel("DNI:");
	    JTextField dniCampo = new JTextField(10);

	    JLabel sueldoLabel = new JLabel("Sueldo:");
	    JTextField sueldoCampo = new JTextField(10);

	    JLabel sedeLabel = new JLabel("Sede:");
	    ArrayList<String> sedes = controlador.getSedes();
	    JComboBox<String> sedeCombo = new JComboBox<>(sedes.toArray(new String[sedes.size()]));

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String nombre = nombreCampo.getText();
	            String apellido = apellidoCampo.getText();
	            String dni = dniCampo.getText();
	            double sueldo = Double.parseDouble(sueldoCampo.getText());
	            String sedeSeleccionada = (String) sedeCombo.getSelectedItem();

	            controlador.crearProfesor(idGestion, nombre, apellido, dni, sueldo, sedeSeleccionada);

	            ventanaCrearProfesor.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(7, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	   
	}


	private void crearAdministrativo() {
	    JFrame ventanaCrearAdmin = new JFrame("Crear Administrativo");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idGestionLabel = new JLabel("ID de Gestión:");
	    JTextField idGestionCampo = new JTextField(10);

	    JLabel nombreLabel = new JLabel("Nombre:");
	    JTextField nombreCampo = new JTextField(10);

	    JLabel apellidoLabel = new JLabel("Apellido:");
	    JTextField apellidoCampo = new JTextField(10);

	    JLabel dniLabel = new JLabel("DNI:");
	    JTextField dniCampo = new JTextField(10);

	    JLabel sedeLabel = new JLabel("Sede:");
	    ArrayList<String> sedes = controlador.getSedes();
	    JComboBox<String> sedeCombo = new JComboBox<>(sedes.toArray(new String[sedes.size()]));

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int idGestion = Integer.parseInt(idGestionCampo.getText());
	            String nombre = nombreCampo.getText();
	            String apellido = apellidoCampo.getText();
	            String dni = dniCampo.getText();
	            String sedeSeleccionada = (String) sedeCombo.getSelectedItem();

	            controlador.crearAdministrativo(idGestion, nombre, apellido, dni, sedeSeleccionada);

	            ventanaCrearAdmin.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(6, 2, 5, 5));
	    panelPrincipal.add(idGestionLabel);
	    panelPrincipal.add(idGestionCampo);
	    panelPrincipal.add(nombreLabel);
	    panelPrincipal.add(nombreCampo);
	    panelPrincipal.add(apellidoLabel);
	    panelPrincipal.add(apellidoCampo);
	    panelPrincipal.add(dniLabel);
	    panelPrincipal.add(dniCampo);
	    panelPrincipal.add(sedeLabel);
	    panelPrincipal.add(sedeCombo);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaCrearAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearAdmin.add(panelPrincipal);
	    ventanaCrearAdmin.pack();
	    ventanaCrearAdmin.setVisible(true);
	}



	private void crearSoporteTecnico() {
	    JFrame ventanaCrearSoporte = new JFrame("Crear Soporte Técnico");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idLabel = new JLabel("ID de Gestion:");
	    JTextField idCampo = new JTextField(10);

	    JLabel nombreLabel = new JLabel("Nombre:");
	    JTextField nombreCampo = new JTextField(10);

	    JLabel apellidoLabel = new JLabel("Apellido:");
	    JTextField apellidoCampo = new JTextField(10);

	    JLabel dniLabel = new JLabel("DNI:");
	    JTextField dniCampo = new JTextField(10);

	    JButton aceptarBoton = new JButton("Aceptar");
	    aceptarBoton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int id = Integer.parseInt(idCampo.getText());
	            String nombre = nombreCampo.getText();
	            String apellido = apellidoCampo.getText();
	            String dni = dniCampo.getText();

	            controlador.crearSoporteTecnico(id, nombre, apellido, dni);

	            ventanaCrearSoporte.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(5, 2, 5, 5));
	    panelPrincipal.add(idLabel);
	    panelPrincipal.add(idCampo);
	    panelPrincipal.add(nombreLabel);
	    panelPrincipal.add(nombreCampo);
	    panelPrincipal.add(apellidoLabel);
	    panelPrincipal.add(apellidoCampo);
	    panelPrincipal.add(dniLabel);
	    panelPrincipal.add(dniCampo);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarBoton);

	    ventanaCrearSoporte.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearSoporte.add(panelPrincipal);
	    ventanaCrearSoporte.pack();
	    ventanaCrearSoporte.setVisible(true);
	}


	private void crearSede() {
	    JFrame ventanaCrearSede = new JFrame("Crear Sede");
	    
	    ControladorST controlador = new ControladorST();

	    JLabel idLabel = new JLabel("ID de Gestion:");
	    JTextField idField = new JTextField(10);

	    JLabel localidadLabel = new JLabel("Localidad:");
	    JTextField localidadField = new JTextField(10);

	    JLabel nivelLabel = new JLabel("Nivel:");
	    String[] niveles = {"ORO", "PLATINUM", "BLACK"};
	    JComboBox<String> nivelCombo = new JComboBox<>(niveles);

	    JLabel precioLabel = new JLabel("Precio de alquiler:");
	    JTextField precioField = new JTextField(10);

	    JLabel capacidadLabel = new JLabel("Capacidad:");
	    JTextField capacidadField = new JTextField(10);

	    JLabel descripcionLabel = new JLabel("Descripción:");
	    JTextField descripcionField = new JTextField(10);

	    JButton aceptarButton = new JButton("Aceptar");
	    aceptarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int id = Integer.parseInt(idField.getText());
	            String localidad = localidadField.getText();
	            String nivel = (String) nivelCombo.getSelectedItem();
	            double precio = Double.parseDouble(precioField.getText());
	            int capacidad = Integer.parseInt(capacidadField.getText());
	            String descripcion = descripcionField.getText();

	            controlador.crearSede(id, localidad, nivel, precio, capacidad, descripcion);

	            ventanaCrearSede.dispose();
	        }
	    });

	    JPanel panelPrincipal = new JPanel(new GridLayout(7, 2, 5, 5));
	    panelPrincipal.add(idLabel);
	    panelPrincipal.add(idField);
	    panelPrincipal.add(localidadLabel);
	    panelPrincipal.add(localidadField);
	    panelPrincipal.add(nivelLabel);
	    panelPrincipal.add(nivelCombo);
	    panelPrincipal.add(precioLabel);
	    panelPrincipal.add(precioField);
	    panelPrincipal.add(capacidadLabel);
	    panelPrincipal.add(capacidadField);
	    panelPrincipal.add(descripcionLabel);
	    panelPrincipal.add(descripcionField);
	    panelPrincipal.add(new JLabel());
	    panelPrincipal.add(aceptarButton);

	    ventanaCrearSede.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    ventanaCrearSede.add(panelPrincipal);
	    ventanaCrearSede.pack();
	    ventanaCrearSede.setVisible(true);
	}

	

	private void crearCliente() {

		JFrame crearClienteFrame = new JFrame("Crear Cliente");		

		ControladorST controlador = new ControladorST();

		
		JTextField idUsuarioCampo = new JTextField(10);
		JTextField nombreCampo = new JTextField(10);
		JTextField apellidoCampo = new JTextField(10);
		JTextField dniCampo = new JTextField(10);

		String[] niveles = { "ORO", "PLATINUM", "BLACK" };
		JComboBox<String> nivelCombo = new JComboBox<>(niveles);

		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idUsuario = Integer.parseInt(idUsuarioCampo.getText());
				String nombre = nombreCampo.getText();
				String apellido = apellidoCampo.getText();
				String dni = dniCampo.getText();
				String nivel = (String) nivelCombo.getSelectedItem();

				controlador.crearCliente(idUsuario, nombre, apellido, dni, nivel);

				crearClienteFrame.dispose();
			}
		});		

		crearClienteFrame.add(new JLabel("ID de Gestion:"));
		crearClienteFrame.add(idUsuarioCampo);
		crearClienteFrame.add(new JLabel("Nombre:"));
		crearClienteFrame.add(nombreCampo);
		crearClienteFrame.add(new JLabel("Apellido:"));
		crearClienteFrame.add(apellidoCampo);
		crearClienteFrame.add(new JLabel("DNI:"));
		crearClienteFrame.add(dniCampo);
		crearClienteFrame.add(new JLabel("Nivel:"));
		crearClienteFrame.add(nivelCombo);
		crearClienteFrame.add(new JLabel()); 
		crearClienteFrame.add(aceptarButton);
		
		crearClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		crearClienteFrame.setLayout(new GridLayout(6, 2, 5, 5));
		crearClienteFrame.pack();
		crearClienteFrame.setVisible(true);
		
	}
	
	private ArrayList<Usuario> obtenerUsuariosSoporteTecnico() {

		ControladorST controlador = new ControladorST();

		return controlador.getSoporteTecnicos();
	}

}
