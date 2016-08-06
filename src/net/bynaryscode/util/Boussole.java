/* <LICENSE>
Copyright 2015-2016 Louis JEAN

This file is part of BynarysCode.

BynarysCode is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BynarysCode is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with BynarysCode. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package net.bynaryscode.util;

import net.bynaryscode.util.maths.MathUtil;
import net.bynaryscode.util.maths.geometric.Vec2;
import net.bynaryscode.util.maths.geometric.Vec2d;

/**
 * Les quatres points cardinaux.
 * <p>Les directions sont des angles en radians. Elles sont définies
 * comme il suit : 
 * <br><blockquote>
 * Soit un cercle trigonométrique de centre O muni d'un repère direct
 * (O;i;j). Soit un point E sur le cercle. La direction du vecteur 
 * {@literal (OE)->} correspond à l'angle (IÔE) en radians.
 * </blockquote>
 * <p>
 * Les quatres points cardinaux sont ordonnés : Le premier est le Nord, 
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
	 * Donne le point cardinal correspondant à l'angle indiqué. 
	 * @param radius - L'angle représentant une direction, en radians.
	 * <code>0</code> correspont à l'est.
	 * @return le point cardinal qui correspont le plus :
	 * <ul><li>NORD pour les valeurs d'angle de {@code PI/4} à
	 * {@code 3PI/4}
	 * <li>EST pour les valeurs d'angle de {@code -PI/4} à
	 * {@code PI/4}
	 * <li>SUD pour les valeurs d'angle de {@code -3PI/4} à
	 * {@code -PI/4}
	 * <li>OUEST pour les valeurs d'angle de {@code 3PI/4} à
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
	
	public static double getDirection(Vec2 origine, Vec2 extrémité) {
		Vec2d o = origine.asDouble();
		Vec2d e = extrémité.asDouble();
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
