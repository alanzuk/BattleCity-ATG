package Tanque;
import java.awt.Rectangle;

import javax.swing.JLabel;

import General.*;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcreto;

public class Blindado extends Enemigo{
	protected final static String ruta="Blindado";
	
	public Blindado(int x,int y){
		this.x=x;
		this.y=y;
		avanzable=true;
		destructible=true;
		direccion=2;
		velocidadMov=2;
		velocidadDisp=1;
		aguante=1;
		//Inicializo el JLabel
		path=ruta;
		path_dinamico=""+direccion;
		grafico=new JLabel();
		refrescarPosicion();
		recompensa=400;
		rectangulo= new Rectangle(x,y,tamanio_celda,tamanio_celda);
		vida=1;
	}
	
	public Proyectil disparar() {
		return null;
	}
	
	public boolean getAvanzable(){
		return avanzable;
	}
	
	public Rectangle getRectangulo(){
		return rectangulo;
	}
	
	public float getVelocidadMov(){
		return velocidadMov/10;
	}
	
	public void afectar(Visitante v){
		v.visit(this);
		
	}
	
}
