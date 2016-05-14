package net.bynaryscode.util.physic;

import net.bynaryscode.util.maths.geometric.CoordonneesDouble;

/**
 * Le point suivant cette trajectoire parcourt une ligne droite.
 * Il accélère puis décélère au court de son mouvement. Son
 * accélèration puis sa décélération sont constantes.
 * @author Louis JEAN
 */
public class TrajectoireDroite2DSmooth extends Trajectoire2D {
	
	public TrajectoireDroite2DSmooth() {
		super();
	}
	
	public TrajectoireDroite2DSmooth(CoordonneesDouble startPoint, CoordonneesDouble endPoint) {
		super(startPoint, endPoint);
	}
	
	@Override
	public CoordonneesDouble getPointAt(float percent) {
		checkPercent(percent);
		
		//calcule, en fonction du pourcentage de temps écoulé, le pourcentage de distance parcourue.
		float var0 = percent <= 0.5f ? percent : 1f - percent;
		float var1 = var0 * var0 * 2;//La courbe de la distance en fonction du temps est une parabole.
		float distPercent = percent <= 0.5f ? var1 : 1f - var1;
		
		//détermination des coordonnées du point sur la ligne droite en fonction du pourcentage de
		//distance parcourue sur celle-ci.
		double vecX = this.endPoint.x - this.startPoint.x;
		double vecY = this.endPoint.y - this.startPoint.y;
		CoordonneesDouble ret = new CoordonneesDouble(
				this.startPoint.x + vecX * distPercent,
				this.startPoint.y + vecY * distPercent);
		
		return ret;
	}

}
