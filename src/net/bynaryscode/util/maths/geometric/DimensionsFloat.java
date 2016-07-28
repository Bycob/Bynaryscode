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

public class DimensionsFloat implements Serializable  {
			
			private static final long serialVersionUID = 1L;
			
			private float width;
			private float height;
			
			public DimensionsFloat(){
				
			}
			
			public DimensionsFloat(float x, float y){
				if (x<0 || y <0)
					throw new IllegalArgumentException();
				this.setWidth(x);
				this.setHeight(y);
			}
			
			public float getWidth() {
				return width;
			}

			public void setWidth(float x) {
				if (x<0)
					throw new IllegalArgumentException();
				this.width = x;
			}

			public float getHeight() {
				return height;
			}

			public void setHeight(float y) {
				if (y <0)
					throw new IllegalArgumentException();
				this.height = y;
			}

			@Override
			public DimensionsFloat clone(){
				return new DimensionsFloat(width,height);
			}
}
