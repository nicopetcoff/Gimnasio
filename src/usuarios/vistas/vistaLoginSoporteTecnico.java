package usuarios.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorST;

public class vistaLoginSoporteTecnico extends JFrame {
	private JTextField usuarioCampo;
	private JPasswordField contraseniaCampo;
	private JButton btnIngresar;
	private ControladorST controlador;

	public vistaLoginSoporteTecnico() {
		controlador = new ControladorST();

		setTitle("Vista Soporte Técnico Nueva");
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

					abrirVentanaSoporteTecnico();
				} else {

					JOptionPane.showMessageDialog(vistaLoginSoporteTecnico.this,
							"Usuario o contraseña incorrectos. Inténtelo de nuevo.", "Error de autenticación",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		pack();
		setVisible(true);
		this.setLocationRelativeTo(null);
	}

	private void abrirVentanaSoporteTecnico() {

		VistaSoporteTecnico vista = new VistaSoporteTecnico();
		this.dispose();
	}
}
