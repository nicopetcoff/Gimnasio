package vista.Funcionalidades;

import modelo.sedes.Sede;

public class ControladorAgendarClase {
	private VistaAgendarClase vista;
	private Sede sede;
	
	public ControladorAgendarClase(Sede sede){
		this.sede=sede;
		this.vista= new VistaAgendarClase(this);
		
		vista.setVisible(true);
		vista.setSize(300,300);
		vista.setLocationRelativeTo(null);
	}
	
	public Sede getSede() {
		return this.sede;
	}
}
