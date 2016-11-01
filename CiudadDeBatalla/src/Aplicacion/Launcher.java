package Aplicacion;

import java.awt.EventQueue;

public class Launcher {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					GUI Gui=new GUI();
					Thread t2=new Thread(Gui);
					GUI.playIntro();
					t2.start();
					Thread.sleep(20);

				}
				catch(Exception e){}


	}
		});}
}
