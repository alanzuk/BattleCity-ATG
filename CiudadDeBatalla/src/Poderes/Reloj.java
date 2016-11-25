package Poderes;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcretoPoder;

public class Reloj extends Powerup implements Runnable{
	public Reloj(int x, int y) {
		this.x=x;
		this.y=y;
		grafico= new JLabel();
		path="PU_Timer";
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
	public void efecto() {
		juego.Reloj((int)x,(int)y);
		
	}
	@Override
	public void run() {
		double duracion=juego.getTiempo();
		double efecto= (duracion+3);
		while(duracion<efecto){
			juego.check_disparosJugador();	
			juego.getGui().refreshGUI();
			duracion+=0.03;
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {}
		}
		juego.pausa();
		juego.setPower();
	}
	
}
