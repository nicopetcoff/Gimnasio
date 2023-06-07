package modelo.productos;

import java.time.LocalDate;

public class Articulo {

	private String marca;
	private String articulo;
	private LocalDate fechaFabricacion;
	private int cantClasesUsadas;
	private TipoAmortizacion tipoAmortizacion;
	private int durabilidad;
	private String atributos;
	private boolean activo;
	private double precio;

	public Articulo(String marca, String articulo, LocalDate fechaFabricacion, TipoAmortizacion tipoAmortizacion,
			int durabilidad, String atributos, double precio) {
		this.marca = marca;
		this.articulo = articulo;
		this.fechaFabricacion = fechaFabricacion;
		this.cantClasesUsadas=0;
		this.tipoAmortizacion = tipoAmortizacion;
		this.durabilidad = durabilidad;
		this.atributos = atributos;
		this.activo= true;
		this.precio = precio;
	}

	public void sumarClase() {
		this.cantClasesUsadas++;
	}

	public String getArticulo() {
		return this.articulo;
	}

	public double calcularDesgaste() {
		return tipoAmortizacion.calcular(this.cantClasesUsadas, this.fechaFabricacion, this.durabilidad);
	}
	
	public double calcularAmortizacion() {
		return tipoAmortizacion.calcularAmortizacion(this.cantClasesUsadas, this.fechaFabricacion, this.durabilidad, this.precio)
	}

	public void darDeBajaArticulo() {
		this.activo=false;
	}



}
