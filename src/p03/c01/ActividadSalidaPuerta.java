package p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ActividadEntradaPuerta.
 * Gestiona las salidas por una puerta.
 * 
 * @author Álvaro Rodríguez Carpintero
 * @author Alberto Porres Fernandez
 */
public class ActividadSalidaPuerta implements Runnable{

	// variables de clase
	private static final int NUMSALIDAS = 20;
	private String puerta;
	private IParque parque;

	/**
	 * Constructor de la clase.
	 */
	public ActividadSalidaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}
	
	/**
	 * Método run.
	 */
	@Override
	public void run() {
		for (int i = 0; i < NUMSALIDAS; i ++) {
			try {
				parque.salirDelParque(puerta);
				
				// Cuanto más bajo sea este valor mayor será el tráfico de salida 	
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Salida interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
