package net.bynaryscode.util.maths.geometric;

public interface Forme {
	CoordonneesDouble[] getSommets();
	/** Indique si cette forme contient le point pass� en param�tres. */
	boolean contains(Coordonnees c);
	CoordonneesDouble center();
}
