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

public class VisitanteConcretoProyectil implements Visitante {

	@Override
	public void visit(Agua a) {
		
	}

	@Override
	public void visit(Aguila a) {
		a.plomo();	
	}

	@Override
	public void visit(Bosque b) {
		
	}

	@Override
	public void visit(Cemento c) {
		c.plomo();
	}

	@Override
	public void visit(Ladrillo l) {
		l.plomo();
	}

	@Override
	public void visit(Vacio v) {

	}

	@Override
	public void visit(Enemigo e) {
		e.plomo();	
	}

	@Override
	public void visit(Jugador j) {
		j.plomo();	
	}

	@Override
	public void visit(DisparoEnemigo d) {
		d.plomo();	
	}

	
	public void visit(DisparoJugador d) {
		
	}

	@Override
	public void visit(Casco c) {
		
	}

	@Override
	public void visit(Estrella e) {
		
	}

	
	public void visit(Granada g) {
		
	}

	
	public void visit(Pala p) {
		
	}

	
	public void visit(Reloj r) {
		
	}

	
	public void visit(Vida v) {
		
	}

	@Override
	public void visit(Limite Limite) {
		
	}

}
