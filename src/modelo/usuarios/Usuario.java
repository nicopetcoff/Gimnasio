package modelo.usuarios;

public abstract class Usuario {
	
	protected int id;
	protected static int idSiguiente=1;
	protected String nombre;
	protected String apellido;
	protected String dni;
	
	
	public Usuario( String nombre, String apellido, String dni) {
		this.id = idSiguiente;
		idSiguiente++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	
	
	
	
	

}
