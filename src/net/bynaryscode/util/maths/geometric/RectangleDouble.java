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

import net.bynaryscode.util.Util;
import net.bynaryscode.util.maths.MathUtil;

public class RectangleDouble extends Rectangle implements Cloneable {
	
	/**
	 * Cr�e un {@link RectangleDouble} ayant pour centre (centerX, centerY)
	 * et pour dimensions {@code width*height}. Attention, {@code ymin} est
	 * sup�rieur � {@code ymax}.
	 * <p><p><i>*compatibilit� avec l'API GUI openGL de Terra Magnetica</i>
	 * @param centerX
	 * @param centerY
	 * @param width
	 * @param height
	 * @return
	 */
	public static RectangleDouble createRectangleFromCenter(double centerX,
			double centerY, double width, double height) {
		RectangleDouble result = new RectangleDouble();
		
		result.xmin = centerX - width / 2;
		result.xmax = centerX + width / 2;
		result.ymin = centerY + height / 2;
		result.ymax = centerY - height / 2;
		
		return result;
	}

	public double xmin, ymin, xmax, ymax;
	
	public RectangleDouble() {
		this.xmin = 0;
		this.xmax = 0;
		this.ymin = 0;
		this.ymax = 0;
	}
	
	public RectangleDouble(double x1, double y1, double x2, double y2) {
		this.xmin = x1;
		this.xmax = x2;
		this.ymin = y1;
		this.ymax = y2;
	}
	
	public RectangleDouble(Vec2d pointMin, Vec2d pointMax) {
		this(pointMin.x, pointMin.y, pointMax.x, pointMax.y);
	}
	
	@Override
	public Vec2d center() {
		return new Vec2d((xmin + xmax) / 2 , (ymin + ymax) / 2);
	}

	public double getWidth() {
		return (xmin > xmax)? xmin - xmax : xmax - xmin;
	}
	
	public double getHeight() {
		return (ymin > ymax)? ymin - ymax : ymax - ymin;
	}
	
	@Override
	public boolean contains(Vec2 c){
		Vec2d cd = c.asDouble();
		
		double x = cd.x;
		double y = cd.y;
		double x1 = this.xmin < this.xmax ? this.xmin : this.xmax;
		double x2 = this.xmin < this.xmax ? this.xmax : this.xmin;
		double y1 = this.ymin < this.ymax ? this.ymin : this.ymax;
		double y2 = this.ymin < this.ymax ? this.ymax : this.ymin;
		
		return x <= x2 && x >= x1 && y <= y2 && y >= y1;
	}
	
	public boolean contains(RectangleDouble r) {
		if (r == null) return false;
		
		Vec2d[] sommets = r.getVertices();
		
		if (!contains(sommets[0])) return false;
		if (!contains(sommets[2])) return false;
		
		return true;
	}
	
