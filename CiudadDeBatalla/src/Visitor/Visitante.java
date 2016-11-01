package Visitor;

import Obstaculo.*;
import Proyectil.DisparoEnemigo;
import Proyectil.DisparoJugador;
import Tanque.Enemigo;
import Tanque.Jugador;
import Poderes.*;

public interface Visitante {

	public void visit(Agua a);
	public void visit(Aguila a);
	public void visit(Bosque b);
	public void visit(Cemento c);
	public void visit(Ladrillo l);
	public void visit(Vacio v);
	
	public void visit(Enemigo e);
	public void visit(Jugador j);
	public void visit(DisparoEnemigo d);
	public void visit(DisparoJugador d);
	
	public void visit(Casco c);
	public void visit(Estrella e);
	public void visit(Granada g);
	public void visit(Pala p);
	public void visit(Reloj r);
	public void visit(Vida v);
	public void visit(Limite limite);


}
