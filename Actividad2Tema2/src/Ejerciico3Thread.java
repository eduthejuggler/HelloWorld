

import java.util.Random;

/**
 * Clase que gestiona el valor total y controla el comportamiento de los hilos
 * 
 */
class Total {
	private int total;
	private int cantidadHilos;
	private int hilosEsperando = 0;
	private int hiloEjecutado = 0;

	/**
	 * Constructor que inicializa la cantidad de hilos
	 * 
	 * @param cantidadHilos
	 */
	Total(int cantidadHilos) {
		this.cantidadHilos = cantidadHilos;
	}

	/**
	 * Metodo getter para obtener el valor de la variable de clasae total.
	 * 
	 * @return
	 */
	public int gettotal() {
		return total;
	}

	/**
	 * // Método sincronizado para que se ejecuten de uno en uno las operaciones
	 * sobre la variable total. Permite a los hilos añadir un valor (positivo o
	 * negativo) al "total"
	 * 
	 * @param valor
	 */
	synchronized void añadir(int valor) {

		// Si la operación resultaría en un valor negativo, el hilo debe esperar
		while (total + valor < 0) {
			System.out
					.println("Quiero restar " + valor + " pero solo hay " + this.total + " --> Mando a dormir el hilo");
			hilosEsperando++; // Incremento el contador de hilos en espera

			// Si todos los hilos están esperando (porque todos intentan restar), se imprime
			// el mensaje y se sale de la función
			if (hilosEsperando == cantidadHilos) {
				System.out.println("Este hilo ya no se va a poder restar. Elimno el hilo.");
				
				return; // Termino el método sin hacer la operación
			}

			try {

				wait(); // El hilo se pone en espera hasta que otro hilo llame a notifyAll()

				System.out.print("Despierto el hilo --> ");
			} catch (InterruptedException e) {
				System.out.println("InterruptedException capturada");
			}
			hilosEsperando--; // Decremento el contador de hilos en espera al despertarse
		}

		// Realizo la operación de sumar/restar al total
		this.total = this.total + valor;
		hiloEjecutado++; // Incremento el contador de hilos ejecutados
		System.out.println("Hilo " + hiloEjecutado + " --> Valor " + valor + " Total ->: " + total);
		cantidadHilos--; // Decremento el contador de cantidad de hilos (se reduce cuando un hilo ha
							// ejecutado su operación)

		// Notifico a todos los hilos en espera para que verifiquen si pueden continuar
		notifyAll();
	}
}

/**
 * Clase que representa los hilos que se crearán
 */
public class Ejerciico3Thread extends Thread {

	private Total total; // Objeto que maneja el "total" compartido entre los hilos

	int cantidadHilos;

	/**
	 * Constructor que recibe el objeto "Total"
	 * 
	 * @param total
	 */
	public Ejerciico3Thread(Total total) {
		this.total = total;
	}

	/**
	 * Método run() que ejecuta cada hilo
	 */
	public void run() {

		total.añadir(numRandomPosONeg(7));
	}

	/**
	 * Método estático que genera un número aleatorio entre -limite y limite
	 * 
	 * @param limite
	 * @return (int)
	 */
	public static int numRandomPosONeg(int limite) {
		Random random = new Random();
		// Generar un número entre -limite y limite (incluyendo 0)
		return random.nextInt((limite * 2) + 1) - limite;
	}

	/**
	 * // Método principal que inicia el programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int cantidadHilos = 20; // Número total de hilos que crearé
		Total total = new Total(cantidadHilos); // Creo el objeto "Total" con la cantidad de hilos

		System.out.println("EMPEZAMOS CON EL EJERCICIO 3 DEL TEMA 2 - M09");
		System.out.println();

		// Muestro el valor inicial de la variable total
		System.out.println("Valor inicial --> " + total.gettotal());
		System.out.println();

		// Creo un array de hilos para posteriormente iterar en el y ahorrar código
		Ejerciico3Thread hilos[] = new Ejerciico3Thread[cantidadHilos];

		// Inicio cada hilo y lo ejecuto
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Ejerciico3Thread(total);
			hilos[i].start();
		}
		// Espero a que todos los hilos terminen su ejecución antes de continuar con el
		// programa

		for (Ejerciico3Thread hilo : hilos) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				System.out.println("Error de interrupción");
			}
		}

		// Imprimo el valor final
		System.out.println();
		System.out.println("-----------------");
		System.out.println("Valor final --> " + total.gettotal());
		System.out.println();

		System.out.println("PROGRAMA FINALIZADO");
	}
}
