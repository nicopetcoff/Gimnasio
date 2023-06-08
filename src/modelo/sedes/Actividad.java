package modelo.sedes;

import java.util.Map;

import modelo.productos.Articulo;

public class Actividad {
	private String tipoClase; // Crossfit, kangoo, etc
	private Map<Articulo, Integer> articulosRequeridos; // Articulo, Cantidad de articulos -- Requeridos por alumno
														// (Ejemplo Map<pesaDeMano, 3>)

	public Actividad(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	// Getter y Setter para cada atributo

	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public Map<Articulo, Integer> getArticulosRequeridos() {
		return articulosRequeridos;
	}

	public void agregarArticuloRequerido(Articulo articulo, int cantidad) {
		this.articulosRequeridos.put(articulo, cantidad); // Agrega un articulo solo con su cantidad
	}

}
