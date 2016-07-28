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

package net.bynaryscode.util.path;

public abstract class SpaceGraph {
	
	/** Retourne tous les points directement accessibles depuis ce point. */
	public abstract SpaceGraphPoint[] getNearPoints(SpaceGraphPoint point);
	/** Renvoie une heuristique repr�sentant la distance qu'il reste �
	 * parcourir depuis le premier point pass� au param�tre pour arriver
	 * au second. Cas particulier : renvoie 0 lorsque <tt>point==destination</tt>. */
	public abstract int getHeuristic(SpaceGraphPoint point, SpaceGraphPoint destination);
	
	/**
	 * Construit un chemin qui se termine par le point pass� en param�tres,
	 * en remontant � ses points parents jusqu'� arriver au d�but du chemin.
	 * Le premier point du chemin sera le premier n<sup>eme</sup> parent � ne
	 * pas poss�der de parent lui-m�me.
	 * <p>Le curseur du chemin retourn� est plac� au d�but du chemin.
	 * @param end
	 * @return
	 */
	public Path buildPath(SpaceGraphPoint end) {
		Path result = new Path();
		result.addNode(end.getLocation());
		
		while (end.hasPrevious()) {
			end = end.getPrevious();
			result.addNodeFirst(end.getLocation());
		}
		
		result.begin();
		
		return result;
	}
}
