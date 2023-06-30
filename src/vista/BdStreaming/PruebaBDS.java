package vista.BdStreaming;

import java.time.LocalDateTime;

import javax.swing.SwingUtilities;

import modelo.baseDeDatos.BdStreaming;
import modelo.baseDeDatos.LimiteClasesException;
import usuarios.vistas.InterfazSeleccionRol;

import modelo.supertlon.*;
import modelo.usuarios.*;
import modelo.productos.*;
import modelo.sedes.*;
import modelo.utilidad.*;
import controlador.ControladorBdStreaming;

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

		Clase clase1 = new Clase(profesor1, sede1, "Clase de Crossfit", actividad, emplazamiento, fecha, duracionClase);
		Clase clase2 = new Clase(profesor2, sede2, "Clase de Zumba1", actividad2, emplazamiento, fecha, duracionClase);
		Clase clase3 = new Clase(profesor2, sede2, "Clase de Zumba2", actividad2, emplazamiento, fecha, duracionClase);
		Clase clase4 = new Clase(profesor2, sede2, "Clase de Zumba3", actividad2, emplazamiento, fecha, duracionClase);
		Clase clase5 = new Clase(profesor2, sede2, "Clase de Zumba4", actividad2, emplazamiento, fecha, duracionClase);
		Clase clase6 = new Clase(profesor2, sede2, "Clase de Zumba5", actividad2, emplazamiento, fecha, duracionClase);
		Clase clase7 = new Clase(profesor2, sede2, "Clase de Zumba6", actividad2, emplazamiento, fecha, duracionClase);

		instancia.agregarClase(clase1);
		instancia.agregarClase(clase2);
		instancia.agregarClase(clase3);
		instancia.agregarClase(clase4);
		instancia.agregarClase(clase5);
		instancia.agregarClase(clase6);
		instancia.agregarClase(clase7);

		instancia.abrirVistaBdStreamingAdmin();
		instancia.abrirVistaBdStreamingUser();
	}

}
