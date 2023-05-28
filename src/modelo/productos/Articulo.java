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
	
	public Articulo(String marca, String articulo, LocalDate fechaFabricacion, TipoAmortizacion tipoAmortizacion,
			int durabilidad, String atributos) {
		this.marca = marca;
		this.articulo = articulo;
		this.fechaFabricacion = fechaFabricacion;
		this.cantClasesUsadas=0;
		this.tipoAmortizacion = tipoAmortizacion;
		this.durabilidad = durabilidad;
		this.atributos = atributos;
	}
	
	public void sumarClase() {
		this.cantClasesUsadas++;
	}
	
	public String getArticulo() {
		return this.articulo;
	}
	
	

}
