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
