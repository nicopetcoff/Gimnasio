package modelo.sedes;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Clase {
	
	private int idClase;
	private static int idClaseSig = 1;
	private Profesor profesor;
	private Sede sede;
	private int capacidadMax=30;
	private int alumnosInscriptos;
	private Actividad actividad;
	private Emplazamiento emplazamiento;
	private LocalDateTime fecha;
	private EstadoClase estado;
	private double precio;
	private ArrayList<Cliente> inscriptos;
	
	public Clase(Profesor profesor, Sede sede, Emplazamiento emplazamiento, LocalDateTime fecha) {
		this.idClase = idClaseSig;
		idClaseSig ++;
		this.profesor = profesor;
		this.sede = sede;
		this.emplazamiento = emplazamiento;
		this.fecha = fecha;
		this.estado= EstadoClase.AGENDADA;
	}
	
	
	
	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", profesor=" + profesor + ", sede=" + sede + ", capacidadMax="
				+ capacidadMax + ", emplazamiento=" + emplazamiento + ", fecha=" + fecha + ", estado=" + estado + "]";
	}
	
	public int getId() {
		return this.idClase;
	}
	
	public EstadoClase getEstado() {
		return this.estado;
	}



	public void agregarCliente(Cliente cliente, Nivel nivel) throws NoMismoNivelException {
		
		if (sede.getnivel().equals(nivel) && this.alumnosInscriptos < this.capacidadMax) {
			inscriptos.add(cliente);
			this.alumnosInscriptos ++;
		} else {
			throw new NoMismoNivelException("No tiene el nivel de la Sede");
		}
		
		
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

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
		
	}
	

}
