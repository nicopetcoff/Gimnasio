package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import modelo.sedes.Clase;
import modelo.usuarios.Cliente;

public class ClienteVista extends JFrame {
    private JButton botonReservarClase = new JButton("Reservar clase");
    private JButton botonCancelarReserva = new JButton("Cancelar reserva");
    private JButton botonBdStreaming = new JButton("BdStreaming");

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

        tablaModelo.addColumn("Precio");

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
        panelTabla.add(new JLabel("Mis clases"));
        panelTabla.add(tablaScroll);

        this.getContentPane().add(panelBotones);
        this.getContentPane().add(panelTabla);

        pack();

        botonReservarClase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.reservarClase();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "No hay ninguna clase seleccionada");
                }
            }
        });

        botonCancelarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.cancelarReserva();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "No hay ninguna clase seleccionada");
                }
            }
        });

        botonBdStreaming.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ControladorBdStreaming instancia = new ControladorBdStreaming();
                instancia.abrirVistaBdStreamingUser();
            }
        });

    }

    public void configurarTabla(ArrayList<Clase> clases) {
        tablaModelo.setRowCount(0); // Limpiar la tabla antes de configurar nuevos datos

        for (Clase clase : clases) {
            Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
                    clase.getFecha() };
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