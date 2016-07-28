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
	 * Cr�e un nouvel �venement � la souris, ayant pour coordonnees (x, y)
	 * et bas� sur l'autre �venement pass� en param�tre.
	 * @param me - l'�venement d'origine.
	 * @param x - la nouvelle abscisse de l'�venement.
	 * @param y - la nouvelle ordonn�e de l'�venement.
	 * @return Un nouvel �venement, avec la m�me source, le m�me ID, le m�me
	 * bouton, ect... que l'�venement d'origine, mais les coordonn�es chang�es.
	 */
	public static MouseEvent createMouseEvent(MouseEvent me, int x, int y) {
		return new MouseEvent(me.getComponent(), me.getID(), me.getWhen(), me.getModifiers(), 
				x, y, me.getClickCount(), me.isPopupTrigger(), me.getButton());
	}
	
	/**
	 * Analyse et retourne l'entier contenu dans une cha�ne de caract�re
	 * provenant d'un {@link JFormattedTextField} format� avec
	 * {@link NumberFormat#getIntegerInstance()}.
	 * @param formatted - le texte contenu par le champ de texte
	 * @return Le nombre qui correspond au texte. Si {@code formatted}
	 * vaut  {@code ""}, le nombre retourn� sera �gal � 0.
	 * @throws NumberFormatException si la cha�ne envoy�e n'est pas
	 * une cha�ne de caract�re format�e correctement.
	 */
	public static int parseFormattedInteger(String formatted) {
		return Integer.parseInt(("0" + formatted).replace("�", ""));
	}
	
	/**
	 * Place le composant pass� en param�tre dans un composant
	 * "enveloppe", permettant ainsi d'att�nuer les d�formation caus�es
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
	 * Cr�e un s�parateur (un trait plein, de 1 pixel de haut,
	 * horizontal) de couleur grise. Le s�parateur est en fait un JPanel, et 
	 * donc peut �tre utilis� en temps que tel.
	 * @return Le s�parateur cr��.
	 */
	public static final JPanel getSeparator() {
		return getSeparator(Color.GRAY);
	}
	
	/**
	 * Cr�e un s�parateur (un trait plein, de 1 pixel de haut,
	 * horizontal) de la couleur demand�e. Le s�parateur est en fait un JPanel, et 
	 * donc peut �tre utilis� en temps que tel.
	 * @param color - la couleur du s�parateur cr��.
	 * @return Le s�parateur cr��.
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
