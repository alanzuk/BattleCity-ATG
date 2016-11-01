package Proyectil;

import Visitor.Visitante;


public class DisparoEnemigo extends Proyectil {

	public DisparoEnemigo(int dir,int x,int y, int v) {
		super(dir,x,y);
		velocidad= v;
		// TODO Auto-generated constructor stub
	}

	
	public void afectar(Visitante v){
		v.visit(this);
		
	}

	public Visitante getVisitante() {
		return null;
	}
	public float getVelocidadMov() {
		return velocidad;
	}
}
