package p03.c01;


/**
 * Clase SistemaLanzador.
 * Inicia la ejecución del sistema.
 * 
 * @author Álvaro Rodríguez Carpintero
 * @author Alberto Porres Fernandez
 */
public class SistemaLanzador {
	
	/**
	 * Método main.
	 * @param args
	 */
	public static void main(String[] args) {
		
		IParque parque = new Parque();
		char letra_puerta = 'A';
		
		System.out.println("¡Parque abierto!");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creacion de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread (entradas).start();

			// Creacion de hilos de entrada
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (salidas).start();
		}
	}	
}