package Tanque;

import Proyectil.DisparoJugador;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;

public class Nivel3 extends Nivel {

	public Nivel3(){
		 simultaneo=2;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=2;
	}

	public Nivel subirNivel() {
		return new Nivel4();
	}
	public void disparar() {
		this.simultaneo--;		
	}
	
	public void setDisparo(){
		this.simultaneo++;
	}
	
	public Visitante getVisitante(){
		return new VisitanteConcretoProyectil();
	}
	public  Proyectil  getDisparoJugador(int direccion, float x_disp,float y_disp, int velocidadDisp2, Jugador jugador) {
		return new DisparoJugador(direccion,x_disp,y_disp,velocidadDisp2,jugador);
	}
}
