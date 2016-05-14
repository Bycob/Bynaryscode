package net.bynaryscode.util.physic;

import net.bynaryscode.util.maths.MathUtil;
import net.bynaryscode.util.maths.geometric.CoordonneesDouble;

/**
 * Cette classe repr�sente la trajectoire d'un point en 2d au
 * cours du temps. L'�chelle de temps n'est en revanche pas
 * pr�cis�e.
 * <p>Ainsi on peut obtenir la localisation du point en
 * fonction du pourcentage de temps �coul�, sans se soucier
 * du temps total n�cessaire, qui peut varier.
 * 
 * @author Louis JEAN
 */
public abstract class Trajectoire2D {
	
	protected CoordonneesDouble startPoint, endPoint;
	
	public Trajectoire2D() {
		this(new CoordonneesDouble(), new CoordonneesDouble());
	}
	
	public Trajectoire2D(CoordonneesDouble startPoint, CoordonneesDouble endPoint) {
		this.startPoint = startPoint.clone();
		this.endPoint = endPoint.clone();
	}
	
	public CoordonneesDouble getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(CoordonneesDouble startPoint) {
		this.startPoint = startPoint;
	}
	
	public CoordonneesDouble getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(CoordonneesDouble endPoint) {
		this.endPoint = endPoint;
	}
	
	/**
	 * Donne les coordonn�es du point en d�placement � un
	 * certain moment de la trajectoire.
	 * @param percent - Le pourcentage de temps �coul� depuis
	 * le d�but de la trajectoire, par rapport au temps total
	 * n�cessaire pour parcourir la trajectoire. Cette valeur
	 * est comprise entre {@code 0f} et {@code 1f}.
	 * @return Les coordonn�es du point qui suit cette trajectoire
	 * � l'instant indiqu�.
	 */
	public abstract CoordonneesDouble getPointAt(float percent);
	
	/** Lance une exception si le pourcentage est au-del� de 100% ou
	 * en-de�� de 0% (la valeur doit �tre comprise dans [0f;1f]) */
	protected void checkPercent(float percent) {
		if (!MathUtil.isInRange_d(percent, 0f, 1f, true)) {
			throw new IllegalArgumentException(percent + " n'est pas un pourcentage de temps �coul� valide.");
		}
	}
}
