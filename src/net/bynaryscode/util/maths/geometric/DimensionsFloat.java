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
