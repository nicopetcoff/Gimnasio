package controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.productos.Articulo;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
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

		for (int i = 0; i < gimnasio.getEmplazamientosDisponibles().size(); i++) {
			emplazamientos.add(gimnasio.getEmplazamientosDisponibles().get(i).getTipoEmplazamiento());
		}
		return emplazamientos;
	}

	public void agendarClase(String dniProfesor, String sede, String nombreClase, String actividad,
			String emplazamiento, LocalDateTime fechaHora, int duracion) throws Exception {

		gimnasio.agendarClase(administrativoControlo.getId(), dniProfesor, sede, nombreClase, actividad, emplazamiento,
				fechaHora, duracion);

	}

	public ArrayList<Clase> getClasesDisponibles(String sedeSeleccionada) throws NoExisteSedeException {

		return gimnasio.verClasesAgendadas(sedeSeleccionada);
	}

	public ArrayList<String> getNombreSedes() {

		ArrayList<String> sedes = new ArrayList<>();

		for (int i = 0; i < gimnasio.getSedesAdministrativo(administrativoControlo).size(); i++) {
			sedes.add(gimnasio.getSedesAdministrativo(administrativoControlo).get(i).getLocalidad());
		}

		return sedes;
	}

	public ArrayList<Articulo> getArticulos() {

		return gimnasio.getArticulosEnCatalogo();
	}

	public void asignarStockSede(String marca, String nombreArticulo, String atributos, int cantidad, String sede)
			throws NoExisteArticuloEnCatalogoException, NoExisteUsuarioException, NoExisteSedeException {

		gimnasio.AsignarStockASede(administrativoControlo.getId(), marca, nombreArticulo, atributos, cantidad, sede);
	}

	public ArrayList<Articulo> getArticulosSede(String sede) throws NoExisteUsuarioException, NoExisteSedeException {

		return gimnasio.getArticulosSede(administrativoControlo.getId(), sede);
	}
	
	public Clase getClase(String nombre) {
		
		return null;
	}
	
	public Sede getSede(String localidad) {
		for(Sede s: this.administrativoControlo.getSedes()) {
			if(s.getLocalidad().equals(localidad)) {
				return s;
			}
		}
		return null;
	}
	

}
