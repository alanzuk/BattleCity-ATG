package Tanque;

import Proyectil.DisparoJugador;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;

public class Nivel2 extends Nivel {
	
	public Nivel2(){
		 simultaneo=1;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=2;
	}

	public Nivel subirNivel() {
		return new Nivel3();
	}public void disparar() {
		this.simultaneo--;		
	}
	public  int getSimultaneo(){
		return simultaneo;
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
