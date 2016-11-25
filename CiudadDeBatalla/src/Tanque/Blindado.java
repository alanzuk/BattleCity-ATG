package Tanque;

import java.awt.Rectangle;
import javax.swing.JLabel;

public class Blindado extends Enemigo{
	protected final static String ruta="T_Blindado";
	
	public Blindado(int x,int y){
		this.x=x;
		this.y=y;
		avanzable=true;
		destructible=true;
		direccion=2;
		velocidadMov=2;
		velocidadDisp=1;
		//Inicializo el JLabel
		path=ruta;
		path_dinamico=""+direccion;
		simultaneo=1;
		grafico=new JLabel();
		refrescarPosicion();
		recompensa=400;
		rectangulo= new Rectangle(x,y,tamanio_celda,tamanio_celda);
		vida=4;
	}
	
	public boolean getAvanzable(){
		return avanzable;
	}
	
	public Rectangle getRectangulo(){
		return rectangulo;
	}
	
	public float getVelocidadDisp(){
		return velocidadDisp / 10;
	}
	
	public float getVelocidadMov(){
		return velocidadMov/10;
	}
	
}
