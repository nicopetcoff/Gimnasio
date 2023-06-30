package modelo.baseDeDatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;

public class BdStreaming {
	private Map<Actividad, List<Clase>> clasesAlmacenadas; // Clases almacenadas por actividad
	private Map<Actividad, Integer> limitePorActividad; // Limite por actividad
	private static BdStreaming instancia; // Instancia del singleton

	// Constructor privado
	private BdStreaming() {
		clasesAlmacenadas = new HashMap<>();
		limitePorActividad = new HashMap<>();
	}

	// Método para obtener la instancia
	public static BdStreaming getInstance() {
		if (instancia == null) {
			instancia = new BdStreaming();
		}
		return instancia;
	}

	// Métodos
	public void agregarClase(Clase clase) throws LimiteClasesException {
		Actividad actividad = clase.getActividad();
		int limite = 0;
		if (limitePorActividad.containsKey(actividad)) {
			limite = limitePorActividad.get(actividad);
		} else {
			throw new LimiteClasesException("No se puede agregar una clase porque no tiene un límite fijado");
		}

		List<Clase> clases = clasesAlmacenadas.get(actividad);
		if (clases == null) {
			clases = new ArrayList<>();
			clasesAlmacenadas.put(actividad, clases);
		}
		clases.add(clase);

		if (clases.size() > limite) {
			ajustarLimiteClases(actividad);
		}
	}

	public Clase buscarClase(String idClase) {
		for (List<Clase> clases : clasesAlmacenadas.values()) {
			for (Clase clase : clases) {
				if (String.valueOf(clase.getId()).equals(idClase)) {
					return clase;
				}
			}
		}
		return null;
	}

	public List<Clase> buscarClasesPorActividad(Actividad actividad) { // devuelve array de clases guardadas
		for (Actividad a : clasesAlmacenadas.keySet()) {
			if (a.getTipoClase() == actividad.getTipoClase()) {
				return clasesAlmacenadas.get(a);
			}
		}
		return null;
	}

	public List<Clase> buscarClasesPorFecha(LocalDate fechaDesde, LocalDate fechaHasta) throws Exception {
		List<Clase> clasesEncontradas = new ArrayList<>();

		for (List<Clase> clases : clasesAlmacenadas.values()) {
			for (Clase clase : clases) {
				LocalDate fechaClase = clase.getFecha().toLocalDate();
				if (fechaClase.isEqual(fechaDesde) || fechaClase.isEqual(fechaHasta)
						|| (fechaClase.isAfter(fechaDesde) && fechaClase.isBefore(fechaHasta))) {
					clasesEncontradas.add(clase);
				}
			}
		}
		return clasesEncontradas;
	}

	public List<Clase> buscarClasePorId(String idClase) {
		List<Clase> clasesEncontradas = new ArrayList<>();
		int idc = Integer.parseInt(idClase);
		for (List<Clase> clases : clasesAlmacenadas.values()) {
			for (Clase clase : clases) {
				int id = clase.getId();
				if (id == idc) {
					clasesEncontradas.add(clase);
				}
			}
		}
		return clasesEncontradas;
	}

	public void definirLimitePorTipo(Actividad actividad, int limite) {
		limitePorActividad.put(actividad, limite);
	}

	public void cambiarLimite(Actividad actividad, int limite) throws LimiteClasesException {
		if (limitePorActividad.containsKey(actividad)) {
			limitePorActividad.replace(actividad, limite);
			ajustarLimiteClases(actividad);
		} else {
			definirLimitePorTipo(actividad, limite);
		}
	}

	public int buscarLimite(Actividad actividad) {
		if (limitePorActividad.containsKey(actividad)) {
			return limitePorActividad.get(actividad);
		} else {
			return -1;
		}
	}

	private void ajustarLimiteClases(Actividad actividad) throws LimiteClasesException {
		if (clasesAlmacenadas.containsKey(actividad)) {
			List<Clase> clases = clasesAlmacenadas.get(actividad);
			int limite = 0;
			if (limitePorActividad.containsKey(actividad)) {
				limite = limitePorActividad.get(actividad);
			} else {
				throw new LimiteClasesException("No se puede agregar una clase porque no tiene un límite fijado");
			}

			if (clases != null && clases.size() > limite) {
				int excedente = clases.size() - limite;
				for (int i = 0; i < excedente; i++) {
					clases.remove(0);
				}
			}
		}
	}

	public void eliminarClase(String idClase) {
		Clase claseAEliminar = buscarClase(idClase);
		if (claseAEliminar != null) {
			Actividad actividad = claseAEliminar.getActividad();
			List<Clase> clases = clasesAlmacenadas.get(actividad);
			if (clases != null) {
				clases.remove(claseAEliminar);
			}
		}
	}

	public Map<Actividad, List<Clase>> getClasesAlmacenadas() {
		return clasesAlmacenadas;
	}

	public Map<Actividad, Integer> getLimitePorActividad() {
		return limitePorActividad;
	}

}
