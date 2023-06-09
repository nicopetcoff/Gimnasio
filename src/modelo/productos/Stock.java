package modelo.productos;

import java.util.*;

public class Stock {

	private Map<Articulo,Integer> articulos;

	public Stock() {
		this.articulos = new HashMap<>();
	}

	public void agregarArticulo(Articulo articulo,int cantidad) {
		articulos.put(articulo, cantidad);
	}

}
