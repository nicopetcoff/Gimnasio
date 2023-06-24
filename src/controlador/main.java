package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

import modelo.productos.Articulo;
import modelo.productos.NoHayStockException;
import modelo.productos.TipoAmortizacion;
import modelo.sedes.Emplazamiento;
import modelo.sedes.NoMismoNivelException;
import modelo.sedes.Sede;
import modelo.supertlon.GimnasioSingleton;
import modelo.supertlon.Excepciones.NoExisteActividadException;
import modelo.supertlon.Excepciones.NoExisteArticuloEnCatalogoException;
import modelo.supertlon.Excepciones.NoExisteSedeException;
import modelo.supertlon.Excepciones.NoExisteUsuarioException;
import modelo.supertlon.Excepciones.NoexisteClaseException;
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
			System.out.println("7. \t Agendar Clase (Admin)"); // aca es el administrativo, si no existe da Exception
			System.out.println("8. \t Cambiar profesor de Sede (ST)");
			System.out.println("9. \t Ver Clases agendadas");
			System.out.println("10. \t Setear Articulo/s requerido por Actividad (ST)"); // Soporte Tecnico
			System.out.println("11. \t Asignar Stock a Sede (Admin)"); // admin
			System.out.println("12. \t Anotarse en Clase (Cliente)");
			System.out.println("13. \t Listar articulos de la Sede (Administrativo)");
			System.out.println("14. \t Visualizar Desgaste de los Articulos de una Sede (Administrativo)");
			System.out.println("15. \t Dar de baja un Articulo de una Sede (Administrativ)");

			System.out.println("Se arregla");
			
			System.out.println("Elija opcion");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:

				crearSede();
				break;

			case 2:
				crearUsuarios();
				break;

			case 3:
				crearActividades();
				break;

			case 4:
				crearArticulosEnCatalogo();
				break;

			case 5:
				crearEmplazamientos();
				break;

			case 6:
				asignarEmplazamientoSede();
				break;

			case 7:
				agendarClase();
				break;

			case 8:
				cambiarProfesorDeSede();

			case 9:
				verClasesAgendadas();
				break;

			case 10:
				setearArticuloRequeridoPorActividad(); // soporte tecnico
				break;

			case 11:
				AsignarleStockASede(); // administrador
				break;

			case 12:
				agendarseEnClase();
				break;
				
			case 13:
				listarArticulosDeLaSede();
				break;
				
			case 14:
				visualizarDesgasteArticulos();
				break;
				
			case 15:
				darDeBajaArticuloDeSede();
				break;

			default:
				break;
			}
			System.out.println();
		} while (1 != 6);
