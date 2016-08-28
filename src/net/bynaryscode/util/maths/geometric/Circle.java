/* <LICENSE>
Copyright (C) 2015-2016 Louis JEAN

This file is part of BynarysCode.

BynarysCode is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

BynarysCode is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with BynarysCode. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package net.bynaryscode.util.maths.geometric;

import net.bynaryscode.util.maths.MathUtil;

/**
 * Classe représentant un cercle.
 * @author Louis JEAN
 *
 */
public class Circle implements Forme {
	
	private double rayon, centreX, centreY;
	private int nbSommets = 10;
	
	/**
	 * Crée un nouvel objet cercle.
	 * @param centreX - L'abscisse du centre du cercle.
	 * @param centreY - L'ordonnée du centre du cercle.
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
	
	public Vec2d getCentre() {
		return new Vec2d(this.centreX, this.centreY);
	}
	
	public void setCentre(double x, double y) {
		this.centreX = x;
		this.centreY = y;
	}
	
	/**
	 * Définit le nombre de sommets à retourner dans la méthode
	 * {@link #getSommets()}. En effet, celle-ci renvoie les sommets
	 * d'un polygone régulier dont ce cercle est le cercle circonscrit.
	 * Le nombre de sommets du polygone retourné peut varier de 3
	 * à infini. Il est a noter que plus les sommets seront nombreux,
	 * plus la méthode {@link #getSommets()} sera coûteuse en
	 * ressources.
	 * @param nb - Le nombre de sommets du polygone inscrit dans ce
	 * cercle, dont les sommets seront renvoyés par la méthode
	 * {@link #getSommets()};
	 */
	public void setNbSommets(int nb) {
		this.nbSommets = Math.max(4, nb);
	}
	
	public Vec2d[] getSommets(int nb) {
		if (nb < 4) throw new IllegalArgumentException("nb should be >= 4");
		
		Vec2d[] result = new Vec2d[nb];
		double var0 = Math.PI * 2d / nb;
		double angle = 0;
		
		for (int i = 0 ; i < result.length ; i++, angle -= var0) {
			result[i] = new Vec2d(
					this.centreX + Math.cos(angle) * this.rayon,
					this.centreY + Math.sin(angle) * this.rayon
					);
		}
		
		return result;
	}
	
	@Override
	public Vec2d[] getSommets() {
		return getSommets(this.nbSommets);
	}

	@Override
	public boolean contains(Vec2 c) {
		if (c == null) return false;
		return MathUtil.getSquaredDistance(c, center()) <= this.rayon * this.rayon;
	}

	@Override
	public Vec2d center() {
		return new Vec2d(this.centreX, this.centreY);
	}

}
