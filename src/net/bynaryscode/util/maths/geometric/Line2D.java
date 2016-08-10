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

package net.bynaryscode.util.maths.geometric;

public class Line2D {
	
	/** Equation de la droite : ax + by + c = 0 */
	private double a, b, c;
	
	public Line2D(Vec3d pt1, Vec3d pt2) {
		set(pt1, pt2);
	}
	
	public Line2D(double x1, double y1, double x2, double y2) {
		set(x1, y1, x2, y2);
	}
	
	public void set(Vec3d pt1, Vec3d pt2) {
		Vec3d vecDir = pt2.substract(pt1);
		this.a = - vecDir.getY();
		this.b = vecDir.getX();
		this.c = - this.b * pt1.getY() - this.a * pt1.getX();
	}
	
	public void set(double x1, double y1, double x2, double y2) {
		this.set(new Vec3d(x1, y1), new Vec3d(x2, y2));
	}
	
	public Vec3d getDirectionVector() {
		return new Vec3d(- this.a, this.b);
	}
	
	public boolean contains(Vec3d pt) {
		return contains(pt.x, pt.y);
	}
	
	/** Indique si la droite contient strictement le point indiqué. */
	public boolean contains(double x, double y) {
		return equation(x, y) == 0;
	}
	
	public boolean contains(double x, double y, double margin) {
		return squaredDistance(x, y) < margin * margin;
	}
	
	/** Retourne la distance qui sépare le point indiqué de cette droite. */
	public double distance(double x, double y) {
		return Math.sqrt(squaredDistance(x, y));
	}
	
	/** Retourne la distance au carré qui sépare le point indiqué de cette droite. */
	public double squaredDistance(double x, double y) {
		double eq = equation(x, y);
		return eq * eq / (a * a + b * b);
	}
	
	/** @deprecated pas encore réalisé. */
	public Vec2d getOrthographicProjection(double x, double y) {
		return new Vec2d();
	}
	
	/** Retourne le résultat de l'opération ax + bx + c. Le résultat est positif
	 * si le point est à gauche de la droite (sens de la droite donné par le vecteur
	 * directeur {@link #getDirectionVector()}) et négatif si le point est à droite
	 * de la droite. Si le résultat est nul, alors le point est sur la droite. */
	public double equation(Vec3d pt) {
		return equation(pt.x, pt.y);
	}
	
	/** @see #equation(Vec3d) */
	public double equation(double x, double y) {
		return a * x + b * y + c;
	}
	
	/** donne un nombre t tel que : <blockquote>t = ax si a != 0 <br> t = by
	 * sinon</blockquote>
	 * Ce nombre peut être utiliser pour définir un segment sur la
	 * droite. */
	public double getParameter(double x, double y) {
		return a == 0 ? b * y : a * x;
	}
	
	public String getEquationStr() {
		return a + "x + " + b + "y + " + c + " = 0";
	}
	
	@Override
	public String toString() {
		return this.getEquationStr();
	}
}
