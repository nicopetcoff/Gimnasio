package controlador;

import java.util.*;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.NoExisteUsuarioExcepcion;
import modelo.usuarios.ExisteLocalidadException;
import modelo.utilidad.Nivel;


public class main {

	public static void main(String[] args) {
		
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bienvenido al Gimnasio Supertlon");
		System.out.println("A continuacion elija la opcion que desea");
		int opcion;
		do {
			System.out.println("1. \t Crear Sede");
			System.out.println("2.\t Crear Usuario");
			
			System.out.println("Elija opcion");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				
				System.out.println("Ingrese su id");
				int idUsuario = sc.nextInt();
				
				System.out.println("Ingrese la localidad");
				String localidad = sc.next();
				
				Nivel nivel = seleccionarNivel();
				
				System.out.println("Ingrese el valor del alquiler");
				double alquiler = sc.nextDouble();
				
				System.out.println("Ingrese la capacidad");
				int capacidad = sc.nextInt();
				
				System.out.println("Ingrese la descripcion de la Sede: ");
				String descripcion = sc.next();
				
				try {
					gimnasio.crearSede(idUsuario, localidad, nivel, alquiler, capacidad, descripcion );
				
				} catch (NoExisteUsuarioExcepcion e) {
					e.printStackTrace();
				}
				
				break;
				
			default:
				break;
			}
			System.out.println();
		}while(opcion!=4);
		sc.close();
		

	}

	private static Nivel seleccionarNivel() {
		
		Nivel[] niveles = Nivel.values();

        System.out.println("Seleccione un nivel:");
        for (int i = 0; i < niveles.length; i++) {
            System.out.println((i + 1) + ". " + niveles[i]);
        }

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= niveles.length) {
            return niveles[opcion - 1];
        } 
		return null;
	}

}
