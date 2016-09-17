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

import java.util.List;

public class AxisAlignedBox3D implements Cloneable {
	
	public double x, y, z;
	public double sizeX, sizeY, sizeZ;
	
	/** Crée la plus petite AABB à contenir tous les points de la liste passée
	 * en paramètres. */
	public static AxisAlignedBox3D createBoxFromList(List<Vec3d> points) {
		if (points.isEmpty()) return new AxisAlignedBox3D();
		
		Vec3d firstPoint = points.get(0);
		double xmin = firstPoint.x, ymin = firstPoint.y, zmin = firstPoint.z, xmax = firstPoint.x, ymax = firstPoint.y, zmax = firstPoint.z;
		
		for (int i = 1 ; i < points.size() ; i++) {
			Vec3d point = points.get(i);
			if (point.x < xmin) {
				xmin = point.x;
			}
			else if (point.x > xmax) {
				xmax = point.x;
			}
			if (point.y < ymin) {
				ymin = point.y;
			}
			else if (point.y > ymax) {
				ymax = point.y;
			}
			if (point.z < zmin) {
				zmin = point.z;
			}
			else if (point.z > zmax) {
				zmax = point.z;
			}
		}

		return new AxisAlignedBox3D(xmin, ymin, zmin, xmax - xmin, ymax - ymin, zmax - zmin);
	}
	
	public static AxisAlignedBox3D merge(AxisAlignedBox3D... boxes) {
		AxisAlignedBox3D result = null;
		
		for (AxisAlignedBox3D box : boxes) {
			if (result == null) result = box.clone();
			else {
				if (result.x > box.x) {
					result.x = box.x;
				}
				if (result.x + result.sizeX < box.x + box.sizeX) {
					result.sizeX = box.x + box.sizeX - result.x;
				}
				if (result.y > box.y) {
					result.y = box.y;
				}
				if (result.y + result.sizeY < box.y + box.sizeY) {
					result.sizeY = box.y + box.sizeY - result.y;
				}
				if (result.z > box.z) {
					result.z = box.z;
				}
				if (result.z + result.sizeZ < box.z + box.sizeZ) {
					result.sizeZ = box.z + box.sizeZ - result.z;
				}
			}
		}
		
		if (result == null) result = new AxisAlignedBox3D();
		return result;
	}
	
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
	
	public void translate(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
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
	
	@Override
	public AxisAlignedBox3D clone() {
		AxisAlignedBox3D clone = null;
		
		try {
			clone = (AxisAlignedBox3D) super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
