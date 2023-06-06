package modelo.usuarios;

import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.utilidad.Nivel;

public interface OperarClase {

	public void eliminarClase(Clase clase);

	void inscribirseClase(Clase clase, Nivel nivel) throws NoMismoNivelException;



}
