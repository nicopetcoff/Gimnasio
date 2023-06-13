package modelo.usuarios;

import java.time.LocalDate;

import modelo.productos.Articulo;
import modelo.productos.TipoAmortizacion;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Excepciones.ExisteLocalidadException;
import modelo.usuarios.Excepciones.NoPudoException;
import modelo.utilidad.Nivel;

public class SoporteTecnico extends Usuario {

	public SoporteTecnico(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);

	}

	public Sede crearSede(GimnasioSingleton gimnasio, String localidad, Nivel nivel, double alquiler, int capacidad,
			String tipoSede) throws ExisteLocalidadException {

		for (Sede element : gimnasio.getSedes()) {
			if (element.getLocalidad().equals(localidad)) {
				throw new ExisteLocalidadException();
			}

		}
		return (new Sede(localidad, nivel, alquiler, capacidad, tipoSede));

	}

	public void cargarTipoClase(Clase clase, Actividad actividad) {

		clase.setActividad(actividad);

	}

	public SoporteTecnico crearSoporteTecnico(String nombre, String apellido, String dni) {
		return (new SoporteTecnico(nombre, apellido, dni));
	}

	public Administrativo crearAdministrativo(String nombre, String apellido, String dni) {

		return (new Administrativo(nombre, apellido, dni));

	}

	public Cliente crearCliente(String nombre, String apellido, String dni, Nivel nivel1) {

		return (new Cliente(nombre, apellido, dni, nivel1));
	}

	public Profesor crearProfesor(String nombre, String apellido, String dni, double sueldo) {

		return (new Profesor(nombre, apellido, dni, sueldo));

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

	public Articulo crearArticulo(String marca, String articulo, LocalDate fechaFabricacion,
			TipoAmortizacion tipoAmortizacion, int durabilidad, String atributos, double precio) {

		return (new Articulo(marca, articulo, fechaFabricacion, tipoAmortizacion, durabilidad, atributos, precio));

	}

	// asigna sede a un administrador
	public void asignarSede(Administrativo admin, Sede sede) {
		admin.agregarSede(sede);
	}

	public void AsignarLaSedeAlAdministrativo(Usuario usuario, Sede sede) throws NoPudoException {
		if (usuario.soyAdministrativo()) {
			Administrativo a = (Administrativo) usuario;
			a.agregarSede(sede);
		} else {
			throw new NoPudoException("No se pudo asignarle la sede");
		}

	}

	public Actividad crearActividad(String actividad) {
		return (new Actividad(actividad));
	}

	public Emplazamiento crearEmplazamiento(String tipoEmplazamiento, double factorCalculo) {
		return (new Emplazamiento(tipoEmplazamiento, factorCalculo));
	}

}
