package usuarios.vistas;

import javax.swing.*;

import modelo.usuarios.Administrativo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazSeleccionRol extends JFrame {
    public InterfazSeleccionRol() {
        // Configuración de la ventana principal
        setTitle("Selección de Rol");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Crear los elementos de la interfaz
        JLabel label = new JLabel("Selecciona tu rol:");
        JButton clienteButton = new JButton("Cliente");
        JButton administrativoButton = new JButton("Administrativo");
        JButton soporteButton = new JButton("Soporte Técnico");
        
        // Configurar el diseño de la interfaz
        setLayout(new BorderLayout());
        
        // Agregar los elementos a la ventana
        add(label, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1));
        panelBotones.add(clienteButton);
        panelBotones.add(administrativoButton);
        panelBotones.add(soporteButton);
        add(panelBotones, BorderLayout.CENTER);
        
        // Configurar acciones para los botones
        clienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para el rol de Cliente
                abrirVentanaCliente();
            }
        });
        
        administrativoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para el rol de Administrativo
                abrirVentanaAdministrativo();
            }
        });
        
        soporteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción para el rol de Soporte Técnico
                abrirVentanaSoporteTecnico();
            }
        });
    }
    
    public void abrirVentanaCliente() {
        JFrame ventanaCliente = new JFrame();
        ventanaCliente.setTitle("Ventana Cliente");
        ventanaCliente.setSize(200, 100);
        ventanaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Ventana para el rol de Cliente");
        ventanaCliente.add(label);
        
        ventanaCliente.setVisible(true);
    }
    
    public void abrirVentanaAdministrativo() {
  
    	
      	AdministrativoControlador controlador = new AdministrativoControlador();
    }
    
    public void abrirVentanaSoporteTecnico() {
        JFrame ventanaSoporteTecnico = new JFrame();
        ventanaSoporteTecnico.setTitle("Ventana Soporte Técnico");
        ventanaSoporteTecnico.setSize(200, 100);
        ventanaSoporteTecnico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Ventana para el rol de Soporte Técnico");
        ventanaSoporteTecnico.add(label);
        
        ventanaSoporteTecnico.setVisible(true);
    }
}