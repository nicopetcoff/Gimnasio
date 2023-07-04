package controlador;

import java.util.ArrayList;

import modelo.productos.NoHayStockException;
import modelo.sedes.Clase;
import modelo.sedes.NoMismoNivelException;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.supertlon.Excepciones.NoexisteClaseException;
import modelo.usuarios.Cliente;
import usuarios.vistas.ClienteVista;

public class ClienteControlador {
    private ClienteVista vista;
    private static Cliente cliente;
    private GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();


    public boolean validarCredenciales(String usuario, String contrasenia) {
        int numero = gimnasio.buscarLoginCliente(usuario, contrasenia);
        guardarUsuario(usuario, contrasenia);
        return (numero != 0);
    }

    public Cliente buscarCliente(String usuario, String contrasenia) {
        Cliente cliente = gimnasio.getCliente(usuario, contrasenia);
        return cliente;
    }

    private void guardarUsuario(String usuario, String contrasenia) {
        cliente = gimnasio.dameCliente(usuario, contrasenia);
    }

    public void reservarClase() throws NoExisteUsuarioException, NoMismoNivelException, NoexisteClaseException, NoHayStockException {
        if (vista.getFilaSeleccionada() >= 0) {
            Clase claseSeleccionada = cliente.getClases()
                    .get(vista.getTablaClases().convertRowIndexToModel(vista.getFilaSeleccionada()));
            
           gimnasio.inscribirseEnClase(cliente.getId(), claseSeleccionada.getNombreClase(), claseSeleccionada.getFecha());            
        } 
    }

    public void cancelarReserva() throws Exception {
        if (vista.getFilaSeleccionada() >= 0) {
            Clase claseSeleccionada = cliente.getClases()
                    .get(vista.getTablaClases().convertRowIndexToModel(vista.getFilaSeleccionada()));
            claseSeleccionada.eliminarCliente(cliente);
        } else {
            throw new Exception();
        }
    }

    public Clase getClaseSeleccionada() throws Exception {
        if (vista.getFilaSeleccionada() >= 0) {
            Clase claseSeleccionada = cliente.getClases()
                    .get(vista.getTablaClases().convertRowIndexToModel(vista.getFilaSeleccionada()));
            return claseSeleccionada;
        } else {
            throw new Exception();
        }
    }

	public ArrayList<Clase> getClasesDisponibles() throws NoExisteSedeException {
		ArrayList<Clase> clasesDispo=new ArrayList<>();
		for(Sede s:gimnasio.getSedes()) {
			if(s.getNivel().getJerarquia()<=cliente.getNivel().getJerarquia()) {
				clasesDispo.addAll(s.getClases());
			}
		}
		return clasesDispo;
	}
}