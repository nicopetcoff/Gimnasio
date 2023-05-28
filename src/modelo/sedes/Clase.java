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
	private LocalDateTime fecha;
	private EstadoClase estado;
	private double precio;
	private ArrayList<Cliente> inscriptos;
	
	public Clase(Profesor profesor, Sede sede, Actividad ejercicio, LocalDateTime fecha) {
		this.profesor = profesor;
		this.sede = sede;
		this.ejercicio = ejercicio;
		this.fecha = fecha;
		this.estado= EstadoClase.AGENDADA;
	}
	
	public void cambiarAConfirmado() {
		
		this.estado= EstadoClase.CONFIRMADA;
	}
	
	public void cambiarAFinalizado() {
		
		this.estado= EstadoClase.FINALIZADA;
	}
	
	public void agregarCliente(Cliente cliente) {
		
		inscriptos.add(cliente);
	}

	public void eliminarCliente(Cliente cliente) {
		inscriptos.remove(cliente);
	}
	
	

}
