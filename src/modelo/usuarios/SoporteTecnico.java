package modelo.usuarios;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.utilidad.Nivel;

public class SoporteTecnico extends Usuario {
	
	private int legajo;
	private static int legajoSig=0;

	public SoporteTecnico(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
		this.legajo=legajoSig;
		legajoSig++;
	}
	
	public void crearSede(GimnasioSingleton gimnasio, String localidad, Nivel nivel, int capidad, String tipoSede) throws ExisteLocalidadException {
		
		for (int i = 0; i < gimnasio.getSedes().size(); i++) {
			if(gimnasio.getSedes().get(i).getLocalidad() == localidad) {
				throw new ExisteLocalidadException(); 
			}
				
		}
		
		gimnasio.agregarSede(new Sede(localidad, nivel, capidad, tipoSede));
		
	}
	
	public void cargarTipoClase(Clase clase, Actividad actividad) {
		
		clase.setActividad(actividad);		
		
	}
	
	

}
