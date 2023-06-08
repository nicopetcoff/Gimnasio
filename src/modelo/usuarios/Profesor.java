package modelo.usuarios;

public class Profesor extends Usuario {

	private double sueldo;

	public Profesor(String nombre, String apellido, String dni, double sueldo) {
		super(nombre, apellido, dni);
		this.sueldo = sueldo;
	}

	@Override
	public boolean soySoporteTecnico() {
		return false;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public boolean soyAdministrativo() {
		return false;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	public double getSueldo() {
		return this.sueldo;
	}

}
