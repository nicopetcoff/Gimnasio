package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.sedes.Actividad;
import modelo.sedes.Clase;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.usuarios.Administrativo;
import modelo.usuarios.ExisteLocalidadException;
import modelo.usuarios.SoporteTecnico;
import modelo.usuarios.Usuario;
import modelo.utilidad.EstadoClase;
import modelo.utilidad.Nivel;

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
			System.out.println("3:\t Cargar tipo de Clase");
			System.out.println("4:\t Crear tipo de Articulo");


			opcion = sc.nextInt();

			switch(opcion) {

			case 1:

				SoporteTecnico s = obtenerSoporteTecnico(gimnasio);
				crearSede(s, gimnasio);
				break;

			case 2:
				SoporteTecnico s1 = obtenerSoporteTecnico(gimnasio);
				crearUsuarios(s1, gimnasio);
				break;

			case 3:
				SoporteTecnico s2 = obtenerSoporteTecnico(gimnasio);
				cargarTipoActividad(s2, gimnasio);
				break;

			default:
				break;

			}

		}while(opcion!=4);

		sc.close();

	}

	private static void cargarTipoActividad(SoporteTecnico s2, GimnasioSingleton gimnasio) {

		verSedes(gimnasio.getSedes());

		Sede se2 = soyLaSede(gimnasio.getSedes());

		Clase cl = soyEsaClase(se2.getClases());

		Actividad act = seleccionarActividad();

		s2.cargarTipoClase(cl, act);
	}

	private static Clase soyEsaClase(ArrayList<Clase> clases) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Mire las clases disponibles");

		for (Clase clase : clases) {
			if (clase.getEstado().equals(EstadoClase.AGENDADA)) {
				System.out.println(clase);
			}

		}

		System.out.println("Diga el ID de la clase que desea: ");

		int id = sc.nextInt();

		for (Clase clase : clases) {
			if (clase.getId() == id) {
				sc.close();
				return clase;
			}
		}
		sc.close();
		return null;
	}

	private static void crearSede(SoporteTecnico s, GimnasioSingleton gimnasio) {

		Scanner sc = new Scanner(System.in);

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
		sc.close();
	}

	private static void crearUsuarios(SoporteTecnico s1, GimnasioSingleton gimnasio) {

		Scanner sc = new Scanner(System.in);

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

			System.out.println("Desea Asignarle la Sede?\n 1.S \n 2.N");

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

			Nivel nivel1 = seleccionarNivel();

			s1.crearCliente(gimnasio, nombre, apellido, dni, nivel1);



		} else if (opcion2 == 4) {


			System.out.println("Nombre: \t");
			String nombre = sc.next();

			System.out.println("Apellido: \t");
			String apellido = sc.next();

			System.out.println("DNI: \t");
			String dni = sc.next();

			System.out.println("Indique su sueldo");
			double sueldo = sc.nextDouble();

			verSedes(gimnasio.getSedes());

			Sede sede = soyLaSede( gimnasio.getSedes());

			s1.crearProfesor(sede, nombre,apellido,dni, sueldo);

		}

		sc.close();
	}

	private static void verSedes(ArrayList<Sede> sedes) {

		for (Sede sede : sedes) {
			System.out.println(sede);
		}
	}

	private static  Sede soyLaSede(ArrayList<Sede> sedes) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Diga la localidad de la Sede elejida:");

		String localidad = sc.next();

		for (Sede sede : sedes) {
			if (sede.getLocalidad().equals(localidad)) {
				sc.close();
				return sede;
			}
		}
		sc.close();
		return null;
	}

	private static void asignarSedeAdministrativo(GimnasioSingleton gimnasio, Administrativo a) {



		Scanner sc = new Scanner(System.in);

		int opcion4;
		do {
			System.out.println("Presione 1 agregar, 2 para salir");
			opcion4 =sc.nextInt();

			verSedes(gimnasio.getSedes());

			switch(opcion4) {

			case 1:

				Sede s = soyLaSede(gimnasio.getSedes());


				a.agregarSede(s);
				break;

			case 2:
				break;

			default:
				break;

			}



		}while(opcion4!=2);
		sc.close();



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

        scanner.close();

		return nivelSeleccionado;
	}

	private static Actividad seleccionarActividad() {

		Scanner scanner = new Scanner(System.in);

		Actividad[] actividades = Actividad.values();
		for (int i = 0; i < actividades.length; i++) {
			System.out.println((i + 1) + ". " + actividades[i]);
		}

		int eleccion = scanner.nextInt();

		Actividad actividadSeleccionado = null;

        if (eleccion >= 1 && eleccion <= actividades.length) {

        	actividadSeleccionado = actividades[eleccion - 1];
            System.out.println("Has elegido: " + actividadSeleccionado);
        } else {
            System.out.println("Elección inválida");
        }

        scanner.close();

		return actividadSeleccionado;
	}

	private static SoporteTecnico obtenerSoporteTecnico(GimnasioSingleton gimnasio) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Ingrese ID: \t");
		int id = sc.nextInt();


		for (Usuario element : gimnasio.getUsuarios()) {
			if (element.soySoporteTecnico() && element.getId() == id) {
				sc.close();
				return (SoporteTecnico) element;
			}

		}
		sc.close();
		return null;
	}

	private static Administrativo obtenerAdministrativo(GimnasioSingleton gimnasio, String dni) {

		for (Usuario element : gimnasio.getUsuarios()) {
			if (element.soyAdministrativo() && element.getDNI().equals(dni)) {
				return (Administrativo) element;
			}
		}
		return null;
	}

}
