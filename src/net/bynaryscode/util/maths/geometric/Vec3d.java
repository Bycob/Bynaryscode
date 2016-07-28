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

/** Un vecteur en trois dimensions. */
public class Vec3d {
	
	public double x;
	public double y;
	public double z;
	
	public Vec3d() {
		this(0, 0, 0);
	}
	
	public Vec3d(double x, double y) {
		this(x, y, 0);
	}
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3d(Coordonnees c) {
		CoordonneesDouble cd = c.asDouble();
		this.x = cd.x;
		this.y = cd.y;
		this.z = 0;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public Vec3d add(Vec3d vec) {
		return new Vec3d(
				x + vec.x, y + vec.y, z + vec.z);
	}
	
	/**
	 * Soustrait le vecteur passé en paramètre à ce vecteur.
	 * @param vec - Le vecteur soustrait.
	 * @return Un vecteur correspondant au résultat.
	 */
	public Vec3d substract(Vec3d vec) {
		return new Vec3d(
				x - vec.x, y - vec.y, z - vec.z);
	}
	
	/**
	 * Calcule le produit scalaire de ce vecteur et du vecteur
	 * passé en paramètre.
	 * @param vec - Le second facteur dans le produit scalaire.
	 * @return Une valeur numérique correspondant au produit
	 * scalaire.
	 */
	public double dotProduct(Vec3d vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}
	
	/**
	 * Calcule le produit vectoriel de ce vecteur et du vecteur
	 * passé en paramètre.
	 * @param vec - Le second facteur dans le produit vectoriel.
	 * @return Un nouveau vecteur, résultat du produit vectoriel.
	 */
	public Vec3d crossProduct(Vec3d vec) {
		return new Vec3d(
				y * vec.z - z * vec.y,
				z * vec.x - x * vec.z,
				x * vec.y - y * vec.x);
	}
	
	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Définit la norme du vecteur à 1. Le vecteur garde
	 * en revanche même direction et même sens.
	 */
	public void normalize() {
		double length = length();
		this.x /= length;
		this.y /= length;
		this.z /= length;
	}
	
	@Override
	public Vec3d clone() {
		return new Vec3d(x, y, z);
	}

	public void translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
}
