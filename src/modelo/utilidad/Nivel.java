package modelo.utilidad;

public enum Nivel {

	BLACK(1), ORO(2), PLATINUM(3);
	
	private final int jerarquia;
	
	private Nivel(int valor) {
		this.jerarquia=valor;
	}
	
	public int getJerarquia() {
		return this.jerarquia;
	}
	
	public double costoMembresia() {
		switch (this) {
		case BLACK:
			return 1000;
		case ORO:
			return 1500;
		case PLATINUM:
			return 10000;
		default:
			return 0;
		}
	}
}
