package Poderes;

import Visitor.Visitante;

public class Pala {

	
	public void afectar(Visitante v){
		v.visit(this);
		
	}
}
