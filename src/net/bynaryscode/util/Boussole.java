package net.bynaryscode.util;

import net.bynaryscode.util.maths.MathUtil;
import net.bynaryscode.util.maths.geometric.Coordonnees;
import net.bynaryscode.util.maths.geometric.CoordonneesDouble;

/**
 * Les quatres points cardinaux.
 * <p>Les directions sont des angles en radians. Elles sont d�finies
 * comme il suit : 
 * <br><blockquote>
 * Soit un cercle trigonom�trique de centre O muni d'un rep�re direct
 * (O;i;j). Soit un point E sur le cercle. La direction du vecteur 
 * {@literal (OE)->} correspond � l'angle (I�E) en radians.
 * </blockquote>
 * <p>
 * Les quatres points cardinaux sont ordonn�s : Le premier est le Nord, 
 * et les autres suivent en tournant dans le sens des aiguilles d'une montre.
 * @author Louis JEAN
 *
 */
public enum Boussole {
	NORD(Math.PI / 2.0),
	EST(0),
	SUD(Math.PI * 1.5),
	OUEST(Math.PI);
	
	/**
	 * Donne le point cardinal correspondant � l'angle indiqu�. 
	 * @param radius - L'angle repr�sentant une direction, en radians.
	 * <code>0</code> correspont � l'est.
	 * @return le point cardinal qui correspont le plus :
	 * <ul><li>NORD pour les valeurs d'angle de {@code PI/4} �
	 * {@code 3PI/4}
	 * <li>EST pour les valeurs d'angle de {@code -PI/4} �
	 * {@code PI/4}
	 * <li>SUD pour les valeurs d'angle de {@code -3PI/4} �
	 * {@code -PI/4}
	 * <li>OUEST pour les valeurs d'angle de {@code 3PI/4} �
	 * {@code 5PI/4}</ul>
	 */
	public static Boussole getPointCardinalPourAngle(double radius) {
		for (Boussole pointCard : values()) {
			double o = pointCard.orientation;
			if(MathUtil.isAngleBetween(radius, o - Math.PI / 4, o + Math.PI / 4)) {
				return pointCard;
			}
		}
		
		return NORD;
	}
	
	public static double getDirection(Coordonnees origine, Coordonnees extr�mit�) {
		CoordonneesDouble o = origine.asDouble();
		CoordonneesDouble e = extr�mit�.asDouble();
		double d = MathUtil.getDistance(o, e);
		double dX = e.x - o.x;
		double dY = e.y - o.y;
		return MathUtil.angle(dX / d, dY / d);
	}
	
	public static Boussole oppose(Boussole b) {
		int count = Boussole.values().length;
		return Boussole.values()[(b.ordinal() + count / 2) % count];
	}
	
	private double orientation;
	
	Boussole(double orientation) {
		this.orientation = orientation;
	}
	
	public double getOrientation() {
		return orientation;
	}
}