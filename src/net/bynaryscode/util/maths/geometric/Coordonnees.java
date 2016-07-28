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

/**
 * Super-type des objets coordonnées. Les méthodes de cette classe
 * permettent de caster les différents objets coordonnées, et les
 * rend polyvalents.
 * @author Louis JEAN
 *
 */
public abstract class Coordonnees implements Cloneable {
	
	/** Retourne une copie de cet objet, de type {@link CoordonneesDouble} */
	public abstract CoordonneesDouble asDouble();
	/** Retourne une copie de cet objet, de type {@link CoordonneesInt} */
	public abstract CoordonneesInt asInteger();
	/** Retourne une copie de cet objet, de type {@link CoordonneesFloat} */
	public abstract CoordonneesFloat asFloat();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		return asDouble().equals(((Coordonnees) obj).asDouble());
	}
	
	@Override
	public int hashCode() {
		return asDouble().hashCode();
	}
	
	@Override
	public Coordonnees clone() {
		Coordonnees clone = null;
		
		try {
			clone = (Coordonnees) super.clone();
		} catch (Exception SOOoooooUseless) {}
		
		return clone;
	}
}
