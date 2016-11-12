package Poderes;
import Visitor.Visitante;
import Visitor.VisitanteConcreto;
import Visitor.VisitanteConcretoPoder;
import General.GameObject;
import General.Logica;

public abstract class Powerup extends GameObject{
	private Visitante visitante=new VisitanteConcretoPoder();
	protected Logica juego;
	
	public void setLogica(Logica l){juego=l;}
	
	public boolean getAvanzable(){
		return true;
	}
	public boolean colision(GameObject obj1, GameObject obj2) {
			boolean b=false;
			 if(obj1.getAvanzable() && obj2.getAvanzable()){
				 obj1.afectar(visitante);
			     b=true;
			 }
		      else	{
				 	b=!(this.getRectangulo().intersects(obj1.getRectangulo()) 
				 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
				 	
				 	if (this.getRectangulo().intersects(obj1.getRectangulo())){
				 			obj1.afectar(new VisitanteConcreto());
				 			this.afectar(obj1.getVisitante());
				 			
				 	}
				 	
				 	if (this.getRectangulo().intersects(obj2.getRectangulo())){
				 			obj2.afectar(new VisitanteConcreto());
				 			this.afectar(obj2.getVisitante());
				 	}
				  
			 }
			
			return b;
		}
	}
	
