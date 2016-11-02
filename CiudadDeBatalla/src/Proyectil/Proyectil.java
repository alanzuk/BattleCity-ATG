package Proyectil;
import java.awt.Rectangle;





import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;
import General.GameObject;
import General.Movible;
import Tanque.Tanque;

public abstract class Proyectil extends GameObject implements Movible{
	protected int direccion;
	protected int velocidad;
	protected GameObject victima;
	protected Rectangle r;
	protected Tanque tanque_origen;
	
	
	public Proyectil(int dir,float x, float y,Tanque t) {
		this.x=x;
		this.y=y;
		direccion=dir;
		tanque_origen=t;
		grafico= new JLabel();
		path="disparo";
		path_dinamico="";
		vida=1;
		r=new Rectangle((int)x,(int)y,5,5);
	}
	
	public Tanque getTanqueDisparador(){
		return tanque_origen;
	}
	
	public void setVictima(GameObject v){
		victima=v;
	}
	
	public GameObject getVictima(){
		return victima;
	}
	
	public boolean getAvanzable() {
		return false;
	}

	public Rectangle getRectangulo() {	
		return r;
	}
	
	public int getDireccion(){
		return direccion;
	}

	public void mover(int i){
		switch(direccion){
			case(1):{
				moverY(-0.4);
				break;
			}
			case(2):{
				moverY(0.4);
				break;
			}
			case(3):{
				moverX(0.4);
				break;
			}
			case(4):{
				moverX(-0.4);
				break;
			}
		}
		r=new Rectangle((int)x,(int)y,5,5);
		refrescarPosicion();
	}
	

	public abstract Visitante getVisitante();

	public abstract void afectar(Visitante v);
		
}
