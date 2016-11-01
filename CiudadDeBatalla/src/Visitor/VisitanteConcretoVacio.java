package Visitor;

import Obstaculo.Agua;
import Obstaculo.Aguila;
import Obstaculo.Bosque;
import Obstaculo.Cemento;
import Obstaculo.Ladrillo;
import Obstaculo.Limite;
import Obstaculo.Vacio;
import Poderes.Casco;
import Poderes.Estrella;
import Poderes.Granada;
import Poderes.Pala;
import Poderes.Reloj;
import Poderes.Vida;
import Proyectil.DisparoEnemigo;
import Proyectil.DisparoJugador;
import Tanque.Enemigo;
import Tanque.Jugador;

public class VisitanteConcretoVacio implements Visitante {

	@Override
	public void visit(Agua a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Aguila a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Bosque b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Cemento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Ladrillo l) {
		
	}

	@Override
	public void visit(Vacio v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Enemigo e) {

	}

	@Override
	public void visit(Jugador j) {
		// TODO Auto-generated method stub
		//System.out.println("me choque un jugador");
	}

	@Override
	public void visit(DisparoEnemigo d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DisparoJugador d) {
	}

	@Override
	public void visit(Casco c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Estrella e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Granada g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Pala p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Reloj r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Vida v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Limite limite) {
		// TODO Auto-generated method stub
		
	}

}
