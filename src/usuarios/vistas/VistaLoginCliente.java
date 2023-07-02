package usuarios.vistas;

import modelo.usuarios.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLoginCliente extends JFrame {
	private JTextField usuarioCampo;
	private JPasswordField contraseniaCampo;
	private JButton btnIngresar;
	private ClienteControlador controlador;

	public VistaLoginCliente() {
		controlador = new ClienteControlador();

		setTitle("Vista Administrativo Nueva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3, 2, 10, 10));
		setLocationRelativeTo(null);

		JLabel usuarioLabel = new JLabel("Usuario:");
		usuarioCampo = new JTextField(10);
		JLabel contraseniaLabel = new JLabel("Contraseña:");
		contraseniaCampo = new JPasswordField(10);
		btnIngresar = new JButton("Ingresar");

		add(usuarioLabel);
		add(usuarioCampo);
		add(contraseniaLabel);
		add(contraseniaCampo);
		add(new JLabel());
		add(btnIngresar);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = usuarioCampo.getText();
				String contrasenia = new String(contraseniaCampo.getPassword());

				if (controlador.validarCredenciales(usuario, contrasenia)) {

					abrirVentanaCliente(usuario,contrasenia);
				} else {

					JOptionPane.showMessageDialog(VistaLoginCliente.this,
							"Usuario o contraseña incorrectos. Inténtelo de nuevo.", "Error de autenticación",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		pack();
		setVisible(true);
	}

	private void abrirVentanaCliente(String usuario,String contrasenia) {

		ClienteVista vista = new ClienteVista(controlador.buscarCliente(usuario, contrasenia));
	}
}