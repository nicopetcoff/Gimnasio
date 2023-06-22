package controlador;

import java.util.List;
import java.util.Map;

import modelo.baseDeDatos.BdStreaming;
import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.baseDeDatos.LimiteClasesException;

public class ControladorBdStreaming {
    private BdStreaming bdStreaming;
    
    public ControladorBdStreaming() {
        bdStreaming = BdStreaming.getInstance();
    }
    
    public void agregarClase(Clase clase) {
        try {
            bdStreaming.agregarClase(clase);
            System.out.println("Clase agregada exitosamente a la Base de Datos de Streaming.");
        } catch (LimiteClasesException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Clase buscarClase(String idClase) {
        return bdStreaming.buscarClase(idClase);
    }
    
    public List<Clase> buscarClasesPorActividad(Actividad actividad) {
        return bdStreaming.buscarClasesPorActividad(actividad);
    }
    
    public void definirLimitePorTipo(Actividad actividad, int limite) {
        bdStreaming.definirLimitePorTipo(actividad, limite);
    }
    
    public void cambiarLimite(Actividad actividad, int limite) {
        bdStreaming.cambiarLimite(actividad, limite);
    }
    
    public int buscarLimite(Actividad actividad) throws LimiteClasesException {
        return bdStreaming.buscarLimite(actividad);
    }
    
    public void eliminarClase(String idClase) {
        bdStreaming.eliminarClase(idClase);
    }
    
    public Map<Actividad, List<Clase>> getClasesAlmacenadas() {
        return bdStreaming.getClasesAlmacenadas();
    }
    
    public Map<Actividad, Integer> getLimitePorActividad() {
        return bdStreaming.getLimitePorActividad();
    }
    
    public List<Actividad> getActividades() {
        return bdStreaming.getActividades();
    }
    
}
