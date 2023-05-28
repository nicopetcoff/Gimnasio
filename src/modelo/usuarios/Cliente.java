package modelo.usuarios;

import java.util.ArrayList;

import modelo.sedes.Clase;
import modelo.sedes.Sede;

public class Cliente extends Usuario implements OperarClase{
	
		public Cliente(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		
	}

	@Override
	public void inscribirseClase(Clase clase) {
		
		clase.agregarCliente(this);
		
	}

	@Override
	public void eliminarClase(Clase clase) {
		
		clase.eliminarCliente(this);
		
	}

	

}
