package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import controlador.ControladorBdStreaming;
import modelo.productos.NoHayStockException;
import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.supertlon.Excepciones.NoexisteClaseException;
import modelo.usuarios.Cliente;

public class ClienteVista extends JFrame {
    private JButton botonReservarClase = new JButton("Reservar clase");
    private JButton botonCancelarReserva = new JButton("Cancelar reserva");
    private JButton botonBdStreaming = new JButton("BdStreaming");
    private JButton BotonConfirmar=new JButton("Confirmar");
    private DefaultTableModel tablaModelo = new DefaultTableModel();
    private JTable tablaClases = new JTable(tablaModelo);
    private Cliente cliente;
    private ClienteControlador controlador;

    public ClienteVista(Cliente cliente) {
        this.cliente = cliente;
        this.controlador = new ClienteControlador();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        this.setBackground(Color.WHITE);
        this.setTitle("Panel de Control - Cliente");
        this.setVisible(true);
       
        controlador.setVista(this);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.add(botonReservarClase);
        panelBotones.add(botonCancelarReserva);
        panelBotones.add(botonBdStreaming);

        tablaModelo.addColumn("Nombre");
        tablaModelo.addColumn("Actividad");
        tablaModelo.addColumn("Sede");
        tablaModelo.addColumn("Fecha");

        JScrollPane tablaScroll = new JScrollPane(tablaClases, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
        panelTabla.add(new JLabel("Clases disponibles"));
        panelTabla.add(tablaScroll);
        configurarTablaClasesDispo();

        this.getContentPane().add(panelBotones);
        this.getContentPane().add(panelTabla);

        pack();

        botonReservarClase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = getFilaSeleccionada();
                if (filaSeleccionada >= 0) {
                    int modeloFilaSeleccionada = tablaClases.convertRowIndexToModel(filaSeleccionada);
                    String nombre = (String) tablaModelo.getValueAt(modeloFilaSeleccionada, 0);  
                    
                    
                    LocalDateTime fecha = (LocalDateTime) tablaModelo.getValueAt(modeloFilaSeleccionada, 3);
                    
                    try {
                        controlador.reservarClase(nombre, fecha);
                        mostrarMensaje("Clase reservada correctamente.");
                    } catch (NoExisteUsuarioException | NoMismoNivelException | NoexisteClaseException
                            | NoHayStockException e1) {
                        JOptionPane.showMessageDialog(null, "No se pudo agendar la clase.");
                        e1.printStackTrace();
                    }
                } else {
                    mostrarMensaje("Por favor, seleccione una clase de la tabla.");
                }
            }
        });


        botonCancelarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelarReserva();
            }
        });


        botonBdStreaming.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorBdStreaming instancia = new ControladorBdStreaming();
                instancia.abrirVistaBdStreamingUser();
            }
        });

    }
    
    private void cancelarReserva() {
    	JFrame cancelarReserva = new JFrame("Clases Agendadas");
        cancelarReserva.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cancelarReserva.setLayout(new BoxLayout(cancelarReserva.getContentPane(), BoxLayout.Y_AXIS));
        cancelarReserva.setBackground(Color.WHITE);
        cancelarReserva.setTitle("Panel de Control - Cliente");
        cancelarReserva.setSize(500, 350);
        cancelarReserva.setVisible(true);
        cancelarReserva.setLocationRelativeTo(null);

        DefaultTableModel tablaModelo = new DefaultTableModel();
        tablaModelo.addColumn("Nombre");
        tablaModelo.addColumn("Actividad");
        tablaModelo.addColumn("Sede");
        tablaModelo.addColumn("Fecha");
        JTable tablaClases = new JTable(tablaModelo);
        JScrollPane tablaScroll = new JScrollPane(tablaClases, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
        panelTabla.add(new JLabel("Mis Clases"));
        panelTabla.add(tablaScroll);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.add(BotonConfirmar);

        cancelarReserva.getContentPane().add(panelTabla);
        cancelarReserva.getContentPane().add(panelBotones);
        configurarTablaMisClases();

        BotonConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = getFilaSeleccionada();
                if (filaSeleccionada >= 0) {
                    int modeloFilaSeleccionada = tablaClases.convertRowIndexToModel(filaSeleccionada);
                    String nombre = (String) tablaModelo.getValueAt(modeloFilaSeleccionada, 0);
                    LocalDateTime fecha = (LocalDateTime) tablaModelo.getValueAt(modeloFilaSeleccionada, 3);

                    try {
                        controlador.cancelarReserva(nombre, fecha);
                        mostrarMensaje("Reserva cancelada correctamente.");
                    } catch (NoExisteUsuarioException | NoMismoNivelException | NoexisteClaseException | NoHayStockException e1) {
                        JOptionPane.showMessageDialog(null, "No se pudo cancelar la reserva.");
                        e1.printStackTrace();
                    }
                } else {
                    mostrarMensaje("Por favor, seleccione una clase de la tabla para cancelar la reserva.");
                }
            }
        });
    }
    
    public void configurarTablaMisClases() {
        tablaModelo.setRowCount(0); 
        
        ArrayList<Clase> clases = null;
		clases = cliente.getClases();

        for (Clase clase : clases) {
            Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
                    clase.getFecha()};
            tablaModelo.addRow(rowData);
        }
    }

    public void configurarTablaClasesDispo() {
        tablaModelo.setRowCount(0); 
        
        ArrayList<Clase> clases = null;
		try {
			clases = controlador.getClasesDisponibles();
		} catch (NoExisteSedeException e) {
			e.printStackTrace();
		}

        for (Clase clase : clases) {
            Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
                    clase.getFecha()};
            tablaModelo.addRow(rowData);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public JTable getTablaClases() {
        return this.tablaClases;
    }

    public int getFilaSeleccionada() {
        return tablaClases.getSelectedRow();
    }
}