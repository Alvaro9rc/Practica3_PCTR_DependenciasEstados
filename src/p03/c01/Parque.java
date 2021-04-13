package p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

public class Parque implements IParque{

	private static final int MIN = 0;
	private static final int MAX = 50;
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	public synchronized void entrarAlParque(String puerta){		// TODO
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		System.out.println("soy hilo de entrar");
		// TODO (abajo)
		comprobarAntesDeEntrar();
				
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// TODO
		checkInvariante();
		
		
		// TODO
		notify();
		
	}
	
	// 
	// TODO Metodo salirDelParque
	//
	
	@Override
	public synchronized void salirDelParque(String puerta) {
		// TODO Auto-generated method stub
		
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		System.out.println("soy hilo de salir");
		// TODO
		comprobarAntesDeSalir();
						
				
		// Aumentamos el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
				
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");
				
		// TODO
		checkInvariante();
				
		// TODO
		notify();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert MIN <= contadorPersonasTotales : "INV: Debe haber entre 0 y 50 personas en el parque";
		assert MAX >= contadorPersonasTotales : "INV: Debe haber entre 0 y 50 personas en el parque";
	}

	protected void comprobarAntesDeEntrar(){	// TODO
		//
		// TODO
		//
		
		if (contadorPersonasTotales == MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void comprobarAntesDeSalir(){		// TODO
		//
		// TODO
		//
		if (contadorPersonasTotales == MIN) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



