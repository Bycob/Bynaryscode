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

package net.bynaryscode.util.swing;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class SwingInputData implements MouseListener, KeyListener {
	
	private static final SwingInputData instance = new SwingInputData();
	private static Component anchor;
	
	private static boolean mouseState = false;
	private static HashMap<Integer, Boolean> keyStateMap = new HashMap<Integer, Boolean>();
	
	private static boolean getKeyState(int key) {
		Boolean b = keyStateMap.get(key);
		return b == null ? false : b;
	}
	
	public static boolean isMousePressed() {
		synchronized (instance) {
			checkAnchor();
			return mouseState;
		}
	}
	
	public static boolean isKeyPressed(int key) {
		synchronized (instance) {
			checkAnchor();
			return getKeyState(key);
		}
	}
	
	/** Définit le point d'ancrage de la classe {@link SwingInputData} : un composant
	 * qui va pouvoir faire parvenir les évenements swing à cette classe. Ces évènements
	 * servent à déduire l'état des touches et des boutons de souris à un instant t. <p>
	 * Il est conseillé d'avoir un seul point d'ancrage pour une application. */
	public static void setAnchor(Component component) {
		synchronized (instance) {
			if (anchor != null) {
				anchor.removeMouseListener(instance);
				anchor.removeKeyListener(instance);
			}
			
			anchor = component;
			
			if (anchor != null) {
				anchor.addMouseListener(instance);
				anchor.addKeyListener(instance);
			}
		}
	}
	
	private static void checkAnchor() {
		if (anchor == null) throw new NullPointerException("No anchor !");
	}
	
	
	
	//Partie objet
	
	private SwingInputData() {
		
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent arg0) {
		keyStateMap.put(arg0.getKeyCode(), true);
	}

	@Override
	public synchronized void keyReleased(KeyEvent arg0) {
		keyStateMap.put(arg0.getKeyCode(), false);
	}

	@Override public void keyTyped(KeyEvent arg0) {}
	@Override public void mouseClicked(MouseEvent arg0) {}
	@Override public void mouseEntered(MouseEvent arg0) {}
	@Override public void mouseExited(MouseEvent arg0) {}

	@Override
	public synchronized void mousePressed(MouseEvent arg0) {
		mouseState = true;
	}

	@Override
	public synchronized void mouseReleased(MouseEvent arg0) {
		mouseState = false;
	}
}
