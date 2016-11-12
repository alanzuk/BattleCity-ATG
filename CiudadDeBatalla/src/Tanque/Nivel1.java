package Tanque;

import Proyectil.DisparoJugador;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;

public class Nivel1 extends Nivel {
	
	public Nivel1(){
		 simultaneo=1;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=1;
	}

	public Nivel subirNivel() {
		return new Nivel2();
	}
	public void disparar() {
		this.simultaneo--;		
	}
	
	public void setDisparo(){
		this.simultaneo++;
	}
	public Nivel bajarNivel(){
		return this;
	}
	public Visitante getVisitante(){
		return new VisitanteConcretoProyectil();
	}
	public  Proyectil  getDisparoJugador(int direccion, float x_disp,float y_disp, int velocidadDisp2, Jugador jugador) {
		return new DisparoJugador(direccion,x_disp,y_disp,velocidadDisp2,jugador);
	}
}
