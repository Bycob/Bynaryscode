package net.bynaryscode.util.maths.geometric;

public abstract class Rectangle implements Forme {
	
	public static RectangleDouble getBounds(Forme f) {
		RectangleDouble result;
		
		if (f instanceof Circle) {
			Circle c = (Circle) f;
			CoordonneesDouble centre = c.center();
			double rayon = c.getRayon();
			result = new RectangleDouble(
					centre.x - rayon, centre.y - rayon,
					centre.x + rayon, centre.y + rayon);
			return result;
		}
		
		CoordonneesDouble[] sommets = f.getSommets();
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
	public abstract boolean contains(Coordonnees c);
}
