package usuarios.vistas;

import modelo.usuarios.*;
import modelo.sedes.*;
//TODO: eliminar datos de prueba
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.utilidad.Nivel;
//TODO: eliminar datos de prueba

public class AdministrativoControlador {
	private AdministrativoVista vista = new AdministrativoVista(this);
	private Administrativo usuario;
	
	public AdministrativoControlador() { //Administrativo usuario) {
//		this.usuario = usuario;
		
		// TODO: eliminar datos de prueba
		Sede sede1 = new Sede("Caballito", Nivel.BLACK, 1000.00, 30, "Sede en Caballito");
		Sede sede2 = new Sede("Flores", Nivel.ORO, 100000.00, 55, "Sede en Flores");

		
		Emplazamiento emplazamiento1=new Emplazamiento("Pileta",500.00);
		sede1.agregarEmplazamiento(emplazamiento1);
		
		
		
		SoporteTecnico st = new SoporteTecnico("Jose", "Menem", "87654321");
		GimnasioSingleton.getInstance().agregarUsuario(st);
		Administrativo admin = new Administrativo("Juan", "Pérez", "12345678");
		st.asignarSede(admin, sede1);
		st.asignarSede(admin, sede2);
		st.crearProfesor("Juan","carlos","39.056.123",100.00,sede1);
		System.out.println("ID: "+st.getId());
		try {
			GimnasioSingleton.getInstance().crearActividades(st.getId(), "Crossfit");
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st.crearActividad("Crossfit");
		
		this.usuario = admin;
		// TODO: eliminar datos de prueba
		
		vista.configurarTabla(usuario.getSedes());
		
		vista.setVisible(true);
		vista.setSize(600,600);
		vista.setLocationRelativeTo(null);
	}
	public Sede getSedeSeleccionada() throws Exception {
		if (this.vista.getFilaSeleccionada() >= 0) {
			Sede SedeSeleccionada = this.usuario.getSedes().get(this.vista.getTablaSedes().convertRowIndexToModel(this.vista.getFilaSeleccionada()));
			//System.out.println(SedeSeleccionada);
			return SedeSeleccionada;
		} else {
			System.out.println(" aaaaa");
			throw new Exception();
		}
	}
}
