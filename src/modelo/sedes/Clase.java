package modelo.sedes;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

public class Clase {

	private int idClase;
	private static int idClaseSig = 1;
	private Profesor profesor;
	private Sede sede;
	private int capacidadMax=30;
	private int alumnosInscriptos;
	private Actividad actividad;
	private Emplazamiento emplazamiento;
	private LocalDateTime fecha;
	private EstadoClase estado;
	private double precio;
	private ArrayList<Cliente> inscriptos;
	//private Map<Articulo, Integer> articulosTotales; //Ejemplo de entradas luego del metodo calcularTotalArticulos: Pesa, 125 (son la cantidad de pesas totales necesarias para la clase)

	public Clase(Profesor profesor, Sede sede, Emplazamiento emplazamiento, LocalDateTime fecha) {
		this.idClase = idClaseSig;
		idClaseSig ++;
		this.profesor = profesor;
		this.sede = sede;
		this.emplazamiento = emplazamiento;
		this.fecha = fecha;
		this.estado= EstadoClase.AGENDADA;
	}



	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", profesor=" + profesor + ", sede=" + sede + ", capacidadMax="
				+ capacidadMax + ", emplazamiento=" + emplazamiento + ", fecha=" + fecha + ", estado=" + estado + "]";
	}

	public int getId() {
		return this.idClase;
	}

	public EstadoClase getEstado() {
		return this.estado;
	}



	public void agregarCliente(Cliente cliente, Nivel nivel) throws NoMismoNivelException {

		if (sede.getnivel().equals(nivel) && this.alumnosInscriptos < this.capacidadMax) {
			inscriptos.add(cliente);
			this.alumnosInscriptos ++;
		} else {
			throw new NoMismoNivelException("No tiene el nivel de la Sede");
		}


	}

	public void eliminarCliente(Cliente cliente) {
		inscriptos.remove(cliente);
	}
	
	public boolean esRentable() {
		return rentabilidadClase() > 0;
	}

	// TODO: mostrar desglose en la vista al chequear (pop up)
	public double rentabilidadClase() {
		return calcularIngreso() - calcularCosto();
	}

	public void cambiarEstado(EstadoClase estadoClase) {

		this.estado = estadoClase;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;

	}

	private double calcularCosto() {
		return this.profesor.getSueldo() +
				emplazamiento.calculate(sede.getAlquiler(), alumnosInscriptos * 2); // +
				// TODO: amortizacion de TODOS los artículos que usa la clase
	}
	
	private double calcularIngreso() {
		double membresias = 0;
		for(Cliente inscripto: inscriptos) {
			membresias += inscripto.getCostoMembresia();
		}
		return membresias / 30 * inscriptos.size();
	}
	
	/*public void calcularTotalArticulos(Map<Articulo, Integer> articulos){ //Recibe como parametro un map de articulos (puede ser sacado de Actividad)
   		for (Map.Entry<Articulo, Integer> entry : articulos.entrySet()) {
		Articulo articulo = entry.getKey(); //Obtiene el articulo
		Integer cantidad = entry.getValue(); //Obtiene la cantidad
		int totalArticulos = cantidad * this.inscriptos.size(); //Multiplica articulos * cantidad de inscriptos
		this.articulosTotales.put(articulo, totalArticulos); //Agrega una entrada a articulosTotales con el tipo de articulo y el total.
    }
    */
    

}
