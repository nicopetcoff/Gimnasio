package modelo.usuarios;

import java.util.ArrayList;

import modelo.productos.NoHayStockException;
import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.utilidad.Nivel;

public class Cliente extends Usuario implements OperarClase {

	private Nivel nivel;
	private ArrayList<Clase> clases = new ArrayList<>();

	public Cliente(String nombre, String apellido, String dni, Nivel nivel) {
		super(nombre, apellido, dni);
		this.nivel = nivel;

	}

	@Override
	public void inscribirseClase(Clase clase, Nivel nivel) throws NoMismoNivelException, NoHayStockException {
		clase.agregarCliente(this, this.nivel);
		clases.add(clase);
	}

	@Override
	public void eliminarClase(Clase clase) {
		clase.eliminarCliente(this);
		clases.remove(clase);
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

	public Nivel getNivel() {
		return this.nivel;
	}
	
	public ArrayList<Clase> getClases() {
		return this.clases;
	}

	public double getCostoMembresia() {
		return this.nivel.costoMembresia();
	}

	@Override
	public boolean soyCliente() {
		return true;
	}

}
