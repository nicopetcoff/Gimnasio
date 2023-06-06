package modelo.supertlon;

import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Sede;
import modelo.usuarios.Cliente;
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;

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



}
