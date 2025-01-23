import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio2 extends Thread {

	private static int[][] matriz = new int[20][10];
	private static int numeroElegido;
	private static boolean encontrado ;
	int columna;

	/**
	 * Constructor de la clase que tiene en cuenta la columna que se le inserta
	 * 
	 * @param columna
	 */
	Ejercicio2(int columna) {
		this.columna = columna;
	}

	/*
	 * Creo el método run() que una vez iniciado los respectivos hilos se encargará
	 * Iterar a través de todas las filas de la matríz de cada columna y si coincide
	 * el número de la posición con el número elegido por el usuario imprimirá la
	 * fila y la columna (para que no apareciera la columna y la fila 0 he sumado 1
	 * a la hora de mostrar los datos por pantalla
	 */
	public void run() {
		
		for (int fila = 0; fila < matriz.length; fila++) {
			if (matriz[fila][columna] == numeroElegido) {
				System.out.println("Hilo: " + (fila + 1) + "--> POSICIÓN: [" + (fila + 1) 
						+ "]" + " [" + (columna + 1) + "]");
				encontrado = true;
			}			
		}
	}

	public static void main(String[] args) {


		System.out.println("BIENVENIDO AL SEGUNDO EJERCICIO DEL TEMA 2 (DAM - M09)");
		System.out.println("Realizado por Eduardo Lucas");
		System.out.println();
		rellenarMatriz();
		printarMatriz();

		Ejercicio2 hilo[] = new Ejercicio2[10];

		while (true) {

			numeroElegido = pedirNumero();
			encontrado = false;

			for (int k = 0; k < 10; k++) {
				hilo[k] = new Ejercicio2(k);
				hilo[k].start();
			}

			// Espero a que terminen todos los hilos
			for (int k = 0; k < 10; k++) {
				try {
					hilo[k].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(encontrado == false) {
				 System.out.println("El número (" + numeroElegido + ") no está presente en la matriz.");
			}

			boolean yesorNot = siOno();
			if (yesorNot != true) {
				break;
			}
		}

		System.out.println("FIN DEL PROGRAMA");

	}

	/**
	 * Método que rellena la matríz con números aleatorios del 0 al 99. Como no
	 * especifica que se rellene con hilos la he rellenado así para que hubiera
	 * menos lineas en el programa.
	 */
	public static void rellenarMatriz() {
		Random rand = new Random();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = rand.nextInt(100);
			}
		}

	}

	/**
	 * Método que printa por pantalla una fila para aclarar la cantidad de filas
	 */
	public static void encabezadoColomnas() {
		System.out.println();
		for (int i = 0; i < matriz[i].length + 1; i++) {
			if (i == 0) {
				System.out.print("\t");
			} else {
				System.out.print(" " + i + "\t");
			}
		}
		System.out.println();
	}

	/**
	 * Método que imprime la matriz por pantalla inclutendo a los laterales columnas
	 * para aclarar el nº de fila.
	 */
	public static void printarMatriz() {
		// Printo la matriz mediante un bucle doble

		encabezadoColomnas();
		System.out.println();

		for (int j = 0; j < matriz.length; j++) {
			for (int g = 0; g < matriz[j].length; g++) {
				if (g == 0) {
					System.out.print((j + 1) + "\t" + "(" + matriz[j][g] + ")" + "\t");
				} else if (g == 9) {
					System.out.print("(" + matriz[j][g] + ")" + "\t" + (j + 1));
				} else {
					System.out.print("(" + matriz[j][g] + ")" + "\t"); 
				}
			}
			System.out.println(); 
		}
		encabezadoColomnas();
	}

	/**
	 * Método que pide un numero entre el 0 y el 99. Contempla errores si se sale
	 * del rango o si se introduce un caracter que no sea un número.
	 * 
	 * @return int
	 */
	public static int pedirNumero() {
		Scanner sc = new Scanner(System.in);
		int num = -1;
		boolean ok = true;

		while (ok) {
			try {
				while (true) {
					System.out.println();
					System.out.print("Escribe un numero que buscar:");
					num = sc.nextInt();
					if (num >= 0 && num <= 99) {
						ok = false;
						break;
					} else {
						System.out.println("ERROR: El número debe de estar comprendido entre 0 y 99");
						System.out.println("Inténtalo otra vez");
					}
				}

			} catch (InputMismatchException e) {
				System.out.println("!ERROR EN ENTRADA DE CARACTERES! Debes introducir un numero entero entre 0 y 99");
				System.out.println("Inténtalo otra vez");

				sc.next();
			}
		}

		return num;
	}

	/**
	 * Método que devuelve un true si la decisión del usuario es continuar y un
	 * false si no es así.
	 * 
	 * @return boolean
	 */
	public static boolean siOno() {
		Scanner sc = new Scanner(System.in);
		String respuesta;
		boolean ok = false;

		System.out.println();
		System.out.println("¿Quieres buscar otro número? (Si es que si pulsa (s), si no cualquier otra tecla)");
		respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("s")) {
			ok = true;
		}

		return ok;

	}
}
