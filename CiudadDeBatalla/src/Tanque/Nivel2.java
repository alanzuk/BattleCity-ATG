package Tanque;

public class Nivel2 extends Nivel {

	
	public Nivel2(){
		 simultaneo=1;
		 rutaGrafica="Jugador";
		 velocidadMov=4;
		 velocidadDisp=2;
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
	public Nivel subirNivel() {
		return new Nivel3();
	}
	public Nivel bajarNivel(){
		return new Nivel2();
	}
}
