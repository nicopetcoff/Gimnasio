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
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;
import modelo.usuarios.Excepciones.NoPudoException;
import modelo.utilidad.Nivel;

public class ControladorST {

	private GimnasioSingleton gimnasio;
	private static SoporteTecnico st;

	public ControladorST() {

		gimnasio = GimnasioSingleton.getInstance();
		

	}

	public void crearCliente(String nombre2, String apellido2, String dni2, String nivelSeleccionado,
			String usuario, String contrasenia) throws NoExisteUsuarioException {

		Nivel nivel = obtenerNivel(nivelSeleccionado);
		gimnasio.crearCliente(st.getId(), nombre2, apellido2, dni2, nivel, usuario, contrasenia);

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

	public void crearSede(String localidad, String nivel, double precio, int capacidad, String descripcion)
			throws NoExisteUsuarioException {

		Nivel nivel2 = obtenerNivel(nivel);

		gimnasio.crearSede(st.getId(), localidad, nivel2, precio, capacidad, descripcion);

	}

	public void crearSoporteTecnico(String nombre, String apellido, String dni, String usuario, String contrasenia)
			throws NoExisteUsuarioException {

		gimnasio.crearSoporteTecnico(st.getId(), nombre, apellido, dni, usuario, contrasenia);

	}

	public ArrayList<String> getSedes() {

		ArrayList<String> sedes = new ArrayList<>();
		for (int i = 0; i < gimnasio.getSedes().size(); i++) {
			sedes.add(gimnasio.getSedes().get(i).getLocalidad());
		}
		return sedes;
	}
	
	public ArrayList<String> getAdmins() {

		ArrayList<String> admins = new ArrayList<>();
		for (int i = 0; i < gimnasio.getAdmins().size(); i++) {
			admins.add(gimnasio.getAdmins().get(i));
		}
		return admins;
	}

	public void crearAdministrativo(String nombre, String apellido, String dni, String sedeSeleccionada,
			String usuario, String contrasenia,String localidad) throws NoExisteUsuarioException, NoExisteSedeException, NoPudoException {

		gimnasio.crearAdministrativo(st.getId(), nombre, apellido, dni, usuario, contrasenia,localidad);


	}

	public void crearProfesor(String nombre, String apellido, String dni, double sueldo,
			String sedeSeleccionada) throws NoExisteUsuarioException, NoExisteSedeException {

		gimnasio.crearProfesor(st.getId(), nombre, apellido, dni, sueldo, sedeSeleccionada);

	}

	public void crearActividad(String nombreActividad) throws NoExisteUsuarioException {

		gimnasio.crearActividades(st.getId(), nombreActividad);

	}

	public void crearEmplazamiento(String nombreEmplazamiento, double factorCalculo)
			throws NoExisteUsuarioException {

		gimnasio.crearEmplazamiento(st.getId(), nombreEmplazamiento, factorCalculo);

	}

	public ArrayList<String> getEmplazamientos() {

		ArrayList<String> emplazamientos = new ArrayList<>();

		for (int i = 0; i < gimnasio.getEmplazamientosDisponibles().size(); i++) {
			emplazamientos.add(gimnasio.getEmplazamientosDisponibles().get(i).getTipoEmplazamiento());
		}

		return emplazamientos;
	}

	public void asignarEmplazamientoASede(String sedeSeleccionada, String emplazamientoSeleccionado)
			throws Exception {

		gimnasio.AsignarEmplazamientoSede(st.getId(), sedeSeleccionada, emplazamientoSeleccionado);
	}

	public void crearArticuloEnStock(String marca, String articulo, LocalDate fechaFabricacion,
			String tipoAmortizacion, int durabilidad, double precio, String atributos) throws NoExisteUsuarioException {

		TipoAmortizacion amortizacion = obtenerTipoAmortizacion(tipoAmortizacion);

		gimnasio.agregarArticuloACatalogo(st.getId(), marca, articulo, fechaFabricacion, amortizacion, durabilidad,
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

	public void setArticuloRequeridoPorActividad(Articulo articuloSeleccionado, int cantidadItems,
			Actividad actividadSeleccionada)
			throws NoExisteUsuarioException, NoExisteActividadException, NoExisteArticuloEnCatalogoException {

		gimnasio.setearArticuloRequeridoPorActividad(st.getId(), actividadSeleccionada.getTipoClase(),
				articuloSeleccionado.getMarca(), articuloSeleccionado.getArticulo(),
				articuloSeleccionado.getAtributos(), cantidadItems);

	}

	public void asignarEmplazamientoActividad(Actividad actividadSeleccionada, String emplazamientoSeleccionado) throws NoExisteUsuarioException, NoExisteActividadException, NoExisteEmplazamientoException {
		
		gimnasio.setearEmplazamientoRequeridoPorActividad(st.getId(), actividadSeleccionada.getTipoClase(), emplazamientoSeleccionado);
	}
	
	public void asignarAdminASede(String localidad,String admin) throws NoExisteSedeException, NoExisteUsuarioException {
		gimnasio.asignarSedeAlAdministrativo(st.getId(), localidad, admin);
	}

	public boolean validarCredenciales(String usuario, String contrasenia) {

		int numero = gimnasio.buscarLoginSoporteTecnico(usuario, contrasenia);
		guardarUsuario(usuario, contrasenia);
		return (numero != 0);
	}

	private void guardarUsuario(String usuario, String contrasenia) {
		st = gimnasio.dameSoporteTecnico(usuario, contrasenia);
	}

}
