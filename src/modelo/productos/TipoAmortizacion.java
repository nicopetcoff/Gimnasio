package modelo.productos;

import java.time.Duration;
import java.time.LocalDate;

public enum TipoAmortizacion {
	DIAS_FABRICACION {

		@Override
		public double calcularDesgaste(int cantClasesUsadas, LocalDate fechaFabricacion, int diasUtiles) {
			LocalDate hoy = LocalDate.now();
			return (double) diasUtiles - Duration.between(fechaFabricacion, hoy).toDays();
			
		}
		
		@Override
		public double calcularAmortizacion(int cantClasesUsadas, LocalDate fechaFabricacion, int diasUtiles, double precio) {
			// TODO
			return 0;
		}
	},
	
	CLASES_USADAS {

		@Override
		public double calcularDesgaste(int cantClasesUsadas, LocalDate fechaFabricacion, int clasesUtiles) {
			return (double) cantClasesUsadas - clasesUtiles;
		}
		
		@Override
		public double calcularAmortizacion(int cantClasesUsadas, LocalDate fechaFabricacion, int clasesUtiles, double precio) {
			// TODO
			return 0;
		}
	};
	
	// Si el resultado es negativo, el artículo no sirve más
	public abstract double calcularDesgaste(int cantClasesUsadas, LocalDate fechaFabricacion, int durabilidad);
	public abstract double calcularAmortizacion(int cantClasesUsadas, LocalDate fechaFabricacion, int durabilidad, double precio);
	
}
