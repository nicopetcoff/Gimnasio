package modelo.utilidad;

public enum Nivel {

	BLACK, ORO, PLATINUM;

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
