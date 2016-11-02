package Poderes;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcretoPoder;

public class Reloj extends Powerup {
	public Reloj(int x, int y) {
		this.x=x;
		this.y=y;
		grafico= new JLabel();
		path="reloj";
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
	
}
