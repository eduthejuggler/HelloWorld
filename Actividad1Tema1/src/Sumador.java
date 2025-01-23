import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sumador {

	public static void main (String args[]) {

		int num1, num2, suma;
		
		try{
			//Leo los números (String) del proceso padre desde la entrada
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Convierto el String a un número entero
            num1 = Integer.parseInt(br.readLine());
            num2 = Integer.parseInt(br.readLine());
            
            //Realizo la suma
            suma = num1 + num2;
            
            //Está controlado con el break del proceso hijo pero si no haría esto para que no hiciera la suma de los dos ceros
            if(suma != 0) {
            	System.out.println(suma);
            	
            }
  
		} catch (IOException e) {
			System.out.println("error" + e.getMessage());
		}
	}
}




// /Users/eduardolucasmunozdelucas/Desktop/Sumador.jar
// /Users/eduardolucasmunozdelucas/Library/Mobile Documents/com~apple~CloudDocs/Desktop/UNIVERSIDAD/FP informático/ASIGNATURAS/M09 PROG. DE SERVICIOS Y PROCESOS/Sumador.jar