package Poderes;
import Visitor.VisitanteConcreto;
import General.GameObject;

public abstract class Powerup extends GameObject{
	private GameObject ubicacion;
	
	
	public GameObject getUbicacion(){
		return ubicacion;
	}

	public boolean getAvanzable(){
		return false;
	}
	public boolean colision(GameObject obj1, GameObject obj2) {
			boolean b=false;
			 if(obj1.getAvanzable() && obj2.getAvanzable()){
				 obj1.afectar(new VisitanteConcreto());
			     b=true;
			 }
		      else	{
				 	b=!(this.getRectangulo().intersects(obj1.getRectangulo()) 
				 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
				 	
				 	if (this.getRectangulo().intersects(obj1.getRectangulo()))
				 			obj1.afectar(new VisitanteConcreto());
				 	if (this.getRectangulo().intersects(obj2.getRectangulo()))
				 			obj2.afectar(new VisitanteConcreto());
				  
			 }
			
			return b;
		}
	}
	
