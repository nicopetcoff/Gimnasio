package modelo.usuarios;

import modelo.productos.NoHayStockException;
import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.usuarios.Excepciones.SinStockDeArticulosException;

public interface OperarClase {

	public void eliminarClase(Clase clase);

	void inscribirseClase(Clase clase) throws NoMismoNivelException, NoHayStockException, SinStockDeArticulosException;

}
