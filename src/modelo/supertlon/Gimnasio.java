package modelo.supertlon;

import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Sede;
import modelo.usuarios.Cliente;
import modelo.usuarios.Usuario;

/*
 * Habria que hacer que sea singleton
 * 
 */

public class Gimnasio {
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Sede> sedes;
	private ArrayList<Articulo> catalogoArticulos;
	
	public Gimnasio() {
		
		this.usuarios = new ArrayList<>();
		this.sedes = new ArrayList<>();
		this.catalogoArticulos = new ArrayList<>();
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
