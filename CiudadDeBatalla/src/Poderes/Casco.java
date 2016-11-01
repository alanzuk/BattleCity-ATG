package Poderes;

import Visitor.Visitante;

public class Casco extends Powerup {
	public Casco(){}
	
	@Override
	public void efecto() {
		 
	}

	public void afectar(Visitante v){
		v.visit(this);
		
	}
}
