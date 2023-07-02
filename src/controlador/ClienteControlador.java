package controlador;

import modelo.usuarios.Cliente;
//TODO: cosas de prueba, borrar
import modelo.utilidad.*;
import usuarios.vistas.ClienteVista;

import java.time.LocalDateTime;

import modelo.productos.NoHayStockException;
import modelo.sedes.*;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.*;
//TODO: cosas de prueba, borrar

public class ClienteControlador {
	private ClienteVista vista = new ClienteVista(null);
	private static Cliente cliente;
	private GimnasioSingleton gimnasio=GimnasioSingleton.getInstance();
	
	public boolean validarCredenciales(String usuario, String contrasenia) {

		int numero = gimnasio.buscarLoginCliente(usuario, contrasenia);
		guardarUsuario(usuario, contrasenia);
		return (numero != 0);
	}
	public Cliente buscarCliente(String usuario,String contrasenia) {
		Cliente cliente=gimnasio.getCliente(usuario,contrasenia);
		return cliente;
	}
	
	private void guardarUsuario(String usuario, String contrasenia) {
		cliente = gimnasio.dameCliente(usuario, contrasenia);
	}

	

	public Clase getClaseSeleccionada() throws Exception {
		if (this.vista.getFilaSeleccionada() >= 0) {
			Clase claseSeleccionada = this.cliente.getClases()
					.get(this.vista.getTablaClases().convertRowIndexToModel(this.vista.getFilaSeleccionada()));
			
			return claseSeleccionada;
		} else {
			throw new Exception();
		}
	}
}
