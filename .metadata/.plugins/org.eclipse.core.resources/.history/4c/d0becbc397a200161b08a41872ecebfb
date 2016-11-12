package Tanque;
import General.*;
import Proyectil.Proyectil;
import Visitor.Visitante;

public class Rapido extends Enemigo {
	
	protected final static String  ruta="Enemigo";
	
	public Rapido(int x,int y){
	     super();
		 recompensa=200;
		 velocidadMov=3;
		 velocidadDisp=2;
		 aguante=1;
		
	}
	
	public Proyectil disparar() {
		return null;
	}


	public void afectar(Visitante v){
		v.visit(this);
		
	}

	@Override
	public float getVelocidadMov() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getAvanzable() {
		// TODO Auto-generated method stub
		return false;
	}
}
