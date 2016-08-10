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

package net.bynaryscode.util.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import net.bynaryscode.util.Util;
import net.bynaryscode.util.maths.geometric.Vec2;

public class Path implements Cloneable {

	public static class Node implements Cloneable {
		
		/** Les croisements avec d'autre chemins en ce noeud précisément. */
		private ArrayList<Path> crossroads = new ArrayList<Path>(4);
		/** Le point qui caractérise le noeud. */
		private Vec2 point;
		
		private Node(Vec2 point) {
			this.point = point;
		}
		
		public Vec2 getPoint() {
			return this.point;
		}
		
		public Path[] getCrossroads() {
			return this.crossroads.toArray(new Path[0]);
		}
		
		public void setCrossroads(Path... crossroads) {
			this.removeAllCrossroads();
			for (Path crossroad : crossroads) {
				this.crossroads.add(crossroad);
			}
		}
		
		public void removeAllCrossroads() {
			this.crossroads.clear();
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((crossroads == null) ? 0 : crossroads.hashCode());
			result = prime * result + ((point == null) ? 0 : point.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Node)) {
				return false;
			}
			Node other = (Node) obj;
			if (!Util.listEqualsUnsorted(crossroads, other.crossroads)) {
				return false;
			}
			if (!point.asDouble().equals(other.point.asDouble())) {
				return false;
			}
			return true;
		}
		
		@Override
		public Node clone() {
			Node clone = null;
			
			try {
				clone = (Node) super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
			clone.point = this.point.clone();
			
			clone.crossroads = new ArrayList<Path>(this.crossroads.size());
			for (Path path : this.crossroads) {
				clone.crossroads.add(path.clone());
			}
			
			return clone;
		}
	}
	
	private LinkedList<Node> pathPoints = new LinkedList<Node>();
	private ListIterator<Node> pathIterator = this.pathPoints.listIterator();
	
	public Path() {
		
	}
	
	/** Ajoute un noeud au chemin et place le curseur après ce noeud. */
	public Node addNode(Vec2 point) {
		Node n = new Node(point);
		this.pathIterator.add(n);
		return n;
	}
	
	/** Ajoute le noeud au début du chemin. La place du curseur est inchangée. */
	public Node addNodeFirst(Vec2 point) {
		Node n = new Node(point);
		
		int currentIndex = getCurrentIndex();
		this.pathPoints.addFirst(n);
		
		goToIndex(currentIndex + 1);
		return n;
	}

	public void reverse() {
		int newIndex = this.pathPoints.size() - 1 - this.getCurrentIndex();
		Collections.reverse(this.pathPoints);
		this.pathIterator = this.pathPoints.listIterator(newIndex);
	}

	/** Renvoie le curseur au début du chemin. */
	public void begin() {
		this.pathIterator = this.pathPoints.listIterator();
		//On se positionne sur le premier élément.
		next();
	}
	
	public void goToIndex(int index) {
		index = Math.min(this.pathPoints.size(), Math.max(index + 1, 1));
		this.pathIterator = this.pathPoints.listIterator(index);
	}
	
	public void goToEnd() {
		goToIndex(this.pathPoints.size() - 1);
	}

	public Node getCurrent() {
		if (this.pathIterator.hasPrevious()) {
			this.pathIterator.previous();
		}
		return next();
	}
	
	public int getCurrentIndex() {
		return this.pathIterator.previousIndex();
	}
	
	/** Retourne le point suivant et incrémente le curseur. Plusieurs appels de
	 * {@link #next()} retournerons les différents points du chemin. */
	public Node next() {
		return this.pathIterator.hasNext() ? this.pathIterator.next() : null;
	}

	/** Retourne le point suivant sans incrémenter le curseur. Plusieurs appels
	 * de {@link #getNext()} consécutifs retournerons toujours le même point.
	 * <p>S'il n'y a pas de points suivant (si la méthode {@link #hasNext()}
	 * retourne <tt>false</tt>) alors cette méthode retournera {@code null}.*/
	public Node getNext() {
		Node n = next();
		if (this.pathIterator.hasPrevious()) this.pathIterator.previous();
		return n;
	}
	
	public boolean hasNext() {
		return this.pathIterator.hasNext();
	}
	
	public Node getLast() {
		int index = this.getCurrentIndex() - 1;
		return index >= 0 ? this.pathPoints.get(index) : null;
	}
	
	public boolean isEnd() {
		return this.pathIterator.hasNext();
	}
	
	public int getLength() {
		return this.pathPoints.size();
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Node n : pathPoints) {
			result += n.getPoint() + " > ";
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pathPoints == null) ? 0 : pathPoints.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Path)) {
			return false;
		}
		Path other = (Path) obj;
		if (!pathPoints.equals(other.pathPoints)) {
			return false;
		}
		return true;
	}
	
	/** Retourne le nombre de noeuds que ce chemin possède en commun avec celui passé
	 * en paramètres, à partir du début. 
	 * 
	 * <p>{@code <!!!>} Les différentes branches ne sont pas comptabilisées dans la recherche.*/
	public int countSameNodes(Path other) {
		if (this.getLength() == 0 || other.getLength() == 0) return 0;
		
		int count = 0;
		
		Node n1 = this.pathPoints.getFirst(), n2 = other.pathPoints.getFirst();
		Iterator<Node> i1 = this.pathPoints.iterator(), i2 = other.pathPoints.iterator();
		for (n1 = i1.next() , n2 = i2.next() ; i1.hasNext() && i2.hasNext() ; n1 = i1.next() , n2 = i2.next()) {
			if (n1.point.equals(n2.point)) {
				count++;
			}
			else {
				break;
			}
		}
		
		return count;
	}
	
	@Override
	public Path clone() {
		Path clone = null;
		
		try {
			clone = (Path) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		clone.pathPoints = new LinkedList<Node>();
		for (Node n : this.pathPoints) {
			clone.pathPoints.add(n.clone());
		}
		
		clone.goToIndex(this.getCurrentIndex());
		
		return clone;
	}
}
