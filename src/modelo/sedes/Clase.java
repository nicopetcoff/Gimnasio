package modelo.sedes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import controlador.ControladorBdStreaming;
import modelo.baseDeDatos.LimiteClasesException;
import modelo.productos.Articulo;
import modelo.productos.NoHayStockException;
import modelo.usuarios.Cliente;
import modelo.usuarios.Profesor;
import modelo.usuarios.Excepciones.SinStockDeArticulosException;
import modelo.utilidad.EstadoClase;

public class Clase {

	private int idClase;
	private static int idClaseSig = 1;
	private String nombreClase;
	private Profesor profesor;
	private Sede sede;
	private int capacidadMax = 30;
	private int alumnosInscriptos;
	private Actividad actividad;
	private Emplazamiento emplazamiento;
	private LocalDateTime fecha;
	private int duracionClase;
	private EstadoClase estado;
	private double costo;
	private ArrayList<Cliente> inscriptos;
	private ArrayList<Articulo> articulosDeLaClase;
	private boolean onLine = false;

	public Clase(Profesor profesor, Sede sede, String nombreClase, Actividad actividad, Emplazamiento emplazamiento,
			LocalDateTime fecha, int duracionClase, boolean online) throws NoExisteEmplazamientoRequerido {
		this.idClase = idClaseSig;
		idClaseSig++;
		this.nombreClase = nombreClase;
		this.actividad = actividad;
		this.profesor = profesor;
		this.sede = sede;
		this.emplazamiento = emplazamiento;
		this.fecha = fecha;
		this.inscriptos = new ArrayList<>();
		this.estado = EstadoClase.AGENDADA;
		this.articulosDeLaClase = new ArrayList<>();
		this.duracionClase = duracionClase;
		this.onLine = online;
		
		if (!actividad.getEmplazamientoRequerido().getTipoEmplazamiento().equals(emplazamiento.getTipoEmplazamiento())) {
			
			throw new NoExisteEmplazamientoRequerido("El emplazamiento requerido para la actividad no es compatible");
		}
	}

	

	@Override
	public String toString() {
		return this.nombreClase + " (" + this.getActividad().getTipoClase() + ")"
				+ ", profesor " + profesor.getNombre() + " " + profesor.getApellido()
				+ ", sede " + sede.getLocalidad()
				+ ", emplazamiento " + emplazamiento.getTipoEmplazamiento()
				+ ", " + fecha
				+ ", " + alumnosInscriptos + " participantes, "
				+ estado;
	}

	public String getnombre() {
		return this.nombreClase;
	}

	public int getId() {
		return this.idClase;
	}

	public EstadoClase getEstado() {
		return this.estado;
	}
	

	public LocalDateTime getFecha() {
		return this.fecha;
	}

	public void agregarCliente(Cliente cliente) throws NoMismoNivelException, NoHayStockException, SinStockDeArticulosException {

		if (sede.getNivel().getJerarquia()<= cliente.getNivel().getJerarquia() && this.alumnosInscriptos < this.capacidadMax) {

			HashMap<Articulo, Integer> artPorAlumno = actividad.getArticulosPorAlumno();

			for (Articulo a : artPorAlumno.keySet()) {
				if (sede.articulosDisponible(a, artPorAlumno.get(a), this.fecha)) {
					inscriptos.add(cliente);
					this.alumnosInscriptos++;
					sede.reservarArticulos(a, artPorAlumno.get(a), fecha);
				}else {
					throw new SinStockDeArticulosException("Sin stock de articulos.");
				}
			}
		} else {
			throw new NoMismoNivelException("No tiene el nivel de la Sede");
		}
	}

	// plan b esta este metodo, pero sino hay que borrar

	private void tomarArticulos() throws NoHayStockException {

		Articulo articulo = null;
		int cantidad = 0;

		for (Map.Entry<Articulo, Integer> entry : this.actividad.getArticulosPorAlumno().entrySet()) {
			articulo = entry.getKey();
			cantidad = entry.getValue();
		}
		this.articulosDeLaClase.addAll(sede.tomarArticulosClase(articulo, cantidad));
	}

	public void eliminarCliente(Cliente cliente) {
		HashMap<Articulo, Integer> artPorAlumno = actividad.getArticulosPorAlumno();

		for (Articulo a : artPorAlumno.keySet()) {
			inscriptos.remove(cliente);
			this.alumnosInscriptos--;
			sede.liberarArticulos(a, artPorAlumno.get(a), this.fecha);
		}
	}

	public boolean esRentable() {
		return rentabilidadClase() > 0;
	}

	public void mostrarCalculo() {
		String textoRentabilidad = esRentable() ? "La clase es rentable" : "La clase NO es rentable";
		String mensaje = String.format("Costo de la clase: %s\n"
				+ "Amortización de artículos necesarios: %s\n"
				+ "Total de inscriptos: %s\n"
				+ "Ingresos totales: %s\n"
				+ "Rentabilidad neta de la clase: %s\n\n"
				+ "%s",
				this.calcularCosto(), this.amortizarArticulos(), this.alumnosInscriptos, this.calcularIngreso(), this.rentabilidadClase(), textoRentabilidad);
		
		JOptionPane.showMessageDialog(null, mensaje, "Aviso de rentabilidad de clases", JOptionPane.INFORMATION_MESSAGE);
	}

	public double rentabilidadClase() {
		return calcularIngreso() - calcularCosto();
	}

	public void cambiarEstado(EstadoClase estadoClase) throws LimiteClasesException {
		this.estado = estadoClase;
		if (this.onLine) {
			agregarBDStreaming();
		}
	}

	public void agregarBDStreaming() throws LimiteClasesException {
		ControladorBdStreaming controladorBdStreaming = new ControladorBdStreaming();
		controladorBdStreaming.agregarClase(this);
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public String getLugar() {
		return this.sede.getLocalidad();
	}

	private double calcularCosto() {

		return ((this.profesor.getSueldo() / 90) + (this.sede.getAlquiler() / this.emplazamiento.getFactorCalculo())
				+ amortizarArticulos());
	}

	private double amortizarArticulos() {

		double amortizacion = 0;

		for (Articulo articulo : articulosDeLaClase) {
			amortizacion = amortizacion + articulo.calcularAmortizacion(this.duracionClase);
		}
		return amortizacion;
	}

	private double calcularIngreso() {
		double membresias = 0;
		for (Cliente inscripto : inscriptos) {
			membresias += inscripto.getCostoMembresia();
		}
		return membresias / 30 * inscriptos.size();
	}


	public boolean isOnLine() {
		return onLine;
	}

	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public int getCapacidad() {
		return capacidadMax;
	}

	public double getCosto() {
		return costo;
	}

	public int getInscriptos() {
		return this.alumnosInscriptos;
	}
}
