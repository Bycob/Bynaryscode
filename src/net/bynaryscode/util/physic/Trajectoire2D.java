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

package net.bynaryscode.util.physic;

import net.bynaryscode.util.maths.MathUtil;
import net.bynaryscode.util.maths.geometric.Vec2d;

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
	
	protected Vec2d startPoint, endPoint;
	
	public Trajectoire2D() {
		this(new Vec2d(), new Vec2d());
	}
	
	public Trajectoire2D(Vec2d startPoint, Vec2d endPoint) {
		this.startPoint = startPoint.clone();
		this.endPoint = endPoint.clone();
	}
	
	public Vec2d getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(Vec2d startPoint) {
		this.startPoint = startPoint;
	}
	
	public Vec2d getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Vec2d endPoint) {
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
	public abstract Vec2d getPointAt(float percent);
	
	/** Lance une exception si le pourcentage est au-del� de 100% ou
	 * en-de�� de 0% (la valeur doit �tre comprise dans [0f;1f]) */
	protected void checkPercent(float percent) {
		if (!MathUtil.isInRange_d(percent, 0f, 1f, true)) {
			throw new IllegalArgumentException(percent + " n'est pas un pourcentage de temps �coul� valide.");
		}
	}
}
