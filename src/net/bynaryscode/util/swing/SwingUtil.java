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

package net.bynaryscode.util.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.UIManager;

public final class SwingUtil {
	
	public static void setUpSystemLF() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Crée un nouvel évenement à la souris, ayant pour coordonnees (x, y)
	 * et basé sur l'autre évenement passé en paramètre.
	 * @param me - l'évenement d'origine.
	 * @param x - la nouvelle abscisse de l'évenement.
	 * @param y - la nouvelle ordonnée de l'évenement.
	 * @return Un nouvel évenement, avec la même source, le même ID, le même
	 * bouton, ect... que l'évenement d'origine, mais les coordonnées changées.
	 */
	public static MouseEvent createMouseEvent(MouseEvent me, int x, int y) {
		return new MouseEvent(me.getComponent(), me.getID(), me.getWhen(), me.getModifiers(), 
				x, y, me.getClickCount(), me.isPopupTrigger(), me.getButton());
	}
	
	/**
	 * Analyse et retourne l'entier contenu dans une chaîne de caractère
	 * provenant d'un {@link JFormattedTextField} formaté avec
	 * {@link NumberFormat#getIntegerInstance()}.
	 * @param formatted - le texte contenu par le champ de texte
	 * @return Le nombre qui correspond au texte. Si {@code formatted}
	 * vaut  {@code ""}, le nombre retourné sera égal à 0.
	 * @throws NumberFormatException si la chaîne envoyée n'est pas
	 * une chaîne de caractère formatée correctement.
	 */
	public static int parseFormattedInteger(String formatted) {
		return Integer.parseInt(("0" + formatted).replace(" ", ""));
	}
	
	/**
	 * Place le composant passé en paramètre dans un composant
	 * "enveloppe", permettant ainsi d'atténuer les déformation causées
	 * par certains layout comme le BoxLayout.
	 * @param component
	 * @return
	 */
	public static JPanel wrap(JComponent component) {
		JPanel ret = new JPanel();
		ret.add(component);
		return ret;
	}
	
	/**
	 * Crée un séparateur (un trait plein, de 1 pixel de haut,
	 * horizontal) de couleur grise. Le séparateur est en fait un JPanel, et 
	 * donc peut être utilisé en temps que tel.
	 * @return Le séparateur créé.
	 */
	public static final JPanel getSeparator() {
		return getSeparator(Color.GRAY);
	}
	
	/**
	 * Crée un séparateur (un trait plein, de 1 pixel de haut,
	 * horizontal) de la couleur demandée. Le séparateur est en fait un JPanel, et 
	 * donc peut être utilisé en temps que tel.
	 * @param color - la couleur du séparateur créé.
	 * @return Le séparateur créé.
	 */
	public static final JPanel getSeparator(Color color) {
		JPanel separator = new JPanel();
		separator.setBackground(color);
		
		Dimension dims = separator.getMaximumSize();
		dims.height = 1;
		separator.setPreferredSize(dims);
		
		return separator;
	}
}
