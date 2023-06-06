package modelo.productos;

import java.time.LocalDate;

public enum TipoAmortizacion {

	DIAS_FABRICACION {

		@Override
		public double calcular(int cantClasesUsadas, LocalDate fechaFabricacion) {
			/*
			 * aca faltaria la logica del calculo
			 */
			return 0;
		}
	},
	CLASES_USADAS {

		@Override
		public double calcular(int cantClasesUsadas, LocalDate fechaFabricacion) {
			/*
			 * aca faltaria la logica del calculo
			 */
			return 0;
		}
	};

	public abstract double calcular(int cantClasesUsadas, LocalDate fechaFabricacion);


}
