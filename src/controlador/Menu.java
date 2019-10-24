package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.AnadirDatosAlXml;
import modelo.LecturaXmlYCreacionObjetos;
import modelo.ValidadorXSLT;

public class Menu {
		public void menu() {
			System.out.print("Elija la opción deseada:\n\t1 - Actualizar el xml añadiendo nuevos datos");
			System.out.print("\n\t2 - Comprobar si esta bien formado con esquema.xsd");
			System.out.print("\n\t3 - Leer Xml y guardar los objetos");
			System.out.println("\n\t4 - Salir");
			Scanner sc =new Scanner(System.in);
			
			try {
				int opcion=sc.nextInt();
				opciones(opcion);
			} catch (InputMismatchException e) {
				
				opciones(0);
			}
		}
		
		public void opciones(int opcion) {
			switch (opcion) {
			case 1:
				AnadirDatosAlXml adax=new AnadirDatosAlXml();
				adax.iniciarXml();
				menu();
				break;
			case 2: 
				ValidadorXSLT sf=new ValidadorXSLT();
				String salida=(sf.validateXMLSchema()) ?"Esta bien formado":"No esta bien formado";
				System.out.println(salida);menu();break;
			case 3: 
				LecturaXmlYCreacionObjetos tt = new LecturaXmlYCreacionObjetos();
				tt.leerXml().forEach(i->System.out.println(i));
				menu();
				break;
			case 4: System.exit(0);break;
			default: System.out.println("opcion no valida");menu(); break;
			}
		}
		
		
		
		
}
