package estructuraTP.modelo;

import java.util.ArrayList;

public class Explicacion extends Articulo{
private int cantChequeos;
private ArrayList<Chequeo> chequeos = new ArrayList<Chequeo>();
	
	public boolean Alto_Impacto() {
		if(cantChequeos>4) {
			return true;
		}
		return false;
	}
	

	public int getCantChequeos() {
		return cantChequeos;
	}

	public void setCantChequeos(int cantChequeos) {
		this.cantChequeos = cantChequeos;
	}
	
}