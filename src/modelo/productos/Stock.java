package modelo.productos;

import java.util.ArrayList;

public class Stock {
	
	private ArrayList<Articulo> articulos;
	
	

	public Stock() {
		this.articulos = new ArrayList<>();
	}


	public void agregarArticulo(Articulo articulo) {
		articulos.add(articulo);
		
	}

}
