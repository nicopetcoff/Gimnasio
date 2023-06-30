package usuarios.vistas;

import javax.swing.*;

import controlador.ControladorLoginCliente;
import modelo.usuarios.Administrativo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazSeleccionRol extends JFrame {
	public InterfazSeleccionRol() {

		setTitle("Selección de Rol");
		setSize(300, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel label = new JLabel("Selecciona tu rol:");
		JButton clienteButton = new JButton("Cliente");
		JButton administrativoButton = new JButton("Administrativo");
		JButton soporteButton = new JButton("Soporte Técnico");

		setLayout(new BorderLayout());

		add(label, BorderLayout.NORTH);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3, 1));
		panelBotones.add(clienteButton);
		panelBotones.add(administrativoButton);
		panelBotones.add(soporteButton);
		add(panelBotones, BorderLayout.CENTER);

		clienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaCliente();
			}
		});

		administrativoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaAdministrativo();
			}
		});

		soporteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaSoporteTecnico();
			}
		});
	}

	public void abrirVentanaCliente() {
		ControladorLoginCliente controlador = new ControladorLoginCliente();
		// ClienteControlador controlador = new ClienteControlador();
	}

	public void abrirVentanaAdministrativo() {

		vistaLoginAdministrativo adm = new vistaLoginAdministrativo();
		// AdministrativoControlador controlador = new AdministrativoControlador();
	}

	public void abrirVentanaSoporteTecnico() {
		VistaSoporteTecnico frame = new VistaSoporteTecnico();
	}
}