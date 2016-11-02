package Proyectil;

import General.GameObject;
import Tanque.Tanque;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectilEnemigo;

public class DisparoEnemigo extends Proyectil {
	protected Visitante visitante=new VisitanteConcretoProyectilEnemigo();

	public DisparoEnemigo(int dir,float x,float y, int v,Tanque t) {
		super(dir,x,y,t);
		velocidad= v;
	}

	public void afectar(Visitante v){
		v.visit(this);
	}
	
	public boolean colision(GameObject obj1, GameObject obj2){
		boolean b=false;
		 if(obj1.getAvanzable() && obj2.getAvanzable()){
			 obj1.afectar(visitante);
		     b=true;
		 }
	      else	{
			 	b=!(r.intersects(obj1.getRectangulo()) 
			 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
			 	if(!obj1.getAvanzable()){
			 		if (r.intersects(obj1.getRectangulo())){
				 		obj1.afectar(visitante);
				 		this.afectar(obj1.getVisitante());
			 		}
			 	}
		 		else{
		 			if(!obj2.getAvanzable())
		 				if (r.intersects(obj2.getRectangulo())){
		 					obj2.afectar(visitante);
		 					this.afectar(obj2.getVisitante());
		 			}
		 		}
	      }
		
		return b;
	}
	
	
	public Visitante getVisitante() {
		return visitante;
	}
	
	public float getVelocidadMov() {
		return velocidad;
	}
}
