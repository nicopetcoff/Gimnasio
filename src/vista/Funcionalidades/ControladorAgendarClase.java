package vista.Funcionalidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.sedes.Sede;

public class ControladorAgendarClase {
	private VistaAgendarClase vista;
	private Sede sede;

	public ControladorAgendarClase(Sede sede) {
		this.sede = sede;
		this.vista = new VistaAgendarClase(this);

		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}

	public Sede getSede() {
		return this.sede;
	}

	public boolean validarFecha(String fechaString) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false); // Establece un modo estricto para el análisis

		try {
			// valida la fecha
			Date fecha = dateFormat.parse(fechaString);
			return true;
		} catch (ParseException ex) {

			return false;
		}
	}
}