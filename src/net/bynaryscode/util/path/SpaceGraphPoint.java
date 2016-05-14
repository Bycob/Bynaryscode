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
