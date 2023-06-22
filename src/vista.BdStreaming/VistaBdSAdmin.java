package vista.BdStreaming;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.baseDeDatos.LimiteClasesException;
import controlador.ControladorBdStreaming;

public class VistaBdSAdmin extends JFrame {

    private ControladorBdStreaming controlador;

    private JTable tablaClases;
    private DefaultTableModel modeloTablaClases;
    private JComboBox<Actividad> comboActividades;
    private JTextField txtLimite;
    private JButton btnDefinirLimite;
    private JButton btnCambiarLimite;
    private JButton btnEliminarClase;

    public VistaBdSAdmin() {
        this.controlador = new ControladorBdStreaming();
        this.setTitle("Administración de Clases");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Establecer tamaño mínimo
        setMinimumSize(new Dimension(800, 600));
        
        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2, 5, 5));
        
        // Combo de actividades
        comboActividades = new JComboBox<>();
        List<Actividad> actividades = controlador.getActividades();
        for (Actividad actividad : actividades) {
            comboActividades.addItem(actividad);
        }
        panelSuperior.add(new JLabel("Actividad:"));
        panelSuperior.add(comboActividades);

        // Campo de límite
        txtLimite = new JTextField();
        panelSuperior.add(new JLabel("Límite:"));
        panelSuperior.add(txtLimite);

        this.add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());

        // Tabla de clases
        tablaClases = new JTable();
        modeloTablaClases = new DefaultTableModel();
        modeloTablaClases.setColumnIdentifiers(new Object[] {"ID", "Nombre Clase", "Actividad", "Fecha", "Limite Almacenamiento"});
        tablaClases.setModel(modeloTablaClases);
        JScrollPane scrollTabla = new JScrollPane(tablaClases);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        this.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel();

        // Botón Definir Límite
        btnDefinirLimite = new JButton("Definir Limite");
        btnDefinirLimite.addActionListener(new BtnDefinirLimiteListener());
        panelInferior.add(btnDefinirLimite);

        // Botón Cambiar Límite
        btnCambiarLimite = new JButton("Cambiar Limite");
        btnCambiarLimite.addActionListener(new BtnCambiarLimiteListener());
        panelInferior.add(btnCambiarLimite);
        
        // Botón Eliminar Clase
        btnEliminarClase = new JButton("Eliminar Clase");
        btnEliminarClase.addActionListener(new BtnCambiarLimiteListener());
        panelInferior.add(btnEliminarClase);

        this.add(panelInferior, BorderLayout.SOUTH);

        // Cargar datos iniciales
        cargarClases();

    }

    private void cargarClases() {
        // Obtener las clases almacenadas del controlador

        // Limpiar la tabla

        // Llenar la tabla con los datos de las clases almacenadas
       
    }


    private class BtnDefinirLimiteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener la actividad seleccionada del combo, necesitamos un listado de actividades totales del gimnasio

            // Obtener el límite ingresado en el campo de texto

            // Llamar al método del controlador para definir el límite por tipo de actividad

            // Actualizar la tabla de clases

            // Limpiar los campos
        }
    }
    
    private class BtnCambiarLimiteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener la actividad seleccionada del combo

            // Obtener el límite ingresado en el campo de texto

            // Llamar al método del controlador para cambiar el límite por tipo de actividad

            // Actualizar la tabla de clases

            // Limpiar los campos
        }
    }
    
    private class BtnEliminarClase implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener la actividad seleccionada del combo

            // Llamar al método del controlador para eliminar una clase

            // Actualizar la tabla de clases

            // Limpiar los campos
        }
    }

    public void mostrarVista() {
        this.setVisible(true);
    }
}
