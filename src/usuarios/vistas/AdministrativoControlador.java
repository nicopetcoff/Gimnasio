package usuarios.vistas;

import modelo.usuarios.Administrativo;
//TODO: eliminar datos de prueba
import modelo.sedes.Sede;
import modelo.usuarios.SoporteTecnico;
import modelo.utilidad.Nivel;
//TODO: eliminar datos de prueba

public class AdministrativoControlador {
	private AdministrativoVista vista = new AdministrativoVista();
	private Administrativo usuario;
	
	public AdministrativoControlador() { //Administrativo usuario) {
//		this.usuario = usuario;
		
		// TODO: eliminar datos de prueba
		Sede sede1 = new Sede("Caballito", Nivel.BLACK, 1000.00, 30, "Sede en Caballito");
		Sede sede2 = new Sede("Flores", Nivel.ORO, 100000.00, 55, "Sede en Flores");
		
		SoporteTecnico st = new SoporteTecnico("Jose", "Menem", "87654321");
		Administrativo admin = new Administrativo("Juan", "Pérez", "12345678");
		st.asignarSede(admin, sede1);
		st.asignarSede(admin, sede2);
		
		this.usuario = admin;
		// TODO: eliminar datos de prueba
		
		vista.configurarTabla(usuario.getSedes());
		
		vista.setVisible(true);
		vista.setSize(600,600);
		vista.setLocationRelativeTo(null);
	}
}