//		sc.close();
	}

	private static void darDeBajaArticuloDeSede() {
		
		Scanner sc = new Scanner (System.in);
		
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		
		int id = digaSuId();
		
		verSedes();
		
		System.out.println("Seleccione Sede");
			
		String localidad = sc.next();
		
		System.out.println("Ingrese la marca: ");		
		String marca = sc.next();
		
		System.out.println("Ingrese el nombre del articulo: ");		
		String nombArticulo = sc.next();
		
		System.out.println("Ingrese los atributos de los articulos: ");		
		String atributos = sc.next();
		
		try {
			gimnasio.darDeBajaArticuloDeSede(id, localidad, marca, nombArticulo, atributos);
		} catch (NoExisteSedeException | NoExisteUsuarioException | NoExisteArticuloEnCatalogoException e) {
			e.printStackTrace();
		}
	}

	private static void visualizarDesgasteArticulos() {
		
		Scanner sc = new Scanner (System.in);
		
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		
		int id = digaSuId();
		
		verSedes();
		
		System.out.println("Seleccione Sede");
			
		String localidad = sc.next();
		
		try {
			for (Entry<Articulo, Integer> entry : gimnasio.visualizarDesgasteArticulos(id, localidad).entrySet()) {
			    Articulo key = entry.getKey();
			    Integer value = entry.getValue();
			    System.out.println("Articulo: " + key + ", Porcentaje: " + value);
			}
		} catch (NoExisteSedeException | NoExisteUsuarioException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private static void listarArticulosDeLaSede() {
		
		Scanner sc = new Scanner (System.in);
		
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();
		
		int id = digaSuId();
		
		verSedes();
		
		System.out.println("Seleccione Sede");
			
		String localidad = sc.next();
		
		try {
			for (int i = 0; i < gimnasio.listarArticulosDeLaSede(id,localidad).size(); i++) {
				System.out.println(gimnasio.listarArticulosDeLaSede(id,localidad).get(i));
			}
		} catch (NoExisteUsuarioException | NoExisteSedeException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void agendarseEnClase() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		int idCliente = digaSuId();

		verClasesAgendadas();

		System.out.println("Ingrese el nombre de la clase: ");

		String nombreClase = sc.next();

		System.out.println("Ingrese el horario");

		LocalDateTime horario = pedirFechaHora();

		try {
			gimnasio.inscribirseEnClase(idCliente, nombreClase, horario);
		} catch (NoExisteUsuarioException | NoMismoNivelException | NoexisteClaseException | NoHayStockException e) {
			e.printStackTrace();
		}

	}

	private static void AsignarleStockASede() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		int id = digaSuId();

		boolean ejecutar = true;

		do {

			verArticulosEnCatalogo();

			System.out.println("Ingrese Marca: ");
			String marca = sc.next();

			System.out.println("Ingrese nombre del articulo: ");
			String nombArticulo = sc.next();

			System.out.println("Ingrese los atributos: ");
			String atributos = sc.next();

			System.out.println("Ingrese la cantidad que desea agregar");
			int cantidad = sc.nextInt();

			verSedes();

			System.out.println("Elija la localidad");

			String localidad = sc.next();

			try {
				gimnasio.AsignarStockASede(id, marca, nombArticulo, atributos, cantidad, localidad);
			} catch (NoExisteArticuloEnCatalogoException e) {
				e.printStackTrace();
			} catch (NoExisteUsuarioException e) {
				e.printStackTrace();
			} catch (NoExisteSedeException e) {
				e.printStackTrace();
			}

			System.out.println("¿Desea volver a agregar? (s/n): ");
			String respuesta = sc.nextLine();

			if (respuesta.equalsIgnoreCase("n")) {
				ejecutar = false;
			}
		} while (ejecutar);

	}

	private static void setearArticuloRequeridoPorActividad() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		int id = digaSuId();

		boolean ejecutar = true;

		do {

			verActividades();

			String actividad = sc.next();

			verArticulosEnCatalogo();

			System.out.println("Ingrese Marca: ");
			String marca = sc.next();

			System.out.println("Ingrese nombre del articulo: ");
			String nombArticulo = sc.next();

			System.out.println("Ingrese los atributos: ");
			String atributos = sc.next();

			System.out.println("Ingrese la cantidad requerida para ese articulo");
			int cantidad = sc.nextInt();

			try {
				gimnasio.setearArticuloRequeridoPorActividad(id, actividad, marca, nombArticulo, atributos, cantidad);
			} catch (NoExisteUsuarioException | NoExisteActividadException | NoExisteArticuloEnCatalogoException e) {
				e.printStackTrace();
			}

			System.out.println("¿Desea volver a agregar? (s/n): ");
			String respuesta = sc.nextLine();

			if (respuesta.equalsIgnoreCase("n")) {
				ejecutar = false;
			}
		} while (ejecutar);

	}

	private static void verArticulosEnCatalogo() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		for (int i = 0; i < gimnasio.getArticulosEnCatalogo().size(); i++) {
			System.out.println(gimnasio.getArticulosEnCatalogo().get(i));
		}

	}

	private static void verClasesAgendadas() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		verSedes();

		System.out.println("Elija la localidad de la Sede ");
		String localidad = sc.next();

		try {
			for (int i = 0; i < gimnasio.verClasesAgendadas(localidad).size(); i++) {
				System.out.println(gimnasio.verClasesAgendadas(localidad).get(i));

			}
		} catch (NoExisteSedeException e) {
			e.printStackTrace();
		}

	}

	private static void agendarClase() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		int idA = digaSuId();

		System.out.println("Ingrese el DNI del profesor: ");
		String nroDNIProfesor = sc.next();

		System.out.println("Ingrese la localidad de la Sede: ");
		String localidad = sc.next();

		System.out.println("Ingrese el nombre de la clase: ");
		String nombreClase = sc.next();

		System.out.println("Ingrese la actividad");
		String actividad = sc.next();

		System.out.println("Ingrese el Emplazamiento: ");
		String emplazamiento1 = sc.next();

		LocalDateTime fecha = pedirFechaHora();

		try {
			gimnasio.agendarClase(idA, nroDNIProfesor, localidad, nombreClase, actividad, emplazamiento1, fecha);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void asignarEmplazamientoSede() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner scanner = new Scanner(System.in);

		Scanner sc = new Scanner(System.in);
		int idA = digaSuId();

		verSedes();

		System.out.println("Elija localidad: ");
		String localidadSede = scanner.next();

		verEmplazamientosDisponibles(gimnasio.getEmplazamientosDisponibles());

		System.out.println("Elija emplazamiento: ");
		String emplazamiento = sc.next();

		try {
			gimnasio.AsignarEmplazamientoSede(idA, localidadSede, emplazamiento);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void verEmplazamientosDisponibles(ArrayList<Emplazamiento> emplazamientosDisponibles) {
		for (Emplazamiento emplazamiento : emplazamientosDisponibles) {
			System.out.println(emplazamiento);
		}
	}

	private static void crearEmplazamientos() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);
		int idSP = digaSuId();

		System.out.println("Ingrese el Tipo de Emplazamiento");
		String tipoEmplazamiento = sc.next();

		System.out.println("Ingrese el factor calculo: " + "\t por ejemplo pileta:150");
		double factorCalculo = sc.nextDouble();

		try {
			gimnasio.crearEmplazamiento(idSP, tipoEmplazamiento, factorCalculo);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}

	}

	private static void crearArticulosEnCatalogo() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

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
			gimnasio.agregarArticuloACatalogo(idSP, marca, articulo, fechaFabricacion, tipoAmortizacion, durabilidad,
					atributos, precio);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}
	}

	private static void crearActividades() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);

		int idSP = digaSuId();

		System.out.println("Escriba la actividad:" + "\t CROSSFIT" + "\t KANGOO" + "\t YOGA");
		String actividad = sc.next();

		try {
			gimnasio.crearActividades(idSP, actividad);
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		}

	}

	private static void crearUsuarios() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

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
				gimnasio.crearSoporteTecnico(idSP, nombre, apellido, dni);
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
				gimnasio.crearAdministrativo(idSP, nombre1, apellido1, dni1);
			} catch (NoExisteUsuarioException e) {
				e.printStackTrace();
			}
			asignarSedeAdministrativo(idSP);
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
				gimnasio.crearCliente(idSP, nombre2, apellido2, dni2, nivel);
			} catch (NoExisteUsuarioException e) {
				e.printStackTrace();
			}

			break;

		case 4:
			System.out.println("Elija que Sede");
			verSedes();

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
				gimnasio.crearProfesor(idSP, nombre4, apellido4, dni4, sueldo, localidad);
			} catch (NoExisteUsuarioException | NoExisteSedeException e) {
				e.printStackTrace();
			}

			break;

		default:
			break;

		}
	}

	private static void cambiarProfesorDeSede() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner sc = new Scanner(System.in);
		int idSP = digaSuId();

		verSedes();

		System.out.println("Ingrese el DNI del profesor: ");
		String nroDNIProfesor = sc.next();

		System.out.println("Diga la localidad de la nueva Sede: ");
		String localidad = sc.next();

		try {
			gimnasio.cambiarProfesorDeSede(idSP, localidad, nroDNIProfesor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void asignarSedeAdministrativo(int idSP) {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		Scanner scanner = new Scanner(System.in);

		int op;

		do {
			System.out.println("Desea asignarle las sedes al administrativo");
			System.out.println("1.S \n2.N");
			op = scanner.nextInt();

			switch (op) {
			case 1:
				verSedes();

				System.out.println("Elija localidad");
				String localidad = scanner.next();

				// aca le vamos a asignar la sede al ultimo administrativo creado, cuidado con
				// este metodo
				try {
					gimnasio.asignarSedeAlAdministrativo(idSP, localidad);
				} catch (NoExisteSedeException | NoExisteUsuarioException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}

		} while (op != 2);
	}

	private static void crearSede() {
		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

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

	private static void verActividades() {

		GimnasioSingleton gimnasio = GimnasioSingleton.getInstance();

		for (int i = 0; i < gimnasio.getActividades().size(); i++) {
			System.out.println(gimnasio.getActividades().get(i));
		}

	}

	private static void verSedes() {
		ArrayList<Sede> sedes = GimnasioSingleton.getInstance().getSedes();
		for (Sede sede : sedes) {
			System.out.println(sede);
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

	private static LocalDate pedirFechaFabricacion() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese la fecha de fabricación (formato: AAAA-MM-DD): ");
		String fechaStr = scanner.nextLine();
		LocalDate fechaFabricacion = LocalDate.parse(fechaStr);
		return fechaFabricacion;
	}

	private static LocalDateTime pedirFechaHora() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingrese la fecha y hora (Formato: yyyy-MM-dd HH:mm:ss): ");
		String fechaHoraString = scanner.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraString, formatter);

		return fechaHora;
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

}
