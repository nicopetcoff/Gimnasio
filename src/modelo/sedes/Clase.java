package modelo.sedes;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.utilidad.EstadoClase;

public class Clase {
	
	private Profesor profesor;
	private Sede sede;
	private int capacidadMax=30;
	private int alumnosInscriptos;
	private Actividad ejercicio;
	private Emplazamiento emplazamiento;
	private LocalDateTime fecha;
	private EstadoClase estado;
	private double precio;
	private ArrayList<Cliente> inscriptos;
	
	public Clase(Profesor profesor, Sede sede, Actividad ejercicio, Emplazamiento emplazamiento, LocalDateTime fecha) {
		this.profesor = profesor;
		this.sede = sede;
		this.ejercicio = ejercicio;
		this.emplazamiento = emplazamiento;
		this.fecha = fecha;
		this.estado= EstadoClase.AGENDADA;
	}
	
	public void agregarCliente(Cliente cliente) {
		
		inscriptos.add(cliente);
	}

	public void eliminarCliente(Cliente cliente) {
		inscriptos.remove(cliente);
	}
	
	public double rentabilidadClase() {
		/*
		 * Aca lo que hacemos es segun el emplazamiento elegido se los pasamos al enum para que calcule el valor
		 * 
		 * Faltaria agregar el sueldo del profesor y las demas cosas
		 */
		return emplazamiento.calculate(sede.getAlquiler(), alumnosInscriptos*2);
	}

	public void cambiarEstado(EstadoClase estadoClase) {
		
		this.estado = estadoClase;
	}
	
	

}
