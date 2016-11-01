package Proyectil;

import General.GameObject;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;


public class DisparoEnemigo extends Proyectil {

	public DisparoEnemigo(int dir,int x,int y, int v) {
		super(dir,x,y);
		velocidad= v;
		// TODO Auto-generated constructor stub
	}

	
	public void afectar(Visitante v){
		v.visit(this);
		
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
			 	if(!obj1.getAvanzable()){
			 		if (r.intersects(obj1.getRectangulo())){
				 		obj1.afectar(new VisitanteConcretoProyectil());
				 		this.afectar(obj1.getVisitante());
			 		}
			 	}
		 		else{
		 			if(!obj2.getAvanzable())
		 				if (r.intersects(obj2.getRectangulo())){
		 					obj2.afectar(new VisitanteConcretoProyectil());
		 					this.afectar(obj2.getVisitante());
		 			}
		 		}
	      }
		
		return b;
	}
	public Visitante getVisitante() {
		return null;
	}
	public float getVelocidadMov() {
		return velocidad;
	}
}
