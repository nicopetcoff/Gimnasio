package vista.Funcionalidades;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import modelo.sedes.Actividad;
import modelo.sedes.Emplazamiento;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Profesor;

public class VistaAgendarClase extends JFrame {
	private ControladorAgendarClase controlador;
	private JComboBox<String> profesorComboBox;
	private JTextField nombreCampo;
	private JComboBox<String> actividadComboBox;
	private JComboBox<String> emplazamientoComboBox;
	private JTextField duracionCampo, campoFecha;
	private JComboBox<String> horarioCampo;

	public VistaAgendarClase(ControladorAgendarClase contr) {
		super("Agendar Clase");
		setLayout(new BorderLayout());
		// Crear el panel principal
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new GridLayout(0, 2));

		this.controlador = contr;
		// Profesor
		JLabel profesorLabel = new JLabel("Profesor:");
		ArrayList<String> profes = listaProfesores();
		profesorComboBox = new JComboBox<>(profes.toArray(new String[profes.size()]));

		// nombre de la clase
		JLabel claseLabel = new JLabel("Nombre de la clase:");
		nombreCampo = new JTextField();

		// actividad
		JLabel actividadLabel = new JLabel("Actividad:");
		ArrayList<String> actividades = getActividades();
		actividadComboBox = new JComboBox<>(actividades.toArray(new String[actividades.size()]));

		// emplazamiento
		JLabel emplazamientoLabel = new JLabel("Emplazamiento:");
		ArrayList<String> emplazamientos = getEmplazamientos();
		emplazamientoComboBox = new JComboBox<>(emplazamientos.toArray(new String[emplazamientos.size()]));

		// fecha
		JLabel fechaLabel = new JLabel("Fecha: (dd/mm/yyyy) ");
		campoFecha = new JTextField();

		// horario
		JLabel horaLabel = new JLabel("Horario:");
		ArrayList<String> horarios = generarHorarios();
		horarioCampo = new JComboBox<>(horarios.toArray(new String[horarios.size()]));

		// duracion de la clase
		JLabel duracionLabel = new JLabel("Duración:");
		duracionCampo = new JTextField();

		// Agregar los componentes al panel
		panel.add(profesorLabel);
		panel.add(profesorComboBox);
		panel.add(claseLabel);
		panel.add(nombreCampo);
		panel.add(actividadLabel);
		panel.add(actividadComboBox);
		panel.add(emplazamientoLabel);
		panel.add(emplazamientoComboBox);
		panel.add(fechaLabel);
		panel.add(campoFecha);
		panel.add(horaLabel);
		panel.add(horarioCampo);
		panel.add(duracionLabel);
		panel.add(duracionCampo);

		// Crear el botón "Aceptar"
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Obtener los valores seleccionados/ingresados
				String profesor = (String) profesorComboBox.getSelectedItem();
				String clase = nombreCampo.getText();
				String actividad = (String) actividadComboBox.getSelectedItem();
				String emplazamiento = (String) emplazamientoComboBox.getSelectedItem();
				// fecha
				String fechaString = (String) campoFecha.getText();

				String duracion = (String) duracionCampo.getSelectedText();
				String hora = (String) horarioCampo.getSelectedItem();
				String[] partes = hora.split(":"); // formato hora es 00:00 y divide en dos elementos

				// cierra la ventana
				if (controlador.validarFecha(fechaString)) {
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate fecha = LocalDate.parse(fechaString, formato);
					LocalDateTime fechaHorario = fecha.atTime(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null,
							"Fecha inválida. Por favor, ingrese una fecha válida en formato dd/MM/yyyy.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
					campoFecha.setText("");
				}

			}
		});

		// Crear el botón "Cancelar"
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpiar los campos
				// nombreCampo.setText("");

			}
		});
		panel.add(cancelarButton);

		// Agregar los botones al panel
		panel.add(aceptarButton);

		// Agregar el panel al marco principal
		getContentPane().add(panel);

		// Configurar el marco principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private ArrayList<String> getActividades() {
		ArrayList<String> act = new ArrayList<>();
		for (Actividad a : GimnasioSingleton.getInstance().getActividades()) {
			act.add(a.getTipoClase());
		}
		return act;
	}

	private ArrayList<String> getEmplazamientos() {
		ArrayList<String> emplazam = new ArrayList<>();
		for (Emplazamiento e : controlador.getSede().getEmplazamientos()) {
			emplazam.add(e.getTipoEmplazamiento());
		}
		return emplazam;
	}

	private ArrayList<String> listaProfesores() {
		ArrayList<String> profes = new ArrayList<>();
		for (Profesor p : controlador.getSede().getProfesores()) {
			profes.add(p.getApellido() + " " + p.getDNI());
		}
		return profes;
	}

	private ArrayList<String> generarHorarios() {
		ArrayList<String> horarios = new ArrayList<>();

		LocalTime hora = LocalTime.of(7, 0); // Iniciar desde las 07:00

		while (!hora.equals(LocalTime.of(23, 30))) {
			horarios.add(hora.toString());
			hora = hora.plusMinutes(30);
		}

		return horarios;
	}

}