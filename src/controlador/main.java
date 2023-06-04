package controlador;

import java.util.*;
import modelo.usuarios.*;

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
			
			opcion = sc.nextInt();
			
			switch(opcion) {
			
			case 1:
					
				SoporteTecnico s = obtenerSoporteTecnico(gimnasio);
				break;
				
			default:
				break;
			
			}
			
		}while(opcion!=5);
		
		sc.close();

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

}
