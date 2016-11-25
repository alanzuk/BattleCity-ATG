package General;

import java.text.DecimalFormat;
import java.util.*;

import Aplicacion.GUI;
import Aplicacion.Temporizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;

import Obstaculo.Agua;
import Obstaculo.Aguila;
import Obstaculo.Cemento;
import Obstaculo.Ladrillo;
import Obstaculo.Limite;
import Obstaculo.Obstaculo;
import Obstaculo.Vacio;
import Poderes.*;
import Proyectil.Proyectil;
import Tanque.Basico;
import Tanque.Blindado;
import Tanque.Enemigo;
import Tanque.Jugador;
import Tanque.Rapido;
import Tanque.Tanque;

public class Logica implements Runnable{
	
	/**
	 * Listas del Juego y atributos 
	 */
	
	protected LinkedList<Enemigo> misEnemigos;
	protected LinkedList<Obstaculo> misObstaculos;
	protected LinkedList<Proyectil> misProyectiles;
	protected LinkedList<Proyectil> misProyectilesEnemigos;
	
	protected LinkedList<LinkedList<Integer>> posicionesPowerup; 
	private int puntosParaSubir;
	protected int iteracion=0;
	protected volatile int oleada=1;
	protected Jugador miJugador;
	protected GameObject[][] mapa;
	protected int puntos;
	protected volatile  boolean seguir=true;
	protected volatile  boolean pausa=false;
	protected volatile  boolean power=false;
	protected Temporizador temporizador;
	protected volatile int enemigosRestantes;
	protected GUI ventana;
	
	public Logica(){
		
		misEnemigos= new LinkedList<Enemigo>();
		misObstaculos=new LinkedList<Obstaculo>();
		misProyectiles = new LinkedList<Proyectil>();
		misProyectilesEnemigos = new LinkedList<Proyectil>();
		posicionesPowerup=new LinkedList<LinkedList<Integer>>();
		//Inicializo las posiciones de los powerups
		puntosParaSubir=0;
		inicializarPosiciones();
		
		miJugador=new Jugador(7,17);
		enemigosRestantes=16;
		puntos=0;
		temporizador=new Temporizador(this);
    	
		/**
		 * Creacion del tablero de juego
		 */
		
		mapa=new GameObject[19][19];
		
		/**
		 * Se carga el archivo con el contenido del nivel. 
		 */
	
		File a= new File("src/Aplicacion/Nivel1.txt");
		
		try{
			FileReader fr= new FileReader(a);
			BufferedReader br= new BufferedReader(fr);
			String s;
						
			for(int f=0;f<19;f++){
				s=br.readLine();
				for(int c=0; c<19;c++){
					char tipo=s.charAt(c);
					crearGameObject(c,f,tipo); 
				}	
			}
			br.close();
		}
		catch(IOException e){e.printStackTrace();}
		
	}
	
	/**
	 * Realiza la creacion del GameObject concreto y lo coloca en el tablero.
	 */
	
	public void setGui(GUI v){
		ventana=v;
	}
	
	private void crearGameObject(int f, int c, char tipo) {
		switch(tipo){
			case 'P' :{
				mapa[f][c]=new Limite(f,c);
				misObstaculos.addLast((Obstaculo)mapa[f][c]);
				break;
			}
			case 'a' :{ mapa[f][c]=new Agua(f,c); 
					misObstaculos.addLast((Obstaculo)mapa[f][c]);
					break;
			}
			case 'l' :{ mapa[f][c]=new Ladrillo(f,c);
					misObstaculos.addLast((Obstaculo)mapa[f][c]);
					break;
			}
			case 'c' :{ mapa[f][c]=new Cemento(f,c);
					misObstaculos.addLast((Obstaculo)mapa[f][c]);
					break;
			}
			case ' ' :{ mapa[f][c]=new Vacio(f,c); 
					break;
			}
			case 'e' :{ mapa[f][c]=new Vacio(f,c); 
					break;
			}
			case 'x' :{ mapa[f][c]=new Aguila(f,c,this);
					misObstaculos.addLast((Obstaculo)mapa[f][c]);
					break;
			}
			case 'r' :{ mapa[f][c]=new Vacio(f,c); 
					break;
			}
			case 'j' :{ mapa[f][c]=new Vacio(f,c); 
					break;
			}
		}
	}

