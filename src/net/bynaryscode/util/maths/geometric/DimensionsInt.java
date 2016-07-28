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

package net.bynaryscode.util.maths.geometric;

import java.io.Serializable;

public class DimensionsInt implements Serializable, Cloneable  {
	
	private static final long serialVersionUID = -1989326028640201827L;
	
	private int width;
	private int height;
	
	public DimensionsInt(){
		this(0, 0);
	}
	
	public DimensionsInt(int x, int y){
		if (x<0 || y <0)
			throw new IllegalArgumentException();
		this.setWidth(x);
		this.setHeight(y);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int x) {
		if (x<0)
			throw new IllegalArgumentException();
		this.width = x;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int y) {
		if (y <0)
			throw new IllegalArgumentException();
		this.height = y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof DimensionsInt)) {
			return false;
		}
		
		DimensionsInt obj = (DimensionsInt) other;
		
		return obj.getWidth() == this.width && obj.getHeight() == this.height;
	}

	@Override
	public DimensionsInt clone(){
		DimensionsInt clone = null;
		try {
			clone = (DimensionsInt) super.clone();
		} catch (CloneNotSupportedException e) {}
		
		return clone;
	}
}
