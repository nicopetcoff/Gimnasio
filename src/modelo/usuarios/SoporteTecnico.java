package modelo.usuarios;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.utilidad.Nivel;

public class SoporteTecnico extends Usuario {



	public SoporteTecnico(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);

	}

	public Sede crearSede(GimnasioSingleton gimnasio, String localidad, Nivel nivel, double alquiler, int capacidad, String tipoSede) throws ExisteLocalidadException {

		for (Sede element : gimnasio.getSedes()) {
			if(element.getLocalidad() == localidad) {
				throw new ExisteLocalidadException();
			}

		}
		return new Sede(localidad, nivel, alquiler, capacidad, tipoSede);

		

	}

	public void cargarTipoClase(Clase clase, Actividad actividad) {

		clase.setActividad(actividad);

	}


	public void crearAdministrativo(GimnasioSingleton gimnasio, String nombre, String apellido, String dni) {

		gimnasio.agregarUsuario(new Administrativo(nombre, apellido, dni));

	}

	public void crearSoporteTecnico(GimnasioSingleton gimnasio, String nombre, String apellido, String dni) {
		gimnasio.agregarUsuario(new SoporteTecnico(nombre, apellido, dni));
	}

	public void crearCliente(GimnasioSingleton gimnasio, String nombre, String apellido, String dni, Nivel nivel1) {

		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni, nivel1));
	}

	public void crearProfesor(Sede sede, String nombre, String apellido, String dni, double sueldo) {

		sede.agregarProfesor(new Profesor(nombre, apellido, dni, sueldo));

	}

	@Override
	public boolean soySoporteTecnico() {
		return true;
	}


	@Override
	public boolean soyAdministrativo() {
		return false;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}
	@Override
	public int getId() {
		return this.id;
	}





}
