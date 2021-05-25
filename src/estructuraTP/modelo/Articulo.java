package estructuraTP.modelo;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Articulo {
	private String titulo;
	private String epigrafe;
	private String contenido;
	private LocalDate fechaCreacion = LocalDate.now();
	
	
	public abstract boolean Alto_Impacto();
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEpigrafe() {
		return epigrafe;
	}
	public void setEpigrafe(String epigrafe) {
		this.epigrafe = epigrafe;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
