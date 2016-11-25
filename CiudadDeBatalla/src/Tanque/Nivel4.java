package Tanque;

import Proyectil.DisparoJugador;
import Proyectil.DisparoJugadorMaximo;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;
import Visitor.VisitanteConcretoProyectilMaximo;

public class Nivel4 extends Nivel {

	public Nivel4(){
		 simultaneo=3;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=3;
	}

	public Nivel subirNivel() {
		return this;
	}
	
	public void disparar() {
		this.simultaneo--;		
	}
	
	public void setDisparo(){
		this.simultaneo++;
	}
	public  int getSimultaneo(){
		return simultaneo;
	}
	public Visitante getVisitante(){
		return new VisitanteConcretoProyectilMaximo();
	}
	public  Proyectil  getDisparoJugador(int direccion, float x_disp,float y_disp, int velocidadDisp2, Jugador jugador) {
		return new DisparoJugadorMaximo(direccion,x_disp,y_disp,velocidadDisp2,jugador);
	}
}
