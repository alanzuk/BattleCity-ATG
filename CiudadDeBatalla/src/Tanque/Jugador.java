package Tanque;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import General.GameObject;
import Proyectil.DisparoJugador;
import Proyectil.Proyectil;
import Visitor.Visitante;
import Visitor.VisitanteConcreto;

public class Jugador extends Tanque {
	private Nivel nivel;
	private Proyectil disparo;
	
	public Jugador(int x, int y){
		this.x=x;
		this.y=y;
		avanzable=false;
		destructible=true;
		direccion=1;
		nivel= new Nivel1();
		rectangulo=new Rectangle(x,y,tamanio_celda,tamanio_celda);
		vida=3;
		//Inicializo el JLabel
		path=nivel.getRutaGrafica();
		path_dinamico="1";
		grafico=new JLabel();
		refrescarPosicion();
	}
	
	//Comandos
	public void setDestructible(boolean b){
		destructible=b;
	}
	public Rectangle getRectangulo(){
		return rectangulo = new Rectangle((int)x,(int)y,tamanio_celda,tamanio_celda);
	}
	
	public int getVida(){
		return vida;
	}
	
	@Override
	public void plomo(){
		if(destructible){
			if(vida>0){
				vida--;		
				path_dinamico=""+1;
				direccion=1;
				x=7;
				y=17;
				refrescarPosicion();
			}
			else{	
				vida=0;
			}
		}
	}
	public void setNivel(){
		nivel=nivel.bajarNivel();
	}
	public Proyectil disparar() {
		float x_disp=x;
		float y_disp=y;
		nivel.disparar();
		switch(direccion){
				case (1):{
					x_disp=x+((tamanio_celda/100)/2);
					break;
				}
				case 2:{
					x_disp=x+((tamanio_celda/100)/2);
					y_disp=y-(tamanio_celda/100);
					break;
				}
				case 3:{
					y_disp=y+((tamanio_celda/100)/2);
					x_disp=x+(tamanio_celda/100);
					break; 
				}
				case 4:{
					x_disp=x+((tamanio_celda/100)/2);
					break;
				}
			}	
		
	if(nivel.getSimultaneo()>=0)
		disparo=nivel.getDisparoJugador(direccion,x_disp,y_disp,(int)nivel.getVelocidadDisp(),this);
		return disparo;
	}
	
	public boolean getAvanzable(){
		return avanzable;
	}
	
	public void subirNivel(){
		nivel=nivel.subirNivel();
		path=nivel.getRutaGrafica();	
	}
	
	public float getVelocidadMov(){
		return nivel.getVelocidadMov()/10;
	}
	
	public float getVelocidadDisp(){
		return nivel.getVelocidadDisp();
	}
	
	public void mover(int i){
		if(direccion==i){
			switch(i){
				case 1:{
						
						y+=-(nivel.getVelocidadMov()/10);
						path_dinamico=""+i;
						break;
				}
				case 2:{
						y+=(nivel.getVelocidadMov()/10);
						path_dinamico=""+i;
						break;
				}
				case 3:{
						x+=(nivel.getVelocidadMov()/10);
						path_dinamico=""+i;
						break; 
				}
				case 4:{
						x+=(-(nivel.getVelocidadMov()/10));
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
	}
	
	public void setDireccion(int i){
		direccion=i;
		path_dinamico=""+i;
		refrescarPosicion();
	}

	public void afectar(Visitante v){
		v.visit(this);	
	}
	
	public boolean colision(GameObject obj1, GameObject obj2){
		boolean b=false;
		 if(obj1.getAvanzable() && obj2.getAvanzable()){
			 obj1.afectar(new VisitanteConcreto());
		     b=true;
		 }
	      else	{
			 	b=!(this.getRectangulo().intersects(obj1.getRectangulo()) 
			 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
			 	
			 	if (this.getRectangulo().intersects(obj1.getRectangulo()))
			 			obj1.afectar(new VisitanteConcreto());
			 	if (this.getRectangulo().intersects(obj2.getRectangulo()))
			 			obj2.afectar(new VisitanteConcreto());
			  
		 }
		
		return b;
	}

	public Visitante getVisitante() {
		return nivel.getVisitante();
	}
	
	public int getSimultaneo(){
		return nivel.getSimultaneo();
	}
	
	public void setSimultaneo(){
		nivel.setDisparo();
	}

	public void addVida() {
		vida++;
	}

}
