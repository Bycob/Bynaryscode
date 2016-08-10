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

package net.bynaryscode.util;

import net.bynaryscode.util.maths.MathUtil;

public class Color4f implements Cloneable {
	
	private float r;
	private float g;
	private float b;
	private float a;
	
	public Color4f() {
		this(1f, 1f, 1f, 1f);
	}
	
	public Color4f(float r, float g, float b) {
		this(r, g, b, 1f);
	}
	
	public Color4f(int r, int g, int b) {
		this(r, g, b, 255);
	}
	
	public Color4f(float r, float g, float b, float a) {
		this.r = MathUtil.valueInRange_f(r, 0, 1);
		this.g = MathUtil.valueInRange_f(g, 0, 1);
		this.b = MathUtil.valueInRange_f(b, 0, 1);
		this.a = MathUtil.valueInRange_f(a, 0, 1);
	}
	
	public Color4f(int r, int g, int b, int a) {
		float ri = MathUtil.valueInRange_f(r, 0, 255);
		float gi = MathUtil.valueInRange_f(g, 0, 255);
		float bi = MathUtil.valueInRange_f(b, 0, 255);
		float ai = MathUtil.valueInRange_f(a, 0, 255);
		
		this.r = ri / 255f;
		this.g = gi / 255f;
		this.b = bi / 255f;
		this.a = ai / 255f;
	}

	public float getRedf() {
		return r;
	}
	
	public int getRed() {
		return (int) (r * 255);
	}

	public void setRedf(float r) {
		if (MathUtil.isInRange_d(r, 0, 1, true))
			this.r = r;
	}

	public float getGreenf() {
		return g;
	}
	
	public int getGreen() {
		return (int) (g * 255);
	}

	public void setGreenf(float g) {
		if (MathUtil.isInRange_d(g, 0, 1, true))
			this.g = g;
	}

	public float getBluef() {
		return b;
	}
	
	public int getBlue() {
		return (int) (b * 255);
	}

	public void setBluef(float b) {
		if (MathUtil.isInRange_d(b, 0, 1, true))
			this.b = b;
	}

	public float getAlphaf() {
		return a;
	}
	
	public int getAlpha() {
		return (int) (a * 255);
	}

	public void setAlphaf(float a) {
		if (MathUtil.isInRange_d(a, 0, 1, true))
			this.a = a;
	}
	
	public Color4f withAlphaf(float a) {
		setAlphaf(a);
		return this;
	}
	
	public Color4f multiply(Color4f color) {
		return multiply(color.r, color.g, color.b, color.a);
	}
	
	public Color4f multiply(float r, float g, float b, float a) {
		return new Color4f(this.r * r, this.g * g, this.b * b, this.a * a);
	}
	
	public Color4f multiply(float coef) {
		return new Color4f(r * coef, g * coef, b * coef,  a * coef);
	}
	
	@Override
	public Color4f clone() {
		Color4f result = null;
		try {
			result = (Color4f) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Color4f)) return false;
		
		Color4f c = (Color4f) o;
		if (c == this) return true;
		
		if (	this.r != c.r
				|| this.g != c.g
				|| this.b != c.b
				|| this.a != c.a)
			
			return false;
		
		
		return true;
	}
}
