package modelo.usuarios;
import java.time.*;
import java.util.ArrayList;
import java.time.LocalTime;

import modelo.sedes.*;

public class Profesor extends Usuario {

	private double sueldo;
	private ArrayList<Clase> clases;
	private Sede sedeAsignada;
	private ArrayList<LocalTime> horariosDisponibles;

	public Profesor(String nombre, String apellido, String dni, double sueldo) {
		super(nombre, apellido, dni);
		this.sueldo= sueldo;
		clases=new ArrayList<>();
	}

	@Override
	public boolean soySoporteTecnico() {
		return false;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public boolean soyAdministrativo() {
		return false;
	}

	@Override
	public String getDNI() {
		return this.dni;
	}

	public double getSueldo() {
		return this.sueldo;
	}
	
	public boolean estoyDisponbile (LocalDate fecha) throws ProfesorNoDisponibleException {
		int clasePorDia=0;
		LocalDate fechaClase;
		for (Clase c: this.clases) {
			fechaClase=c.getFecha();
			
			//diferenciaHs se le asigna la diferencia en horas entre la fecha que se quiere agregar la clase y las que tiene el profesor
			long diferenciaHs=Duration.between(fechaClase, fecha).toHours();
			if(3>diferenciaHs && diferenciaHs >-3 ) {
				throw new ProfesorNoDisponibleException("El profesor "+this.nombre+", "+this.apellido+" ya da una clase en una distancia menor a 3 horas");
			}
			clasePorDia++;
			if(clasePorDia>3) {
				throw new ProfesorNoDisponibleException("El profesor "+this.nombre+", "+this.apellido+" ya da 3 clases el "+ fecha);
			}
		}
		return true;
	}
	
	public void agregarClase(Clase clase) {
		clases.add(clase);
	}

	public void setSedeAsignada(Sede sede) {
		this.sedeAsignada = sede;
	}

	public Sede getSedeAsignada() {
		return sedeAsignada;
	}
	
	public void agregarHorarioDisponible(LocalTime horario) {
		this.horariosDisponibles.add(horario);
	}
	
	public ArrayList<LocalTime> getHorariosDisponibles() {
		return horariosDisponibles;
	}

	public LocalTime buscarHorarioDisponible(LocalTime horario){
		if (!horariosAsignados.contains(horario)) {
	        	return horario;
	    	}	
		return null;
	}
}
