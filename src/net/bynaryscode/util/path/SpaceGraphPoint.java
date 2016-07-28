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

import net.bynaryscode.util.maths.geometric.Coordonnees;

public abstract class SpaceGraphPoint {
	
	private float heuristic = -1;
	private SpaceGraphPoint prev;
	
	public abstract Coordonnees getLocation();
	
	public boolean hasHeuristic() {
		return this.heuristic == -1;
	}
	
	public void eraseHeuristic() {
		this.heuristic = -1;
	}
	
	public void setHeuristic(float h) {
		this.heuristic = h;
	}
	
	public float getHeuristic() {
		return this.heuristic;
	}
	
	public SpaceGraphPoint getPrevious() {
		return prev;
	}
	
	public void setPrevious(SpaceGraphPoint prev) {
		this.prev = prev;
	}
	
	public boolean hasPrevious() {
		return this.prev != null;
	}
}
