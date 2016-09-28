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
	public Vec2d center() {
		return new Vec2d((xmin - xmax) / 2 , (ymin - ymax) / 2);
	}
	
	public int getWidth() {
		return (xmin > xmax)? xmin - xmax : xmax - xmin;
	}
	
	public int getHeight() {
		return (ymin > ymax)? ymin - ymax : ymax - ymin;
	}
	
	@Override
	public boolean contains(Vec2 c) {
		Vec2i ci = c.asInteger();
		
		int x = ci.x;
		int y = ci.y;
		int x1 = (this.xmin < this.xmax)? this.xmin : this.xmax;
		int x2 = (this.xmin < this.xmax)? this.xmax : this.xmin;
		int y1 = (this.ymin < this.ymax)? this.ymin : this.ymax;
		int y2 = (this.ymin < this.ymax)? this.ymax : this.ymin;
		
		return x <= x2 && x >= x1 && y <= y2 && y >= y1;
	}
	
	@Override
	public Vec2d[] getVertices() {
		int xmin = Math.min(this.xmin, this.xmax);
		int xmax = Math.max(this.xmin, this.xmax);
		int ymin = Math.min(this.ymin, this.ymax);
		int ymax = Math.max(this.ymin, this.ymax);
		return new Vec2d[] {
				new Vec2d(xmin, ymax),
				new Vec2d(xmax, ymax),
				new Vec2d(xmax, ymin),
				new Vec2d(xmin, ymin)
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
		Vec2d c = center();
		
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
