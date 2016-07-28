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

public class Plane3D {
	
	/** Equation du plan : ax + by + cz + d = 0 */
	private double a, b, c, d;
	
	public Plane3D() {
		this(new Vec3d(), new Vec3d(), new Vec3d());
	}
	
	public Plane3D(double x1, double y1, double z1, double x2, double y2,
			double z2, double x3, double y3, double z3) {
		
		this(new Vec3d(x1, y1, z1),
				new Vec3d(x2, y2, z2),
				new Vec3d(x3, y3, z3));
	}
	
	public Plane3D(Vec3d vec1, Vec3d vec2, Vec3d vec3) {
		set(vec1, vec2, vec3);
	}
	
	public void set(Vec3d vec1, Vec3d vec2, Vec3d vec3) {
		Vec3d vd1 = vec2.substract(vec1);
		Vec3d vd2 = vec3.substract(vec1);
		Vec3d vn = vd1.crossProduct(vd2); vn.normalize();
		
		this.a = vn.getX();
		this.b = vn.getY();
		this.c = vn.getZ();
		
		this.d = - a * vec1.getX() - b * vec1.getY() - c * vec1.getZ();
	}
}
