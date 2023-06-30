package usuarios.vistas;

import modelo.usuarios.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.ControladorLoginCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLoginCliente extends JFrame {
	private JPanel panel;
	private JTextField dniCampo;
	private ControladorLoginCliente controlador;

	public VistaLoginCliente(ControladorLoginCliente contr) {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBackground(Color.lightGray);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);

		this.controlador = contr;

		panel.setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("SansSerif", Font.BOLD, 26));
		lblLogin.setBounds(175, 55, 100, 20);
		panel.add(lblLogin);

		JLabel lblUsuario = new JLabel("DNI:");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblUsuario.setBounds(120, 95, 60, 20);
		panel.add(lblUsuario);

		dniCampo = new JTextField();
		dniCampo.setBounds(190, 95, 100, 20);
		panel.add(dniCampo);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(100, 135, 100, 30);
		panel.add(btnSalir);

		JButton btnIngresar = new JButton("INGRESAR");
		btnIngresar.setBounds(220, 135, 100, 30);
		panel.add(btnIngresar);

		// Crear el botón "Aceptar"

		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Obtener los valores seleccionados/ingresados

				String dni = dniCampo.getText();
				Cliente cliente = controlador.buscarCliente(dni);

				if (!dni.matches("\\d{2}\\.\\d{3}\\.\\d{3}")) {
					JOptionPane.showMessageDialog(null, "Formato incorrecto. Ingrese su DNI en formato XX.XXX.XXX",
							"Error de Formato", JOptionPane.ERROR_MESSAGE);
					dniCampo.setText("");
				}

				// si el cliente existe cierra la ventana sino mensaje de error
				else if (cliente != null) {

					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario no registrado.", "Usuario no registrado",
							JOptionPane.ERROR_MESSAGE);
					dniCampo.setText("");
				}

			}
		});

		// Crear el botón "Cancelar"
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Limpiar los campos
				dniCampo.setText("");

			}
		});

	}

}
