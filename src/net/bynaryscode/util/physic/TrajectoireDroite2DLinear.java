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

package net.bynaryscode.util.physic;

import net.bynaryscode.util.maths.geometric.Vec2d;

/**
 * Une trajectoire de mouvement rectiligne uniforme.
 * @author Louis JEAN
 */
public class TrajectoireDroite2DLinear extends Trajectoire2D {
	
	public TrajectoireDroite2DLinear() {
		super();
	}
	
	public TrajectoireDroite2DLinear(Vec2d startPoint, Vec2d endPoint) {
		super(startPoint, endPoint);
	}
	
	@Override
	public Vec2d getPointAt(float percent) {
		double x = startPoint.x + (endPoint.x - startPoint.x) * percent;
		double y = startPoint.y + (endPoint.y - startPoint.y) * percent;
		
		return new Vec2d(x, y);
	}

}
