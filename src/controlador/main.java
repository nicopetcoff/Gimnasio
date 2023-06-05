package controlador;

import java.util.*;
import modelo.usuarios.*;
import modelo.utilidad.Nivel;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
public class main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int opcion ;
		
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		
		System.out.println("Bienvenido al Gimnasio Supertlon");
		System.out.println("Elija la opcion deseada");
		
		do {
			System.out.println("Elija la opcion deseada");
			System.out.println("1:\t Crear Sede ");
			System.out.println("2:\t Crear Usuarios");
			System.out.println("3:\t Asignar sede a Administrativo");
			
			opcion = sc.nextInt();
			
			switch(opcion) {
			
			case 1:
					
				SoporteTecnico s = obtenerSoporteTecnico(gimnasio);
				
				System.out.println("Ingrese Localidad: \t");
				String locidad = sc.next();
				
				Nivel nivel = seleccionarNivel();
				
				System.out.println("Ingrese capacidad: \t");
				
				int capacidad = sc.nextInt();
				
				System.out.println("Diga descripcion de la sede");
				
				String descripcion = sc.next();
				
				try {
					s.crearSede(GimnasioSingleton.getInstance(), locidad, nivel, capacidad, descripcion);
				} catch (ExisteLocalidadException e) {
					
					e.printStackTrace();
				}				
				break;
				
			case 2:
				SoporteTecnico s1 = obtenerSoporteTecnico(gimnasio);
				
				System.out.println("Elija el tipo de usuario a cargar:");
				System.out.println("1.\t Administrativo");
				System.out.println("2.\t SoporteTecnico");
				System.out.println("3.\t Cliente");
				System.out.println("4.\t Profesor");
				
				int opcion2 = sc.nextInt();
				
				if(opcion2 ==1) {
					
					System.out.println("Nombre: \t");
					String nombre = sc.next();
					
					System.out.println("Apellido: \t");
					String apellido = sc.next();
					
					System.out.println("DNI: \t");
					String dni = sc.next();
					
					s1.crearAdministrativo(gimnasio, nombre, apellido, dni);
					
					System.out.println("Desea Asignarle la Sede?\n1. S \n 2.N");
					
					int opcion3 = sc.nextInt();
					
					if (opcion3 == 1) {
						
						Administrativo a = obtenerAdministrativo(gimnasio, dni);
						asignarSedeAdministrativo(gimnasio, a);
					}
					
				}else if (opcion2 ==2) {
					System.out.println("Nombre: \t");
					String nombre = sc.next();
					
					System.out.println("Apellido: \t");
					String apellido = sc.next();
					
					System.out.println("DNI: \t");
					String dni = sc.next();
					s1.crearSoporteTecnico(gimnasio, nombre, apellido, dni);
				}else if (opcion2 == 3) {
					System.out.println("Nombre: \t");
					String nombre = sc.next();
					
					System.out.println("Apellido: \t");
					String apellido = sc.next();
					
					System.out.println("DNI: \t");
					String dni = sc.next();
					s1.crearCliente(gimnasio, nombre, apellido, dni);
					
					
					
				} else if (opcion2 == 4) {
									
					
				}
				
				
				
				break;
				
			default:
				break;
			
			}
			
		}while(opcion!=4);
		
		

	}

	private static void asignarSedeAdministrativo(GimnasioSingleton gimnasio, Administrativo a) {
		
		/*
		 * Tengo que mejorar este metodo, ponerle un sw case
		 */
		
		Scanner sc = new Scanner(System.in);
				
		int opcion4;
		String localidad4;
		
		do {
			System.out.println("Presione 1 agregar, 2 para salir");
			opcion4 =sc.nextInt();
			
			System.out.println("Elija las sedes que desea agregar");
			
			for (int i = 0; i < gimnasio.getSedes().size(); i++) {
				System.out.println(gimnasio.getSedes().get(i));
			}
			
			System.out.println("Elija la localidad de la Sede que desea agregar");
			localidad4 = sc.next();
			
			Sede s = soyLaSede(localidad4, gimnasio.getSedes());
			
						
			a.agregarSede(s);
			
		}while(opcion4!=2);
		
		
		
	}
	
	private static  Sede soyLaSede(String localidad, ArrayList<Sede> sedes) {
		
		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				return sede;
			}
		}
		return null;
	}

	private static Nivel seleccionarNivel() {
		
		Scanner scanner = new Scanner(System.in);
		
		Nivel[] niveles = Nivel.values();
		for (int i = 0; i < niveles.length; i++) {
			System.out.println((i + 1) + ". " + niveles[i]);
		}
		
		int eleccion = scanner.nextInt();

		Nivel nivelSeleccionado = null;
		
        if (eleccion >= 1 && eleccion <= niveles.length) {
            
            nivelSeleccionado = niveles[eleccion - 1];
            System.out.println("Has elegido: " + nivelSeleccionado);
        } else {
            System.out.println("Elección inválida");
        }
	
		
		
		return nivelSeleccionado;
	}

	private static SoporteTecnico obtenerSoporteTecnico(GimnasioSingleton gimnasio) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ingrese ID: \t");
		int id = sc.nextInt();
	
		
		for (int i = 0; i < gimnasio.getUsuarios().size(); i++) {
			if (gimnasio.getUsuarios().get(i).soySoporteTecnico() && gimnasio.getUsuarios().get(i).getId() == id) {
				return (SoporteTecnico) gimnasio.getUsuarios().get(i);
			}
			
		}
		return null;
	}

	private static Administrativo obtenerAdministrativo(GimnasioSingleton gimnasio, String dni) {
		
		for (int i = 0; i < gimnasio.getUsuarios().size(); i++) {
			if (gimnasio.getUsuarios().get(i).soyAdministrativo() && gimnasio.getUsuarios().get(i).getDNI().equals(dni)) {
				return (Administrativo) gimnasio.getUsuarios().get(i);
			}
		}
		return null;
	}

}
