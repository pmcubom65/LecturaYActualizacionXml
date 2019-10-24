package modelo;

import java.util.ArrayList;

public class Libro {
	
	String isbn;
	String tipo;
	String titulo;
	ArrayList<String> autores=new ArrayList<>();
	
	String edicion;
	String fechadepublicación;
	String categoria;
	
	

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Libro(String isbn) {
	
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getFechadepublicación() {
		return fechadepublicación;
	}

	public void setFechadepublicación(String fechadepublicación) {
		this.fechadepublicación = fechadepublicación;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", tipo=" + tipo + ", titulo=" + titulo + ", autores=" + autores + ", edicion="
				+ edicion + ", fechadepublicación=" + fechadepublicación + ", categoria=" + categoria + "]\n";
	}








	
	
	

}
