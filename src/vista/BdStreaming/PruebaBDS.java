package vista.BdStreaming;

import java.time.LocalDateTime;

import controlador.ControladorBdStreaming;
import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.NoExisteEmplazamientoRequerido;
import modelo.sedes.Sede;
import modelo.usuarios.Profesor;
import modelo.utilidad.Nivel;

public class PruebaBDS {

	public static void main(String[] args) {
		ControladorBdStreaming instancia = new ControladorBdStreaming();
		Profesor profesor1 = new Profesor("Juan", "Perez", "12345678", 2500.0);
		Profesor profesor2 = new Profesor("Maria", "Gomez", "98765432", 3000.0);

		Sede sede1 = new Sede("Sede A", Nivel.BLACK, 1500.0, 50, "Sede principal");
		Sede sede2 = new Sede("Sede B", Nivel.ORO, 2000.0, 40, "Sede secundaria");

		Actividad actividad = new Actividad("Crossfit");
		Articulo pesaDeMano = new Articulo("Pesa de mano", null, null, null, 0, null, 0);
		actividad.agregarArticuloPorAlumno(pesaDeMano, 3);

		Actividad actividad2 = new Actividad("Zumba");
		Articulo pesaDeMano2 = new Articulo("Pesa de mano2", null, null, null, 0, null, 0);
		actividad.agregarArticuloPorAlumno(pesaDeMano2, 3);

		Emplazamiento emplazamiento = new Emplazamiento("Pileta", 150);

		LocalDateTime fecha = LocalDateTime.now();
		int duracionClase = 60;

		Clase clase1;
		try {
			clase1 = new Clase(profesor1, sede1, "Clase de Crossfit", actividad, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase2;
		try {
			clase2 = new Clase(profesor2, sede2, "Clase de Zumba1", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase3;
		try {
			clase3 = new Clase(profesor2, sede2, "Clase de Zumba2", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase4;
		try {
			clase4 = new Clase(profesor2, sede2, "Clase de Zumba3", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase5;
		try {
			clase5 = new Clase(profesor2, sede2, "Clase de Zumba4", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase6;
		try {
			clase6 = new Clase(profesor2, sede2, "Clase de Zumba5", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}
		Clase clase7;
		try {
			clase7 = new Clase(profesor2, sede2, "Clase de Zumba6", actividad2, emplazamiento, fecha, duracionClase, true);
		} catch (NoExisteEmplazamientoRequerido e) {
			e.printStackTrace();
		}

		//instancia.agregarClase(clase1);
		//instancia.agregarClase(clase2);
		//instancia.agregarClase(clase3);
		//instancia.agregarClase(clase4);
		//instancia.agregarClase(clase5);
		////instancia.agregarClase(clase6);
		//instancia.agregarClase(clase7);

		instancia.abrirVistaBdStreamingAdmin();
		instancia.abrirVistaBdStreamingUser();
	}

}
