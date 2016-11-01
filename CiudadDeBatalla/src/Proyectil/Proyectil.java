package Proyectil;
import java.awt.Rectangle;





import javax.swing.JLabel;

import Visitor.Visitante;
import Visitor.VisitanteConcreto;
import Visitor.VisitanteConcretoProyectil;
import General.GameObject;
import General.Movible;

public abstract class Proyectil extends GameObject implements Movible{
	protected int direccion;
	protected int velocidad;
	protected GameObject victima;
	protected Rectangle r;
	
	
	public Proyectil(int dir,float x, float y) {
		this.x=x;
		this.y=y;
		direccion=dir;
		grafico= new JLabel();
		path="disparo";
		path_dinamico="";
		vida=1;
		r=new Rectangle((int)x,(int)y,5,5);
		
	}
	
	public void setVictima(GameObject v){
		victima=v;
	}
	
	public GameObject getVictima(){
		return victima;
	}
	
	public void setGrafico(){
		
	}

	public GameObject afectar(){
		return null;
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
	
	public boolean colision(GameObject obj1, GameObject obj2){
		boolean b=false;
		 if(obj1.getAvanzable() && obj2.getAvanzable()){
			 obj1.afectar(new VisitanteConcretoProyectil());
		     b=true;
		 }
	      else	{
			 	b=!(r.intersects(obj1.getRectangulo()) 
			 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
			 	
			 	if (r.intersects(obj1.getRectangulo())){
			 			obj1.afectar(new VisitanteConcretoProyectil());
			 			this.afectar(obj1.getVisitante());
			 			victima=obj1;
			 			
			 	}
			 	
			 	if (r.intersects(obj2.getRectangulo())){
			 			obj2.afectar(new VisitanteConcretoProyectil());
			 			this.afectar(obj2.getVisitante());
			 			victima=obj2;
			 	}
		 }
		
		return b;
	}

	public abstract Visitante getVisitante();

	public abstract void afectar(Visitante v);
		
}
