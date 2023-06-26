package vista.Funcionalidades;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import modelo.sedes.*;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;

public class VistaAgendarClase extends JFrame {
	private ControladorAgendarClase controlador;
    private JComboBox<String> profesorComboBox;
    private JTextField nombreCampo;
    private JComboBox<String> actividadComboBox;
    private JComboBox<String> emplazamientoComboBox;
    private JButton btnSeleccionarFecha;
    private JTextField duracionCampo;
    private JComboBox<String> horarioCampo;

    public VistaAgendarClase(ControladorAgendarClase contr) {
        super("Agendar Clase");
        setLayout(new BorderLayout());
        // Crear el panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setLayout(new GridLayout(0, 2));

        this.controlador=contr;
        // Profesor
        JLabel profesorLabel = new JLabel("Profesor:");
        ArrayList<String> profes=listaProfesores();
        profesorComboBox = new JComboBox<>(profes.toArray(new String[profes.size()]));
        
        //nombre de la clase
        JLabel claseLabel = new JLabel("Nombre de la clase:");
        nombreCampo = new JTextField();

        //actividad
        JLabel actividadLabel = new JLabel("Actividad:");
        ArrayList<String> actividades=getActividades();
        actividadComboBox = new JComboBox<>(actividades.toArray(new String[actividades.size()]));

        //emplazamiento
        JLabel emplazamientoLabel = new JLabel("Emplazamiento:");
        ArrayList<String> emplazamientos=getEmplazamientos();
        emplazamientoComboBox = new JComboBox<>(emplazamientos.toArray(new String[emplazamientos.size()]));

        //fecha
        JLabel fechaLabel = new JLabel("Fecha:");
        btnSeleccionarFecha= new JButton("Seleccionar Fecha");
        JCalendar calendar = new JCalendar();
        
        //horario
        JLabel horaLabel = new JLabel("Horario:");
        ArrayList<String> horarios=generarHorarios();
        horarioCampo = new JComboBox<>(horarios.toArray(new String[horarios.size()]));
   
        
        //duracion de la clase
        JLabel duracionLabel = new JLabel("Duración:");
        duracionCampo=new JTextField();
        


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
        panel.add(btnSeleccionarFecha);
        panel.add(horaLabel);
        panel.add(horarioCampo);
        panel.add(duracionLabel);
        panel.add(duracionCampo);
               
        
        //crea el boton seleccionar fecha
        btnSeleccionarFecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	JOptionPane.showMessageDialog(null, calendar, "Seleccione una Fecha", JOptionPane.PLAIN_MESSAGE);
            	
            }
        });
        
        
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
                
                String duracion =  (String) duracionCampo.getSelectedText();
                String hora=(String) horarioCampo.getSelectedItem();
                String[] partes = hora.split(":");  // formato hora es 00:00 y divide en dos elementos 
                LocalDateTime fechaSeleccionada = LocalDateTime.of(calendar.getCalendar().get(Calendar.YEAR), calendar.getCalendar().get(Calendar.MONTH) + 1, calendar.getCalendar().get(Calendar.DAY_OF_MONTH), Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
                // Realizar la acción deseada con los datos

                // ...

                
                // cierra la ventana
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			    frame.dispose();
                
            }
        });
        
       
        
                // Crear el botón "Cancelar"
                JButton cancelarButton = new JButton("Cancelar");
                cancelarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Limpiar los campos
                    	//nombreCampo.setText("");
                        
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
    
    private ArrayList<String> getActividades(){
    	ArrayList<String> act =new ArrayList<>();
    	for(Actividad a:GimnasioSingleton.getInstance().getActividades()) {
    		act.add(a.getTipoClase());
    	}
    	return act;
    }
    
    
    private ArrayList<String> getEmplazamientos(){
    	ArrayList<String> emplazam =new ArrayList<>();
    	for(Emplazamiento e:controlador.getSede().getEmplazamientos()) {
    		emplazam.add(e.getTipoEmplazamiento());
    	}
    	return emplazam;
    }
    
    private ArrayList<String> listaProfesores(){
    	ArrayList<String> profes=new ArrayList<>();
    	for(Profesor p: controlador.getSede().getProfesores()) {
    		profes.add(p.getApellido()+" "+p.getDNI());
    	}
    	return profes;
    }
    
    private ArrayList<String> generarHorarios() {
    	ArrayList<String> horarios = new ArrayList<>();

        LocalTime hora = LocalTime.of(7,0); // Iniciar desde las 07:00

        while (!hora.equals(LocalTime.of(23, 30))) {
            horarios.add(hora.toString());
            hora = hora.plusMinutes(30);
        }

        return horarios;
    }

 
}

