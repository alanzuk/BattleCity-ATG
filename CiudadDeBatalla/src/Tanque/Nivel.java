package Tanque;

public abstract class Nivel {
	protected int simultaneo;
	protected String rutaGrafica;
	protected float velocidadMov;
	protected float velocidadDisp;
	protected int aguante;
	
	public abstract int getSimultaneo();

	
	public abstract String getRutaGrafica();


	public abstract float getVelocidadMov();
	public abstract float getVelocidadDisp();
	public abstract int getVida();


	public abstract Nivel subirNivel();
	

}

