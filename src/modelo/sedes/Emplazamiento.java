package modelo.sedes;

public enum Emplazamiento {
	
	SALON{
        @Override
        public double calculate(double alquiler, double metroCuadrado) {
            return alquiler/300;
        }
    },
	PILETA {
		@Override
		public double calculate(double alquiler, double metroCuadrado) {
            return alquiler/150;
        }
	},
	AIRE_LIBRE {
		@Override
		public double calculate(double alquiler, double metroCuadrado) {
			
            return 500*metroCuadrado;
        }
	};
	
	public  abstract double calculate(double alquiler, double metroCuadrado);

}
