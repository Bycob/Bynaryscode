package net.bynaryscode.util.path;

public abstract class SpaceGraph {
	
	/** Retourne tous les points directement accessibles depuis ce point. */
	public abstract SpaceGraphPoint[] getNearPoints(SpaceGraphPoint point);
	/** Renvoie une heuristique représentant la distance qu'il reste à
	 * parcourir depuis le premier point passé au paramètre pour arriver
	 * au second. Cas particulier : renvoie 0 lorsque <tt>point==destination</tt>. */
	public abstract int getHeuristic(SpaceGraphPoint point, SpaceGraphPoint destination);
	
	/**
	 * Construit un chemin qui se termine par le point passé en paramètres,
	 * en remontant à ses points parents jusqu'à arriver au début du chemin.
	 * Le premier point du chemin sera le premier n<sup>eme</sup> parent à ne
	 * pas posséder de parent lui-même.
	 * <p>Le curseur du chemin retourné est placé au début du chemin.
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
