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

public class VisitanteConcretoObstaculo implements Visitante {

	@Override
	public void visit(Agua a) {
		
	}

	@Override
	public void visit(Aguila a) {
		
	}

	@Override
	public void visit(Bosque b) {
		
	}

	@Override
	public void visit(Cemento c) {
		
	}

	@Override
	public void visit(Ladrillo l) {
		
	}

	@Override
	public void visit(Vacio v) {
		
	}

	@Override
	public void visit(Enemigo e) {
		
	}

	@Override
	public void visit(Jugador j) {
	}

	@Override
	public void visit(DisparoEnemigo d) {
		d.plomo();
	}

	@Override
	public void visit(DisparoJugador d) {
		d.plomo();
	}

	@Override
	public void visit(Casco c) {
		
	}

	@Override
	public void visit(Estrella e) {
		
	}

	@Override
	public void visit(Granada g) {
		
	}

	@Override
	public void visit(Pala p) {
		
	}

	@Override
	public void visit(Reloj r) {
		
	}

	@Override
	public void visit(Vida v) {
		
	}

	@Override
	public void visit(Limite limite) {
		
	}

}
