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

/**
 * Super-type des objets coordonnées. Les méthodes de cette classe
 * permettent de caster les différents objets coordonnées, et les
 * rend polyvalents.
 * @author Louis JEAN
 *
 */
public abstract class Vec2 implements Cloneable {
	
	/** Retourne une copie de cet objet, de type {@link Vec2d} */
	public abstract Vec2d asDouble();
	/** Retourne une copie de cet objet, de type {@link Vec2i} */
	public abstract Vec2i asInteger();
	/** Retourne une copie de cet objet, de type {@link Vec2f} */
	public abstract Vec2f asFloat();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		return asDouble().equals(((Vec2) obj).asDouble());
	}
	
	@Override
	public int hashCode() {
		return asDouble().hashCode();
	}
	
	@Override
	public Vec2 clone() {
		Vec2 clone = null;
		
		try {
			clone = (Vec2) super.clone();
		} catch (Exception SOOoooooUseless) {}
		
		return clone;
	}
}
