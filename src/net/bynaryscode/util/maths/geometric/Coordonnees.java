package net.bynaryscode.util.maths.geometric;

/**
 * Super-type des objets coordonnées. Les méthodes de cette classe
 * permettent de caster les différents objets coordonnées, et les
 * rend polyvalents.
 * @author Louis JEAN
 *
 */
public abstract class Coordonnees implements Cloneable {
	
	/** Retourne une copie de cet objet, de type {@link CoordonneesDouble} */
	public abstract CoordonneesDouble asDouble();
	/** Retourne une copie de cet objet, de type {@link CoordonneesInt} */
	public abstract CoordonneesInt asInteger();
	/** Retourne une copie de cet objet, de type {@link CoordonneesFloat} */
	public abstract CoordonneesFloat asFloat();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		return asDouble().equals(((Coordonnees) obj).asDouble());
	}
	
	@Override
	public int hashCode() {
		return asDouble().hashCode();
	}
	
	@Override
	public Coordonnees clone() {
		Coordonnees clone = null;
		
		try {
			clone = (Coordonnees) super.clone();
		} catch (Exception SOOoooooUseless) {}
		
		return clone;
	}
}
