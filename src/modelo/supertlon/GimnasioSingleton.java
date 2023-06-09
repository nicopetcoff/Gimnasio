package modelo.supertlon;

import java.lang.reflect.Array;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Sede;
import modelo.usuarios.Administrativo;
import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;
import modelo.usuarios.Excepciones.ExisteLocalidadException;
import modelo.usuarios.Excepciones.NoPudoException;
import modelo.utilidad.Nivel;

public class GimnasioSingleton {

	private static GimnasioSingleton instance;

	private ArrayList<Usuario> usuarios;
	private ArrayList<Sede> sedes;
	private ArrayList<Articulo> catalogoArticulos;
	private ArrayList<Actividad> actividades;

	private GimnasioSingleton() {

		this.usuarios = new ArrayList<>();
		this.sedes = new ArrayList<>();
		this.catalogoArticulos = new ArrayList<>();
		this.actividades = new ArrayList<>();

		usuarios.add(new SoporteTecnico("Juan", "Peres", "41577777"));
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

	public boolean existeEnCatalogo(String nombreArticulo) {

		for (Articulo articulo : catalogoArticulos) {

			if (articulo.getArticulo().equals(nombreArticulo))
				return true;

		}

		return false;

	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public ArrayList<Sede> getSedes() {
		return this.sedes;
	}
	
	public ArrayList<Actividad> getActividades(){
		return this.actividades;
	}

 	public void eliminarUsuario(Cliente cliente) {
		usuarios.remove(cliente);

	}

	public void crearSede(int idUsuario, String localidad, Nivel nivel, double alquiler, int capacidad,
			String descripcion) throws NoExisteUsuarioExcepcion {

		SoporteTecnico sp = soyEseSoporteTecnico(idUsuario);

		if (sp != null) {
			try {
				Sede s = sp.crearSede(this, localidad, nivel, alquiler, capacidad, descripcion);
				sedes.add(s);
			} catch (ExisteLocalidadException e) {
				e.printStackTrace();
			}

		} else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
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

	public void crearSoporteTecnico(int idSP, String nombre, String apellido, String dni)
			throws NoExisteUsuarioExcepcion {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			SoporteTecnico nsp = sp.crearSoporteTecnico(nombre, apellido, dni);
			this.usuarios.add(nsp);
		} else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
		}

	}

	public void crearAdministrativo(int idSP, String nombre1, String apellido1, String dni1)
			throws NoExisteUsuarioExcepcion {
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);

		if (sp != null) {
			Administrativo ad = sp.crearAdministrativo(nombre1, apellido1, dni1);
			usuarios.add(ad);
		} else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
		}
	}

	public void crearCliente(int idSP, String nombre2, String apellido2, String dni2, Nivel nivel)
			throws NoExisteUsuarioExcepcion {
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);
		if (sp != null) {
			Cliente cl = sp.crearCliente(nombre2, apellido2, dni2, nivel);
			usuarios.add(cl);
		} else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
		}

	}

	public void crearProfesor(int idSP, String nombre4, String apellido4, String dni4, double sueldo, String localidad)
			throws NoExisteUsuarioExcepcion, NoExisteSedeException {

		SoporteTecnico sp = soyEseSoporteTecnico(idSP);
		if (sp != null) {
			Profesor pr = sp.crearProfesor(nombre4, apellido4, dni4, sueldo);
			Sede s = soyEsaSede(localidad);
			if (s != null) {
				s.agregarProfesor(pr);
			} else {
				throw new NoExisteSedeException("No existe la Sede");
			}

		} else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
		}

	}

	private Sede soyEsaSede(String localidad) {

		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				return sede;
			}
		}
		return null;
	}

	public void asignarSedeAlAdministrativo(int idSP, String localidad)
			throws NoExisteSedeException, NoExisteUsuarioExcepcion {
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
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico");
		}

	}

	
	public void crearActividades(int idSP, String actividad) throws NoExisteUsuarioExcepcion {
		
		SoporteTecnico sp = soyEseSoporteTecnico(idSP);
		
		if (sp!=null) {
			
			Actividad a = sp.crearActividad(actividad);
		}else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico");
		}
				
	}

}
