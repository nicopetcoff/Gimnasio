package controlador;

import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Cliente;
import usuarios.vistas.VistaLoginCliente;

public class ControladorLoginCliente {
	private VistaLoginCliente vista;

	public ControladorLoginCliente() {
		this.vista = new VistaLoginCliente(this);

		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}

	public Cliente buscarCliente(String dni) {

		return GimnasioSingleton.getInstance().getCliente(dni);
	}

}
