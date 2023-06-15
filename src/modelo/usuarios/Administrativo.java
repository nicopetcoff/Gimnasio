package modelo.usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.usuarios.Excepciones.ProfesorNoDisponibleException;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Administrativo extends Usuario {

	private ArrayList<Sede> sedes;

	public Administrativo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.sedes = new ArrayList<>();

	}

	public void agregarSede(Sede sede) {

		sedes.add(sede);
	}

	public ArrayList<Sede> getSedes() {
		return sedes;
	}

	public Clase agendarClase(String nroDNIProfesor, String localidad, String nombreClase, String emplazamiento,
			LocalDateTime fecha) throws Exception {

		Sede sede = soyEsaSede(localidad);
		Profesor profesor = sede.buscarProfesor(nroDNIProfesor);
		Emplazamiento empla = sede.buscarEmplazamiento(emplazamiento);

		try {
			if (profesor.estoyDisponbile(fecha)) {
				Clase clase = new Clase(profesor, sede, nombreClase, empla, fecha);
				profesor.agregarClase(clase);
				return clase;
			}
		} catch (ProfesorNoDisponibleException e) {
			e.printStackTrace();
		}

		lanzarExcepcion("No se pudo agendar");
		return null;

	}

	private void lanzarExcepcion(String string) throws Exception {
		throw new Exception(string);
	}

	public void cambiarEstadoClase(Clase clase, EstadoClase estadoClase) {

		clase.cambiarEstado(estadoClase);

	}

	public void crearCliente(String nombre, String apellido, String dni, Nivel nivel) {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni, nivel));

	}

	public void bajaCliente(Cliente cliente) {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		gimnasio.eliminarUsuario(cliente);

	}

	// agrega articulo a sede, necesita mandar cantidad de articulos que quiere
	// agregarle a la sede
	public void agregarArticulo(Sede sede, Articulo articulo, int cantidad) {
		sede.agregarArticuloAStock(articulo, cantidad);

	}

	public double consultarDesgaste(Articulo articulo) {

		return articulo.calcularDesgaste();
	}

	@Override
	public boolean soySoporteTecnico() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getId() {

		return this.id;
	}

	@Override
	public boolean soyAdministrativo() {
		return true;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	private Sede soyEsaSede(String localidad) throws Exception {
		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				return sede;
			}
		}
		lanzarExcepcionSede("No existe la sede");
		return null;
	}

	private void lanzarExcepcionSede(String mensaje) throws Exception {
		throw new NoExisteSedeException(mensaje);
	}

	public void asignarEmplazamientoSede(Sede s, Emplazamiento em) throws Exception {

		Sede s1 = soyEsaSede(s.getLocalidad());

		s1.agregarEmplazamiento(em);
	}

}
