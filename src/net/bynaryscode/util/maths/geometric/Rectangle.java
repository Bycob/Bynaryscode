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

public abstract class Rectangle implements Shape {
	
	public static RectangleDouble getBounds(Shape f) {
		RectangleDouble result;
		
		if (f instanceof Circle) {
			Circle c = (Circle) f;
			Vec2d centre = c.center();
			double rayon = c.getRayon();
			result = new RectangleDouble(
					centre.x - rayon, centre.y - rayon,
					centre.x + rayon, centre.y + rayon);
			return result;
		}
		
		Vec2d[] sommets = f.getVertices();
		if (sommets.length == 0) return new RectangleDouble();
		
		result = new RectangleDouble(sommets[0].x, sommets[0].y, sommets[0].x, sommets[0].y);
		
		for (int i = 1 ; i < sommets.length ; i++) {
			if (sommets[i].x < result.xmin) {
				result.xmin = sommets[i].x;
			}
			if (sommets[i].y < result.ymin) {
				result.ymin = sommets[i].y;
			}
			if (sommets[i].x > result.xmax) {
				result.xmax = sommets[i].x;
			}
			if (sommets[i].y > result.ymax) {
				result.ymax = sommets[i].y;
			}
		}
		
		return result;
	}
	
	/** @return Une copie de <code>this</code>, 
	 * casté en {@link RectangleDouble} */
	public abstract RectangleDouble asDouble();
	/** @return Une copie de <code>this</code>, 
	 * casté en {@link RectangleInt} */
	public abstract RectangleInt asInteger();
	
	/** Pour un rectangle, indique si le rectangle contient le point passé
	 * en paramètres. Si le point est sur un côté du rectangle alors il est
	 * considéré comme <i>dans</i> le rectangle. */
	@Override
	public abstract boolean contains(Vec2 c);
}
