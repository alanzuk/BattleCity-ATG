package Obstaculo;


import Visitor.Visitante;
import Visitor.VisitanteConcretoObstaculo;
import General.GameObject;

public abstract class Obstaculo extends GameObject{
	protected  boolean avanzable;
	protected  boolean destruible;
	protected Visitante visitante=new VisitanteConcretoObstaculo();
	
	
	public Visitante getVisitante(){
		return new VisitanteConcretoObstaculo();
	}
	
	public boolean colision(GameObject o1,GameObject o2){
		return false;
	} 
	
}
