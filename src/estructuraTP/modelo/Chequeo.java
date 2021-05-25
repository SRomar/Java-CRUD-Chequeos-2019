package estructuraTP.modelo;

import java.time.LocalDate;
import java.util.Date;

public class Chequeo {
	
	private String frase;
	private String autor_frase;
	private String medio_frase;
	private int categoria_frase;
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPalabra_clave() {
		return palabra_clave;
	}
	public void setPalabra_clave(String palabra_clave) {
		this.palabra_clave = palabra_clave;
	}
	private String enlace_frase;
	private String palabra_clave;
	private LocalDate fecha_frase;
	private int res_verif;
	private String palabras_clave;
	
	

	
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public String getAutor_frase() {
		return autor_frase;
	}
	public void setAutor_frase(String autor_frase) {
		this.autor_frase = autor_frase;
	}
	public String getMedio_frase() {
		return medio_frase;
	}
	public void setMedio_frase(String medio_frase) {
		this.medio_frase = medio_frase;
	}
	public int getCategoria_frase() {
		return categoria_frase;
	}
	public void setCategoria_frase(int categoria_frase) {
		this.categoria_frase = categoria_frase;
	}
	public String getEnlace_frase() {
		return enlace_frase;
	}
	public void setEnlace_frase(String enlace_frase) {
		this.enlace_frase = enlace_frase;
	}
	

	public String getPalabras_clave() {
		return palabras_clave;
	}
	public void setPalabras_clave(String palabras_clave) {
		this.palabras_clave = palabras_clave;
	}

	public LocalDate getFecha_frase() {
		return fecha_frase;
	}

	public void setFecha_frase(LocalDate fecha_frase) {
		this.fecha_frase = fecha_frase;
	}
	public int getRes_verif() {
		return res_verif;
	}
	public void setRes_verif(int res_verif) {
		this.res_verif = res_verif;
	}
	
	

}
