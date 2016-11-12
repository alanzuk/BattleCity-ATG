package Aplicacion;

public class tiempo implements Runnable{
		protected static int segundos=0;

		
		public void run() {
				segundos=0;
				while(true){
					try {
						Thread.sleep(1000);
						segundos++;
					} catch (InterruptedException e) {	}
					
			}
			
		}
		public static void reset(){segundos=0;}
		public static int tiempo(){return segundos;}
}
