package modelo.usuarios;

public class Profesor extends Usuario{
	
	private double sueldo;

	public Profesor(String nombre, String apellido, String dni, double sueldo) {
		super(nombre, apellido, dni);
		this.sueldo= sueldo;
	}
	
	

}
