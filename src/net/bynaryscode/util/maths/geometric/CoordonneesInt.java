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

public class CoordonneesInt extends Coordonnees implements Serializable {
	
	private static final long serialVersionUID = 4384881409638581287L;
	public int x;
	public int y;
	
	public CoordonneesInt(){
		this.x = 0;
		this.y = 0;
	}
	
	public CoordonneesInt(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		if (!(obj instanceof CoordonneesInt)) {
			return false;
		}
		CoordonneesInt other = (CoordonneesInt) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	
	/** Indique si les coordonnées correspondent à ce qui est passé en paramètres. */
	public boolean matches(int x, int y) {
		return this.x == x && this.y == y;
	}

	@Override
	public CoordonneesInt clone(){
		return (CoordonneesInt) super.clone();
	}
	
	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}
	
	@Override
	public CoordonneesDouble asDouble() {
		return new CoordonneesDouble(this.x, this.y);
	}
	
	@Override
	public CoordonneesFloat asFloat() {
		return new CoordonneesFloat(this.x, this.y);
	}
	
	@Override
	public CoordonneesInt asInteger() {
		return this.clone();
	}
}
