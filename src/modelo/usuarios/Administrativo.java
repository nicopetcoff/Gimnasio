package modelo.usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.productos.Articulo;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Excepciones.ClienteNoRegistradoException;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Administrativo extends Usuario {

	private ArrayList<Sede> sedes;

	public Administrativo(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.sedes = new ArrayList<>();

	}

	public void agregarSede(Sede sede) {

		sedes.add(sede);
	}

	public ArrayList<Sede> getSedes() {
		return sedes;
	}

	public void agendarClase(String nroDNIProfesor, String localidad,String nombreClase, String emplazamiento, LocalDateTime fecha) throws Exception {
		  
		
		  Sede sede = soyEsaSede(localidad);
		  Profesor profesor = sede.soyEseProfesor(nroDNIProfesor);
		  Emplazamiento empla = sede.soyEseEmplazamiento(emplazamiento);
		  
		  try {
				if(profesor.estoyDisponbile(fecha)) {
					Clase clase=new Clase(profesor, sede, nombreClase, empla, fecha);
					sede.agregarClase(clase);
					profesor.agregarClase(clase);
				}
			}catch(ProfesorNoDisponibleException e){
				e.printStackTrace();
			}
	}

	public void cambiarEstadoClase(Clase clase, EstadoClase estadoClase) {

		clase.cambiarEstado(estadoClase);

	}

	public void crearCliente(GimnasioSingleton gimnasio, String nombre, String apellido, String dni, Nivel nivel) {

		gimnasio.agregarUsuario(new Cliente(nombre, apellido, dni, nivel));

	}

	public void bajaCliente(GimnasioSingleton gimnasio, Cliente cliente) {

		gimnasio.eliminarUsuario(cliente);

	}

	//agrega articulo a sede, necesita mandar cantidad de articulos que quiere agregarle a la sede
	public void agregarArticulo(Sede sede, Articulo articulo,int cantidad) {
			sede.agregarArticuloAStock(articulo,cantidad);
		
		
	}

	public double consultarDesgaste(Articulo articulo) {

		return articulo.calcularDesgaste();
	}

	@Override
	public boolean soySoporteTecnico() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getId() {

		return this.id;
	}

	@Override
	public boolean soyAdministrativo() {
		return true;
	}

	@Override
	public boolean soyCliente() {
		return false;
	}
	
	@Override
	public String getDNI() {
		return this.dni;
	}
	

	private Sede soyEsaSede(String localidad)  {
		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				return sede;
			}
		}
	
		return null;
	}

	
	
	public void darDeAltaCliente(String clienteDNI,String localidad) {
		Sede sede=soyEsaSede(localidad);
		try {
			Cliente cliente=(Cliente) GimnasioSingleton.getInstance().buscarCliente(clienteDNI);
			sede.darAltaACliente(cliente);
		}catch (ClienteNoRegistradoException e) {
			e.printStackTrace();
		}
		
	}
	public void darDeBajaCliente(String clienteDNI,String localidad) {
		Sede sede=soyEsaSede(localidad);
		sede.darBajaACliente(clienteDNI);
	}

	
}
