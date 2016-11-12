package Aplicacion;

import General.Logica;

public class Temporizador implements Runnable {
	protected Logica juego;
	protected int segundos;
	protected boolean seguir;
	public Temporizador(Logica l){
		juego=l;
		segundos=0;
		seguir=true;
	}
	
	public void run() {
		while(seguir){
			try {
				Thread.sleep(1000);
				segundos++;
			} catch (InterruptedException e) {
			}
		}
		
	}
	
	public void frenar(){seguir=false;}
	public int tiempo(){return segundos;}

}
