package vista.Funcionalidades;

import javax.swing.JOptionPane;

import modelo.productos.Articulo;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;

public class ControladorIncorporarArticulo {
	private VistaIncorporarArticulo vista=new VistaIncorporarArticulo(this);
	private Sede sede;
	
	public ControladorIncorporarArticulo(Sede sede){
		this.sede=sede;
		vista.configurarTabla(GimnasioSingleton.getInstance().getArticulosEnCatalogo());
		vista.setSize(500,570);
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	
	public Articulo getArticuloSeleccionado() throws Exception {
		if (this.vista.getArticuloSeleccionado() >= 0) {
			Articulo ArticuloSeleccionado = GimnasioSingleton.getInstance().getArticulosEnCatalogo().get(vista.getArticuloSeleccionado());
			//System.out.println(SedeSeleccionada);
			return ArticuloSeleccionado;
		} else {
			JOptionPane.showMessageDialog(null, "Falta seleccionar articulo que desea agregar.",
    	            "Campo incompleto", JOptionPane.ERROR_MESSAGE);
			throw new Exception();
		}
	}
	
	public Sede getSede() {
		return this.sede;
	}

}
