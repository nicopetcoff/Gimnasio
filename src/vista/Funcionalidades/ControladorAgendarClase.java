package vista.Funcionalidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.sedes.Sede;

public class ControladorAgendarClase {
	private VistaAgendarClase vista;
	private Sede sede;
	
	public ControladorAgendarClase(Sede sede){
		this.sede=sede;
		this.vista= new VistaAgendarClase(this);
		
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	public Sede getSede() {
		return this.sede;
	}
	
	public boolean validarFecha(String fechaString) {
        

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Establecer el modo estricto para el análisis

        try {
            Date fecha = dateFormat.parse(fechaString);
            // La fecha es válida, puedes continuar con el procesamiento
            return true;
        } catch (ParseException ex) {
            
            return false;
        }
    }
}