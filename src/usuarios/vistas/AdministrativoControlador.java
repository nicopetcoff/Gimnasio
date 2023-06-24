package usuarios.vistas;

import modelo.usuarios.Administrativo;

public class AdministrativoControlador {
	private AdministrativoVista vista = new AdministrativoVista();
	private Administrativo usuario;
	
	public AdministrativoControlador() {
		
		vista.configurarTabla(usuario.getSedes());
		
		vista.setVisible(true);
		vista.setSize(600,600);
		vista.setLocationRelativeTo(null);
	}
}
