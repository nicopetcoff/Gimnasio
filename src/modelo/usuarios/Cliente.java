package modelo.usuarios;

public class Cliente extends Usuario implements OperarClase{

	public Cliente(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}

	@Override
	public void inscribirseClase() {
		
	}

	@Override
	public void eliminarClase() {
		
	}

}
