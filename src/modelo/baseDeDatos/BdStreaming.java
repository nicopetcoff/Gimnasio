import modelo.sedes.Clase;
import modelo.sedes.LimiteClasesException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BdStreaming {
    private Map<Actividad, List<Clase>> clasesAlmacenadas; // Clases almacenadas por actividad
    private Map<Actividad, Integer> limitePorActividad; // Limite por actividad
    private static BdStreaming instancia; // Instancia del singleton

    // Constructor privado
    private BdStreaming() {
        clasesAlmacenadas = new HashMap<>();
        limitePorActividad = new HashMap<>();
    }

    // Método para obtener la instancia
    public static BdStreaming getInstance() {
        if (instancia == null) {
            instancia = new BdStreaming();
        }
        return instancia;
    }

    // Métodos
    public void agregarClase(Clase clase) throws LimiteClasesException { 
        Actividad actividad = clase.getActividad(); 
        int limite = 0; 
        if (limitePorActividad.containsKey(actividad)) {
            limite = limitePorActividad.get(actividad);
        } else {
            throw new LimiteClasesException("No se puede agregar una clase porque no tiene un límite fijado");
        }

        List<Clase> clases = clasesAlmacenadas.get(actividad);
        if (clases == null) {
            clases = new ArrayList<>();
            clasesAlmacenadas.put(actividad, clases);
        }
        clases.add(clase);

        if (clases.size() > limite) {
            ajustarLimiteClases(actividad);
        }
    }

    public Clase buscarClase(String idClase) {
        for (List<Clase> clases : clasesAlmacenadas.values()) {
            for (Clase clase : clases) {
                if (String.valueOf(clase.getId()).equals(idClase)) {
                    return clase;
                }
            }
        }
        return null;
    }

    public void definirLimitePorTipo(Actividad actividad, int limite) {
        limitePorActividad.put(actividad, limite);
        ajustarLimiteClases(actividad);
    }

    private void ajustarLimiteClases(Actividad actividad) throws LimiteClasesException {
        if (clasesAlmacenadas.containsKey(actividad)) {
            List<Clase> clases = clasesAlmacenadas.get(actividad);
            int limite = 0;
            if (limitePorActividad.containsKey(actividad)) {
                limite = limitePorActividad.get(actividad);
            } else {
                throw new LimiteClasesException("No se puede agregar una clase porque no tiene un límite fijado");
            }
            
            if (clases != null && clases.size() > limite) {
                int excedente = clases.size() - limite;
                for (int i = 0; i < excedente; i++) {
                    clases.remove(0);
                }
            }
        }
    }
}
