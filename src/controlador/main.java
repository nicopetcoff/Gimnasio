package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.NoExisteSedeException;
import modelo.supertlon.NoExisteUsuarioExcepcion;
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
			System.out.println("2. \t Crear Usuario");
			System.out.println("3. \t Crear Actividades");
			System.out.println("4. \t Crear Articulos en Catalogo del Gimnasio");

			System.out.println("Elija opcion");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:

				crearSede(gimnasio.getInstance());
				break;

			case 2:
				crearUsuarios(gimnasio.getInstance());
				break;
				
			case 3:
				crearActividades(gimnasio.getInstance());
				break;
				
			case 4:
				break;

			default:
				break;
			}
			System.out.println();
		} while (opcion != 5);
		sc.close();

	}

	private static void crearActividades(GimnasioSingleton instance) {
		
		Scanner sc = new Scanner(System.in);

		int idSP = digaSuId();
		
		System.out.println();
		String actividad = sc.next("Escriba la actividad:"
				+ "\t CROSSFIT"
				+ "\t KANGOO"
				+ "\t YOGA");
		
		try {
			instance.crearActividades(idSP, actividad);
		} catch (NoExisteUsuarioExcepcion e) {
			e.printStackTrace();
		}
		
		
	}

	private static void crearUsuarios(GimnasioSingleton instance) {

		Scanner sc = new Scanner(System.in);

		int idSP = digaSuId();

		System.out.println("Elija que Usuario desea crear:");
		System.out.println("1. \t Soporte Tecnico");
		System.out.println("2. \t Administrativo");
		System.out.println("3. \t Cliente");
		System.out.println("4. \t Profesor");

		int opcion = sc.nextInt();

		switch (opcion) {
		case 1:
			System.out.println("Ingrese nombre: ");
			String nombre = sc.next();

			System.out.println("Ingrese apellido");
			String apellido = sc.next();

			System.out.println("Ingrese DNI: ");
			String dni = sc.next();

			try {
				instance.crearSoporteTecnico(idSP, nombre, apellido, dni);
			} catch (NoExisteUsuarioExcepcion e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Ingrese nombre: ");
			String nombre1 = sc.next();

			System.out.println("Ingrese apellido");
			String apellido1 = sc.next();

			System.out.println("Ingrese DNI: ");
			String dni1 = sc.next();

			try {
				instance.crearAdministrativo(idSP, nombre1, apellido1, dni1);
			} catch (NoExisteUsuarioExcepcion e) {
				e.printStackTrace();
			}
			asignarSedeAdministrativo(instance, idSP);
			break;

		case 3:
			System.out.println("Ingrese nombre: ");
			String nombre2 = sc.next();

			System.out.println("Ingrese apellido");
			String apellido2 = sc.next();

			System.out.println("Ingrese DNI: ");
			String dni2 = sc.next();

			Nivel nivel = seleccionarNivel();

			try {
				instance.crearCliente(idSP, nombre2, apellido2, dni2, nivel);
			} catch (NoExisteUsuarioExcepcion e) {
				e.printStackTrace();
			}
			
			break;

		case 4:
			System.out.println("Elija que Sede");
			verSedes(instance.getSedes());

			System.out.println("Elija la localidad de la sede");
			String localidad = sc.next();

			System.out.println("Ingrese nombre: ");
			String nombre4 = sc.next();

			System.out.println("Ingrese apellido");
			String apellido4 = sc.next();

			System.out.println("Ingrese DNI: ");
			String dni4 = sc.next();

			System.out.println("Ingrese el sueldo: ");
			double sueldo = sc.nextDouble();

			try {
				instance.crearProfesor(idSP, nombre4, apellido4, dni4, sueldo, localidad);
			} catch (NoExisteUsuarioExcepcion | NoExisteSedeException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;

		}
	}

	private static void asignarSedeAdministrativo(GimnasioSingleton instance, int idSP) {

		Scanner scanner = new Scanner(System.in);

		int op;

		do {
			System.out.println("Desea asignarle las sedes al administrativo");
			System.out.println("1.S \n2.N");
			op = scanner.nextInt();

			switch (op) {
			case 1:
				verSedes(instance.getSedes());

				System.out.println("Elija localidad");
				String localidad = scanner.next();

				// aca le vamos a asignar la sede al ultimo administrativo creado, cuidado con
				// este metodo
				try {
					instance.asignarSedeAlAdministrativo(idSP, localidad);
				} catch (NoExisteSedeException | NoExisteUsuarioExcepcion e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}

		} while (op != 2);
	}

	private static void verSedes(ArrayList<Sede> sedes) {
		for (Sede sede : sedes) {
			System.out.println(sede);
		}
	}

	private static void crearSede(GimnasioSingleton gimnasio) {

		Scanner sc = new Scanner(System.in);

		int idUsuario = digaSuId();

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
			gimnasio.crearSede(idUsuario, localidad, nivel, alquiler, capacidad, descripcion);

		} catch (NoExisteUsuarioExcepcion e) {
			e.printStackTrace();
		}
	}

	private static int digaSuId() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Ingrese su id");

		int id = sc.nextInt();

		return id;
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
