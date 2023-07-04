package modelo.sedes;

public class Emplazamiento {
	private String tipoEmplazamiento; // Ejemplo Pileta: "Pileta"
	private double factorCalculo; // Ejemplo Pileta: 150 -- ver consigna

	public Emplazamiento(String tipoEmplazamiento, double factorCalculo) {
		this.tipoEmplazamiento = tipoEmplazamiento;
		this.factorCalculo = factorCalculo;
	}

	@Override
	public String toString() {
		return tipoEmplazamiento + "(factor: " + factorCalculo + ")";
	}

	public String getTipoEmplazamiento() {
		return tipoEmplazamiento;
	}

	public void setTipoEmplazamiento(String tipoEmplazamiento) {
		this.tipoEmplazamiento = tipoEmplazamiento;
	}

	public double getFactorCalculo() {
		return factorCalculo;
	}

	public void setFactorCalculo(double factorCalculo) {
		this.factorCalculo = factorCalculo;
	}

	public double calcularCosto(double alquilerSede) { // Calcula el costo alquilerSede/factorCalculo
		return alquilerSede / this.factorCalculo;
	}
}
