package Aplicacion;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;
import General.GameObject;
import General.Logica;
import Proyectil.Proyectil;
import Tanque.Enemigo;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class GUI implements KeyListener,Runnable  {
	private Logica Juego;
	private JFrame ventana=new JFrame("	-=:Battle City :=-	");
	protected JPanel frame;
	private int tamanio_celda=32;
	//Label Puntaje
	private LinkedList<JLabel> lista_puntaje;
	private JLabel P_Dig1;
	private JLabel P_Dig2;
	private JLabel P_Dig3;
	private JLabel P_Dig4;
	private JLabel P_Dig5;
	private JPanel panel_puntos;
	private JLabel lblScore;
	//Label Tiempo
	private LinkedList<JLabel> lista_tiempo;
	private JPanel panel_tiempo;

	private JButton btnNuevaPartida;
	private JLabel [] enemigosRestantes;
	private int cant_enemigos;
	private JPanel panelEnemigosRestantes;
	private JPanel panelVida;
 
	
	/**
	 * Launch the application.
	 */
			public void run() {
				initialize();
				refreshGUI();
				this.ventana.setVisible(true);	
			}
		
	/**
	 * Create the application.
	 */
	public GUI(Logica Logic) {
		Juego=Logic;
	}

	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	
	private void initialize() {
		
		//Creacion ventana
		
		//Creacion del Frame (Es el que contiene los jugadores)
		frame=new JPanel();
		
		ventana.setForeground(Color.GRAY);
		ventana.setBounds(100,10,767,717); //560,580
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);	
		ventana.getContentPane().setBackground(Color.DARK_GRAY);
		
		//Registramos Oyente Teclas
		ventana.addKeyListener(this);

		frame.setLayout(null);	
		frame.setBackground(Color.BLACK);
		frame.setBounds(35,67, 19*tamanio_celda, 19*tamanio_celda);
		ventana.getContentPane().add(frame,BorderLayout.CENTER);
		
		lista_tiempo=new LinkedList<JLabel>();
		inicializarLista(lista_tiempo);
		armarDigitos(Juego.getTiempo(),lista_tiempo);
		
		panelVida = new JPanel();
		panelVida.setBackground(Color.DARK_GRAY);
		panelVida.setBounds(644, 429, 97, 179);
		ventana.getContentPane().add(panelVida);
		panelVida.setLayout(new GridLayout(1, 0, 0, 0));
		
		actualizarVida();
		
		panelEnemigosRestantes = new JPanel();
		panelEnemigosRestantes.setBackground(Color.DARK_GRAY);
		panelEnemigosRestantes.setBounds(644, 67, 68, 288);
		ventana.getContentPane().add(panelEnemigosRestantes);
		
		enemigosRestantes=new JLabel[16];
		cant_enemigos=Juego.getEnemigosRestantes();
		lista_puntaje=new LinkedList<JLabel>();

		inicializarLista(lista_puntaje);
		inicializarEnemigosRestantes();
		// Se agregan todos los labels inicializados 
		crearMapa();
		// Agregamos el Jugador
		frame.add(Juego.getJugador().getGrafico());
								
		//Label compuesta para el puntaje		
								
		panel_tiempo = new JPanel();
		panel_tiempo.setBounds(35, 36, 137, 38);
		ventana.getContentPane().add(panel_tiempo);
		panel_tiempo.setBackground(Color.DARK_GRAY);
		panel_tiempo.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblScore = new JLabel("");
		lblScore.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/SCORE.png").getImage().getScaledInstance(122,28, Image.SCALE_SMOOTH)));
		lblScore.repaint();
		lblScore.setBounds(432, 0, 173, 38);
		ventana.getContentPane().add(lblScore);		
		
		JLabel T_Dig1=new JLabel("");
		T_Dig1.setForeground(Color.WHITE);
		T_Dig1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		T_Dig1.setHorizontalAlignment(SwingConstants.CENTER);
		T_Dig1.setBounds(56, 26, 30, 30);
		panel_tiempo.add(T_Dig1);
								
		JLabel T_Dig2=new JLabel("");
		T_Dig2.setForeground(Color.WHITE);
		T_Dig2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		T_Dig2.setHorizontalAlignment(SwingConstants.CENTER);
		T_Dig2.setBounds(90, 26, 30, 30);
		panel_tiempo.add(T_Dig2);
										
		JLabel T_Dig3=new JLabel("");
		T_Dig3.setForeground(Color.WHITE);
		T_Dig3.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		T_Dig3.setHorizontalAlignment(SwingConstants.CENTER);
		T_Dig3.setBounds(119, 26, 30, 30);
		panel_tiempo.add(T_Dig3);
										
		JLabel T_Dig4=new JLabel("");
		T_Dig4.setForeground(Color.WHITE);
		T_Dig4.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		T_Dig4.setBounds(132, 26, 30, 30);
		panel_tiempo.add(T_Dig4);
												
		JLabel T_Dig5=new JLabel("");
		T_Dig5.setForeground(Color.WHITE);
		T_Dig5.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		T_Dig5.setHorizontalAlignment(SwingConstants.CENTER);
		T_Dig5.setBounds(144, 26, 30, 30);
		panel_tiempo.add(T_Dig5);
		lista_tiempo.addLast(T_Dig5);
		lista_tiempo.addLast(T_Dig4);
		lista_tiempo.addLast(T_Dig3);
		lista_tiempo.addLast(T_Dig2);
		lista_tiempo.addLast(T_Dig1);
														
		JLabel lblTDP = new JLabel("");
		lblTDP.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/TDP.png").getImage().getScaledInstance(94,30, Image.SCALE_SMOOTH)));
		lblTDP.repaint();
		lblTDP.setBounds(651, 632, 90, 43);
		ventana.getContentPane().add(lblTDP);
		
		panel_puntos = new JPanel();
		panel_puntos.setBounds(411, 36, 173, 38);
		ventana.getContentPane().add(panel_puntos);
		panel_puntos.setBackground(Color.DARK_GRAY);
		panel_puntos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		P_Dig1=new JLabel("");
		P_Dig1.setForeground(Color.WHITE);
		P_Dig1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		P_Dig1.setHorizontalAlignment(SwingConstants.CENTER);
		P_Dig1.setBounds(56, 26, 30, 30);
		panel_puntos.add(P_Dig1);
		
				P_Dig2=new JLabel("");
				P_Dig2.setForeground(Color.WHITE);
				P_Dig2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
				P_Dig2.setHorizontalAlignment(SwingConstants.CENTER);
				P_Dig2.setBounds(90, 26, 30, 30);
				panel_puntos.add(P_Dig2);
				
				P_Dig3=new JLabel("");
				P_Dig3.setForeground(Color.WHITE);
				P_Dig3.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
				P_Dig3.setHorizontalAlignment(SwingConstants.CENTER);
				P_Dig3.setBounds(119, 26, 30, 30);
				panel_puntos.add(P_Dig3);
				
						P_Dig4=new JLabel("");
						P_Dig4.setForeground(Color.WHITE);
						P_Dig4.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
						P_Dig4.setHorizontalAlignment(SwingConstants.CENTER);
						P_Dig4.setBounds(132, 26, 30, 30);
						panel_puntos.add(P_Dig4);
						
								P_Dig5=new JLabel("");
								P_Dig5.setForeground(Color.WHITE);
								P_Dig5.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
								P_Dig5.setHorizontalAlignment(SwingConstants.CENTER);
								P_Dig5.setBounds(144, 26, 30, 30);
								panel_puntos.add(P_Dig5);
								lista_puntaje.addLast(P_Dig5);
								lista_puntaje.addLast(P_Dig4);
								lista_puntaje.addLast(P_Dig3);
								lista_puntaje.addLast(P_Dig2);
								lista_puntaje.addLast(P_Dig1);
								
	    btnNuevaPartida = new JButton("Jugar de nuevo");
	    btnNuevaPartida.setBounds(208, 429, 313, 92);
	    ventana.getContentPane().add(btnNuevaPartida);
	    btnNuevaPartida.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    btnNuevaPartida.setVisible(false);
	    btnNuevaPartida.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    	    reiniciarJuego();
	    	    Juego.terminar();
	    		Launcher nuevoJuego=new Launcher();
	    	  } 
	    	} );
		
		
		
	}
	
	public void actualizarListaEnemigos(LinkedList<Enemigo> lista){
		for(Enemigo o : lista)
			frame.add(o.getGrafico());
	}
	
	
	public void refreshGUI(){	
		frame.repaint();
		armarDigitos(Juego.getPuntos(),lista_puntaje);
		actualizarVida();
		armarDigitos(Juego.getTiempo(),lista_tiempo);

	}

	private void armarDigitos(int entero,LinkedList<JLabel> lista) {
		int dig=0;
		for(JLabel j:lista){
			dig=entero%10;
			entero=entero/10;
			j.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/"+dig+".png").getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
			j.repaint();
		}
	}

	public void crearMapa(){
		GameObject[][] mapa=Juego.getMapa();
		
		for(int f=0;f<mapa.length;f++){
			for(int c=0;c<mapa.length;c++){	
				frame.add(mapa[f][c].getGrafico());
			}
		}
		actualizarListaEnemigos(Juego.getEnemigos());
	}

	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		case (KeyEvent.VK_UP):{ 
			playSound("mover");
			Juego.mover(1); 
			break;
			}
		case (KeyEvent.VK_DOWN):{
			playSound("mover");
			Juego.mover(2);
			break;
			}
		case (KeyEvent.VK_RIGHT):{
			playSound("mover");
			Juego.mover(3);
			break;
			}
		case (KeyEvent.VK_LEFT):{
			playSound("mover");
			Juego.mover(4);
			break;
			}
		case(KeyEvent.VK_F):{
			if(Juego.getSimultaneoJugador()>0){
				Proyectil p=Juego.getJugador().disparar();
				agregarDisparo(p);
				Juego.getProyectiles().addLast(p);
				playSound("disparo");
			}
			break;
		} 
		case(KeyEvent.VK_SPACE):{
			if(Juego.getSimultaneoJugador()>0){
				Proyectil p=Juego.getJugador().disparar();
				agregarDisparo(p);
				Juego.getProyectiles().addLast(p);
				playSound("disparo");
			}
			break;
		} 
		case(KeyEvent.VK_T):{
			gameOver();
			break;
		} 
		case(KeyEvent.VK_P):{
			Juego.pausa();
			break;
		} 
	
		case(KeyEvent.VK_W):{
			win();
			break;
		}
		}
		refreshGUI();
	}

	
	public void agregarDisparo(Proyectil p) {
		frame.add(p.getGrafico());
	}
	
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void add(Component comp){
		frame.add(comp);
	}

	public static synchronized void playSound(String ruta) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        File archivo=new File("src/Aplicacion/resources/Sonido/"+ruta+".au");
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(archivo);		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}

	public void remover(JLabel grafico) {
		frame.remove(grafico);
	}
	
	public void agregar(JLabel grafico) {
		frame.add(grafico);
	}
	
	private void inicializarLista(LinkedList<JLabel> lista) {
		for(JLabel j:lista){
			j.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/0.png").getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH)));
			j.repaint();
		}
	}
	
	private void inicializarEnemigosRestantes() {
		for(int i=0;i<enemigosRestantes.length;i++){
			enemigosRestantes[i]=new JLabel("");
			enemigosRestantes[i].setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/ER.png").getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
			panelEnemigosRestantes.add(enemigosRestantes[i]);
			enemigosRestantes[i].repaint();
		}
	}
	
	public void quitarEnemigoRestante() {
		if(cant_enemigos>=0){
			cant_enemigos--;
			enemigosRestantes[cant_enemigos].setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/null.png").getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
		}
		else{
			cant_enemigos=16;
			inicializarEnemigosRestantes();
		}
	}
	
	private void actualizarVida() {
		int cant_vidas=Juego.getVida();
		panelVida.removeAll();
		for(int i=0;i<cant_vidas;i++){
			JLabel v=new JLabel("");
			v.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/vida.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
			panelVida.add(v);
			v.repaint();
		}

	}

	public void gameOver(){
		ventana.getContentPane().removeAll();
		JLabel lblGameOver = new JLabel("");
		ventana.getContentPane().add(btnNuevaPartida);
		btnNuevaPartida.setVisible(true);
		lblGameOver.setBounds(150, 189, 388, 194);
		ventana.getContentPane().add(lblGameOver);
		ventana.getContentPane().setBackground(Color.BLACK);
		lblGameOver.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/GameOver.png").getImage().getScaledInstance(388,194, Image.SCALE_SMOOTH)));
		lblGameOver.setVisible(true);		
		ventana.repaint();
		
	}
	
	public void win() {
		ventana.getContentPane().removeAll();
		JLabel lblGameOver = new JLabel("");
		ventana.getContentPane().add(btnNuevaPartida);
		btnNuevaPartida.setVisible(true);
		lblGameOver.setBounds(140, 220, 486, 93);
		ventana.getContentPane().add(lblGameOver);
		ventana.getContentPane().setBackground(Color.BLACK);
		lblGameOver.setIcon(new ImageIcon(new ImageIcon("src/Aplicacion/resources/Numeros/Winner.png").getImage().getScaledInstance(486,93, Image.SCALE_SMOOTH)));
		lblGameOver.setVisible(true);		
		ventana.repaint();
	}
	
	public void reiniciarJuego(){
		btnNuevaPartida.setVisible(false);
		ventana.setVisible(false);		

	}

}
