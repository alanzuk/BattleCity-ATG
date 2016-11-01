package Tanque;

public class Nivel3 extends Nivel {

	
	public Nivel3(){
		 simultaneo=2;
		 rutaGrafica="Jugador";
		 velocidadMov=2;
		 velocidadDisp=2;
		 aguante=2;
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
		return new Nivel4();
	}
	public Nivel bajarNivel(){
		return new Nivel2();
	}
}
