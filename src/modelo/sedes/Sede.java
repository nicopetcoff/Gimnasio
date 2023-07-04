package modelo.sedes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modelo.productos.Articulo;
import modelo.productos.NoHayStockException;
import modelo.productos.Stock;
import modelo.usuarios.Profesor;
import modelo.usuarios.Excepciones.ProfesorNoRegistradoException;
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

	public boolean articulosDisponible(Articulo articulo, int cantidad, LocalDateTime horario) {
		Map<Articulo,Integer> mapInterno=reservas.get(horario);
		if(mapInterno!=null) {
			System.out.println(stock.cantidadDeArticulo(articulo));
			if (stock.cantidadDeArticulo(articulo) >= (mapInterno.get(articulo) + cantidad)) {
				return true;
			}else {
				return false;
			}
		}else {
			return true;
		}
		

	}

	public void reservarArticulos(Articulo articulo, int cantidad, LocalDateTime fecha) {
		Map<Articulo, Integer> mapInterno = reservas.get(fecha);
		
		if (mapInterno != null) {
			cantidad += mapInterno.get(articulo);
			
		}else {
			mapInterno=new HashMap<>();
		}
		mapInterno.put(articulo, cantidad);
		reservas.put(fecha, mapInterno);
	}

	public ArrayList<Emplazamiento> getEmplazamientos() {
		return this.emplazamientosSede;
	}

	@Override
	public String toString() {
		return localidad + ", membresía " + nivel + ", capacidad máxima: " + capacidadMax + ", descripción: "
				+ descripcion;
	}

	
	public void agregarArticuloAStock(Articulo articulo, int cantidad) {
		stock.agregarArticulo(articulo, cantidad);
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

	public Profesor buscarProfesor(String nroDNIProfesor) throws ProfesorNoRegistradoException {
		for (Profesor profesor : profesores) {
			if (profesor.getDNI().equals(nroDNIProfesor)) {
				return profesor;
			}
		}
		throw new ProfesorNoRegistradoException("Profesor indicado no existe.");
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
