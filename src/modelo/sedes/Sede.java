package modelo.sedes;

import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.productos.Stock;
import modelo.usuarios.Profesor;
import modelo.utilidad.Nivel;

public class Sede {
	
	private String barrio;
	private Nivel nivel;
	private int capacidadMax;
	private String tipoSede;
	private ArrayList<Clase> clases;
	private ArrayList<Profesor> profesores;
	private Stock stock;
	
	public Sede(String barrio, Nivel nivel, int capacidadMax, String tipoSede) {
		this.barrio = barrio;
		this.nivel = nivel;
		this.capacidadMax = capacidadMax;
		this.tipoSede = tipoSede;
		this.clases= new ArrayList<>();
		this.profesores= new ArrayList<>();
		this.stock = new Stock();
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

}
