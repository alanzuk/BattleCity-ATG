package Obstaculo;

import java.awt.Rectangle;

import javax.swing.JLabel;

import General.Logica;
import Visitor.Visitante;

public class Aguila extends Obstaculo{
	private static final String ruta="Aguilucho";
	private Logica l;
	public Aguila(int x,int y, Logica l){
		this.l=l;
		this.x=x;
		this.y=y;
		path=ruta;
		path_dinamico="";
		avanzable=false;
		destructible=true;
		grafico=new JLabel();
		vida=1;
		refrescarPosicion();
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);
	}
	
	public boolean getAvanzable(){
		return avanzable;
	}
	
	@Override
	public void plomo(){
		if(destructible){
			vida--;		
			path_dinamico=""+vida;
			refrescarPosicion();
		}
		l.gameOver();
	}
	public  Rectangle getRectangulo(){
		return rectangulo;
	}
	
	public void afectar(Visitante v){
		v.visit(this);
	}
	
}
