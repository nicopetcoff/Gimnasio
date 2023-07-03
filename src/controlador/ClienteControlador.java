package controlador;

import modelo.sedes.Clase;
import modelo.supertlon.GimnasioSingleton;
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

    public void reservarClase() throws Exception {
        if (vista.getFilaSeleccionada() >= 0) {
            Clase claseSeleccionada = cliente.getClases()
                    .get(vista.getTablaClases().convertRowIndexToModel(vista.getFilaSeleccionada()));
            claseSeleccionada.agregarCliente(cliente);
        } else {
            throw new Exception();
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
}