package controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.usuarios.Administrativo;

public class ControladorAdministrativo {

	private static Administrativo administrativoControlo;

	GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

	public boolean validarCredenciales(String usuario, String contrasenia) {

		int numero = gimnasio.buscarLoginAdminstrativo(usuario, contrasenia);
		guardarUsuario(usuario, contrasenia);
		return (numero != 0);
	}

	private void guardarUsuario(String usuario, String contrasenia) {
		administrativoControlo = gimnasio.dameAdministrativo(usuario, contrasenia);
	}

	public ArrayList<Sede> getSedes() {

		return gimnasio.getSedesAdministrativo(administrativoControlo);
	}

	public ArrayList<String> getActividades() {
		ArrayList<String> actividades = new ArrayList<>();

		for (int i = 0; i < gimnasio.getActividades().size(); i++) {
			actividades.add(gimnasio.getActividades().get(i).getTipoClase());
		}

		return actividades;
	}

	public ArrayList<String> getEmplazamientos() {

		ArrayList<String> emplazamientos = new ArrayList<>();

		for (int i = 0; i < gimnasio.getEmplazamientosSede().size(); i++) {
			emplazamientos.add(gimnasio.getEmplazamientosSede().get(i).getTipoEmplazamiento());
		}
		return emplazamientos;
	}

	public void agendarClase(String dniProfesor, String sede, String nombreClase, String actividad,
			String emplazamiento, LocalDateTime fechaHora, int duracion) throws Exception {
		gimnasio.agendarClase(administrativoControlo.getId(), dniProfesor, sede, nombreClase, actividad, emplazamiento,
				fechaHora, duracion);

	}

	public ArrayList<Clase> getClasesDisponibles(String sedeSeleccionada) throws NoExisteSedeException {
		//String sede = administrativoControlo.getSedes().get(0).getLocalidad();
	
		return gimnasio.verClasesAgendadas(sedeSeleccionada);
	}

	public ArrayList<String> getNombreSedes() {
		
		ArrayList<String> sedes = new ArrayList<>();
		
		for (int i = 0; i < gimnasio.getSedesAdministrativo(administrativoControlo).size(); i++) {
			sedes.add(gimnasio.getSedesAdministrativo(administrativoControlo).get(i).getLocalidad());
		}		
		
		return sedes;
	}

}
