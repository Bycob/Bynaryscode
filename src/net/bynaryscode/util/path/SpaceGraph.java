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
