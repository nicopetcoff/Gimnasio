package modelo.supertlon;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.productos.Articulo;
import modelo.sedes.Sede;
import modelo.usuarios.Cliente;
import modelo.usuarios.ExisteLocalidadException;
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;
import modelo.utilidad.Nivel;

public class GimnasioSingleton {

	 private static GimnasioSingleton instance;

	private ArrayList<Usuario> usuarios;
	private ArrayList<Sede> sedes;
	private ArrayList<Articulo> catalogoArticulos;

	private GimnasioSingleton() {

		this.usuarios = new ArrayList<>();
		this.sedes = new ArrayList<>();
		this.catalogoArticulos = new ArrayList<>();

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

			if(articulo.getArticulo().equals(nombreArticulo))
				return true;

		}

		return false;

	}

	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}

	public ArrayList<Sede> getSedes(){
		return sedes;
	}

	public void eliminarUsuario(Cliente cliente) {
		usuarios.remove(cliente);

	}

	public void crearSede(int idUsuario, String localidad, Nivel nivel, double alquiler, int capacidad, String descripcion) throws NoExisteUsuarioExcepcion {
		
		SoporteTecnico sp = soyEseSoporteTecnico(idUsuario);
		
		if(sp!=null) {
			Sede s;
			try {
				s = sp.crearSede(instance, localidad, nivel, alquiler, capacidad, descripcion);
				sedes.add(s);
			} catch (ExisteLocalidadException e) {
				e.printStackTrace();
			}
			
		}else {
			throw new NoExisteUsuarioExcepcion("No existe el Soporte Tecnico Ingresado");
		}
	}

	private SoporteTecnico soyEseSoporteTecnico(int idUsuario) {
		
	for (Usuario usu: usuarios) {
		if (usu.soySoporteTecnico() && usu.getId() == idUsuario) {
			return (SoporteTecnico) usu;
			
		}		
	}
		return null;
	}



}
