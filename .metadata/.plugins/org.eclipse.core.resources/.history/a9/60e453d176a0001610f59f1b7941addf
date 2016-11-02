package Tanque;

import Proyectil.Proyectil;
import Visitor.Visitante;

public class Basico extends Enemigo {
	protected final static String ruta="Enemigo";

	public Basico(int x,int y){
		 super();
		 recompensa=100;
		 velocidadMov=1;
		 velocidadDisp=1;
		 aguante=1;
	}
	
	public Proyectil disparar() {
		return null;
	}
	
	public void afectar(Visitante v){
		v.visit(this);
		
	}

	public float getVelocidadMov() {
		return 0;
	}

	public boolean getAvanzable() {
		return false;
	}

	public Visitante getVisitante() {
		return null;
	}
	
}
