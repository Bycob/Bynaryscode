package net.bynaryscode.util.maths.geometric;

public class Line2D {
	
	/** Equation de la droite : ax + by + c = 0 */
	private double a, b, c;
	
	public Line2D(Vec3d pt1, Vec3d pt2) {
		set(pt1, pt2);
	}
	
	public Line2D(double x1, double y1, double x2, double y2) {
		set(x1, y1, x2, y2);
	}
	
	public void set(Vec3d pt1, Vec3d pt2) {
		Vec3d vecDir = pt2.substract(pt1);
		this.a = - vecDir.getY();
		this.b = vecDir.getX();
		this.c = - this.b * pt1.getY() - this.a * pt1.getX();
	}
	
	public void set(double x1, double y1, double x2, double y2) {
		this.set(new Vec3d(x1, y1), new Vec3d(x2, y2));
	}
	
	public Vec3d getDirectionVector() {
		return new Vec3d(- this.a, this.b);
	}
	
	public boolean contains(Vec3d pt) {
		return contains(pt.x, pt.y);
	}
	
	public boolean contains(double x, double y) {
		return equation(x, y) == 0;
	}
	
	public double distance(double x, double y) {
		return Math.sqrt(squaredDistance(x, y));
	}

	public double squaredDistance(double x, double y) {
		double eq = equation(x, y);
		return eq * eq / (a * a + b * b);
	}
	
	/** @deprecated */
	public CoordonneesDouble getOrthographicProjection(double x, double y) {
		return new CoordonneesDouble();
	}
	
	/** Retourne le résultat de l'opération ax + bx + c. Le résultat est positif
	 * si le point est à gauche de la droite (sens de la droite donné par le vecteur
	 * directeur {@link #getDirectionVector()}) et négatif si le point est à droite
	 * de la droite. Si le résultat est nul, alors le point est sur la droite. */
	public double equation(Vec3d pt) {
		return equation(pt.x, pt.y);
	}
	
	/** @see #equation(Vec3d) */
	public double equation(double x, double y) {
		return a * x + b * y + c;
	}
	
	public String getEquationStr() {
		return a + "x + " + b + "y + " + c + " = 0";
	}
	
	@Override
	public String toString() {
		return this.getEquationStr();
	}
}
