package modelo.productos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Stock {
	private ArrayList<Articulo> articulos;
	private Map<Articulo, Integer> inventario;

	public Stock() {
		this.articulos = new ArrayList<>();
		this.inventario = new HashMap<>();
	}

	public void agregarArticulo(Articulo articulo, int cantidad) {

		if (inventario.containsKey(articulo)) {
			cantidad += inventario.get(articulo);
		}

		for (int i = 0; i < cantidad; i++) {
			this.articulos.add(new Articulo(articulo.getMarca(), articulo.getArticulo(), articulo.getFechaFabricacion(),
					articulo.getTipoAmortizacion(), articulo.getDurabilidad(), articulo.getAtributos(),
					articulo.getPrecio()));
		}

		inventario.put(articulo, cantidad);
	}

	public int cantidadDeArticulo(Articulo articulo) {
		return inventario.get(articulo);
	}

	/*
	 * esto estaria para borrar
	 * 
	 * public void agregarArticulo(Articulo articulo, int cantidad) { if
	 * (articulos.containsKey(articulo)) { int cantidadExistente =
	 * articulos.get(articulo); cantidad += cantidadExistente; }
	 * articulos.put(articulo, cantidad); }
	 */

	public ArrayList<Articulo> tomarArticulos(Articulo articulo, int cantidad) throws NoHayStockException {

		ArrayList<Articulo> tomados = new ArrayList<>();

		if (validarSiTengoLacantidad(articulo, cantidad)) {

			for (Articulo articulo2 : articulos) {
				if (articulo2.getDisponibilidad() && articulo2.getMarca().equals(articulo.getMarca())
						&& articulo2.getArticulo().equals(articulo.getArticulo())
						&& articulo2.getAtributos().equals(articulo.getAtributos())) {

					articulo2.indisponibilizar();
					tomados.add(articulo2);
				}
			}

		} else {
			throw new NoHayStockException("No hay stock suficiente");
		}

		return null;
	}

	private boolean validarSiTengoLacantidad(Articulo articulo, int cantidad) {

		int sumador = 0;

		// recorro los articulos y veo si tengo la cantidad que necesito usando las
		// caracteristicas

		for (Articulo articulo2 : articulos) {
			if (articulo2.getMarca().equals(articulo.getMarca())
					&& articulo2.getArticulo().equals(articulo.getArticulo())
					&& articulo2.getAtributos().equals(articulo.getAtributos())) {

				sumador++;
			}

		}
		if (sumador == cantidad) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Articulo> listarArticulos() {
		return articulos;
	}

	public Map<Articulo, Integer> visualizarDesgasteArticulo() {

		Map<Articulo, Integer> articuloDesgaste = new HashMap<>();

		for (Articulo articulo : articulos) {

			int desgaste = (int) (articulo.getDurabilidad() / articulo.calcularDesgaste());
			articuloDesgaste.put(articulo, desgaste);
		}

		return articuloDesgaste;
	}

	public void bajaArticulo(Articulo art) {
		this.articulos.remove(art);
	}
}
