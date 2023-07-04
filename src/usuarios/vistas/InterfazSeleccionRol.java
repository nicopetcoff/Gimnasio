package usuarios.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public void abrirVentanaAdministrativo() {

		vistaLoginAdministrativo vistaUsuarioLogin = new vistaLoginAdministrativo();

	}

	public void abrirVentanaSoporteTecnico() {
		vistaLoginSoporteTecnico vistaLoginST = new vistaLoginSoporteTecnico();
	}

	public void abrirVentanaCliente() {
		VistaLoginCliente log = new VistaLoginCliente();

	}
}