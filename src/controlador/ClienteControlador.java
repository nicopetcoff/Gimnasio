package controlador;

import modelo.usuarios.Cliente;
//TODO: cosas de prueba, borrar
import modelo.utilidad.*;
import usuarios.vistas.ClienteVista;

import java.time.LocalDateTime;

import modelo.productos.NoHayStockException;
import modelo.sedes.*;
import modelo.usuarios.*;
//TODO: cosas de prueba, borrar

public class ClienteControlador {
	private ClienteVista vista = new ClienteVista(this);
	private Cliente usuario;

	public ClienteControlador(Cliente cliente) { // Cliente usuario) {
//		this.usuario = usuario;

		// TODO: cosas de prueba, borrar
		Cliente user = new Cliente("Pedro", "lopez", "12345678", Nivel.BLACK);
		Profesor profe = new Profesor("a", "a", "1", 1);
		Sede sede = new Sede("Almagro", Nivel.BLACK, 1, 10, "Sede Almagro");
		Actividad actividad = new Actividad("Aero");
		Emplazamiento emplazamiento = new Emplazamiento("Sala", 1);
		Clase clase1 = new Clase(profe, sede, "Aerobico", actividad, emplazamiento, LocalDateTime.now(), 0);

		this.usuario = user;

		try {
			user.inscribirseClase(clase1, usuario.getNivel());
		} catch (NoMismoNivelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoHayStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: cosas de prueba, borrar

		vista.configurarTabla(usuario.getClases());
		vista.setVisible(true);
		vista.setSize(600, 600);
		vista.setLocationRelativeTo(null);
	}

	public Clase getClaseSeleccionada() throws Exception {
		if (this.vista.getFilaSeleccionada() >= 0) {
			Clase claseSeleccionada = this.usuario.getClases()
					.get(this.vista.getTablaClases().convertRowIndexToModel(this.vista.getFilaSeleccionada()));
			System.out.println(claseSeleccionada);
			return claseSeleccionada;
		} else {
			throw new Exception();
		}
	}
}
