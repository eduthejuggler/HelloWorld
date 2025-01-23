import java.util.Random;

/**
 * Hago que la calase herede de Thread para poder crear hilos
 */
public class Ejercicio1Thread extends Thread{

	//Creo las variables estáticas de la clase
	private static int [][] matriz= new int [25][10];
	int columna;
	
	//Creo el constructor de la clase para asignar la columna para rellenar
	public Ejercicio1Thread(int columna) {
		this.columna = columna;
	}
	
	/*Creo el método run() que una vez iniciado los respectivos hilos se encargará 
	 * de rellennar la matriz con números random
	 */
	public void run() {
		Random rand = new Random(); //Utilizo la clase random para rellenar la matriz de manera autómática
		
		//Itero a través de todas las filas de la matríz de cada columna asignándole un nº aleatorio entre 1 y 100 a cada posición
		for (int fila = 0; fila < matriz.length ; fila++) {
			matriz [fila][columna]= rand.nextInt(100)+1;
		}
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("BIENVENIDO AL PRIMER EJERCICIO DEL TEMA 2 (DAM - M09)");
		System.out.println("Realizado por Eduardo Lucas");
		System.out.println();
		
		//Genero un array de la clase Ejercicio1Thread de 10 , ya que es la cantidad de columnas que hay
		Ejercicio1Thread hilo []= new Ejercicio1Thread [10];
		
		/*Hago un bucle en el que asigno un valor entre el 0 y el 9 (10 en total) al constructor
		 * de la clase Ejercicio1Thread creando 10 hilos  
		 */
		for (int i = 0; i<10 ; i++) {
			hilo [i]= new Ejercicio1Thread(i);
			hilo[i].start(); //Inicializo los hilos
			
			//Controlo con un bloque try catch los posibles errores de interrupciones de entrada y salida
			try {
				/*
				 * //Controlo que se terminen de ejecutar primero todos los hilos antes de continuaar con el 
				 * resto del programa usando el metodo join()
				 */
				hilo[i].join(); 

			} catch (InterruptedException e) {
				System.out.println("error "+ e.getMessage());
				
			}
		}
		
		// Printo la matriz mediante un bucle doble
		
		for (int j = 0; j < matriz.length ; j++ ) {
			for (int g = 0 ; g < matriz[j].length ; g++) {
				System.out.print("("+matriz[j][g]+")"+"\t"); //Dejo una separación entre cada número
			}
			System.out.println(); //creo un salto de linea para que me genere la forma de tabla de 25 x 10
		}
	}
}
