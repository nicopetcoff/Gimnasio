package vista.Funcionalidades;

import modelo.sedes.Sede;

public class ControladorCambiarEstado {
	private VistaCambiarEstado vista;
	private Sede sede;
	
	public ControladorCambiarEstado(Sede sede){
		this.sede=sede;
		this.vista= new VistaCambiarEstado(this);
		
		vista.configurarTabla(sede.getClases());
		vista.setSize(580,580);
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	public Sede getSede() {
		return this.sede;
	}
}
