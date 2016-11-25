package Aplicacion;

import java.awt.EventQueue;

import General.Logica;

public class Launcher {

	public Launcher(){
		main(null);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Logica Juego=new Logica();
					GUI Gui=new GUI(Juego);
					Juego.setGui(Gui);
					Gui.run();
					Thread t2=new Thread(Juego);
					t2.start();
					GUI.playSound("intro");
				}
				catch(Exception e){}


	}
		});}
}
