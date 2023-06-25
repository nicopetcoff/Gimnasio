package controlador;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.usuarios.Usuario;
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

	public ArrayList<Usuario> getSoporteTecnicos() {
		
		ArrayList<Usuario> usuariosST = new ArrayList<>();
		
		for (int i = 0; i < gimnasio.getUsuarios().size(); i++) {
			
			if (gimnasio.getUsuarios().get(i).soySoporteTecnico()) {
				usuariosST.add(gimnasio.getUsuarios().get(i));
			}
		}
		return usuariosST;
	}

	

}
