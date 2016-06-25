package net.bynaryscode.util.maths.geometric;

public interface Forme {
	/** Donne les sommets de la forme dans le sens horaire. */
	CoordonneesDouble[] getSommets();
	/** Indique si cette forme contient le point pass� en param�tres. */
	boolean contains(Coordonnees c);
	CoordonneesDouble center();
}
