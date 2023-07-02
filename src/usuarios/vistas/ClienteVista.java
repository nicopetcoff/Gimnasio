package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import controlador.*;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.usuarios.Cliente;
import modelo.utilidad.EstadoClase;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ClienteVista extends JFrame {
	private JButton botonReservarClase = new JButton("Reservar clase");
	private JButton botonCancelarReserva = new JButton("Cancelar reserva");
	private JButton botonBdStreaming = new JButton("BdStreaming");

	private DefaultTableModel tablaModelo = new DefaultTableModel();
	private JTable tablaClases = new JTable(tablaModelo);
	private Cliente cliente;
	private ClienteControlador controlador;

	public ClienteVista(Cliente cliente) {
		this.cliente=cliente;
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

		// JScrollPane tablaScroll = new JScrollPane(tablaClases,
		// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaClases.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
					controlador.getClaseSeleccionada().agregarCliente(cliente);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No hay ninguna clase seleccionada");
				}
			}
		});
		
		botonCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.getClaseSeleccionada().agregarCliente(cliente);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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

		for (Clase clase : clases) {
			Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
					clase.getFecha() };
		}

	}
	
	private void cancelarReserva() {
		JFrame cancelarReserva = new JFrame("Clases Agendadas");
		cancelarReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cancelarReserva.setLayout(new BorderLayout());
		cancelarReserva.setLocationRelativeTo(null);

		DefaultTableModel tablaModelo = new DefaultTableModel();
		tablaModelo.addColumn("Nombre");
		tablaModelo.addColumn("Actividad");
		tablaModelo.addColumn("Sede");
		tablaModelo.addColumn("Fecha");
		JTable tablaClases = new JTable(tablaModelo);

		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.add(new JScrollPane(tablaClases), BorderLayout.CENTER);


		JButton confirmarButton = new JButton("Confirmar");
		

		JPanel panelInferior = new JPanel();
		panelInferior.add(confirmarButton);

		cancelarReserva.add(panelCentral, BorderLayout.CENTER);
		cancelarReserva.add(panelInferior, BorderLayout.SOUTH);

		for (Clase clase : this.cliente.getClases()) {
			Object[] rowData = { clase.getnombre(), clase.getActividad().getTipoClase(), clase.getLugar(),
					clase.getFecha() };
		}
		
		

		confirmarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Clase clase=controlador.getClaseSeleccionada();
					clase.eliminarCliente(cliente);
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(ClienteVista.this, "Debe seleccionar una clase.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
	}

	public JTable getTablaClases() {
		return this.tablaClases;
	}

	public int getFilaSeleccionada() {
		return tablaClases.getSelectedRow();
	}
}
