package modelo.sedes;

import java.util.HashMap;

import modelo.productos.Articulo;

public class Actividad {

	private String tipoClase; // Crossfit, kangoo, etc
	private HashMap<Articulo, Integer> articulosRequeridosPorAlumno; // Articulo, Cantidad de articulos -- Requeridos
																		// por
																		// alumno
	// (Ejemplo Map<pesaDeMano, 3>)
	private Emplazamiento emplazamientoRequerido;

	public Actividad(String tipoClase) {
		this.tipoClase = tipoClase;
		this.articulosRequeridosPorAlumno = new HashMap<>();
	}

	// Getter y Setter para cada atributo

	public String getTipoClase() {
		return tipoClase;
	}

	@Override
	public String toString() {
		return "Actividad [tipoClase=" + tipoClase + "]";
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public HashMap<Articulo, Integer> getArticulosPorAlumno() {
		return articulosRequeridosPorAlumno;
	}

	public void agregarArticuloPorAlumno(Articulo articulo, int cantidad) {
		this.articulosRequeridosPorAlumno.put(articulo, cantidad); // Agrega un articulo solo con su cantidad
	}

	public Emplazamiento getEmplazamientoRequerido() {
		return emplazamientoRequerido;
	}

	public void setEmplazamientoRequerido(Emplazamiento emplazamiento) {
		this.emplazamientoRequerido = emplazamiento;
	}
}
