package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LecturaXmlYCreacionObjetos {

	String isbn;
	LinkedHashMap<String, String> contenido = new LinkedHashMap<>();
	ArrayList<Libro> biblioteca = new ArrayList<>();

	public ArrayList<Libro> leerXml() {
		File file = new File("libros.xml");
		Libro l = null;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder documentbuilder = documentBuilderFactory.newDocumentBuilder();
			try {
				Document d = documentbuilder.parse(file);

				NodeList libros = d.getElementsByTagName("libro");
				for (int i = 0; i < libros.getLength(); i++) {
					org.w3c.dom.Node libro = libros.item(i);

					if (libro.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) libro;
						isbn = e.getAttribute("isbn");
						l = new Libro(isbn);

						NodeList resto = e.getChildNodes();
						for (int j = 0; j < resto.getLength(); j++) {
							int contador = 1;
							org.w3c.dom.Node nresto = resto.item(j);
							if (nresto.getNodeType() == Node.ELEMENT_NODE) {
								Element lect = (Element) nresto;
								if (lect.hasAttribute("tipo")) {
									contenido.put("tipo", lect.getAttribute("tipo"));
								}
								if (contenido.containsKey(lect.getTagName())) {
									contenido.put(lect.getTagName() + contador, lect.getTextContent());
									contador++;
								} else {
									contenido.put(lect.getTagName(), lect.getTextContent());
								}
								NodeList detalledia = lect.getChildNodes();
								for (int k = 0; k < detalledia.getLength(); k++) {
									org.w3c.dom.Node nododia = detalledia.item(k);
									if (nododia.getNodeType() == Node.ELEMENT_NODE) {
										Element dia = (Element) nododia;
										contenido.put(dia.getTagName(), dia.getTextContent());

									}
								}

							}

							contador = 1;
						}

					}

					l.setTitulo(contenido.get("titulo"));
					l.getAutores().add(contenido.get("autor").replaceAll("\n", " "));
				
					if (contenido.containsKey("autor1"))
						l.getAutores().add(contenido.get("autor1").replaceAll("\n", " "));
					l.setEdicion(contenido.get("edicion"));
					l.setFechadepublicación(String.format("%s-%s-%s", contenido.get("dia"), contenido.get("mes"), contenido.get("anyo")));
							
					l.setTipo(contenido.get("tipo"));
					l.setCategoria(contenido.get("tipo1"));
					biblioteca.add(l);

					contenido.clear();
				}

			} catch (SAXException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (ParserConfigurationException e1) {

			e1.printStackTrace();
		}

	//	System.out.println(biblioteca);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("salida.dat")))) {
			biblioteca.forEach(i -> {
				try {
					oos.writeObject(i);
				} catch (IOException e) {

				}
			});
		} catch (IOException f) {

		}

		System.out.println("Objetos guardados en salida.dat");
		return biblioteca;
	}



}
