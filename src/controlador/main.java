package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.productos.TipoAmortizacion;
import modelo.sedes.Emplazamiento;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.utilidad.Nivel;

public class main {

	public static void main(String[] args) {
		
		int prueba;

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
			System.out.println("5. \t Crear Emplazamiento");
			System.out.println("6. \t Asignar Emplazamiento a Sede"); // administrativo
			System.out.println("7. \t Agendar CLase"); // aca es el administrativo, si no existe da Exception

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
				crearArticulosEnCatalogo(gimnasio.getInstance());
				break;

			case 5:
				crearEmplazamientos(gimnasio.getInstance());
				break;

			case 6:
				asignarEmplazamientoSede(gimnasio.getInstance());
				break;

			case 7:
				agendarClase(gimnasio.getInstance());
				break;

			default:
				break;
			}
			System.out.println();
		} while (opcion != 6);
		sc.close();

	}

	private static void agendarClase(GimnasioSingleton instance) {

		Scanner sc = new Scanner(System.in);

		int idA = digaSuId();

		System.out.println("Ingrese el DNI del profesor: ");
		String nroDNIProfesor = sc.next();

		System.out.println("Diga la localidad de la Sede: ");
		String localidad = sc.next();

		System.out.println("Diga el nombre de la clase: ");
		String nombreClase = sc.next();

		System.out.println("Diga el Emplazamiento: ");
		String emplazamiento1 = sc.next();

		LocalDate fecha = pedirFecha();

		try {
			instance.agendarClase(idA, nroDNIProfesor, localidad, nombreClase, emplazamiento1, fecha);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void asignarEmplazamientoSede(GimnasioSingleton instance) {

		Scanner scanner = new Scanner(System.in);

		Scanner sc = new Scanner(System.in);
		int idA = digaSuId();

		verSedes(instance.getSedes());

		System.out.println("Elija localidad: ");
		String localidadSede = scanner.next();

		verEmplazamientosDisponibles(instance.getEmplazamientosDisponibles());

		System.out.println("Elija emplazamiento: ");
		String emplazamiento = sc.next();

		try {
			instance.AsignarEmplazamientoSede(idA, localidadSede, emplazamiento);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void verEmplazamientosDisponibles(ArrayList<Emplazamiento> emplazamientosDisponibles) {
		for (Emplazamiento emplazamiento : emplazamientosDisponibles) {
			System.out.println(emplazamiento);
		}
	}

	private static void crearEmplazamientos(GimnasioSingleton instance) {

		Scanner sc = new Scanner(System.in);
		int idSP = digaSuId();

		System.out.println("Ingrese el Tipo de Emplazamiento");
		String tipoEmplazamiento = sc.next();

		System.out.println("Ingrese el factor calculo: " + "\t por ejemplo pileta:150");
		double factorCalculo = sc.nextDouble();

		try {
			instance.crearEmplazamiento(idSP, tipoEmplazamiento, factorCalculo);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}

	}

	private static LocalDate pedirFecha() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingrese la fecha (formato: dd/MM/yyyy): ");
		String fechaStr = scanner.nextLine();

		// Definimos el formato de fecha esperado
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Convertimos la cadena ingresada a LocalDate
		LocalDate fecha = LocalDate.parse(fechaStr, formatter);

		return fecha;
	}

	private static void crearArticulosEnCatalogo(GimnasioSingleton instance) {

		Scanner sc = new Scanner(System.in);

		int idSP = digaSuId();

		System.out.println("Diga la marca: ");
		String marca = sc.next();

		System.out.println("Diga el articulo: ");
		String articulo = sc.next();

		LocalDate fechaFabricacion = pedirFechaFabricacion();

		TipoAmortizacion tipoAmortizacion = seleccionarTipoAmortizacion();

		System.out.println("Ingrese la durabilidad: ");
		// que va aca?
		int durabilidad = sc.nextInt();

		System.out.println("Ingrese los atributos: ");
		String atributos = sc.next();

		System.out.println("Ingrese el precio del articulo: ");
		double precio = sc.nextDouble();

		try {
			instance.agregarArticuloACatalogo(idSP, marca, articulo, fechaFabricacion, tipoAmortizacion, durabilidad,
					atributos, precio);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	private static TipoAmortizacion seleccionarTipoAmortizacion() {

		TipoAmortizacion[] tipos = TipoAmortizacion.values();

		System.out.println("Seleccione un tipo de amortización:");
		for (int i = 0; i < tipos.length; i++) {
			System.out.println((i + 1) + ". " + tipos[i]);
		}

		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();

		if (opcion >= 1 && opcion <= tipos.length) {
			return tipos[opcion - 1];
		}
		return null;
	}

	private static LocalDate pedirFechaFabricacion() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese la fecha de fabricación (formato: AAAA-MM-DD): ");
		String fechaStr = scanner.nextLine();
		LocalDate fechaFabricacion = LocalDate.parse(fechaStr);
		return fechaFabricacion;
	}

	private static void crearActividades(GimnasioSingleton instance) {

		Scanner sc = new Scanner(System.in);

		int idSP = digaSuId();

		System.out.println("Escriba la actividad:" + "\t CROSSFIT" + "\t KANGOO" + "\t YOGA");
		String actividad = sc.next();

		try {
			instance.crearActividades(idSP, actividad);
		} catch (NoExisteUsuarioException e) {
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
			} catch (NoExisteUsuarioException e) {
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
			} catch (NoExisteUsuarioException e) {
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
			} catch (NoExisteUsuarioException e) {
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
			} catch (NoExisteUsuarioException | NoExisteSedeException e) {
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
				} catch (NoExisteSedeException | NoExisteUsuarioException e) {
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

		} catch (NoExisteUsuarioException e) {
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
