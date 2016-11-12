package Tanque;

public class Nivel1 extends Nivel {

	
	public Nivel1(){
		 simultaneo=1;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=1;
		 aguante=1;
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

	@Override
	public Nivel subirNivel() {
		return new Nivel2();
	}
	public Nivel bajarNivel(){
		return this;
	}
}
