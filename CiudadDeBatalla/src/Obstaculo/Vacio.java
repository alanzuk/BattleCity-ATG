package Obstaculo;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcreto;
import General.GameObject;

public class Vacio extends Obstaculo {
	protected final static String ruta="Vacio";

	public Vacio(int x, int y) {
		this.x=x;
		this.y=y;
		path_dinamico="";
		avanzable=true;
		destructible=false;
		grafico=new JLabel();
		refrescarPosicion();
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);
		visitante=new VisitanteConcreto();
	}
	
	public boolean getAvanzable(){
		return avanzable;
	}
	

	public  Rectangle getRectangulo(){
		return rectangulo;
	}
	
	public void afectar(Visitante v){
		v.visit(this);
	}
	
	public boolean colision(GameObject obj1, GameObject obj2) {
		return false;
	}
}
