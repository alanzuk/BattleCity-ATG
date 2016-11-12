package Obstaculo;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;
import General.GameObject;

public class Aguila extends Obstaculo{
	private static final String ruta="Aguilucho";
	
	public Aguila(int x,int y){
		this.x=x;
		this.y=y;
		path=ruta;
		path_dinamico="";
		avanzable=false;
		destructible=true;
		grafico=new JLabel();
		refrescarPosicion();
		
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);

	}
	public boolean getAvanzable(){return avanzable;}
	public  Rectangle getRectangulo(){
		return rectangulo;
	}
	
	public void afectar(Visitante v){
		v.visit(this);
		
	}
}
