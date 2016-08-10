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

package net.bynaryscode.util.maths.geometric;

import java.io.Serializable;

import net.bynaryscode.util.maths.MathUtil;

public class Vec2f extends Vec2 implements Serializable {
	
	private static final long serialVersionUID = 6373262196341726681L;
	
	public float x;
	public float y;
	
	public Vec2f(){
		x = 0;
		y = 0;
	}
	
	public Vec2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public double getDistance(Vec2 c) {
		Vec2f other = c.asFloat();
		return MathUtil.getDistance(this.x, this.y, other.x, other.y);
	}
	
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	@Override
	public Vec2f clone(){
		return (Vec2f) super.clone();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		if (!(other instanceof Vec2f)) {
			return false;
		}
		Vec2f c = (Vec2f) other;
		
		return MathUtil.aproximatelyEquals(c.x, this.x, 0.00001) && MathUtil.aproximatelyEquals(c.y, this.y, 0.00001);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + this.x);
		result = (int) (prime * result + this.y);
		return result;
	}
	
	@Override
	public String toString() {
		return "(" + x + "; " + y + ")";
	}
	
	@Override
	public Vec2i asInteger() {
		return new Vec2i((int) x, (int) y);
	}
	
	@Override
	public Vec2d asDouble() {
		return new Vec2d(x, y);
	}
	
	@Override
	public Vec2f asFloat() {
		return this.clone();
	}
}
