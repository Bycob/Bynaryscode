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

public class AxisAlignedBox3D {
	
	public double x, y, z;
	public double sizeX, sizeY, sizeZ;
	
	public AxisAlignedBox3D() {
		this(0, 0, 0, 0, 0, 0);
	}
	
	public AxisAlignedBox3D(double x, double y, double z, double sizeX,
			double sizeY, double sizeZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
	}
	
	public Vec3d[] getPoints() {
		return new Vec3d[] {
			new Vec3d(x, y, z),
			new Vec3d(x + sizeX, y, z),
			new Vec3d(x + sizeX, y + sizeY, z),
			new Vec3d(x, y + sizeY, z),
			new Vec3d(x, y, z + sizeZ),
			new Vec3d(x + sizeX, y, z + sizeZ),
			new Vec3d(x + sizeX, y + sizeY, z + sizeZ),
			new Vec3d(x, y + sizeY, z + sizeZ)
		};
	}
}
