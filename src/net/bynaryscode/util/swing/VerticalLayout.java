package net.bynaryscode.util.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.ArrayList;

/**
 * Empile les composant de haut en bas suivant leur taille
 * définie au préalable.
 * @author Louis
 */
public class VerticalLayout implements LayoutManager2 {
	
	private Container cont = null;
	private ArrayList<Component> componentList = new ArrayList<Component>();
	
	private int gap = 0;
	
	/**
	 * Créé un VerticalLayout sur le container passé en paramètres.
	 * @param cont - le container à qui est appliqué le layout.
	 */
	public VerticalLayout(Container cont) {
		this.cont = cont;
	}
	
	public VerticalLayout(Container cont, int gap) {
		this(cont);
		this.gap = gap;
	}
	
	public int getGap() {
		return gap;
	}
	
	public void setGap(int gap) {
		this.gap = gap;
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
			c.setBounds(centerX - width / 2, y, width, d.height);
			
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
