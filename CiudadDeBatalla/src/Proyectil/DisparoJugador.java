package Proyectil;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import General.GameObject;
import Tanque.Tanque;
import Visitor.Visitante;
import Visitor.VisitanteConcretoProyectil;

public class DisparoJugador extends Proyectil {
	protected Visitante visitante=new VisitanteConcretoProyectil();

	public DisparoJugador(int dir,float x,float y,int v,Tanque t) {
		super(dir,x,y,t);
		velocidad= v;
	}

	public void afectar(Visitante v){
		v.visit(this);
 		playExplosion();
	}
	
	public boolean colision(GameObject obj1, GameObject obj2){
		boolean b=false;
		 if(obj1.getAvanzable() && obj2.getAvanzable()){
			 obj1.afectar(new VisitanteConcretoProyectil());
		     b=true;
		 }
	      else	{
			 	b=!(r.intersects(obj1.getRectangulo()) 
			 			&& this.getRectangulo().intersects(obj2.getRectangulo())) ;
			 	if(!obj1.getAvanzable()){
			 		if (r.intersects(obj1.getRectangulo())){
				 		obj1.afectar(new VisitanteConcretoProyectil());
				 		this.afectar(obj1.getVisitante());
			 		}
			 	}
		 		else{
		 			if(!obj2.getAvanzable())
		 				if (r.intersects(obj2.getRectangulo())){
		 					obj2.afectar(new VisitanteConcretoProyectil());
		 					this.afectar(obj2.getVisitante());
		 			}
		 		}
	      }
		
		return b;
	}

	public Visitante getVisitante() {
		return visitante;
	}

	public void setDireccion(int i) {
		direccion=i;
	}

	public float getVelocidadMov() {
		return velocidad/10;
	}
	
	public static synchronized void playExplosion() {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        File archivo=new File("src/Aplicacion/resources/Sonido/explosion.au");
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(archivo);		        
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
	
}
