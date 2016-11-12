package Tanque;

import Proyectil.DisparoJugador;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;

public abstract class Nivel {
	protected int simultaneo;
	protected String rutaGrafica;
	protected float velocidadMov;
	protected float velocidadDisp;	
	
	

	public String getRutaGrafica(){
		return rutaGrafica;
	}
	
	public int getSimultaneo(){
		return simultaneo;
	}

	public float getVelocidadMov(){
		return velocidadMov;
	}
	
	public float getVelocidadDisp(){
		return velocidadDisp;
	}

	public abstract Nivel subirNivel();

	public abstract Visitante getVisitante();
	public abstract void disparar();
	
	public abstract void setDisparo();

	public abstract Proyectil getDisparoJugador(int direccion, float x_disp,
			float y_disp, int velocidadDisp2, Jugador jugador);
}

