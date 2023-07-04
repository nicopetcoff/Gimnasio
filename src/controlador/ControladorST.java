package controlador;

import java.time.LocalDate;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.productos.TipoAmortizacion;
import modelo.sedes.Actividad;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteActividadException;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteEmplazamientoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.usuarios.Usuario;
import modelo.utilidad.Nivel;

public class ControladorST {

	private GimnasioSingleton gimnasio;
	int prueba;

	public ControladorST() {

		gimnasio = GimnasioSingleton.getInstance();

	}

	public void crearCliente(int idSP, String nombre2, String apellido2, String dni2, String nivelSeleccionado,
			String usuario, String contrasenia) throws NoExisteUsuarioException {

		Nivel nivel = obtenerNivel(nivelSeleccionado);
		gimnasio.crearCliente(idSP, nombre2, apellido2, dni2, nivel, usuario, contrasenia);

	}

	private Nivel obtenerNivel(String nivelSeleccionado) {
		for (Nivel nivel : Nivel.values()) {
			if (nivel.name().equalsIgnoreCase(nivelSeleccionado)) {
				return nivel;
			}
		}
		return null;
	}

	public ArrayList<Usuario> getSoporteTecnicos() {

		ArrayList<Usuario> usuariosST = new ArrayList<>();

		for (int i = 0; i < gimnasio.getUsuarios().size(); i++) {

			if (gimnasio.getUsuarios().get(i).soySoporteTecnico()) {
				usuariosST.add(gimnasio.getUsuarios().get(i));
			}
		}
		return usuariosST;
	}

	public void crearSede(int id, String localidad, String nivel, double precio, int capacidad, String descripcion)
			throws NoExisteUsuarioException {

		Nivel nivel2 = obtenerNivel(nivel);

		gimnasio.crearSede(id, localidad, nivel2, precio, capacidad, descripcion);

	}

	public void crearSoporteTecnico(int id, String nombre, String apellido, String dni)
			throws NoExisteUsuarioException {

		gimnasio.crearSoporteTecnico(id, nombre, apellido, dni);

	}

	public ArrayList<String> getSedes() {

		ArrayList<String> sedes = new ArrayList<>();
		for (int i = 0; i < gimnasio.getSedes().size(); i++) {
			sedes.add(gimnasio.getSedes().get(i).getLocalidad());
		}
		return sedes;
	}

	public void crearAdministrativo(int idGestion, String nombre, String apellido, String dni, String sedeSeleccionada,
			String usuario, String contrasenia) throws NoExisteUsuarioException, NoExisteSedeException {

		gimnasio.crearAdministrativo(idGestion, nombre, apellido, dni, usuario, contrasenia);

		gimnasio.asignarSedeAlAdministrativo(idGestion, sedeSeleccionada);

	}

	public void crearProfesor(int idGestion, String nombre, String apellido, String dni, double sueldo,
			String sedeSeleccionada) throws NoExisteUsuarioException, NoExisteSedeException {

		gimnasio.crearProfesor(idGestion, nombre, apellido, dni, sueldo, sedeSeleccionada);

	}

	public void crearActividad(int idGestion, String nombreActividad) throws NoExisteUsuarioException {

		gimnasio.crearActividades(idGestion, nombreActividad);

	}

	public void crearEmplazamiento(int idGestion, String nombreEmplazamiento, double factorCalculo)
			throws NoExisteUsuarioException {

		gimnasio.crearEmplazamiento(idGestion, nombreEmplazamiento, factorCalculo);

	}

	public ArrayList<String> getEmplazamientos() {

		ArrayList<String> emplazamientos = new ArrayList<>();

		for (int i = 0; i < gimnasio.getEmplazamientosDisponibles().size(); i++) {
			emplazamientos.add(gimnasio.getEmplazamientosDisponibles().get(i).getTipoEmplazamiento());
		}

		return emplazamientos;
	}

	public void asignarEmplazamientoASede(int idGestion, String sedeSeleccionada, String emplazamientoSeleccionado)
			throws Exception {

		gimnasio.AsignarEmplazamientoSede(idGestion, sedeSeleccionada, emplazamientoSeleccionado);
	}

	public void crearArticuloEnStock(int idGestion, String marca, String articulo, LocalDate fechaFabricacion,
			String tipoAmortizacion, int durabilidad, double precio, String atributos) throws NoExisteUsuarioException {

		TipoAmortizacion amortizacion = obtenerTipoAmortizacion(tipoAmortizacion);

		gimnasio.agregarArticuloACatalogo(idGestion, marca, articulo, fechaFabricacion, amortizacion, durabilidad,
				atributos, precio);
	}

	private TipoAmortizacion obtenerTipoAmortizacion(String tipoAmortizacion) {

		for (TipoAmortizacion tipo : TipoAmortizacion.values()) {
			if (tipo.name().equalsIgnoreCase(tipoAmortizacion)) {
				return tipo;
			}
		}
		return null;

	}

	public ArrayList<Articulo> getArticulosDisponibles() {
		return gimnasio.getArticulosEnCatalogo();
	}

	public ArrayList<Actividad> getActividades() {
		return gimnasio.getActividades();
	}

	public void setArticuloRequeridoPorActividad(int idGestion, Articulo articuloSeleccionado, int cantidadItems,
			Actividad actividadSeleccionada)
			throws NoExisteUsuarioException, NoExisteActividadException, NoExisteArticuloEnCatalogoException {

		gimnasio.setearArticuloRequeridoPorActividad(idGestion, actividadSeleccionada.getTipoClase(),
				articuloSeleccionado.getMarca(), articuloSeleccionado.getArticulo(),
				articuloSeleccionado.getAtributos(), cantidadItems);

	}

	public void asignarEmplazamientoActividad(int iD, Actividad actividadSeleccionada, String emplazamientoSeleccionado) throws NoExisteUsuarioException, NoExisteActividadException, NoExisteEmplazamientoException {
		
		gimnasio.setearEmplazamientoRequeridoPorActividad(iD, actividadSeleccionada.getTipoClase(), emplazamientoSeleccionado);
	}

}
