import java.util.Random;

public class Ejercicio1 {

	private static int [] [] matriz = new int [25][10];
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t []= new Thread[10];
		
		for (int j = 0 ; j <10 ; j++) {
			t [j] = new Thread(new rellenarColumna(j));
			t [j].start();
			t [j].join();
		}
		
		
		
		for (int i = 0 ; i<matriz.length ; i++) {
			for(int b =0 ; b<matriz[i].length ; b++) {
				System.out.print("("+matriz [i][b] +")"+"\t");

			}
			System.out.println();
		}
		
		
	}
	
	 static class rellenarColumna implements Runnable{
		
		 private int columna;
		 
		 public rellenarColumna(int columna) {
			 this.columna = columna;
		 }

		@Override
		public void run() {
			Random rand = new Random();
			
			for (int fila = 0; fila < matriz.length ; fila++) {
				matriz [fila][columna]= rand.nextInt(100);
			}
			
		}
	}
}
