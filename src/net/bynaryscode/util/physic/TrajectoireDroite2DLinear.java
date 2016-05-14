package net.bynaryscode.util.physic;

import net.bynaryscode.util.maths.geometric.CoordonneesDouble;

/**
 * Une trajectoire de mouvement rectiligne uniforme.
 * @author Louis JEAN
 */
public class TrajectoireDroite2DLinear extends Trajectoire2D {
	
	public TrajectoireDroite2DLinear() {
		super();
	}
	
	public TrajectoireDroite2DLinear(CoordonneesDouble startPoint, CoordonneesDouble endPoint) {
		super(startPoint, endPoint);
	}
	
	@Override
	public CoordonneesDouble getPointAt(float percent) {
		double x = startPoint.x + (endPoint.x - startPoint.x) * percent;
		double y = startPoint.y + (endPoint.y - startPoint.y) * percent;
		
		return new CoordonneesDouble(x, y);
	}

}
