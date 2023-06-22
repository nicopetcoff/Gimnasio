package modelo.sedes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modelo.baseDeDatos.*;
import modelo.productos.Articulo;
import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Clase {

	private int idClase;
	private static int idClaseSig = 1;
	private String nombreClase;
	private Profesor profesor;
	private Sede sede;
	private int capacidadMax = 30;
	private int alumnosInscriptos;
	private Actividad actividad;
	private Emplazamiento emplazamiento;
	private LocalDateTime fecha;
	private EstadoClase estado;
	private double costo;
	private ArrayList<Cliente> inscriptos;
	private HashMap<Articulo, Integer> articulosTotales; // Ejemplo de entradas luego del metodo calcularTotalArticulos:
														// Pesa, 125 (son la cantidad de pesas totales necesarias para
														// la clase)
	private boolean onLine = false;
	
	public Clase(Profesor profesor, Sede sede, String nombreClase, Actividad actividad, Emplazamiento emplazamiento, LocalDateTime fecha) {
		this.idClase = idClaseSig;
		idClaseSig++;
		this.nombreClase = nombreClase;
		this.actividad = actividad;
		this.profesor = profesor;
		this.sede = sede;
		this.emplazamiento = emplazamiento;
		this.fecha = fecha;
		this.inscriptos = new ArrayList<>();
		this.estado = EstadoClase.AGENDADA;
	}

	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", profesor=" + profesor + ", sede=" + sede + ", capacidadMax="
				+ capacidadMax + ", emplazamiento=" + emplazamiento + ", fecha=" + fecha + ", estado=" + estado + "]";
	}
	public String getnombre() {
		return this.nombreClase;
	}

	public int getId() {
		return this.idClase;
	}

	public EstadoClase getEstado() {
		return this.estado;
	}

	public LocalDateTime getFecha() {
		return this.fecha;
	}

	public void agregarCliente(Cliente cliente, Nivel nivel) throws NoMismoNivelException {

		if (sede.getnivel().equals(nivel) && this.alumnosInscriptos < this.capacidadMax) {
			inscriptos.add(cliente);
			tomarArticulos();
			this.alumnosInscriptos++;
		} else {
			throw new NoMismoNivelException("No tiene el nivel de la Sede");
		}

	}

	private void tomarArticulos() {
		
		this.actividad.getArticulosPorAlumno();
		
		/*
		 * aca tendriamos que poner la logica para que vaya tomando los articulos
		 * 
		 */
			sede.tomarArticulosClase();
	}

	public void eliminarCliente(Cliente cliente) {
		inscriptos.remove(cliente);
	}

	public boolean esRentable() {
		return rentabilidadClase() > 0;
	}

	// TODO: mostrar desglose en la vista al chequear (pop up)
	public double rentabilidadClase() {
		return calcularIngreso() - calcularCosto();
	}

	public void cambiarEstado(EstadoClase estadoClase) throws LimiteClasesException {
		this.estado = estadoClase;
		if(estadoClase == EstadoClase.FINALIZADA && this.onLine) {
			ControladorBdStreaming controladorBdStreaming = new ControladorBdStreaming();
			controladorBdStreaming.agregarClase(this);
		}
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;

	}

	public Actividad getActividad() {
		return this.actividad;
	}

	private double calcularCosto() {

		/*
		 * aca da error, tenemos que ver porque
		 * 
		 */
		return this.profesor.getSueldo(); // + emplazamiento.calculate(sede.getAlquiler(), alumnosInscriptos * 2); // +
		// TODO: amortizacion de TODOS los artículos que usa la clase
	}

	private double calcularIngreso() {
		double membresias = 0;
		for (Cliente inscripto : inscriptos) {
			membresias += inscripto.getCostoMembresia();
		}
		return membresias / 30 * inscriptos.size();
	}

	public void calcularTotalArticulos(Map<Articulo, Integer> articulos) { // Recibe como parametro un map de articulos
																			// (puede ser sacado de Actividad)
		for (Map.Entry<Articulo, Integer> entry : articulos.entrySet()) {
			Articulo articulo = entry.getKey(); // Obtiene el articulo
			Integer cantidad = entry.getValue(); // Obtiene la cantidad
			int totalArticulos = cantidad * this.inscriptos.size(); // Multiplica articulos * cantidad de inscriptos
			this.articulosTotales.put(articulo, totalArticulos); // Agrega una entrada a articulosTotales con el tipo de
																	// articulo y el total.
		}
	}
	
	public boolean isOnLine() {
		return onLine;
	}

	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}
	
}
