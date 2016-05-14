package net.bynaryscode.util.maths.geometric;

public interface Forme {
	CoordonneesDouble[] getSommets();
	/** Indique si cette forme contient le point passé en paramètres. */
	boolean contains(Coordonnees c);
	CoordonneesDouble center();
}
