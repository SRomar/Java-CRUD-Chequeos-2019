package estructuraTP.modelo;

import java.sql.Date;

public class Investigacion extends Articulo{
	private int categoria;
	private String tema;
	private String palabras_clave;



	public boolean Alto_Impacto() {
		return true;
	}
	
	
	
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getPalabras_clave() {
		return palabras_clave;
	}
	public void setPalabras_clave(String palabras_clave) {
		this.palabras_clave = palabras_clave;
	}
}
