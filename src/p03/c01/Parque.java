package p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase parque implementa interfaz IParque.
 * Gestiona el comportamiento del parque.
 * 
 * @author Álvaro Rodríguez Carpintero
 * @author Alberto Porres Fernandez
 */
public class Parque implements IParque{
	
	// mínimo numero de personas que puede haber
	private static final int MIN = 0;
	// máximo numero de personas que puede haber
	private static final int MAX = 50;
	// número actual de personas 
	private int contadorPersonasTotales;
	// mapa de personas por puerta
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	// contador de la cola de personas que quieren entrar
	private int entradasEnCola;
	// contador de la cola de personas que quieren salir
	private int salidasEnCola;

	/**
	 * Constructor de la clase.
	 */
	public Parque() {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		entradasEnCola = 0;
		salidasEnCola = 0;
	}

	/**
	 * Metodo entrarAlParque.
	 * Controla las entradas a este.
	 */
	@Override
	public synchronized void entrarAlParque(String puerta){		
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// dependencias de estado
		comprobarAntesDeEntrar();
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// reducimos numero de entradas en cola
		if(entradasEnCola > 0)
			entradasEnCola --;
		
		// Imprimimos el estado del parque y comprovamos invariante
		imprimirInfo(puerta, "Entrada");
		checkInvariante();
		
		// Prevencion de una entrada no autorizada
		if (entradasEnCola == 0)
			notify();
	}
	
	/**
	 * Metodo salirDelParque.
	 * Controla las salidas a este.
	 */
	@Override
	public synchronized void salirDelParque(String puerta) {
		
		// Si no hay entradas por esa puerta
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		// dependencias de estado
		comprobarAntesDeSalir();
								
		// Aumentamos el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
		
		// reducimos numero de salidas en cola
		if(salidasEnCola > 0)
			salidasEnCola --;
				
		// Imprimimos el estado del parque y comprovamos invariante
		imprimirInfo(puerta, "Salida");
		checkInvariante();
		
		// Prevencion de una salida no autorizada
		if (salidasEnCola == 0)
			notify();
	}
	
	/**
	 * Método imprimirInfo.
	 * Muestra los movimientos que ocurren en el parque.
	 * 
	 * @param puerta
	 * @param movimiento
	 */
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	/**
	 * Método sumarContadoresPuerta.
	 * Suma las personas del mapa de puertas.
	 * 
	 * @return suma total.
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	/**
	 * Comprobador del invariante.
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert MIN <= contadorPersonasTotales : "INV: No puede haber menos de 0 personas";
		assert MAX >= contadorPersonasTotales : "INV: No puede haber mas de 50 personas";
	}
	
	/**
	 * Precondiciones de la entrada al parque.
	 */
	protected void comprobarAntesDeEntrar(){	

		if (contadorPersonasTotales == MAX || entradasEnCola > 0 ) {
			try {
				entradasEnCola ++;
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Precondiciones de la salida al parque.
	 */
	protected void comprobarAntesDeSalir(){		
	
		if (contadorPersonasTotales == MIN || salidasEnCola > 0) {
			try {
				salidasEnCola ++;
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}