package modelo.usuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.utilidad.EstadoClase;
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
	
	public void agregarSede( Sede sede) {
		
		sedes.add(sede);
	}

	public ArrayList<Sede> getSedes(){
		return sedes;
	}
	
	public void agendarClase(Profesor profesor, Sede sede, Emplazamiento emplazamiento, LocalDateTime fecha) {
		
		sede.agregarClase(new Clase(profesor, sede, emplazamiento, fecha));
	}
	
	public void cambiarEstadoClase(Clase clase, EstadoClase estadoClase) {
		
		clase.cambiarEstado(estadoClase);
		
	}
	
	public void crearCliente(GimnasioSingleton gimnasio,String nombre, String apellido, String dni, Nivel nivel) {
		
		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni));		
		
	}
	
	
	public void bajaCliente(GimnasioSingleton gimnasio, Cliente cliente) {
		
		gimnasio.eliminarUsuario(cliente);		
		
	}
	
	public void agregarArticulo(Sede sede, Articulo articulo) {
		sede.agregarArticuloAStock(articulo);
	}
	
	public double consultarDesgaste(Articulo articulo) {
		
		return articulo.calcularDesgaste();
	}
}
