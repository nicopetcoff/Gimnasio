package modelo.productos;

import java.time.Duration;
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
		this.cantClasesUsadas = 0;
		this.tipoAmortizacion = tipoAmortizacion;
		this.durabilidad = durabilidad;
		this.atributos = atributos;
		this.activo = true;
		this.precio = precio;
	}
	
	

	@Override
	public String toString() {
		return "Articulo [marca=" + marca + ", articulo=" + articulo + ", atributos=" + atributos + "]";
	}



	public void sumarClase() {
		this.cantClasesUsadas++;
	}

	public String getArticulo() {
		return this.articulo;
	}

	public String getMarca() {
		return this.marca;
	}

	public String getAtributos() {
		return this.atributos;
	}

	public TipoAmortizacion getTipoAmortizacion() {
		return this.tipoAmortizacion;
	}

	public int getDurabilidad() {
		return this.durabilidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public double calcularDesgaste() {
		
		// Si el resultado es negativo, el artículo no sirve más
		
		if (this.tipoAmortizacion.equals(TipoAmortizacion.CLASES_USADAS)) {
			return (double) durabilidad - cantClasesUsadas;
		}
		
		else if (this.tipoAmortizacion.equals(TipoAmortizacion.DIAS_FABRICACION)) {
			LocalDate hoy = LocalDate.now();
			return (double) durabilidad - Duration.between(fechaFabricacion, hoy).toDays();
		}
		return 0;
	}

	public double calcularAmortizacion() {
		
		if (this.tipoAmortizacion.equals(TipoAmortizacion.CLASES_USADAS)) {
			
			// falta calculo
		}
		
		else if (this.tipoAmortizacion.equals(TipoAmortizacion.DIAS_FABRICACION)) {
			
			// falta calculo;
			
		}
		
		return 0;
	}

	public void darDeBajaArticulo() {
		this.activo = false;
	}

}
