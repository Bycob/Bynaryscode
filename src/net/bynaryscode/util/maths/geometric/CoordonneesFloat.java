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

import net.bynaryscode.util.maths.MathUtil;

public class CoordonneesFloat extends Coordonnees implements Serializable {
	
	private static final long serialVersionUID = 6373262196341726681L;
	
	public float x;
	public float y;
	
	public CoordonneesFloat(){
		x = 0;
		y = 0;
	}
	
	public CoordonneesFloat(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public double getDistance(Coordonnees c) {
		CoordonneesFloat other = c.asFloat();
		return MathUtil.getDistance(this.x, this.y, other.x, other.y);
	}
	
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public CoordonneesFloat clone(){
		return (CoordonneesFloat) super.clone();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		if (!(other instanceof CoordonneesFloat)) {
			return false;
		}
		CoordonneesFloat c = (CoordonneesFloat) other;
		
		return MathUtil.aproximatelyEquals(c.x, this.x, 0.00001) && MathUtil.aproximatelyEquals(c.y, this.y, 0.00001);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + this.x);
		result = (int) (prime * result + this.y);
		return result;
	}
	
	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}
	
	@Override
	public CoordonneesInt asInteger() {
		return new CoordonneesInt((int) x, (int) y);
	}
	
	@Override
	public CoordonneesDouble asDouble() {
		return new CoordonneesDouble(x, y);
	}
	
	@Override
	public CoordonneesFloat asFloat() {
		return this.clone();
	}
}