	public boolean intersects(RectangleDouble other) {
		if (other == null) return false;
		
		Vec2d[] otherSommets = other.getVertices();
		int somCount = 0;
		
		//on commence par compter les sommets de l'autre rectangle, compris dans celui-ci.
		for (int i = 0 ; i < 4 ; i++) {
			if (contains(otherSommets[i])) {
				somCount++;
			}
		}
		
		if (somCount == 4) {//signifie que l'autre rectangle est contenu dans celui-ci.
			return false;
		}
		else if (somCount == 0) {
			Vec2d[] thisSommets = this.getVertices();
			somCount = 0;
			for (int i = 0 ; i < 4 ; i++) {
				if (other.contains(thisSommets[i])) {
					somCount++;
				}
			}
			
			if (somCount != 0 && somCount != 4) return true;
		}
		else {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Vec2d[] getVertices() {
		double xmin = Math.min(this.xmin, this.xmax);
		double xmax = Math.max(this.xmin, this.xmax);
		double ymin = Math.min(this.ymin, this.ymax);
		double ymax = Math.max(this.ymin, this.ymax);
		return new Vec2d[] {
				new Vec2d(xmin, ymax),
				new Vec2d(xmax, ymax),
				new Vec2d(xmax, ymin),
				new Vec2d(xmin, ymin)
		};
	}
	
	/**
	 * @deprecated Attention cette m�thode n'est pas termin�e, une exeption
	 * de type {@link UnsupportedOperationException} a de fortes chances d'�tre
	 * lanc�e.
	 * @param other
	 * @return
	 */
	@Deprecated
	public RectangleDouble intersection(RectangleDouble other) {
		if (other == null) return null;
		if (contains(other)) return other.clone();
		if (other.contains(this)) return this.clone();
		if (!intersects(other)) return null;
		
		//RectangleDouble result = new RectangleDouble();
		
		throw new UnsupportedOperationException("fonction non termin�e !");
	}
	
	/**
	 * Translate le rectangle de fa�on � ce que {@link #xmin} se
	 * trouve en {@code x} et {@link #ymin} en {@code y}
	 * @param x - abscisse
	 * @param y - ordonn�e
	 */
	public void moveAt(double x, double y) {
		double xdif = x - this.xmin;
		double ydif = y - this.ymin;
		
		this.translate(xdif, ydif);
	}
	
	public void translate(double x, double y) {
		this.xmin += x;
		this.xmax += x;
		this.ymin += y;
		this.ymax += y;
	}
	
	/**
	 * Fait tourner le rectangle autour du point sp�cifi�, avec l'angle
	 * de rotation sp�cifi�. Les seules valeurs autoris�es pour cette
	 * m�thode sont :
	 * <ul><li> 0
	 * <li>PI / 2
	 * <li>PI
	 * <li>3 * PI / 2
	 * </ul>
	 * <p>En effet, un {@link Rectangle} ne peut avoir ses c�t�s que
	 * verticaux ou horizontaux, car il est d�fini par une abscisse
	 * minimum et maximum, et par une ordonn�e minimum et maximum. Or
	 * seules les rotations � angle droit permettent de garder cet �tat.
	 * @param cX - Abscisse du centre de rotation.
	 * @param cY - Ordonn�e du centre de rotation.
	 * @param rad - Angle de rotation en radians.
	 */
	public void rotateFromPoint(double cX, double cY, double rad) {
		double enabled[] = {0, Math.PI / 2, Math.PI, Math.PI * 3 / 2};
		double rotation = MathUtil.angleMainValue(rad);
		if (!Util.arrayContainsd(enabled, rotation)) throw new UnsupportedOperationException("seulement des rotations � angle droit.");
		
		//rotation
		Vec2d[] sommets = getVertices();
		Vec2d min = MathUtil.rotatePoint(sommets[0], cX, cY, rotation);
		Vec2d max = MathUtil.rotatePoint(sommets[2], cX, cY, rotation);
		
		//sens des coordonnees du rectangle : varie en fonction du rep�re (parfois ymax < ymin, par exemple)
		int senseX = (int) Math.signum(this.xmax - this.xmin);
		int senseY = (int) Math.signum(this.ymax - this.ymin);
		
		//attribution des nouvelles coordonn�es.
		if (senseX == 1) {
			this.xmin = Math.min(min.x, max.x);
			this.xmax = Math.max(min.x, max.x);
		}
		else {
			this.xmax = Math.min(min.x, max.x);
			this.xmin = Math.max(min.x, max.x);
		}
		
		if (senseY == 1) {
			this.ymin = Math.min(min.y, max.y);
			this.ymax = Math.max(min.y, max.y);
		}
		else {
			this.ymax = Math.max(min.y, max.y);
			this.ymin = Math.min(min.y, max.y);
		}
	}
	
	/**
	 * Multiplie la hauteur de ce rectangle par {@code scaleY} et sa largeur
	 * par {@code scaleX}. Le rectangle final garde le m�me centre.
	 * @param scaleX - L'�chelle � appliquer au rectangle en largeur.
	 * @param scaleY - L'�chelle � appliquer au rectangle en hauteur.
	 */
	public void scaleFromCenter(double scaleX, double scaleY) {
		Vec2d c = center();
		
		int senseX = (int) Math.signum(this.xmax - this.xmin);
		int senseY = (int) Math.signum(this.ymax - this.ymin);
		
		double hw = this.getWidth() * scaleX / 2;
		double hh = this.getHeight() * scaleY / 2;
		
		this.xmin = c.x - hw * senseX;
		this.xmax = c.x + hw * senseX;
		this.ymin = c.y - hh * senseY;
		this.ymax = c.y + hh * senseY;
	}
	
	@Override
	public RectangleDouble clone() {
		RectangleDouble result = null;
		try {
			result = (RectangleDouble) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(xmax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(xmin);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ymax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ymin);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RectangleDouble)) {
			return false;
		}
		RectangleDouble other = (RectangleDouble) obj;
		if (Double.doubleToLongBits(xmax) != Double
				.doubleToLongBits(other.xmax)) {
			return false;
		}
		if (Double.doubleToLongBits(xmin) != Double
				.doubleToLongBits(other.xmin)) {
			return false;
		}
		if (Double.doubleToLongBits(ymax) != Double
				.doubleToLongBits(other.ymax)) {
			return false;
		}
		if (Double.doubleToLongBits(ymin) != Double
				.doubleToLongBits(other.ymin)) {
			return false;
		}
		return true;
	}

	@Override
	public RectangleDouble asDouble() {
		return this.clone();
	}
	
	@Override
	public RectangleInt asInteger() {
		return new RectangleInt((int) this.xmin, (int) this.ymin, (int) this.xmax, (int) this.ymax);
	}
}
