package modelo.sedes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modelo.productos.Articulo;
import modelo.productos.NoHayStockException;
import modelo.productos.Stock;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.usuarios.Profesor;
import modelo.utilidad.Nivel;

public class Sede {

	private String localidad;
	private Nivel nivel;
	private int capacidadMax;
	private String descripcion;
	private double alquiler;
	private ArrayList<Clase> clases;
	private ArrayList<Profesor> profesores;
	private ArrayList<Emplazamiento> emplazamientosSede;
	private Stock stock;
	private Map<LocalDateTime, Map<Articulo, Integer>> reservas;

	public Sede(String localidad, Nivel nivel, double alquiler, int capacidadMax, String descripcion) {
		this.localidad = localidad;
		this.nivel = nivel;
		this.alquiler = alquiler;
		this.capacidadMax = capacidadMax;
		this.descripcion = descripcion;
		this.clases = new ArrayList<>();
		this.profesores = new ArrayList<>();
		this.emplazamientosSede = new ArrayList<>();
		this.stock = new Stock();
		this.reservas = new HashMap<>();
	}

	public boolean articulosDisponible(Articulo articulo, int cantidad, LocalTime horario) {
		if (stock.cantidadDeArticulo(articulo) >= (reservas.get(horario).get(articulo) + cantidad)) {
			return true;
		}
		return false;

	}

	public void reservarArticulos(Articulo articulo, int cantidad, LocalDateTime fecha) {
		Map<Articulo, Integer> mapInterno = reservas.get(fecha);

		if (reservas.containsKey(fecha)) {
			cantidad += mapInterno.get(articulo);
		}
		mapInterno.put(articulo, cantidad);
		reservas.put(fecha, mapInterno);
	}

	public ArrayList<Emplazamiento> getEmplazamientos() {
		return this.emplazamientosSede;
	}

	@Override
	public String toString() {
		return "Sede [localidad=" + localidad + ", nivel=" + nivel + ", capacidadMax=" + capacidadMax + ", tipoSede="
				+ descripcion + "]";
	}

	// "crea" el objeto antes de agregarlo a stock ya que el q esta en catalogo solo
	// es una muestra y su fechaFabricacion podria ser mucho tiempo atras

	public void agregarArticuloAStock(Articulo articulo, int cantidad) {
		stock.agregarArticulo(new Articulo(articulo.getMarca(), articulo.getArticulo(), LocalDate.now(),
				articulo.getTipoAmortizacion(), articulo.getDurabilidad(), articulo.getAtributos(),
				articulo.getPrecio()), cantidad);
	}

	public void agregarClase(Clase clase) {

		clases.add(clase);
	}

	public void eliminarClase(Clase clase) {
		clases.remove(clase);
	}

	public ArrayList<Profesor> getProfesores() {
		return profesores;
	}

	public double getAlquiler() {
		return this.alquiler;
	}

	public String getLocalidad() {
		// TODO Auto-generated method stub
		return this.localidad;
	}

	public int getCapacidadMax() {
		return this.capacidadMax;
	}

	public void agregarProfesor(Profesor profesor) {
		if (!this.profesores.contains(profesor)) {
			this.profesores.add(profesor);
		}
	}

	public Nivel getNivel() {

		return this.nivel;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public ArrayList<Clase> getClases() {

		return this.clases;
	}

	public Profesor buscarProfesor(String nroDNIProfesor) {
		for (Profesor profesor : profesores) {
			if (profesor.getDNI().equals(nroDNIProfesor)) {
				return profesor;
			}
		}
		return null;
	}

	public void agregarEmplazamiento(Emplazamiento em) {

		Emplazamiento empla = new Emplazamiento(em.getTipoEmplazamiento(), em.getFactorCalculo());
		this.emplazamientosSede.add(empla);
	}

	public void removerProfesor(Profesor profesor) {
		if (this.profesores.contains(profesor)) {
			this.profesores.remove(profesor);
		}
	}

	public ArrayList<Articulo> tomarArticulosClase(Articulo articulo, int cantidad) throws NoHayStockException {

		return stock.tomarArticulos(articulo, cantidad);

	}

	public ArrayList<Articulo> listarArticulos() {
		return stock.listarArticulos();
	}

	public Map<Articulo, Integer> visualizarDesgasteArticulos() {
		return stock.visualizarDesgasteArticulo();
	}

	public void darBajaArticulo(Articulo art) {
		this.stock.bajaArticulo(art);
	}

}