	/**
	 * Consultas utilizadas por la parte grafica del Juego 
	 */
	
	public Jugador getJugador(){
		return miJugador;
	}
	
	public GameObject[][] getMapa(){
		return mapa;
	}
	
	public LinkedList<Proyectil> getProyectiles(){
		return misProyectiles;
	}
	
	public LinkedList<Obstaculo> getObstaculos() {
		return misObstaculos;
	}
	
	public LinkedList<Enemigo> getEnemigos() {
		return  misEnemigos;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public int getVida(){
		return miJugador.getVida();
	}
	
	public int getEnemigosRestantes(){
		return enemigosRestantes;
	}
	
	/**
	 * Comandos ultilizados por la parte grafica del Juego 
	 */
	
	public  void terminar(){
		seguir=false;
		reiniciar();
	}
	
	public void mover(int i) {
		if(!pausa || power){
			boolean movete=true;
			if(caminoLibre(miJugador)){
				for(Enemigo e: misEnemigos){
					if(cuentaInter(e,miJugador) || cuentaInter(miJugador,e))
						movete=false;
				}
				if(movete)miJugador.mover(i);
				else{
						miJugador.mover((i%4)+1);
				}
			}
			else
				miJugador.setDireccion(i);
		}
	}
		
	public void gameOver(){
		pausa();
		ventana.gameOver();
	}
	
	
	/**
	 * Hilo de ejecucion de la parte logica . 
	 * Es el encargado de movimiento de enemigos , disparos y aparciones de powerups
	 */
	
	public  void run(){
		Thread hilo_temporizador=new Thread(temporizador);
		hilo_temporizador.start();
		reaparecer();
		while(seguir){
			
			if(!pausa){
			try{
				if(enemigosRestantes==0){
					ventana.win();
				}
				if(misEnemigos.isEmpty()) // Al llegar a la oleada 4 se avanza de nivel.
					nuevaOleada();				
					int t=temporizador.tiempo();
					//Revisar los disparos del jugador con el resto del mapa.
					if (t> 1.5){
						
						if(t%2==0) // Es para que tengan una pausa entre disparo y disparo
							dispararEnemigos();
						check_choqueJugador();
						check_disparosEnemigo();
						moverEnemigos();
					}
				
				//Se controla la lista de los disparos de el Jugador.
				check_choqueJugador();	
				check_disparosJugador();
				ventana.refreshGUI();
				
				Thread.sleep(40);
				
			}catch(InterruptedException  e){}
			catch(ConcurrentModificationException e1){}
		  }
	   }
	}
	
	
	/**
	 * Cuando la lista "misEnemgios" esta vacia , se hace la reaparicion de los enemigos.
	 */
	
	private void nuevaOleada() {
		switch(oleada){
		case (1):{
			oleada++;
			misEnemigos.addLast(new Basico(1,1));
			misEnemigos.addLast(new Basico(7,1));
			misEnemigos.addLast(new Basico(11,1));
			misEnemigos.addLast(new Basico(17,1));
			break;
		}case (2):{

			}
		case (3):{
			oleada++;
			misEnemigos.addLast(new Rapido(1,1));
			misEnemigos.addLast(new Rapido(7,1));
			misEnemigos.addLast(new Rapido(11,1));
			misEnemigos.addLast(new Rapido(17,1));
			break;
		}case (4):{
			oleada++;
			misEnemigos.addLast(new Blindado(1,1));
			misEnemigos.addLast(new Blindado(7,1));
			misEnemigos.addLast(new Blindado(11,1));
			misEnemigos.addLast(new Blindado(17,1));
			break;
		}	
		
		
		}
		ventana.actualizarListaEnemigos(misEnemigos);
	}
	
	public int getSimultaneoJugador(){
		return miJugador.getSimultaneo();
	}
	
	public void eliminarEnemigo(Enemigo g){
		
		enemigosRestantes--;

		chequearPower();
		chequearPuntos(g.getRecompensa());
		g.morir();
		misEnemigos.remove(g);
		g=null;
		ventana.quitarEnemigoRestante();
	}
	
	private void chequearPuntos(int p) {
		puntos+=p;
		puntosParaSubir+=p;
		if(puntosParaSubir<=3600)
		if (puntosParaSubir%1200==0)
			  miJugador.subirNivel();
	
	}

	private void chequearPower() {
		
		Random rnd=new Random();
		
		//Posicion aleatoria del powerup
		LinkedList<Integer> pos=posicionesPowerup.get(rnd.nextInt(5));
				
		switch (enemigosRestantes){
		
			case(12):{PonerPower(rnd.nextInt(6),pos.getFirst(),pos.getLast());
						break;
			}
			case(8):{PonerPower(rnd.nextInt(6),pos.getFirst(),pos.getLast());
					break;
			}
			case(4):{PonerPower(rnd.nextInt(6),pos.getFirst(),pos.getLast());
					break;
			}

		}
		
	}

	private void inicializarPosiciones(){
		
		LinkedList<Integer> par1=new LinkedList<Integer>();
		LinkedList<Integer> par2=new LinkedList<Integer>();
		LinkedList<Integer> par3=new LinkedList<Integer>();
		LinkedList<Integer> par4=new LinkedList<Integer>();
		LinkedList<Integer> par5=new LinkedList<Integer>();

		par1.add(7);
		par1.add(12);
		par2.add(1);
		par2.add(12);
		par3.add(17);
		par3.add(4);
		par4.add(14);
		par4.add(5);
		par5.add(1);
		par5.add(8);

		
		posicionesPowerup.add(par1);
		posicionesPowerup.add(par2);
		posicionesPowerup.add(par3);
		posicionesPowerup.add(par4);
		posicionesPowerup.add(par5);

		
	}
	
	public void eliminarProyectil(Proyectil g){		
		boolean seguir=true;
		for(Proyectil e : misProyectiles){
			if(e==g){
				if(!misProyectiles.isEmpty()){
					seguir=false;
					g.morir();
					g=null;	
					break;
				}
			}
			break;
		}
		
		if(seguir){			
			for(Proyectil e : misProyectilesEnemigos){
				if(e==g){
					if(!misProyectilesEnemigos.isEmpty()){
						g.morir();
						g=null;	
						break;
					}
				}
				break;
			}
		}
	}
	
	
	public void eliminarObstaculo(GameObject g){
		int x=(int)g.getX();
		int y=(int)g.getY();
		if(!misObstaculos.isEmpty()){
			Obstaculo o=new Vacio(x,y);
			misObstaculos.set(misObstaculos.indexOf(g),o);
			mapa[x][y]=o;
		}
	}

	//Metodos privados que utilizan listas
	
	public void check_disparosJugador(){
		try{
		Iterator<Proyectil> itProyectil=misProyectiles.iterator();
		 Proyectil p;
		 while(itProyectil.hasNext()){
			 
			 p=itProyectil.next();
			
			 for(Enemigo e:misEnemigos){
				if(cuentaInter(e, p)){
					p.afectar(e.getVisitante());
			 		e.afectar(p.getVisitante());
				}
			 }
			 
			check_choqueEntreDisparos(p,misProyectilesEnemigos);
			 
			choqueDisparos(p);

			 if(p.getVida()>0){
				 //if(caminoLibre(p)){
					 p.mover(p.getDireccion());
			 }
			 else{
				 if(p.getVida()==0){//Si la vida es igual a 0 entonces debo eliminar algo 
					eliminarProyectil(p);
				 	itProyectil.remove();
				 	//ELIMINO OBSTACULOS 
				 	Iterator<Obstaculo> itObstaculo=misObstaculos.iterator();
				 	GameObject o;
				 	while(itObstaculo.hasNext()){
				 		o=itObstaculo.next();
				 		if(o.getVida()==0){
				 			eliminarObstaculo(o);
				 		}
				 	}
				 	//ELIMINO ENEMIGO 
				 	Iterator<Enemigo> itEnemigo=misEnemigos.iterator();
				 	Enemigo e;
				 	while(itEnemigo.hasNext()){
				 		e=itEnemigo.next();
				 		if(e.getVida()==0){
				 			eliminarEnemigo(e);
				 			itEnemigo.remove();
				 			break;
				 		}
				 	}
				 } 
			 
			 }
		}
		}
		catch(Exception e){}
	}
	
    private void check_choqueEntreDisparos(Proyectil p,LinkedList<Proyectil> lista) {
    	
    	for(Proyectil disp:lista){
    		if(p.getDestructible() && disp.getDestructible()){
    		if(cuentaInter(p,disp) || cuentaInterDisparos(p,disp)){
    			p.afectar(disp.getVisitante());
    			disp.afectar(p.getVisitante());
    		}
    		}
    	}
		
	}

	public void subirNivel(){
    		miJugador.subirNivel();
    }

	public void pausa() {
		if(pausa)
			pausa=false;
		else
			pausa=true;
	}
	
	/**
	 * Metodos que se encargan de los movimientos de los enemigos y proyectiles
	 * Asi tambien de las colisiones entre las listas y el tablero.
	 */
	
	private void dispararEnemigos(){
		for(Enemigo e: misEnemigos){
			if(e.getSimultaneo()==1){
				Proyectil pe=e.disparar();
				if(pe!=null){
					 misProyectilesEnemigos.addLast(pe);
					 ventana.agregarDisparo(pe);
				}
		    }
	   }
	}
	public void check_choqueJugador(){
		
		for(Enemigo disp: misEnemigos){
    		if(cuentaInter(miJugador,disp) || cuentaInterDisparos(disp,miJugador)){
    			disp.marchaAtraz();
    		
    		}
    	}
		
		
	}
	public void check_disparosEnemigo(){
		if(!power){
		Iterator<Proyectil> itProyectil=misProyectilesEnemigos.iterator();
		 Proyectil p;
		 while(itProyectil.hasNext()){
			 
			 p=itProyectil.next();
							 
				if(cuentaInter(miJugador, p)){
					p.afectar(miJugador.getVisitante());
					miJugador.afectar(p.getVisitante());
					reaparecer();
				}
				
				check_choqueEntreDisparos(p,misProyectiles);
				choqueDisparos(p);
				
			 if(p.getVida()>0){
					 p.mover(p.getDireccion());
			 }
			 else{
				 if(p.getVida()==0){//Si la vida es igual a 0 entonces debo eliminar algo 
					eliminarProyectil(p);
				 	itProyectil.remove();
				 	//ELIMINO OBSTACULOS 
				 	Iterator<Obstaculo> itObstaculo=misObstaculos.iterator();
				 	GameObject o;
				 	while(itObstaculo.hasNext()){
				 		o=itObstaculo.next();
				 		if(o.getVida()==0){
				 			eliminarObstaculo(o);
				 		}
				 	}
				 } 
			 }
		}
		}
		else{
			Iterator<Proyectil> itProyectil=misProyectilesEnemigos.iterator();
			Proyectil p;
			 while(itProyectil.hasNext()){ 
				p=itProyectil.next();
				check_choqueEntreDisparos(p,misProyectiles);
				}
			}
	}

	private void reaparecer() {
		if(miJugador.getVida()==0){
    		pausa();
    		ventana.gameOver();
    	}
		miJugador.setNivel();
		puntosParaSubir=0;
		Powerup c=new Casco(7,12);
		c.setLogica(this);
    	Thread y= new Thread((Runnable)c);
    	y.start();
	}

	private void moverEnemigos() {
		for(Enemigo e:misEnemigos){
			choqueEnemigo(e);
			if(cuentaInter(miJugador,e))
				e.marchaAtraz();
			 Random rnd= new Random();
			 if(caminoLibre(e)){
				e.mover(e.getDireccion());
			 }
			 else{
		    	 int nuevaDireccion= rnd.nextInt(4)+1;
			     while(e.getDireccion()==nuevaDireccion)
			    	 nuevaDireccion= rnd.nextInt(4)+1;
				 e.setDireccion(nuevaDireccion);
			}
			 choqueEnemigo(e);	 
			 e.refrescarPosicion();
		 }
	}
	
	private boolean cuentaInter(GameObject t,GameObject t1){
		float x1=t.getX();
		float y1=t.getY();
		float x2=t1.getX();
		float y2=t1.getY();
		boolean seChocan=false;
		float resX=x1-x2;
		float resY=y1-y2;
		if(1>resX && -1<resX && 1>resY && -1<resY ){
			seChocan=true;
		}
		
		return seChocan;
	}
	
	private boolean cuentaInterDisparos(GameObject t,GameObject t1){
		float x1=t.getX();
		float y1=t.getY();
		float x2=t1.getX();
		float y2=t1.getY();
		boolean seChocan=false;
		float resX=x1-x2;
		float resY=y1-y2;
		if(0.5f>resX && -0.5f<resX && 0.5f>resY && -0.5f<resY ){
			seChocan=true;
		}
		return seChocan;
	}
	
	private boolean choqueDisparos(Proyectil p){
		for(Obstaculo o:misObstaculos){
			if(!o.getAvanzable()){
				if(cuentaInterDisparos(p,o)){
					o.afectar(p.getVisitante());
					p.afectar(o.getVisitante());
					return true;
				}
			}
			
		}
		return false;
	}
	private boolean choqueEnemigo(Tanque v){
		
		for(Enemigo e:misEnemigos){
			if(e!=v){
				if(cuentaInter(e,v)){
					int choqueFrente=v.getDireccion()+e.getDireccion();
					
					if(choqueFrente==3 || choqueFrente==7){
						v.afectar(e.getVisitante());
						e.afectar(v.getVisitante());
						return true;
					}
					else{
						e.afectar(v.getVisitante());
						return true;
				}
			}
		 }
			else 
				return false;
		}
		return false;
	}
	
	private boolean caminoLibre(Movible t) {
		try{
		int i=t.getDireccion();
		t.refrescarPosicion();
		float x=t.getX();
		float y=t.getY();
		int xI;
		int yI;
		boolean b=true;
		switch(i){
			case 1:{
				y-=0.2;
				xI=redondeoAbajo(x);
				yI=redondeoAbajo(y);
						t.refrescarPosicion();				 
				 		GameObject obj1=mapa[xI][yI];
				xI=redondeoArriba(x);
				yI=redondeoAbajo(y);
						GameObject obj2=mapa[xI][yI];
				 		b= t.colision(obj1, obj2);			
				 break;
			}
			case 2:{
				y+=0.2;
				xI=redondeoAbajo(x);
				yI=redondeoArriba(y);
			    		t.refrescarPosicion();	
				 		GameObject obj1=mapa[xI][yI];
				xI=redondeoArriba(x);
				yI=redondeoArriba(y);
						GameObject obj2=mapa[xI][yI];
				 		b= t.colision(obj1, obj2);
				 break;

			}
			case 3:{
				x+=0.2;
				xI=redondeoArriba(x);
				yI=redondeoAbajo(y);
							t.refrescarPosicion();				 
				 			GameObject obj1=mapa[xI][yI];
				xI=redondeoArriba(x);
				yI=redondeoArriba(y);
							GameObject obj2=mapa[xI][yI];
							b= t.colision(obj1, obj2);	 
				 break;
			}
			case 4:{
				x-=0.2;
				xI=redondeoAbajo(x);
				yI=redondeoArriba(y);
						t.refrescarPosicion();				 
				 		GameObject obj1=mapa[xI][yI];
				xI=redondeoAbajo(x);
				yI=redondeoAbajo(y);
				 		GameObject obj2=mapa[xI][yI];
				 		b= t.colision(obj1, obj2) ;
				break;
			}
		}
		return b;
		}
		catch(Exception e){
			return false;
		}
	}

	private int redondeoAbajo(float f){
		DecimalFormat df = new DecimalFormat("##");	
		df.setRoundingMode(RoundingMode.DOWN);
		String xIp;
		xIp=df.format(f); //down
		return Integer.parseInt(xIp);
		
	}
	
	private int redondeoArriba(float f){
		DecimalFormat dfY = new DecimalFormat("##");
		dfY.setRoundingMode(RoundingMode.CEILING);
		String yIp;
		yIp=dfY.format(f); //down 
		return Integer.parseInt(yIp);
	}
	
	
	/**
	 * POWERUPS
	 * Implementacion de los efectos y la aparicion.
	 * @param y 
	 * @param x 
	 */
	
	public  void Granada(int x, int y){
		for(Enemigo e : misEnemigos){
			 enemigosRestantes--;
			ventana.quitarEnemigoRestante();
			chequearPuntos(e.getRecompensa());
			e.morir();
		}
      
	  misEnemigos.removeAll(misEnemigos);
   	   mapa[x][y]=new Vacio(x,y);
    }
   
    public void Vida(int x,int y) {
    	miJugador.addVida();
    	mapa[x][y]=new Vacio(x,y);
	}
    public  void Estrella(int x, int y){
    	miJugador.subirNivel();
    	mapa[x][y]=new Vacio(x,y);
		
    }
    public void Casco(int x, int y){
    	Thread h= new Thread((Casco)mapa[x][y]);
    	h.start();
    	mapa[x][y]=new Vacio(x,y);
    
    }
    public void Pala(int x, int y){
    	Thread h= new Thread((Pala)mapa[x][y]);
    	h.start();
    	mapa[x][y]=new Vacio(x,y);
    }
    public void Reloj(int x, int y){
    	Thread h= new Thread((Reloj)mapa[x][y]);
    	power=true;
    	pausa();
    	h.start();
    	mapa[x][y]=new Vacio(x,y);
    }

	
	public void PonerPower(int nextInt,int x, int y) {
		Powerup c=null;;
		switch(nextInt){
		case(0):{
			c=new Pala(x,y);
			mapa[x][y]=c;
				break;
		}
		case(1):{
			c=new Casco(x,y);
			mapa[x][y]=c;
			break;
	}
		case(2):{
			c=new Vida(x,y);
			mapa[x][y]=c;
			break;
	}
		case(3):{
			c=new Granada(x,y);
			mapa[x][y]=c;
			break;
	}
		case(4):{
			c=new Reloj(x,y);
			mapa[x][y]=c;
			break;
	}
		case(5):{
			c=new Estrella(x,y);
			mapa[x][y]=c;
			break;
	}
		
		}
		ventana.add(c.getGrafico());
		c.setLogica(this);
 		//return c.getGrafico();
	}

	public GUI getGui() {
		return ventana;
	}

	public int getTiempo() {
		return temporizador.tiempo();
	}

	public void setPower() {
		power=false;
		
	}

	public void reiniciar() {
		misEnemigos.removeAll(misEnemigos);
		misObstaculos.removeAll(misObstaculos);
		misProyectiles.removeAll(misProyectiles);
		misProyectilesEnemigos.removeAll(misProyectilesEnemigos);
		enemigosRestantes=16;
		puntos=0;
		mapa=null;
	}


}
