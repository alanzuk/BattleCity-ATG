package Tanque;

public class Nivel4 extends Nivel {

	
	public Nivel4(){
		 simultaneo=3;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=3;
		 aguante=4;
	}

	@Override
	public int getSimultaneo() {
		return simultaneo;
	}

	@Override
	public String getRutaGrafica() {
		return rutaGrafica;
	}

	@Override
	public float getVelocidadMov() {
		return velocidadMov;
	}

	@Override
	public float getVelocidadDisp() {
		return velocidadDisp;
	}

	@Override
	public int getVida() {
		return aguante;
	}
	public Nivel subirNivel() {
		//no puedo 
		return this;
	}
	public Nivel bajarNivel(){
		return new Nivel3();
	}
}
