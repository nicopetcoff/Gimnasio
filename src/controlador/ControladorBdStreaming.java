package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import modelo.baseDeDatos.BdStreaming;
import modelo.baseDeDatos.LimiteClasesException;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import vista.BdStreaming.VistaBdStreamingActividades;
import vista.BdStreaming.VistaBdStreamingAdmin;
import vista.BdStreaming.VistaBdStreamingUser;

public class ControladorBdStreaming {
	private BdStreaming bdStreaming;

	public ControladorBdStreaming() {
		bdStreaming = BdStreaming.getInstance();
	}

	public void agregarClase(Clase clase) {
		try {
			int limiteActual = bdStreaming.buscarLimite(clase.getActividad());
			if (limiteActual == -1) {
				bdStreaming.cambiarLimite(clase.getActividad(), 5); // Se establece un limite por defecto
			}
			bdStreaming.agregarClase(clase);
			System.out.println("Clase agregada exitosamente a la Base de Datos de Streaming.");
		} catch (LimiteClasesException e) {
			System.out.println(e.getMessage());
		}
	}

	public Clase buscarClase(String idClase) {
		return bdStreaming.buscarClase(idClase);
	}

	public List<Clase> buscarClasesPorActividad(Actividad actividad) {
		return bdStreaming.buscarClasesPorActividad(actividad);
	}

	public void definirLimitePorTipo(Actividad actividad, int limite) {
		bdStreaming.definirLimitePorTipo(actividad, limite);
	}

	public void cambiarLimite(Actividad actividad, int limite) throws LimiteClasesException {
		bdStreaming.cambiarLimite(actividad, limite);
	}

	public int buscarLimite(Actividad actividad) throws LimiteClasesException {
		return bdStreaming.buscarLimite(actividad);
	}

	public void eliminarClase(String idClase) {
		bdStreaming.eliminarClase(idClase);
	}

	public Map<Actividad, List<Clase>> getClasesAlmacenadas() {
		return bdStreaming.getClasesAlmacenadas();
	}

	public Map<Actividad, Integer> getLimitePorActividad() {
		return bdStreaming.getLimitePorActividad();
	}

	public List<Clase> filtrarClasesPorId(String filtroId) {
		List<Clase> clases = bdStreaming.buscarClasePorId(filtroId);
		return clases;
	}

	public LocalDate fechaStrAFecha(String stringDate) {
		LocalDate date = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date = LocalDate.parse(stringDate, formatter);

		return date;
	}

	public List<Clase> filtrarClasesPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) throws Exception {
		List<Clase> clases = bdStreaming.buscarClasesPorFecha(fechaDesde, fechaHasta);
		return clases;
	}

	public void abrirVistaBdStreamingUser() {
		VistaBdStreamingUser interfaz = new VistaBdStreamingUser();
		interfaz.setVisible(true);
	}

	public void abrirVistaBdStreamingAdmin() {
		VistaBdStreamingAdmin interfaz = new VistaBdStreamingAdmin();
		interfaz.setVisible(true);
	}

	public void abrirVistaBdStreamingActividades() {
		VistaBdStreamingActividades interfaz = new VistaBdStreamingActividades();
		interfaz.setVisible(true);
	}

}
