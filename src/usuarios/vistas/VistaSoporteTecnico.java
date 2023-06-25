package usuarios.vistas;

import controlador.*;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;

import javax.swing.JButton;
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

        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");

        // Crear la opción "Crear Cliente"
        JMenuItem crearClienteItem = new JMenuItem("Crear Cliente");
        crearClienteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                crearCliente();
            }
        });
        opcionesMenu.add(crearClienteItem);

        
        menuBar.add(opcionesMenu);

        // Configurar la ventana principal
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        this.setVisible(true);
    }
	
	/*
	 * Metodos 
	 */

    private void crearCliente() {
        
        JFrame crearClienteFrame = new JFrame("Crear Cliente");
        
        ControladorSoporteTecnico controlador = new ControladorSoporteTecnico();

        // Crear los campos de texto para los datos del cliente
        JTextField idUsuarioCampo = new JTextField(10);
        JTextField nombreCampo = new JTextField(10);
        JTextField apellidoCampo = new JTextField(10);
        JTextField dniCampo = new JTextField(10);

        // Crear el menú desplegable para elegir el nivel
        String[] niveles = {"ORO", "PLATINUM", "BLACK"};
        JComboBox<String> nivelCombo = new JComboBox<>(niveles);

        // Crear el botón "Aceptar"
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados por el usuario
                int idUsuario = Integer.parseInt(idUsuarioCampo.getText());
                String nombre = nombreCampo.getText();
                String apellido = apellidoCampo.getText();
                String dni = dniCampo.getText();
                String nivel = (String) nivelCombo.getSelectedItem();

                // Llamar al controlador pasando los datos
                controlador.crearCliente(idUsuario, nombre, apellido, dni, nivel);

                // Cerrar la ventana de creación de cliente
                crearClienteFrame.dispose();
            }
        });

        // Configurar la ventana de creación de cliente
        crearClienteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        crearClienteFrame.setLayout(new GridLayout(6, 2, 5, 5));

        // Agregar los campos de texto, el menú desplegable y el botón a la ventana de creación de cliente
        crearClienteFrame.add(new JLabel("ID de Usuario:"));
        crearClienteFrame.add(idUsuarioCampo);
        crearClienteFrame.add(new JLabel("Nombre:"));
        crearClienteFrame.add(nombreCampo);
        crearClienteFrame.add(new JLabel("Apellido:"));
        crearClienteFrame.add(apellidoCampo);
        crearClienteFrame.add(new JLabel("DNI:"));
        crearClienteFrame.add(dniCampo);
        crearClienteFrame.add(new JLabel("Nivel:"));
        crearClienteFrame.add(nivelCombo);
        crearClienteFrame.add(new JLabel()); // Espacio en blanco
        crearClienteFrame.add(aceptarButton);

        // Mostrar la ventana de creación de cliente
        crearClienteFrame.pack();
        crearClienteFrame.setVisible(true);
    }


	

}
