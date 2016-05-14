package net.bynaryscode.util.path;

import java.util.ArrayList;

import net.bynaryscode.util.maths.geometric.CoordonneesInt;

/** Représente une case  */
public class Case extends SpaceGraphPoint {
	private int x, y;
	private ArrayList<Case> previous = new ArrayList<Case>(2);
	
	public Case(int x, int y, Case... previous) {
		this.x = x;
		this.y = y;
		
		for (Case prev : previous) {
			this.previous.add(prev);
		}
	}
	
	public Case(CoordonneesInt cCase, Case... previous) {
		this(cCase.x, cCase.y, previous);
	}

	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	public void setLocation(int x, int y) { this.x = x; this.y = y; }
	
	@Override
	public CoordonneesInt getLocation() {
		return new CoordonneesInt(x, y);
	}
	
	public void addPrevious(Case previous) {
		if (!hasPrevious(previous)) {
			this.previous.add(previous);
		}
	}
	
	public void removeAllPrevious() {
		this.previous.clear();
	}
	
	public Case[] getPreviousCases() {
		return this.previous.toArray(new Case[0]);
	}
	
	public boolean hasPrevious(Case c) {
		return this.previous.contains(c);
	}
	
	@Override
	public void setPrevious(SpaceGraphPoint prev) {
		//On doit toujours être en accord avec ce qui vient du haut.
		super.setPrevious(prev);
		//Si prev est null, alors la haut on n'en a plus du tout -> ici aussi.
		if (prev == null) {
			removeAllPrevious();
		}
		
		//Sinon on ne fait que l'ajouter à la liste des previous.
		if (prev instanceof Case) {
			Case prevCase = (Case) prev;
			addPrevious(prevCase);
		}
		else {
			throw new IllegalArgumentException("prev du mauvais type");
		}
	}
	
	/** Deux cases sont égales si et seulement si elles ont les mêmes coordonnées. */
	@Override
	public boolean equals(Object other) {
		if (! (other instanceof Case)) return false;
		
		Case otherCase = (Case) other;
		
		return this.x == otherCase.x && this.y == otherCase.y;
	}
}
