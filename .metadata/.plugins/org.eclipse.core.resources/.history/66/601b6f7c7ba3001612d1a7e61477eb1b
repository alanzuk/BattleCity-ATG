package Obstaculo;


import java.awt.Rectangle;
import javax.swing.JLabel;
import Visitor.Elemento;
import Visitor.Visitante;

public class Ladrillo extends Obstaculo implements Elemento {
	private static final String ruta="Ladrillo";
	
	public Ladrillo(int x,int y){
		this.x=x;
		this.y=y;
		path=ruta;
		path_dinamico="4";
		avanzable=false;
		destructible=true;
		grafico=new JLabel();
		refrescarPosicion();
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);
		vida=4;
	
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
