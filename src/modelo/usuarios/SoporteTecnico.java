package modelo.usuarios;

import java.time.LocalDate;

import modelo.productos.Articulo;
import modelo.productos.TipoAmortizacion;
import modelo.sedes.Actividad;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Excepciones.ExisteLocalidadException;
import modelo.usuarios.Excepciones.NoPudoException;
import modelo.usuarios.Excepciones.ProfesorNoRegistradoException;
import modelo.utilidad.Nivel;

public class SoporteTecnico extends Usuario {
	
	private String usuario;
	private String contrasenia;

	public SoporteTecnico(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);

	}

	public Sede crearSede(String localidad, Nivel nivel, double alquiler, int capacidad, String tipoSede)
			throws ExisteLocalidadException {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		for (Sede element : gimnasio.getSedes()) {
			if (element.getLocalidad().equals(localidad)) {
				throw new ExisteLocalidadException();
			}

		}
		return (new Sede(localidad, nivel, alquiler, capacidad, tipoSede));

	}

	public SoporteTecnico crearSoporteTecnico(String nombre, String apellido, String dni, String usuario, String contrasenia) {
		
		SoporteTecnico st = new SoporteTecnico(nombre, apellido, dni);
		
		st.setUsuarioContraseniaSoporte(usuario, contrasenia);
		
		return (st);
	}

	public Administrativo crearAdministrativo(String nombre, String apellido, String dni, String usuario,
			String contrasenia) {

		Administrativo a = new Administrativo(nombre, apellido, dni);
		a.setUsuarioContraseniaAdmin(usuario, contrasenia);
		return (a);

	}

	public Cliente crearCliente(String nombre, String apellido, String dni, Nivel nivel1, String usuario,
			String contrasenia) {

		Cliente cliente = new Cliente(nombre, apellido, dni, nivel1);
		cliente.setUsuarioContraseniaCliente(usuario, contrasenia);

		return (cliente);
	}

	public Profesor crearProfesor(String nombre, String apellido, String dni, double sueldo, Sede sede) {
		Profesor profe = new Profesor(nombre, apellido, dni, sueldo);
		asingarSedeProfesor(sede, profe);
		return (profe);
	}

	private void asingarSedeProfesor(Sede sede, Profesor profe) {
		sede.agregarProfesor(profe);
	}

	@Override
	public boolean soySoporteTecnico() {
		return true;
	}

	@Override
	public boolean soyAdministrativo() {
		return false;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	@Override
	public int getId() {
		return this.id;
	}

	public Articulo crearArticulo(String marca, String articulo, LocalDate fechaFabricacion,
			TipoAmortizacion tipoAmortizacion, int durabilidad, String atributos, double precio) {

		return (new Articulo(marca, articulo, fechaFabricacion, tipoAmortizacion, durabilidad, atributos, precio));

	}

	// asigna sede a un administrador
	public void asignarSede(Administrativo admin, Sede sede) {
		admin.agregarSede(sede);
	}

	public void AsignarLaSedeAlAdministrativo(Administrativo usuario, Sede sede) throws NoPudoException {
			usuario.agregarSede(sede);
		

	}

	public void cambiarProfesorDeSede(String dniProfesor, Sede nuevaSede) throws ProfesorNoRegistradoException {
		for (Sede sede : GimnasioSingleton.getInstance().getSedes()) {
			Profesor profesor = sede.buscarProfesor(dniProfesor);
			if (profesor != null && sede.getProfesores().contains(profesor)) {
				sede.removerProfesor(profesor);
				nuevaSede.agregarProfesor(profesor);
			}
		}
	}

	public Actividad crearActividad(String actividad) {
		return (new Actividad(actividad));
	}

	public Emplazamiento crearEmplazamiento(String tipoEmplazamiento, double factorCalculo) {
		return (new Emplazamiento(tipoEmplazamiento, factorCalculo));
	}

	public void setearArticuloRequeridoPorActividad(Actividad act, Articulo art, int cantidad) {

		act.agregarArticuloPorAlumno(art, cantidad);

	}

	@Override
	public boolean soyCliente() {
		return false;
	}

	public void asignarEmplazamientoSede(Sede s, Emplazamiento em) {

		s.agregarEmplazamiento(em);
	}

	public void setearEmplazamientoRequeridoPorActiviadad(Actividad act, Emplazamiento emp) {
		act.setEmplazamientoRequerido(emp);
	}
	
	public void setUsuarioContraseniaSoporte(String usuario, String contrasenia) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}
	
	public String getUsuario() {
		return this.usuario;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

}
