package Obstaculo;

import java.awt.Rectangle;

import javax.swing.JLabel;

import Visitor.Visitante;

public class Limite extends Obstaculo{ // LIMITE ES UN OBSTACULO CREADO PARA DEFINIR LOS "LIMITES" DEL MAPA
	
	private static final String ruta="Limite";
	
	public Limite(int x,int y){
		this.x=x;
		this.y=y;
		path=ruta;
		path_dinamico="";
		avanzable=false;
		destructible=false;
		grafico=new JLabel();
		refrescarPosicion();
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);
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

}
