package p03.c01;

import java.util.Random;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ActividadEntradaPuerta.
 * Gestiona las entradas por una puerta.
 * 
 * @author �lvaro Rodr�guez Carpintero
 * @author Alberto Porres Fernandez
 */
public class ActividadEntradaPuerta implements Runnable{
	
		// variables de clase
		private static final int NUMENTRADAS = 20;
		private String puerta;
		private IParque parque;

		/**
		 * Constructor de la clase.
		 */
		public ActividadEntradaPuerta(String puerta, IParque parque) {
			this.puerta = puerta;
			this.parque = parque;
		}

		/**
		 * M�todo run.
		 */
		@Override
		public void run() {
			for (int i = 0; i < NUMENTRADAS; i ++) {
				try {
					parque.entrarAlParque(puerta);
					
					// Cuanto m�s bajo sea este valor mayor ser� el tr�fico de entrada 	
					TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5)*1000);
				} catch (InterruptedException e) {
					Logger.getGlobal().log(Level.INFO, "Entrada interrumpida");
					Logger.getGlobal().log(Level.INFO, e.toString());
					return;
				}
			}
		}

}
