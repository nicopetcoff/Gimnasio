package modelo.sedes;

import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.productos.Stock;
import modelo.usuarios.Profesor;
import modelo.utilidad.Nivel;

public class Sede {

	private String localidad;
	private Nivel nivel;
	private int capacidadMax;
	private String tipoSede;
	private double alquiler;
	private ArrayList<Clase> clases;
	private ArrayList<Profesor> profesores;
	private Stock stock;

	public Sede(String barrio, Nivel nivel, int capacidadMax, String tipoSede) {
		this.localidad = barrio;
		this.nivel = nivel;
		this.capacidadMax = capacidadMax;
		this.tipoSede = tipoSede;
		this.clases= new ArrayList<>();
		this.profesores= new ArrayList<>();
		this.stock = new Stock();
	}



	@Override
	public String toString() {
		return "Sede [localidad=" + localidad + ", nivel=" + nivel + ", capacidadMax=" + capacidadMax + ", tipoSede="
				+ tipoSede + "]";
	}



	public void agregarArticuloAStock(Articulo articulo) {

		stock.agregarArticulo(articulo);
	}

	public void agregarClase(Clase clase) {

		clases.add(clase);
	}

	public void eliminarClase(Clase clase) {
		clases.remove(clase);
	}

	public ArrayList<Profesor> getProfesores(){
		return profesores;
	}

	public double getAlquiler() {
		return this.alquiler;
	}

	public String getLocalidad() {
		// TODO Auto-generated method stub
		return this.localidad;
	}



	public void agregarProfesor(Profesor profesor) {
		this.profesores.add(profesor);
	}



	public Nivel getnivel() {

		return this.nivel;
	}



	public ArrayList<Clase> getClases() {

		return this.clases;
	}

}
