package modelo.supertlon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

import modelo.baseDeDatos.LimiteClasesException;
import modelo.productos.Articulo;
import modelo.productos.NoHayStockException;
import modelo.productos.TipoAmortizacion;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.NoEsRentableException;
import modelo.sedes.NoMismoNivelException;
import modelo.sedes.Sede;
import modelo.supertlon.Excepciones.NoExisteActividadException;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteEmplazamientoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.supertlon.Excepciones.NoexisteClaseException;
import modelo.usuarios.Administrativo;
import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;
import modelo.usuarios.Excepciones.ExisteLocalidadException;
import modelo.usuarios.Excepciones.NoPudoException;
import modelo.usuarios.Excepciones.ProfesorNoDisponibleException;
import modelo.usuarios.Excepciones.ProfesorNoRegistradoException;
import modelo.utilidad.Nivel;

public class GimnasioSingleton {

	private static GimnasioSingleton instance;

	private ArrayList<Usuario> usuarios;
	private ArrayList<Sede> sedes;
	private ArrayList<Articulo> catalogoArticulos;
	private ArrayList<Actividad> actividades;
	private ArrayList<Emplazamiento> emplazamientos;

	private GimnasioSingleton() {

		this.usuarios = new ArrayList<>();
		this.sedes = new ArrayList<>();
		this.catalogoArticulos = new ArrayList<>();
		this.actividades = new ArrayList<>();
		this.emplazamientos = new ArrayList<>();

		usuarios.add(new SoporteTecnico("Carlos", "Peres", "41111222"));
	}

	// Método estático para obtener la única instancia de la clase
	public static GimnasioSingleton getInstance() {
		if (instance == null) {
			instance = new GimnasioSingleton();
		}
		return instance;
	}

	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void agregarSede(Sede sede) {

		sedes.add(sede);
	}

	public void agregarArticuloACatalogo(Articulo articulo) {

		this.catalogoArticulos.add(articulo);

	}

	public Articulo existeEnCatalogo(String marca, String nombArticulo, String atributos) {

		for (Articulo articulo : catalogoArticulos) {
			// lo compara por tipo de articulo, marca y atributos ya que es lo q lo
			// identifica de forma univoca
			if (articulo.getArticulo().equals(nombArticulo) && articulo.getMarca().equals(marca)
					&& articulo.getAtributos().equals(atributos)) {
				return articulo;
			}
		}
		return null;
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public ArrayList<Sede> getSedes() {
		return this.sedes;
	}

	public ArrayList<Actividad> getActividades() {
		return this.actividades;
	}

	public ArrayList<Emplazamiento> getEmplazamientosDisponibles() {
		return this.emplazamientos;
	}

	public ArrayList<Articulo> getArticulosEnCatalogo() {
		return this.catalogoArticulos;
	}

	public void eliminarUsuario(Cliente cliente) {
		usuarios.remove(cliente);

	}

	public void crearSede(int idUsuario, String localidad, Nivel nivel, double alquiler, int capacidad,
			String descripcion) throws NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idUsuario);

		if (sp != null) {
			try {
				Sede s = sp.crearSede(localidad, nivel, alquiler, capacidad, descripcion);
				sedes.add(s);
			} catch (ExisteLocalidadException e) {
				e.printStackTrace();
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico Ingresado");
		}
	}

	private SoporteTecnico soyEseSoporteTecnico(int idUsuario) {

		for (Usuario usu : usuarios) {
			if (usu.soySoporteTecnico() && usu.getId() == idUsuario) {
				return (SoporteTecnico) usu;

			}
		}
		return null;
	}

	private Administrativo soyEseAdministrativo(int idUsuario) {

		for (Usuario usu : usuarios) {
			if (usu.soyAdministrativo() && usu.getId() == idUsuario) {
				return (Administrativo) usu;

			}
		}
		return null;

	}

