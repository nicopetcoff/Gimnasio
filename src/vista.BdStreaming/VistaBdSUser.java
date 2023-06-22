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

import controlador.ControladorBdStreaming;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;

public class VistaBdSUser extends JFrame {
    private JComboBox<Actividad> comboActividades;
    private JTextField txtIDClase;
    private JTable tablaClases;
    private ControladorBdStreaming controlador;
    
    public VistaBdSUser() {
        super("BdStreaming");
        this.controlador = new ControladorBdStreaming();
        this.setTitle("Busqueda por Actividad");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        // Establecer tamaño minimo
        setMinimumSize(new Dimension(800, 600));
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2, 2, 5, 5));
        
        // Crear el combo de actividades
        comboActividades = new JComboBox<>();
        comboActividades.addActionListener(new ComboActividadesListener());
        
        // Agregar las actividades al combo
        List<Actividad> actividades = controlador.getActividades();
        for (Actividad actividad : actividades) {
            comboActividades.addItem(actividad); //Revisar
        }
        
        panelSuperior.add(new JLabel("Actividad"));
        panelSuperior.add(comboActividades);
        add(panelSuperior, BorderLayout.NORTH);
        
        // Campo de límite
        txtIDClase = new JTextField();
        panelSuperior.add(new JLabel("Buscar por ID de Clase:"));
        panelSuperior.add(txtIDClase);
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
        
        DefaultTableModel modeloTablaClases = new DefaultTableModel();
        Object[] titulos = {"ID", "Nombre Clase", "Actividad", "Fecha"};
        modeloTablaClases.setColumnIdentifiers(titulos);
        
        tablaClases = new JTable(modeloTablaClases);
        JScrollPane scrollTabla = new JScrollPane(tablaClases);
        
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        
        
        // Panel inferior
        JPanel panelInferior = new JPanel();
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new BtnBuscarListener());
        panelInferior.add(btnBuscar, BorderLayout.SOUTH);
        
        this.add(panelInferior, BorderLayout.SOUTH);
        
        // Cargar datos iniciales
        cargarClases();
        
    }

    private void cargarClases() {
        // Obtener las clases almacenadas del controlador

        // Limpiar la tabla

        // Llenar la tabla con los datos de las clases almacenadas
    }
    
    
    private class ComboActividadesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
	        //CargarClases?, mostrar todas las clases
			
		}
      
    }

    
    private class BtnBuscarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener la actividad seleccionada del combo

            // Obtener las clases segun actividad seleccionada

            // Actualizar la tabla de clases
        }
    }

    public void mostrarVista() {
        this.setVisible(true);
    }
    
}
