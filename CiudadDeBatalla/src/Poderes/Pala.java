package Poderes;

import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JLabel;

import General.GameObject;
import General.Logica;
import Obstaculo.Cemento;
import Obstaculo.Ladrillo;
import Obstaculo.Obstaculo;
import Obstaculo.Vacio;
import Visitor.Visitante;
import Visitor.VisitanteConcretoPoder;

public class Pala extends Powerup implements Runnable{

	public Pala(int x, int y) {
		this.x=x;
		this.y=y;
		grafico= new JLabel();
		path="PU_PALA";
		path_dinamico="";
		rectangulo=new Rectangle((int)x,(int)y,tamanio_celda,tamanio_celda);
	}
	public void afectar(Visitante v){
		v.visit(this);
		
	}
	public Rectangle getRectangulo() {
	
		return rectangulo;
	}

	@Override
	public Visitante getVisitante() {
		
		return new VisitanteConcretoPoder();
	}
	
	
	
	@Override
	public void run() {	
		double duracion=juego.getTiempo();
		double efecto= (duracion+5);
		ponerBarrera();
		while(duracion<efecto){
			duracion+=0.05;
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {}
		}
		sacarBarrera();		
		Thread.interrupted();
	}
		
		
	
			
	private void sacarBarrera() {
		GameObject[][] mapa=juego.getMapa();
		Ladrillo l1=new Ladrillo(8,17);
		Ladrillo l2=new Ladrillo(8,16);
		Ladrillo l3=new Ladrillo(9,16);
		Ladrillo l4=new Ladrillo(10,16);
		Ladrillo l5=new Ladrillo(10,17);
		Obstaculo cambio;
		cambio=buscarEnLista(8,17);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l1);
		juego.getGui().remover(mapa[8][17].getGrafico());
		mapa[8][17]=l1;
		juego.getGui().agregar(mapa[8][17].getGrafico());
		cambio=buscarEnLista(8,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l2);
		juego.getGui().remover(mapa[8][16].getGrafico());
		mapa[8][16]=l2;
		juego.getGui().agregar(mapa[8][16].getGrafico());
		cambio=buscarEnLista(9,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l3);
		juego.getGui().remover(mapa[9][16].getGrafico());
		mapa[9][16]=l3;
		juego.getGui().agregar(mapa[9][16].getGrafico());
		cambio=buscarEnLista(10,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l4);
		juego.getGui().remover(mapa[10][16].getGrafico());
		mapa[10][16]=l4;
		juego.getGui().agregar(mapa[10][16].getGrafico());
		cambio=buscarEnLista(10,17);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l5);
		juego.getGui().remover(mapa[10][17].getGrafico());
		mapa[10][17]=l5;
		juego.getGui().agregar(mapa[10][17].getGrafico());
		
	}
	
	private Obstaculo buscarEnLista(int x,int y){
		for(Obstaculo o :juego.getObstaculos()){
			if(o.getX()==x && o.getY()==y){
				return o;
			}
		}
		
		return null;
	}

	private void ponerBarrera() {
		GameObject[][] mapa=juego.getMapa();
		Cemento l1=new Cemento(8,17);
		Cemento l2=new Cemento(8,16);
		Cemento l3=new Cemento(9,16);
		Cemento l4=new Cemento(10,16);
		Cemento l5=new Cemento(10,17);
		Obstaculo cambio;
		cambio=buscarEnLista(8,17);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l1);
		juego.getGui().remover(mapa[8][17].getGrafico());
		mapa[8][17]=l1;
		juego.getGui().agregar(mapa[8][17].getGrafico());
		cambio=buscarEnLista(8,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l2);
		juego.getGui().remover(mapa[8][16].getGrafico());
		mapa[8][16]=l2;
		juego.getGui().agregar(mapa[8][16].getGrafico());
		cambio=buscarEnLista(9,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l3);
		juego.getGui().remover(mapa[9][16].getGrafico());
		mapa[9][16]=l3;
		juego.getGui().agregar(mapa[9][16].getGrafico());
		cambio=buscarEnLista(10,16);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l4);
		juego.getGui().remover(mapa[10][16].getGrafico());
		mapa[10][16]=l4;
		juego.getGui().agregar(mapa[10][16].getGrafico());
		cambio=buscarEnLista(10,17);
		juego.getObstaculos().set(juego.getObstaculos().indexOf(cambio),l5);
		juego.getGui().remover(mapa[10][17].getGrafico());
		mapa[10][17]=l5;
		juego.getGui().agregar(mapa[10][17].getGrafico());

	}
	
	public void efecto() {
		juego.Pala((int)x,(int) y);
		
	}
	
}
