package modelo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import controlador.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AnadirDatosAlXml {



	public static void iniciarXml() {
		try {
			try {
				crearXml();
			} catch (TransformerException e) {

				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void crearXml()
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		ControlarDatosIntroducidos micontrolador=new ControlarDatosIntroducidos();
		LinkedList<String> datos = micontrolador.crearLibro();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder =  factory.newDocumentBuilder();

		Document doc = documentBuilder.parse(new File("libros.xml"));

		doc.getDocumentElement().normalize();
		Node nodoraiz = doc.getDocumentElement();
		Element nuevolibro = doc.createElement("libro");
		
		

		nuevolibro.setAttribute("isbn", datos.get(0));
		Element nuevotitulo = doc.createElement("titulo");
		nuevotitulo.setAttribute("tipo", datos.get(1).toUpperCase());
		nuevotitulo.setTextContent(datos.get(2));
		Element nuevaedicion = doc.createElement("edicion");
		nuevaedicion.setTextContent(datos.get(5));
		Element nuevoautor = doc.createElement("autor");
		Element nuevoprimernombre = doc.createElement("primer_nombre");
		nuevoprimernombre.setTextContent(datos.get(3));

		Element nuevoapellido = doc.createElement("apellido");
		nuevoapellido.setTextContent(datos.get(4));
		Element nuevafechapublicacion = doc.createElement("fecha_publicacion");
		Element nuevames = doc.createElement("mes");

		Element nuevadia = doc.createElement("dia");

		Element nuevaanyo = doc.createElement("anyo");

		LocalDate ld = LocalDate.parse(datos.get(6));
		nuevames.setTextContent(String.valueOf(ld.getMonthValue()));
		nuevadia.setTextContent(String.valueOf(ld.getDayOfMonth()));
		nuevaanyo.setTextContent(String.valueOf(ld.getYear()));
		Element nuevatipo = doc.createElement("tipo");
		nuevatipo.setTextContent(datos.get(7));

		Element nuevaprecio = doc.createElement("precio");
		nuevaprecio.setTextContent(datos.get(8));

		nuevolibro.appendChild(nuevotitulo);
		nuevolibro.appendChild(nuevaedicion);
		nuevoautor.appendChild(nuevoprimernombre);
		nuevoautor.appendChild(nuevoapellido);

		nuevolibro.appendChild(nuevoautor);
		nuevafechapublicacion.appendChild(nuevames);
		nuevafechapublicacion.appendChild(nuevadia);
		nuevafechapublicacion.appendChild(nuevaanyo);
		nuevolibro.appendChild(nuevafechapublicacion);

		nuevolibro.appendChild(nuevatipo);
		nuevolibro.appendChild(nuevaprecio);

		nodoraiz.appendChild(nuevolibro);
		TransformerFactory transfactory = TransformerFactory.newInstance();
		Transformer transformer = transfactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("libros.xml"));
		transformer.transform(source, result);
		System.out.println("Documento libros.xml actualizado");
	}

	

}
