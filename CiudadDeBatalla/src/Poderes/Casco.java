package Poderes;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcretoPoder;

public class Casco extends Powerup implements Runnable {
	private int tiempo;
	public Casco(int x, int y) {
		this.x=x;
		this.y=y;
		grafico= new JLabel();
		path="PU_Casco";
		this.tiempo=3;
		path_dinamico="";
		rectangulo=new Rectangle((int)x,(int)y,tamanio_celda,tamanio_celda);
	}
	public void afectar(Visitante v){
		v.visit(this);
		
	}
	
	public Rectangle getRectangulo() {
		return rectangulo;
	}

	@Override
	public Visitante getVisitante() {
		
		return new VisitanteConcretoPoder();
	}
	public void run() {
		double duracion=juego.getTiempo();
		double efecto= (duracion+tiempo);
	  	juego.getJugador().setDestructible(false);
		while(duracion<efecto){
			duracion+=0.03;
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {}
		}
		juego.getJugador().setDestructible(true);
	}
	
	public void efecto() {
		
		juego.Casco((int)x,(int)y);
	}
}
