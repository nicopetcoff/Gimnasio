package modelo.usuarios;

import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.utilidad.Nivel;

public class Cliente extends Usuario implements OperarClase{
	
	private Nivel nivel;
	
		public Cliente(String nombre, String apellido, String dni, Nivel nivel) {
		super(nombre, apellido, dni);
		this.nivel = nivel;
		
	}

	@Override
	public void inscribirseClase(Clase clase, Nivel nivel) throws NoMismoNivelException {
		
		clase.agregarCliente(this, this.nivel);
		
	}

	@Override
	public void eliminarClase(Clase clase) {
		
		clase.eliminarCliente(this);
		
	}

	@Override
	public boolean soySoporteTecnico() {
		return false;
	}
	

	@Override
	public boolean soyAdministrativo() {
		return false;
	}
	
	@Override
	public int getId() {		
		return this.id;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	

}