	private Sede soyEsaSede(String localidad) throws NoExisteSedeException {

		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				return sede;
			}
		}
		throw new NoExisteSedeException("No hay sede en esa localidad.");
	}

	private Emplazamiento soyEseEmplazamiento(String emplazamiento)  {

		for (Emplazamiento emp : emplazamientos)
			if (emp.getTipoEmplazamiento().equals(emplazamiento)) {
				return emp;
			}

		return null;
	}

	private Actividad soyEsaActividad(String actividad) {
		for (Actividad act : actividades) {
			if (act.getTipoClase().equals(actividad)) {
				return act;
			}
		}
		return null;
	}

	private Clase soyEsaClase(String nombreClase, LocalDateTime horario) {

		for (Sede sede : sedes) {
			for (int i = 0; i < sede.getClases().size(); i++) {
				if (sede.getClases().get(i).getnombre().equals(nombreClase)
						&& sede.getClases().get(i).getFecha().equals(horario)) {
					return sede.getClases().get(i);
				}
			}
		}
		return null;
	}

	private Cliente soyEseCliente(int idCliente) {

		for (Usuario usuario : usuarios) {
			if (usuario.soyCliente() && usuario.getId() == idCliente) {
				return (Cliente) usuario;
			}
		}
		return null;
	}

	public void crearSoporteTecnico(int idSP, String nombre, String apellido, String dni)
			throws NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			SoporteTecnico nsp = sp.crearSoporteTecnico(nombre, apellido, dni);
			this.usuarios.add(nsp);
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico Ingresado");
		}

	}

	public void crearAdministrativo(int idSP, String nombre1, String apellido1, String dni1, String usuario,
			String contrasenia) throws NoExisteUsuarioException {
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			Administrativo ad = sp.crearAdministrativo(nombre1, apellido1, dni1, usuario, contrasenia);
			usuarios.add(ad);
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico Ingresado");
		}
	}

	public void crearCliente(int idSP, String nombre2, String apellido2, String dni2, Nivel nivel, String usuario,
			String contrasenia) throws NoExisteUsuarioException {
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);
		if (sp != null) {
			Cliente cl = sp.crearCliente(nombre2, apellido2, dni2, nivel, usuario, contrasenia);
			usuarios.add(cl);
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico Ingresado");
		}

	}

	public void crearProfesor(int idSP, String nombre4, String apellido4, String dni4, double sueldo, String localidad)
			throws NoExisteUsuarioException, NoExisteSedeException {

		Sede s = soyEsaSede(localidad);

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {

			if (s != null) {
				Profesor pr = sp.crearProfesor(nombre4, apellido4, dni4, sueldo, s);

			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico ingresado");
		}

	}

	public void asignarSedeAlAdministrativo(int idSP, String localidad)
			throws NoExisteSedeException, NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);
		if (sp != null) {

			int ultimo = this.usuarios.size() - 1;
			Sede sede = soyEsaSede(localidad);

			if (sede != null) {
				try {
					sp.AsignarLaSedeAlAdministrativo(this.usuarios.get(ultimo), sede);
				} catch (NoPudoException e) {
					e.printStackTrace();
				}
			} else {
				throw new NoExisteSedeException("No Existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico");
		}

	}

	public void crearActividades(int idSP, String actividad) throws NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {

			Actividad a = sp.crearActividad(actividad);
			actividades.add(a);
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico");
		}

	}

	public void agregarArticuloACatalogo(int idSP, String marca, String articulo, LocalDate fechaFabricacion,
			TipoAmortizacion tipoAmortizacion, int durabilidad, String atributos, double precio)
			throws NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			this.catalogoArticulos.add(sp.crearArticulo(marca, articulo, fechaFabricacion, tipoAmortizacion,
					durabilidad, atributos, precio));
		} else {
			throw new NoExisteUsuarioException("No Existe el Soporte Tecnico");
		}

	}

	public void agendarClase(int idA, String nroDNIProfesor, String localidad, String nombreClase, String actividad,
			String emplazamiento, LocalDateTime fecha, int duracionClase, boolean online) throws ProfesorNoRegistradoException,
							ProfesorNoDisponibleException,NoExisteEmplazamientoException,NoExisteSedeException,NoExisteActividadException,NoExisteUsuarioException {

		Administrativo a = soyEseAdministrativo(idA);

		Sede s = soyEsaSede(localidad);

		Actividad act = soyEsaActividad(actividad);

		Emplazamiento emp = soyEseEmplazamiento(emplazamiento);

		if (a != null) {

			if (s != null) {

				if (act != null) {

					if (emp != null) {
						a.agendarClase(nroDNIProfesor, s, nombreClase, act, emp, fecha, duracionClase, online);
					} else {
						throw new NoExisteEmplazamientoException("No existe el emplazamiento");
					}

				} else {
					throw new NoExisteActividadException("No existe la actividad");
				}

			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Administrativo");
		}

	}

	public void crearEmplazamiento(int idSP, String tipoEmplazamiento, double factorCalculo)
			throws NoExisteUsuarioException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			this.emplazamientos.add(sp.crearEmplazamiento(tipoEmplazamiento, factorCalculo));
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Técnico");
		}
	}

	public void AsignarEmplazamientoSede(int id, String localidadSede, String emplazamiento) throws Exception {
		SoporteTecnico st = soyEseSoporteTecnico(id);
		Sede s = soyEsaSede(localidadSede);

		Emplazamiento em = soyEseEmplazamiento(emplazamiento);

		if (st != null) {
			if (s != null) {
				st.asignarEmplazamientoSede(s, em);
			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico");
		}
	}

	public void agregarArticuloAStock(int idA, String localidad, String marca, String articulo, String atributos,
			int cantidad) throws NoExisteArticuloEnCatalogoException, NoExisteSedeException {

		Administrativo adm = soyEseAdministrativo(idA);

		if (adm != null) {
			Sede sede = soyEsaSede(localidad);
			Articulo art = existeEnCatalogo(articulo, marca, atributos);
			if (art != null) {
				adm.agregarArticulos(sede, art, cantidad);
			} else {
				throw new NoExisteArticuloEnCatalogoException("No existe ese articulo en catalogo.");
			}
		}
	}

	public void cambiarProfesorDeSede(int idSP, String localidad, String nroDNIProfesor) throws Exception {
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			Sede nuevaSede = soyEsaSede(localidad);
			System.out.println(nuevaSede.getProfesores());
			sp.cambiarProfesorDeSede(nroDNIProfesor, nuevaSede);
			System.out.println(nuevaSede.getProfesores());
		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Técnico");
		}
	}

	public ArrayList<Clase> verClasesAgendadas(String localidad) throws NoExisteSedeException {

		Sede s = soyEsaSede(localidad);

		if (s != null) {
			return s.getClases();
		} else {
			throw new NoExisteSedeException("No existe la Sede ");
		}

	}
	
	
	
	

	public void setearArticuloRequeridoPorActividad(int id, String actividad, String marca, String nombArticulo,
			String atributos, int cantidad)
			throws NoExisteUsuarioException, NoExisteActividadException, NoExisteArticuloEnCatalogoException {

		SoporteTecnico sp = soyEseSoporteTecnico(id);

		Actividad act = soyEsaActividad(actividad);

		Articulo art = existeEnCatalogo(marca, nombArticulo, atributos);

		if (sp != null) {
			if (act != null) {

				if (art != null) {
					sp.setearArticuloRequeridoPorActividad(act, art, cantidad);
				} else {
					throw new NoExisteArticuloEnCatalogoException("No Existe el articulo");
				}

			} else {
				throw new NoExisteActividadException("No existe la Actividad");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Soporte Tecnico");
		}

	}

	public void AsignarStockASede(int idAdministrativo, String marca, String nombArticulo, String atributos,
			int cantidad, String localidad)
			throws NoExisteArticuloEnCatalogoException, NoExisteUsuarioException, NoExisteSedeException {

		Administrativo a = soyEseAdministrativo(idAdministrativo);

		Sede s = soyEsaSede(localidad);

		Articulo art = existeEnCatalogo(marca, nombArticulo, atributos);

		if (a != null) {
			if (s != null) {
				if (art != null) {

					a.agregarArticulos(s, art, cantidad);

				} else {
					throw new NoExisteArticuloEnCatalogoException("No existe el articulo");
				}
			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}
		} else {
			throw new NoExisteUsuarioException("No existe el Administrador");
		}

	}

	public void inscribirseEnClase(int idCliente, String nombreClase, LocalDateTime horario)
			throws NoExisteUsuarioException, NoMismoNivelException, NoexisteClaseException, NoHayStockException {

		Cliente cliente = soyEseCliente(idCliente);

		Clase clase = soyEsaClase(nombreClase, horario);

		if (cliente != null) {
			if (clase != null) {
				cliente.inscribirseClase(clase);
			} else {
				throw new NoexisteClaseException("No existe la clase seleccionada");
			}
		} else {
			throw new NoExisteUsuarioException("No existe el Cliente");
		}
	}

	public ArrayList<Articulo> listarArticulosDeLaSede(int id, String localidad)
			throws NoExisteSedeException, NoExisteUsuarioException {
		Administrativo a = soyEseAdministrativo(id);

		Sede s = soyEsaSede(localidad);

		if (a != null) {
			if (s != null) {

				return a.listarArticulosSede(s);

			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el administrativo");
		}
	}

	public Map<Articulo, Integer> visualizarDesgasteArticulos(int id, String localidad)
			throws NoExisteSedeException, NoExisteUsuarioException {

		Administrativo a = soyEseAdministrativo(id);

		Sede s = soyEsaSede(localidad);

		if (a != null) {
			if (s != null) {

				return a.visualizarDesgasteArticulos(s);

			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el administrativo");
		}
	}

	public void darDeBajaArticuloDeSede(int id, String localidad, String marca, String nombArticulo, String atributos)
			throws NoExisteArticuloEnCatalogoException, NoExisteSedeException, NoExisteUsuarioException {

		Administrativo a = soyEseAdministrativo(id);

		Sede s = soyEsaSede(localidad);

		Articulo art = existeEnCatalogo(marca, nombArticulo, atributos);

		if (a != null) {
			if (s != null) {

				if (art != null) {
					a.darBajaArticulo(s, art);
				} else {
					throw new NoExisteArticuloEnCatalogoException("No existe el articulo");
				}

			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el administrativo");
		}
	}

	public void confirmarClase(int id, String nombreClase, LocalDateTime horario)
			throws LimiteClasesException, NoExisteUsuarioException, NoexisteClaseException, NoEsRentableException {

		Administrativo a = soyEseAdministrativo(id);

		Clase cla = soyEsaClase(nombreClase, horario);

		if (a != null) {
			if (cla != null) {
				a.confirmarClase(cla);

			} else {
				throw new NoexisteClaseException("No existe la Clase");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Administrativo");
		}

	}

	public double verRentabilidadClase(int id, String nombreClase, LocalDateTime horario)
			throws NoexisteClaseException, NoExisteUsuarioException {

		Administrativo a = soyEseAdministrativo(id);

		Clase cla = soyEsaClase(nombreClase, horario);

		if (a != null) {
			if (cla != null) {
				return a.verRentabilidadClase(cla);

			} else {
				throw new NoexisteClaseException("No existe la Clase");
			}

		} else {
			throw new NoExisteUsuarioException("No existe el Administrativo");
		}

	}

	public Cliente getCliente(String usuario, String contrasenia) {
		for (Usuario usu : usuarios) {
			if (usu.soyCliente()) {
				Cliente c = (Cliente) usu;
				if (c.getUsuario().equals(usuario) && c.getContrasenia().equals(contrasenia)) {
					return c;
				}
			}
		}
		return null;
	}

	public int buscarLoginAdminstrativo(String usuario, String contrasenia) {
		for (Usuario usu : usuarios) {
			if (usu.soyAdministrativo()) {
				Administrativo a = (Administrativo) usu;
				if (a.getUsuario().equals(usuario) && a.getContrasenia().equals(contrasenia)) {
					return a.getId();
				}
			}
		}
		return 0;
	}

	public int buscarLoginCliente(String usuario, String contrasenia) {
		for (Usuario usu : usuarios) {
			if (usu.soyCliente()) {
				Cliente c = (Cliente) usu;
				if (c.getUsuario().equals(usuario) && c.getContrasenia().equals(contrasenia)) {
					return c.getId();
				}
			}
		}
		return 0;
	}

	public Administrativo dameAdministrativo(String usuario, String contrasenia) {

		for (Usuario usu : usuarios) {
			if (usu.soyAdministrativo()) {
				Administrativo a = (Administrativo) usu;
				if (a.getUsuario().equals(usuario) && a.getContrasenia().equals(contrasenia)) {
					return a;
				}
			}
		}
		return null;
	}

	public Cliente dameCliente(String usuario, String contrasenia) {

		for (Usuario usu : usuarios) {
			if (usu.soyCliente()) {
				Cliente a = (Cliente) usu;
				if (a.getUsuario().equals(usuario) && a.getContrasenia().equals(contrasenia)) {
					return a;
				}
			}
		}
		return null;
	}

	public ArrayList<Sede> getSedesAdministrativo(Administrativo administrativoControlo) {
		return administrativoControlo.getSedes();
	}

	public ArrayList<Articulo> getArticulosSede(int id, String sede)
			throws NoExisteUsuarioException, NoExisteSedeException {

		Administrativo a = soyEseAdministrativo(id);
		Sede s = soyEsaSede(sede);

		if (a != null) {
			if (s != null) {
				return a.listarArticulosSede(s);
			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}
		} else {
			throw new NoExisteUsuarioException("No existe el Administrativo");
		}

	}

}
