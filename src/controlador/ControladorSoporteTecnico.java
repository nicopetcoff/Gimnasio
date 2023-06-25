package controlador;

import java.util.Scanner;

import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.utilidad.Nivel;

public class ControladorSoporteTecnico {
	
	private GimnasioSingleton gimnasio;
	
	public ControladorSoporteTecnico() {
		
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
	
	public Nivel obtenerNivel(String nivelSeleccionado) {
	    for (Nivel nivel : Nivel.values()) {
	        if (nivel.name().equalsIgnoreCase(nivelSeleccionado)) {
	            return nivel;
	        }
	    }
	    return null;
	}

	

}
