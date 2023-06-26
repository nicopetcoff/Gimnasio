package controlador;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.productos.TipoAmortizacion;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.usuarios.Usuario;
import modelo.utilidad.Nivel;

public class ControladorST {
	
	private GimnasioSingleton gimnasio;
	
	public ControladorST() {
		
		gimnasio = GimnasioSingleton.getInstance();
		
	}
	
	public void crearCliente(int idSP, String nombre2, String apellido2, String dni2, String nivelSeleccionado)  {
		
		Nivel nivel = obtenerNivel(nivelSeleccionado);
		try {
			gimnasio.crearCliente(idSP, nombre2, apellido2, dni2, nivel);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
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

	public void crearSede(int id, String localidad, String nivel, double precio, int capacidad, String descripcion) {
	
		Nivel nivel2 = obtenerNivel(nivel);
		
		try {
			gimnasio.crearSede(id, localidad, nivel2, precio, capacidad, descripcion);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	
	}

	public void crearSoporteTecnico(int id, String nombre, String apellido, String dni) {
		try {
			gimnasio.crearSoporteTecnico(id, nombre, apellido, dni);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getSedes() {
		
		ArrayList<String> sedes = new ArrayList<>();
		for (int i = 0; i < gimnasio.getSedes().size(); i++) {
			sedes.add(gimnasio.getSedes().get(i).getLocalidad());			
		}
		return sedes;
	}

	public void crearAdministrativo(int idGestion, String nombre, String apellido, String dni,
			String sedeSeleccionada) {
		
		try {
			gimnasio.crearAdministrativo(idGestion, nombre, apellido, dni);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
		
		try {
			gimnasio.asignarSedeAlAdministrativo(idGestion, sedeSeleccionada);
		} catch (NoExisteSedeException e) {
			e.printStackTrace();
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
		
	}

	public void crearProfesor(int idGestion, String nombre, String apellido, String dni, double sueldo, String sedeSeleccionada) {
	
		try {
			gimnasio.crearProfesor(idGestion, nombre, apellido, dni, sueldo, sedeSeleccionada);
		} catch (NoExisteUsuarioException | NoExisteSedeException e) {
			e.printStackTrace();
		}
	}

	public void crearActividad(int idGestion, String nombreActividad) {
		
		try {
			gimnasio.crearActividades(idGestion, nombreActividad);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	public void crearEmplazamiento(int idGestion, String nombreEmplazamiento, double factorCalculo) {
		
		try {
			gimnasio.crearEmplazamiento(idGestion, nombreEmplazamiento, factorCalculo);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getEmplazamientos() {
		
		ArrayList<String> emplazamientos = new ArrayList<>();
		
		for (int i = 0; i < gimnasio.getEmplazamientosDisponibles().size(); i++) {
			emplazamientos.add(gimnasio.getEmplazamientosDisponibles().get(i).getTipoEmplazamiento());
		}
		
		return emplazamientos;
	}

	public void asignarEmplazamientoASede(int idGestion, String sedeSeleccionada, String emplazamientoSeleccionado) {
		
		try {
			gimnasio.AsignarEmplazamientoSede(idGestion, sedeSeleccionada, emplazamientoSeleccionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void crearArticuloEnStock(int idGestion, String marca, String articulo, LocalDate fechaFabricacion,
			String tipoAmortizacion, int durabilidad, double precio, String atributos) {
		
		TipoAmortizacion amortizacion = obtenerTipoAmortizacion(tipoAmortizacion);
		
		try {
			gimnasio.agregarArticuloACatalogo(idGestion, marca, articulo, fechaFabricacion, amortizacion, durabilidad, atributos, precio);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	private TipoAmortizacion obtenerTipoAmortizacion(String tipoAmortizacion) {
       
		for (TipoAmortizacion tipo : TipoAmortizacion.values()) {
            if (tipo.name().equalsIgnoreCase(tipoAmortizacion)) {
                return tipo;
            }
        }
        return null; 
   
	}

	

	

	

}
