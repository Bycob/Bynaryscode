package net.bynaryscode.util.maths.geometric;

/**
 * Classe repr�sentant un cercle.
 * @author Louis JEAN
 *
 */
public class Circle implements Forme {
	
	private double rayon, centreX, centreY;
	private int nbSommets = 10;
	
	/**
	 * Cr�e un nouvel objet cercle.
	 * @param centreX - L'abscisse du centre du cercle.
	 * @param centreY - L'ordonn�e du centre du cercle.
	 * @param rayon - Le rayon du cercle.
	 */
	public Circle(double centreX, double centreY, double rayon) {
		this.rayon = rayon >= 0 ? rayon : 0;
		this.centreX = centreX;
		this.centreY = centreY;
	}
	
	public double getRayon() {
		return this.rayon;
	}
	
	public void setRayon(double rayon) {
		this.rayon = rayon >= 0 ? rayon : 0;
	}
	
	public CoordonneesDouble getCentre() {
		return new CoordonneesDouble(this.centreX, this.centreY);
	}
	
	public void setCentre(double x, double y) {
		this.centreX = x;
		this.centreY = y;
	}
	
	/**
	 * D�finit le nombre de sommets � retourner dans la m�thode
	 * {@link #getSommets()}. En effet, celle-ci renvoie les sommets
	 * d'un polygone r�gulier dont ce cercle est le cercle circonscrit.
	 * Le nombre de sommets du polygone retourn� peut varier de 3
	 * � infini. Il est a noter que plus les sommets seront nombreux,
	 * plus la m�thode {@link #getSommets()} sera co�teuse en
	 * ressources.
	 * @param nb - Le nombre de sommets du polygone inscrit dans ce
	 * cercle, dont les sommets seront renvoy�s par la m�thode
	 * {@link #getSommets()};
	 */
	public void setNbSommets(int nb) {
		this.nbSommets = Math.max(4, nb);
	}
	
	@Override
	public CoordonneesDouble[] getSommets() {
		CoordonneesDouble[] result = new CoordonneesDouble[this.nbSommets];
		double var0 = Math.PI * 2d / this.nbSommets;
		double angle = 0;
		
		for (int i = 0 ; i < result.length ; i++, angle += var0) {
			result[i] = new CoordonneesDouble(
					this.centreX + Math.cos(angle) * this.rayon,
					this.centreY + Math.sin(angle) * this.rayon
					);
		}
		
		return result;
	}

	@Override
	public boolean contains(Coordonnees c) {
		if (c == null) return false;
		return c.asDouble().getDistance(center()) <= this.rayon;
	}

	@Override
	public CoordonneesDouble center() {
		return new CoordonneesDouble(this.centreX, this.centreY);
	}

}
