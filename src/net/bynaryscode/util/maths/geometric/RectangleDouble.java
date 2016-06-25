package net.bynaryscode.util.maths.geometric;

import net.bynaryscode.util.Util;
import net.bynaryscode.util.maths.MathUtil;

public class RectangleDouble extends Rectangle implements Cloneable {
	
	/**
	 * Crée un {@link RectangleDouble} ayant pour centre (centerX, centerY)
	 * et pour dimensions {@code width*height}. Attention, {@code ymin} est
	 * supérieur à {@code ymax}.
	 * <p><p><i>*compatibilité avec l'API GUI openGL de Terra Magnetica</i>
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
	
	public RectangleDouble(CoordonneesDouble pointMin, CoordonneesDouble pointMax) {
		this(pointMin.x, pointMin.y, pointMax.x, pointMax.y);
	}
	
	@Override
	public CoordonneesDouble center() {
		return new CoordonneesDouble((xmin + xmax) / 2 , (ymin + ymax) / 2);
	}

	public double getWidth() {
		return (xmin > xmax)? xmin - xmax : xmax - xmin;
	}
	
	public double getHeight() {
		return (ymin > ymax)? ymin - ymax : ymax - ymin;
	}
	
	@Override
	public boolean contains(Coordonnees c){
		CoordonneesDouble cd = c.asDouble();
		
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
		
		CoordonneesDouble[] sommets = r.getSommets();
		
		if (!contains(sommets[0])) return false;
		if (!contains(sommets[2])) return false;
		
		return true;
	}
	
	public boolean intersects(RectangleDouble other) {
		if (other == null) return false;
		
		CoordonneesDouble[] otherSommets = other.getSommets();
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
			CoordonneesDouble[] thisSommets = this.getSommets();
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
	public CoordonneesDouble[] getSommets() {
		double xmin = Math.min(this.xmin, this.xmax);
		double xmax = Math.max(this.xmin, this.xmax);
		double ymin = Math.min(this.ymin, this.ymax);
		double ymax = Math.max(this.ymin, this.ymax);
		return new CoordonneesDouble[] {
				new CoordonneesDouble(xmin, ymax),
				new CoordonneesDouble(xmax, ymax),
				new CoordonneesDouble(xmax, ymin),
				new CoordonneesDouble(xmin, ymin)
		};
	}
	
	/**
	 * @deprecated Attention cette méthode n'est pas terminée, une exeption
	 * de type {@link UnsupportedOperationException} a de fortes chances d'être
	 * lancée.
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
		
		throw new UnsupportedOperationException("fonction non terminée !");
	}
	
	/**
	 * Translate le rectangle de façon à ce que {@link #xmin} se
	 * trouve en {@code x} et {@link #ymin} en {@code y}
	 * @param x - abscisse
	 * @param y - ordonnée
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
	 * Fait tourner le rectangle autour du point spécifié, avec l'angle
	 * de rotation spécifié. Les seules valeurs autorisées pour cette
	 * méthode sont :
	 * <ul><li> 0
	 * <li>PI / 2
	 * <li>PI
	 * <li>3 * PI / 2
	 * </ul>
	 * <p>En effet, un {@link Rectangle} ne peut avoir ses côtés que
	 * verticaux ou horizontaux, car il est défini par une abscisse
	 * minimum et maximum, et par une ordonnée minimum et maximum. Or
	 * seules les rotations à angle droit permettent de garder cet état.
	 * @param cX - Abscisse du centre de rotation.
	 * @param cY - Ordonnée du centre de rotation.
	 * @param rad - Angle de rotation en radians.
	 */
	public void rotateFromPoint(double cX, double cY, double rad) {
		double enabled[] = {0, Math.PI / 2, Math.PI, Math.PI * 3 / 2};
		double rotation = MathUtil.angleMainValue(rad);
		if (!Util.arrayContainsd(enabled, rotation)) throw new UnsupportedOperationException("seulement des rotations à angle droit.");
		
		//rotation
		CoordonneesDouble[] sommets = getSommets();
		CoordonneesDouble min = MathUtil.rotatePoint(sommets[0], cX, cY, rotation);
		CoordonneesDouble max = MathUtil.rotatePoint(sommets[2], cX, cY, rotation);
		
		//sens des coordonnees du rectangle : varie en fonction du repère (parfois ymax < ymin, par exemple)
		int senseX = (int) Math.signum(this.xmax - this.xmin);
		int senseY = (int) Math.signum(this.ymax - this.ymin);
		
		//attribution des nouvelles coordonnées.
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
	 * par {@code scaleX}. Le rectangle final garde le même centre.
	 * @param scaleX - L'échelle à appliquer au rectangle en largeur.
	 * @param scaleY - L'échelle à appliquer au rectangle en hauteur.
	 */
	public void scaleFromCenter(double scaleX, double scaleY) {
		CoordonneesDouble c = center();
		
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
