import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String args[]) throws IOException, InterruptedException {
		
		//Creo las variables que utilizaré en el main tururu
		boolean ok = true;
		int num1, num2;
		String suma;
		
		//Primeros mensajes de bienvenida al programa por pantalla.
		System.out.println("--- Bien venido al primer ejercicio del módulo M09 ---");
		System.out.println("* Recuerda escribir solamente números positivos * ");
		
		//Bucle que repite el proceso de pedir numeros y comunicarse con proceso hijo hasta el break
		while (ok) {
			
			// Llamo al metodo para pedir los números por consola.
			System.out.println(" ");
			num1 = numEnteroPositivo("Introduce un número: "); 
			System.out.println(" ");
			num2 = numEnteroPositivo("Escribe el segundo número: ");
			System.out.println(" ");
			
			//Condición: si el numero 1 y el 2 son 0 se sale del bucle.
			if (num1 == 0 & num2 == 0) {
				System.out.println("FIN DEL PROGRAMA");
				break;
			}
			
			try {
				//Creo el proceso hijo a partir del .jar exportado de la clase Sumador y lo inicializo
				Process hijoSumador = new ProcessBuilder("java", "-jar", "/Users/eduardolucasmunozdelucas/Desktop/Sumador.jar").start();
				
				//Lo hice primero con PrintStream y luego probé con OutputStream directamente
//				//Envío los numeros a traves de la clase PrintStream (salida de datos) mediante un Stream
//				PrintStream ps = new PrintStream(hijoSumador.getOutputStream());
//				
//				ps.println(num1);
//				ps.println(num2);
//				ps.flush();
//				ps.close();
				
				
				//Envío los numeros a traves de la clase OutputStream (salida de datos) mediante un Stream
				OutputStream os = hijoSumador.getOutputStream();
				
				os.write((num1 + "\n").getBytes()); //Escribo una linea del Stream con el primer numero y hago salto de linea
				os.write((num2 + "\n").getBytes()); //Igual con el segundo número
				os.flush();		//Evito la pérdida de datos mediante la escritura
				os.close();		//Cierro el proceso de escritura
				
				//Estraigo los datos de salida del proceso hijo Sumador leyendo el Stream que debe entrar al padre desde el hijo
				BufferedReader br = new BufferedReader(new InputStreamReader(hijoSumador.getInputStream()));
				suma = br.readLine();
				
				//Imprimo por pantalla los datos extraidos
				System.out.println("La suma es: " + suma);
				System.out.println("--------------------");
				
			}catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}

		}
	}
	
	/**
	 * Método para pedir numero entero positivo pasandole un texto como enunciado.
	 * @param texto
	 * @return
	 */
	public static int numEnteroPositivo (String texto) {
		Scanner sc = new Scanner (System.in);
		int n = -15 ;
		boolean ok = true;
		
		/*Bucle que se repite mientras no se cumpla la condición y te avisa en caso de escribir un nº negativo 
		 * o un caracter que no sea un entero
		 */
		do {
			try {
				System.out.print(texto);
				n = sc.nextInt();
				if (n >= 0) {
					ok = false;
				}else {
					System.out.print("El número debe de ser positivo");
				}
				
			}catch (InputMismatchException e) {
				System.out.println("Caracter no válido (Escriba un número entero)");
                sc.next();
			}
		}while (ok);		
		return n;
	}
}





// Prueba anterior con BufferedReader


//
//}
//
//
//
//
//
//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//while (ok) {
//System.out.println("Escoge un número: ");
//
//numeroString = in.readLine();
//num1 = Integer.parseInt(numeroString);
//
//System.out.println("Escoge un segundo número: ");
//
//numeroString2 = in.readLine();
//num2 = Integer.parseInt(numeroString2);
//
//if (num1 == 0 & num2 == 0) {
//	System.out.println("Fin");
//	ok=false;
////	break;
//}
//
//try {
//	Process hijo = new ProcessBuilder("java", "-jar", "/Users/eduardolucasmunozdelucas/Desktop/Sumador.jar")
//			.start();
//
//	OutputStream os = hijo.getOutputStream();
//
//	os.write((num1 + "\n").getBytes());
//	os.write((num2 + "\n").getBytes());
//	os.flush();
//	os.close();
//
//	// Recepción
//	BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
//// datosrecibidos = br.readLine();
//	suma = br.readLine();
//// System.out.println(datosrecibidos);
//	System.out.println(suma);
//
//	hijo.waitFor();
//
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

