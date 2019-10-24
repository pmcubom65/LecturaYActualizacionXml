package controlador;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class ControlarDatosIntroducidos {

	public LinkedList<String> crearLibro() {
		LinkedList<String> datos = new LinkedList<>();
		datos.clear();
		Scanner sc = new Scanner(System.in);
		boolean bucle = false;
		do {
			System.out.println("introduzca isbn (restricción I\\d{13})");
			String isbn = sc.nextLine();
			if (verificarIsbn(isbn)) {
				datos.add(isbn);
				bucle = false;
			} else {
				System.out.println("El isbn no cumple el formato I\\\\d{13}. Valdría por ejemplo I0000000000001");
				bucle = true;
			}
		} while (bucle);

		do {
			System.out.println("introduzca tipo puede ser o P o H");
			String tipo = sc.nextLine();
			if (comprobarTipo(tipo)) {
				datos.add(tipo);
				bucle = false;
			} else {
				System.out.println("El tipo solo puede ser o P o H");
				bucle = true;

			}
		} while (bucle);

		System.out.println("introduzca titulo");
		String titulo = sc.nextLine();
		datos.add(titulo);
		System.out.println("introduzca primer_nombre autor");
		String primer_nombre = sc.nextLine();
		datos.add(primer_nombre+" ");
		System.out.println("introduzca apellido autor");
		String apellido = sc.nextLine();
		datos.add(apellido);
		bucle = false;
		do {

			System.out.println("introduzca edicion (tiene que ser un número)");
			String edicion = sc.nextLine();

			if (comprobarEdicion(edicion)) {
				datos.add(edicion);
				bucle = false;
			} else {
				bucle = true;
			}

		} while (bucle);

		System.out.println("introduzca fechapublicacion yyyy-MM-dd (Si no es correcta, se pondrá la de hoy)");
		String fechapublicacion = sc.nextLine();
		LocalDate ld;

		try {
			ld = LocalDate.parse(fechapublicacion);
		} catch (Exception e) {
			ld = LocalDate.now();

		}
		datos.add(ld.toString());
		bucle = false;
		do {

			System.out.println("introduzca categoria (tiene que ser o fiction o nonfiction)");
			String categoria = sc.nextLine();

			if (fictionOno(categoria)) {
				datos.add(categoria);
				bucle = false;
			} else {
				bucle = true;
			}

		} while (bucle);
		boolean bucledos = false;
		do {
			System.out.println("introduzca precio, tiene que ser tipo double");
			String precio = sc.nextLine();
			if (comprobarPrecio(precio)) {
				datos.add(precio);
				bucledos = false;
			} else {
				bucledos = true;
			}

		} while (bucledos);

		return datos;

	}

	private boolean comprobarPrecio(String precio) {
		try {
			Double.parseDouble(precio);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private boolean fictionOno(String categoria) {
		if (categoria.equalsIgnoreCase("fiction") || categoria.equalsIgnoreCase("nonfiction")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comprobarEdicion(String edicion) {
		try {
			Integer.parseInt(edicion);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean comprobarTipo(String tipo) {

		if (tipo.equalsIgnoreCase("P") || tipo.equalsIgnoreCase("H")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verificarIsbn(String isbn) {
		if (isbn.charAt(0) == 'I') {
			for (int i = 1; i < isbn.length(); i++) {
				if (!Character.isDigit(isbn.charAt(i))) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
