package net.bynaryscode.util.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingEventData implements ActionListener, MouseListener, KeyListener {
	
	private static boolean mouseState = false;
	
	public static boolean isMousePressed(){
		return mouseState;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseState = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseState = false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
