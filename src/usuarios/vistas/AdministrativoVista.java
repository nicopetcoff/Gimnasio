package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import modelo.sedes.Sede;
import modelo.utilidad.Nivel;

public class AdministrativoVista extends JFrame {
	private JButton botonAgendarClase = new JButton("Agendar clase");
	private JButton cambiarEstadoClase = new JButton("Cambiar estado de clase");
	private JButton incorporarArticulo = new JButton("Incorporar artículo");
	private JButton altaCliente = new JButton("Dar de alta un cliente");
	private JButton bajaCliente = new JButton("Dar de baja un cliente");
	private JButton verDetallesSede = new JButton("Ver detalles de sede");
	private JButton verClasesBDStreaming = new JButton("Ver clases guardadas en Streaming");
	
	private DefaultTableModel tablaModelo = new DefaultTableModel();
	private JTable tablaSedes = new JTable(tablaModelo);
	
	public AdministrativoVista() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setBackground(Color.WHITE);
		this.setTitle("Panel de Control - Administrativo");
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(botonAgendarClase);
		panelBotones.add(cambiarEstadoClase);
		panelBotones.add(incorporarArticulo);
		panelBotones.add(altaCliente);
		panelBotones.add(bajaCliente);
		panelBotones.add(verDetallesSede);
		panelBotones.add(verClasesBDStreaming);
		
		tablaModelo.addColumn("Localidad");
		tablaModelo.addColumn("Nivel");
		tablaModelo.addColumn("Alquiler");
		tablaModelo.addColumn("Capacidad");
		
		JScrollPane tablaScroll = new JScrollPane(tablaSedes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaSedes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		this.getContentPane().add(panelBotones);
		this.getContentPane().add(tablaScroll);
		
		pack();
		setVisible(true);
	}
	
	public void configurarTabla(ArrayList<Sede> sedes) {
		for(Sede sede: sedes) {
			Object[] rowData = { sede.getLocalidad(), sede.getnivel(), sede.getAlquiler(), sede.getCapacidadMax() };
			tablaModelo.addRow(rowData);
		}
	}
	
	public int getFilaSeleccionada() {
		return tablaSedes.getSelectedRow();
	}
}
