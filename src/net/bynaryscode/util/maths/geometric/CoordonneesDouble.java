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

import java.io.Serializable;

public class CoordonneesDouble extends Coordonnees implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public double x;
	public double y;
	
	public CoordonneesDouble(){
		x = 0;
		y = 0;
	}
	
	public CoordonneesDouble(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getDistance(Coordonnees c) {
		CoordonneesDouble other = c.asDouble();
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public CoordonneesDouble clone(){
		return (CoordonneesDouble) super.clone();
	}
	
	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		CoordonneesDouble other = (CoordonneesDouble) obj;
		
		return x == other.x && y == other.y;
	}
	
	@Override
	public CoordonneesInt asInteger() {
		return new CoordonneesInt((int) x, (int) y);
	}
	
	@Override
	public CoordonneesFloat asFloat() {
		return new CoordonneesFloat((float) x, (float) y);
	}
	
	@Override
	public CoordonneesDouble asDouble() {
		return this.clone();
	}
}