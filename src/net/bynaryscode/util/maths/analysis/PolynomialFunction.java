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

package net.bynaryscode.util.maths.analysis;

import java.util.ArrayList;

import net.bynaryscode.util.maths.geometric.Vec2d;

public class PolynomialFunction implements MathFunction {
	
	@SafeVarargs
	public static final PolynomialFunction interpolate(Vec2d... points) {
		PolynomialFunction result = new PolynomialFunction();
		
		for (int i = 0 ; i < points.length ; i++) {
			PolynomialFunction lagrange = new PolynomialFunction(1);
			for (int j = 0 ; j < points.length ; j++) {
				if (i != j) {
					double dif = points[i].x - points[j].x;
					lagrange = lagrange.multiply(new PolynomialFunction(- points[j].x / dif, 1 / dif));
				}
			}
			result = result.add(lagrange.multiply(points[i].y));
		}
		
		return result;
	}
	
	private ArrayList<Double> coefficients = new ArrayList<Double>();
	
	public PolynomialFunction(double... coefficients) {
		setCoefficients(coefficients);
	}
	
	/** Définit les coefficients du polynome. Le premier coefficient est la constante,
	 * le deuxième celui du terme en x, le troisième celui du terme en x<sup>2</sup>...
	 * <p>Cette méthode définit <i>tous</i> les coefficients du polynome. Ainsi, les précédents
	 * coefficients seront <i>tous</i> supprimés. */
	public void setCoefficients(double... coefficients) {
		this.coefficients.clear();
		for (double coef : coefficients) {
			this.coefficients.add(coef);
		}
	}
	
	/** Définit le coefficient <tt>n</tt> du polynome, soit le coefficient du terme
	 * à la puissance <tt>n</tt>. */
	public void setCoefficient(int power, double coef) {
		while (power >= this.coefficients.size()) {
			this.coefficients.add(0.0);
		}
		this.coefficients.set(power, coef);
	}
	
	public double[] getCoefficients() {
		double[] result = new double[this.coefficients.size()];
		for (int i = 0 ; i < this.coefficients.size(); i++) result[i] = this.coefficients.get(i);
		return result;
	}
	
	public double getCoefficient(int power) {
		if (power < this.coefficients.size()) {
			return this.coefficients.get(power);
		}
		else {
			return 0;
		}
	}
	
	public int getDegree() {
		int result = this.coefficients.size() - 1;
		while (result >= 0 && this.getCoefficient(result) == 0) {
			result--;
		}
		
		return result;
	}
	
	public PolynomialFunction add(PolynomialFunction other) {
		PolynomialFunction result = new PolynomialFunction();
		int degree = Math.max(this.getDegree(), other.getDegree());
		result.coefficients.ensureCapacity(degree);
		
		for (int i = 0 ; i <= degree ; i++) {
			result.setCoefficient(i, this.getCoefficient(i) + other.getCoefficient(i));
		}
		
		return result;
	}
	
	public PolynomialFunction multiply(PolynomialFunction other) {
		PolynomialFunction result = new PolynomialFunction();
		int degree = other.getDegree() * this.getDegree();
		result.coefficients.ensureCapacity(degree);
		
		for (int i = 0 ; i <= other.getDegree() ; i++) {
			for (int j = 0 ; j <= this.getDegree() ; j++) {
				result.setCoefficient(i + j, result.getCoefficient(i + j) + other.getCoefficient(i) * this.getCoefficient(j));
			}
		}
		
		return result;
	}
	
	public PolynomialFunction multiply(double d) {
		return this.multiply(new PolynomialFunction(d));
	}
	
	@Override
	public double apply(double x) {
		double result = 0;
		for (int i = 0 ; i < this.coefficients.size() ; i++) {
			result += this.coefficients.get(i) * Math.pow(x, i);
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = getDegree() ; i >=0 ; i--) {
			double coef = getCoefficient(i);
			if (coef != 0) {
				if (i != getDegree()) result += " + ";
				result += coef + (i == 0 ? "" : (i == 1 ? "x" : "x^" + i));
			}
		}
		
		return result;
	}
}
