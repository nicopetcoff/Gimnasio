package modelo.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.Gimnasio;
import modelo.utilidad.Nivel;

public class Administrativo extends Usuario{
	
	private int legajo;
	private static int legajoSig=0;
	private ArrayList<Sede> sedes;

	public Administrativo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.legajo=legajoSig;
		legajoSig++;
	}
	
	public void agregarSede(Sede sede) {
		sedes.add(sede);
	}

	public ArrayList<Sede> getSedes(){
		return sedes;
	}
	
	public void agendarClase(Profesor profesor, Sede sede, Actividad ejercicio, LocalDateTime fecha) {
		
		sede.agregarClase(new Clase(profesor, sede, ejercicio, fecha));
	}
	
	public void crearCliente(Gimnasio gimnasio,String nombre, String apellido, String dni, Nivel nivel) {
		
		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni));		
		
	}
	
	
	public void bajaCliente(Gimnasio gimnasio, Cliente cliente) {
		
		gimnasio.eliminarUsuario(cliente);		
		
	}
	
	public void agregarArticulo(Sede sede, Articulo articulo) {
		sede.agregarArticuloAStock(articulo);
	}
}
