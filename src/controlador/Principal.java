package controlador;

import javax.swing.SwingUtilities;

import usuarios.vistas.InterfazSeleccionRol;

public class Principal {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			InterfazSeleccionRol interfaz = new InterfazSeleccionRol();
			interfaz.setVisible(true);
		});

	}

}
