package net.bynaryscode.util.path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import net.bynaryscode.util.maths.geometric.Coordonnees;

public class PathFinder {
	
	private SpaceGraph graph;
	
	public PathFinder() {
		this(null);
	}
	
	public PathFinder(SpaceGraph graph) {
		this.graph = graph;
	}
	
	private LinkedList<SpaceGraphPoint> openList;
	private HashMap<Coordonnees, SpaceGraphPoint> closedList;
	
	/** Trouve et renvoie le chemin du point <tt>from</tt> au point <tt>to</tt>.
	 * Si aucun chemin n'est trouv�, renvoie <tt>null</tt>
	 * <p>Cette m�thode est une impl�mentation de l'algorithme A*. */
	public Path findPath(SpaceGraphPoint from, SpaceGraphPoint to) {
		if (this.graph.getHeuristic(from, to) == 0) return null;
		
		this.openList = new LinkedList<SpaceGraphPoint>();
		this.closedList = new HashMap<Coordonnees, SpaceGraphPoint>();
		
		SpaceGraphPoint lastNode = from;
		
		while (true) {
			SpaceGraphPoint[] newNodes = this.graph.getNearPoints(lastNode);
			
			for (SpaceGraphPoint node : newNodes) {
				if (this.closedList.containsKey(node.getLocation())) {
					continue;
				}
				
				node.setHeuristic(this.graph.getHeuristic(node, to));
				addNodeToOpenList(node);
			}
			
			lastNode = this.openList.removeFirst();
			this.closedList.put(lastNode.getLocation(), lastNode);
			
			if (lastNode == null || lastNode.getHeuristic() == 0) {
				break;
			}
		}
		
		if (lastNode == null) return null;
		
		return this.graph.buildPath(lastNode);
	}
	
	/** Ajoute le noeud pass� en param�tres � la liste ouverte. La liste ouverte
	 * est tri�e du noeud ayant l'heuristique la plus faible � celui � l'heuristique
	 * la plus forte.
	 * <p>Si le noeud est d�j� pr�sent dans la liste alors il est mis � jour. */
	private void addNodeToOpenList(SpaceGraphPoint point) {
		if (this.openList.size() == 0) {
			this.openList.add(point);
			return;
		}
		
		ListIterator<SpaceGraphPoint> openListItr = this.openList.listIterator();
		
		for (SpaceGraphPoint iPoint = openListItr.next() ; openListItr.hasNext() && point != null ; iPoint = openListItr.next()) {
			if (point.equals(iPoint)) {
				iPoint.setHeuristic(point.getHeuristic());
				point = null;
			}
			else if (iPoint.getHeuristic() >= point.getHeuristic()) {
				openListItr.previous();
				openListItr.add(point);
				point = null;
			}
		}
		
		if (point != null) {
			this.openList.addLast(point);
		}
	}
}
