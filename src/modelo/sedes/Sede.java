package modelo.sedes;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.productos.Articulo;
import modelo.productos.Stock;
import modelo.usuarios.*;
import modelo.usuarios.Excepciones.ClienteNoRegistradoException;
import modelo.utilidad.Nivel;

public class Sede {

	private String localidad;
	private Nivel nivel;
	private int capacidadMax;
	private String descripcion;
	private double alquiler;
	private ArrayList<Clase> clases;
	private ArrayList<Profesor> profesores;
	private ArrayList<Emplazamiento> emplazamientos;
	private ArrayList<Cliente> clientes;
	private Stock stock;

	public Sede(String localidad, Nivel nivel, double alquiler, int capacidadMax, String descripcion) {
		this.localidad = localidad;
		this.nivel = nivel;
		this.alquiler = alquiler;
		this.capacidadMax = capacidadMax;
		this.descripcion = descripcion;
		this.clases = new ArrayList<>();
		this.profesores = new ArrayList<>();
		this.emplazamientos = new ArrayList<>();
		this.stock = new Stock();
		this.clientes=new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Sede [localidad=" + localidad + ", nivel=" + nivel + ", capacidadMax=" + capacidadMax + ", tipoSede="
				+ descripcion + "]";
	}

	//"crea" el objeto antes de agregarlo a stock ya que el q esta en catalogo solo es una muestra y su fechaFabricacion podria ser mucho tiempo atras 
	public void agregarArticuloAStock(Articulo articulo,int cantidad) {
		stock.agregarArticulo(new Articulo(articulo.getMarca(),articulo.getArticulo(),LocalDate.now(),articulo.getTipoAmortizacion(),articulo.getDurabilidad(),articulo.getAtributos(),articulo.getPrecio())
								,cantidad);
	}

	public void agregarClase(Clase clase) {

		clases.add(clase);
	}

	public void eliminarClase(Clase clase) {
		clases.remove(clase);
	}

	public ArrayList<Profesor> getProfesores() {
		return profesores;
	}

	public double getAlquiler() {
		return this.alquiler;
	}

	public String getLocalidad() {
		// TODO Auto-generated method stub
		return this.localidad;
	}

	public void agregarProfesor(Profesor profesor) {
		this.profesores.add(profesor);
	}

	public Nivel getnivel() {

		return this.nivel;
	}

	public ArrayList<Clase> getClases() {

		return this.clases;
	}

	public Profesor soyEseProfesor(String nroDNIProfesor) throws Exception {
		for (Profesor profesor : profesores) {
			if (profesor.getDNI().equals(nroDNIProfesor)) {
				
			}
		}
		lanzarExcepcion("No existe el profesor");
		return null;
	}

	private void lanzarExcepcion(String msg) throws Exception {
		throw new Exception(msg);
	}

	public Emplazamiento soyEseEmplazamiento(String emplazamiento) throws Exception {
		for(Emplazamiento empla : emplazamientos)
			if (empla.getTipoEmplazamiento().equals(emplazamiento)) {
				return empla;
			}
		lanzarExcepcion("No existe el emplazamiento");
		return null;
	}
	
	public void darAltaACliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public Cliente buscarCliente(String dni) throws ClienteNoRegistradoException {
		for (Cliente c: this.clientes) {
			if(c.soyCliente() && c.getDNI().equals(dni)) {
				return c;
			}
		}
		throw new ClienteNoRegistradoException("Cliente no registrado");
	}
	
	public void darBajaACliente(String clienteDNI ) {
		
		try {
			Cliente cliente = buscarCliente(clienteDNI);
			this.clientes.remove(cliente);
		} catch (ClienteNoRegistradoException e) {
			e.printStackTrace();
		}
		
	}

}
