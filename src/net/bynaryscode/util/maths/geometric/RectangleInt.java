package net.bynaryscode.util.maths.geometric;

public class RectangleInt extends Rectangle implements Cloneable {
	
	public int xmin, ymin, xmax, ymax;
	
	public RectangleInt() {
		xmin = 0;
		xmax = 0;
		ymin = 0;
		ymax = 0;
	}
	
	public RectangleInt(int x1, int y1, int x2, int y2) {
		this.xmin = x1;
		this.xmax = x2;
		this.ymin = y1;
		this.ymax = y2;
	}
	
	@Override
	public CoordonneesDouble center() {
		return new CoordonneesDouble((xmin - xmax) / 2 , (ymin - ymax) / 2);
	}
	
	public int getWidth() {
		return (xmin > xmax)? xmin - xmax : xmax - xmin;
	}
	
	public int getHeight() {
		return (ymin > ymax)? ymin - ymax : ymax - ymin;
	}
	
	@Override
	public boolean contains(Coordonnees c) {
		CoordonneesInt ci = c.asInteger();
		
		int x = ci.x;
		int y = ci.y;
		int x1 = (this.xmin < this.xmax)? this.xmin : this.xmax;
		int x2 = (this.xmin < this.xmax)? this.xmax : this.xmin;
		int y1 = (this.ymin < this.ymax)? this.ymin : this.ymax;
		int y2 = (this.ymin < this.ymax)? this.ymax : this.ymin;
		
		return x <= x2 && x >= x1 && y <= y2 && y >= y1;
	}
	
	@Override
	public CoordonneesDouble[] getSommets() {
		return new CoordonneesDouble[] {
				new CoordonneesDouble(xmin, ymin),
				new CoordonneesDouble(xmax, ymin),
				new CoordonneesDouble(xmax, ymax),
				new CoordonneesDouble(xmin, ymax)
		};
	}
	
	public void translate(int x, int y) {
		this.xmin += x;
		this.xmax += x;
		this.ymin += y;
		this.ymax += y;
	}
	
	/**
	 * Multiplie la hauteur de ce rectangle par {@code scaleY} et sa largeur
	 * par {@code scaleX}. Le rectangle final garde le même centre.
	 * @param scaleX - L'échelle à appliquer au rectangle en largeur.
	 * @param scaleY - L'échelle à appliquer au rectangle en hauteur.
	 */
	public RectangleInt scaleFromCenter(double scaleX, double scaleY) {
		CoordonneesDouble c = center();
		
		int senseX = (int) Math.signum(this.xmax - this.xmin);
		int senseY = (int) Math.signum(this.ymax - this.ymin);
		
		double hw = this.getWidth() * scaleX / 2;
		double hh = this.getHeight() * scaleY / 2;
		
		this.xmin = (int) (c.x - hw * senseX);
		this.xmax = (int) (c.x + hw * senseX);
		this.ymin = (int) (c.y - hh * senseY);
		this.ymax = (int) (c.y + hh * senseY);
		
		return this;
	}
	
	@Override
	public RectangleInt clone() {
		RectangleInt result = null;
		try {
			result = (RectangleInt) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public RectangleDouble asDouble() {
		return new RectangleDouble(xmin, xmax, ymin, ymax);
	}
	
	@Override
	public RectangleInt asInteger() {
		return this.clone();
	}
}
