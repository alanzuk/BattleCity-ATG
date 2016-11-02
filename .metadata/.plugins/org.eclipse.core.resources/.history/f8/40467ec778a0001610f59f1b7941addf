package Tanque;

import java.awt.Rectangle;
import General.*;
import Proyectil.Proyectil;

public abstract class Tanque extends GameObject implements Movible {
	//ATRIBUTOS
	protected String rutaGrafica;
	protected float velocidadMov;
	protected float velocidadDisp;
	protected int aguante;
	protected int direccion;
	//protected int x,y;
	

	public abstract Proyectil disparar();
			
	public int getDireccion(){
		return direccion;
	}
	
	public abstract float getVelocidadMov();
	
	public void setDireccion(int d){
		direccion=d;
	}
	
	public void mover(int i){
		if(direccion==i){
			switch(i){
				case 1:{
						
						y+=-(velocidadMov/10);
						path_dinamico=""+i;
						break;
				}
				case 2:{
						y+=(velocidadMov/10);
						path_dinamico=""+i;
						break;
				}
				case 3:{
						x+=(velocidadMov/10);
						path_dinamico=""+i;
						break; 
				}
				case 4:{
						x+=(-(velocidadMov/10));
						path_dinamico=""+i;
						break;
				}
			}	
		}
		else{
			direccion=i;
			path_dinamico=""+i;
		}
		refrescarPosicion();
		rectangulo=new Rectangle((int)x,(int)y,tamanio_celda,tamanio_celda);
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

}
