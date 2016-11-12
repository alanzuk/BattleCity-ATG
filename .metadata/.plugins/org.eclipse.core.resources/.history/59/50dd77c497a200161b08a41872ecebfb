package Tanque;
import General.*;
import Proyectil.Proyectil;
import Visitor.Visitante;

public class Poder extends Enemigo
{
	protected final static String  ruta="E_Poder";

	public Poder(int x,int y){
		 super();
		 recompensa=300;
		 velocidadMov=2;
		 velocidadDisp=3;
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
