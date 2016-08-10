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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;

/**
 * Empile les composant de haut en bas suivant leur taille
 * définie au préalable.
 * @author Louis
 */
public class VerticalLayout implements LayoutManager2 {
	
	public static final int DEFAULT_GAP = 3;
	
	private Container cont = null;
	private ArrayList<Component> componentList = new ArrayList<Component>();
	
	private int gap;
	private Alignment alignment;
	
	/**
	 * Créé un VerticalLayout sur le container passé en paramètres.
	 * @param cont - le container à qui est appliqué le layout.
	 */
	public VerticalLayout(Container cont) {
		this(cont, DEFAULT_GAP, Alignment.CENTER);
	}
	
	public VerticalLayout(Container cont, int gap) {
		this(cont, gap, Alignment.CENTER);
	}
	
	public VerticalLayout(Container cont, Alignment alignment) {
		this(cont, DEFAULT_GAP, alignment);
	}
	
	public VerticalLayout(Container cont, int gap, Alignment alignment) {
		this.cont = cont;
		this.gap = gap;
		setAlignment(alignment);
	}
	
	public int getGap() {
		return gap;
	}
	
	public void setGap(int gap) {
		this.gap = gap;
	}
	
	public Alignment getAlignment() {
		return this.alignment;
	}
	
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment == null ? Alignment.CENTER : alignment;
	}
	
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dims = new Dimension(0, 0);
		
		for (Component c : this.componentList) {
			Dimension cDims = c.getPreferredSize();
			
			if (cDims.width > dims.width) {
				dims.width = cDims.width;
			}
			
			dims.height += cDims.height + gap;
		}
		
		if (this.componentList.size() != 0) {
			dims.height += gap;
		}
		return dims;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	@Override
	public Dimension maximumLayoutSize(Container target) {
		return preferredLayoutSize(target);
	}

	@Override
	public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int top = insets.top;
        //int bottom = parent.getHeight() - insets.bottom;
        int left = insets.left;
        int right = parent.getWidth() - insets.right;
        
		final int centerX = (right - left) / 2;
		int y = this.gap + top;
		
		for (Component c : this.componentList) {
			Dimension d = c.getPreferredSize();
			int width = Math.min(right - left, d.width);
			
			int x;
			switch (this.alignment) {
			case LEADING:
				x = left + gap;
				break;
			case CENTER:
			default:
				x = centerX - width / 2;
				break;
			
			}
			
			c.setBounds(x, y, width, d.height);
			
			y += d.height + this.gap;
		}
	}
	
	@Override
	public void addLayoutComponent(Component comp, Object constraints) {
		this.componentList.add(comp);
	}
	
	@Override
	public void addLayoutComponent(String name, Component comp) {
		addLayoutComponent(comp, name);
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		this.componentList.remove(comp);
	}

	@Override
	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container target) {
		return 0;
	}

	@Override
	public void invalidateLayout(Container target) {
		
	}
}
