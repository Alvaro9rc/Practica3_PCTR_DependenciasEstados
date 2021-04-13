package p03.c01;

/**
 * Interfaz IParque.
 * 
 * @author Álvaro Rodríguez Carpintero
 * @author Alberto Porres Fernandez
 */
public interface IParque {
	
	/**
	 * Metodo entrarAlParque.
	 * Controla las entradas a este.
	 */
	public abstract void entrarAlParque(String puerta);
	
	/**
	 * Metodo salirDelParque.
	 * Controla las salidas a este.
	 */
	public abstract void salirDelParque(String puerta);

}
