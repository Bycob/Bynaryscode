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

package net.bynaryscode.util.maths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.bynaryscode.util.maths.geometric.Vec2;
import net.bynaryscode.util.maths.geometric.Vec2d;

public class MathUtil {
	
	/**
	 * Vérifie que les deux nombres a et b sont séparés d'une distance inférieure
	 * au paramètre {@code dif}
	 * @param a - le premier nombre.
	 * @param b - le second nombre.
	 * @param dif - la différence maximum qu'il peut y avoir entre ces deux nombres
	 * avant que ceux-ci soient considérés comme différents.
	 * @return {@code true} si les deux nombres sont à peut près égaux, {@code false}
	 * sinon.
	 */
	public static boolean aproximatelyEquals(double a, double b, double dif) {
		return absoluteDifference(a, b) < Math.abs(dif);
	}
	
	public static boolean valueIn(double value, double min, double max, boolean included){
		if (included)
			return value >= min && value <= max;
		else
			return value > min && value < max;
	}
	
	public static boolean valuesIn(double[] values, double min, double max, boolean included){
		boolean result = true;
		
		for (double value : values){
			if (included){
				if (!(result = value >= min && value <= max)) {
					return false;
				}
			}
			else {
				if (!(result = value > min && value < max)) {
					return false;
				}
			}
		}
		
		return result;
	}
	
	public static double valueIn(double value, double min, double max){
		return (value >= min && value <= max)? value : ((value < min)? min : max);
	}
	
	public static float valueIn(float value, float min, float max){
		return (value >= min && value <= max)? value : ((value < min)? min : max);
	}
	
	public static int valueIn(int value, int min, int max){
		return (value >= min && value <= max)? value : ((value < min)? min : max);
	}
	
	public static int integerPart(double dbl) {
		return (int) dbl;
	}
	
	public static double decimalPart(double dbl) {
		return dbl - integerPart(dbl);
	}
	
	public static double round10(double dbl, int digit) {
		return Math.round(dbl * Math.pow(10, digit)) / Math.pow(10, digit);
	}
	
	public static double addAngle(double angle, double plusValue) {
		angle += plusValue;
		return angleMainValue(angle);
	}
	
	public static double absoluteDifference(double a, double b) {
		return a > b ? a - b : b - a; 
	}
	
	public static boolean isAngleBetween(double angle, double min, double max) {
		angle = angleMainValue(angle);
		min = angleMainValue(min);
		max = angleMainValue(max);
		
		if (min <= max) {
			return MathUtil.valueIn(angle, min, max, true);
		}
		else {
			if (angle > min) {
				return MathUtil.valueIn(angle, min, max + Math.PI * 2d, true);
			}
			else {
				return MathUtil.valueIn(angle, min - Math.PI * 2d, max, true);
			}
		}
	}
	
	/**
	 * Donne la valeur d'un angle en radian comprise entre
	 * <code>0</code> et <code>pi * 2</code>
	 */
	public static double angleMainValue(double angle) {
		while (angle >= Math.PI * 2.0) {
			angle -= Math.PI * 2.0;
		}
		
		while (angle < 0) {
			angle += Math.PI * 2.0;
		}
		
		return angle;
	}
	
	/**
	 * Donne l'angle qui à pour cosinus <code>cos</code> et pour
	 * sinus <code>sin</code> (en radians).
	 * @param cos - Le cosinus de l'angle à trouver.
	 * @param sin - Le sinus de l'angle à trouver.
	 * @return L'angle qui à cos pour cosinus et sin pour sinus, en
	 * radians. L'angle retourné est compris entre 0 et 2*PI
	 */
	public static double atan2(double cos, double sin) {
		cos = valueIn(cos, -1, 1);
		sin = valueIn(sin, -1, 1);
		
		double angSin = Math.asin(sin);
		double angCos = Math.acos(cos);
		double angMoy = (angCos + angSin) / 2.0;
		
		if (cos >= 0 && sin >= 0) {
			return angMoy;
		}
		else if (sin < 0 && cos >= 0) {
			return angSin + 2 * Math.PI;
		}
		else if (cos < 0 && sin >= 0) {
			return angCos;
		}
		else {
			return (Math.PI * 2 - angCos);
		}
	}
	
	/**
	 * Fait tourner le point passé en paramètre autour du centre de
	 * rotation indiqué, de la rotation indiquée.
	 * @param point - Le point à faire tourner.
	 * @param centerX - Abscisse du centre de rotation.
	 * @param centerY - Ordonnée du centre de rotation.
	 * @param rotation - L'angle de rotation à appliquer au point, en
	 * radians.
	 * @return Les nouvelles coordonnées du point, l'ancien objet 
	 * {@link Vec2} n'est pas modifié.
	 */
	public static Vec2d rotatePoint(Vec2 point,
			double centerX, double centerY, double rotation) {
		
		Vec2d p = point.asDouble();
		
		double dist = getLength(p.x, p.y, centerX, centerY);
		
		double sinO = (p.y - centerY) / dist;
		double cosO = (p.x - centerX) / dist;
		double angleO = atan2(cosO, sinO);
		
		double angleDest = addAngle(angleO, rotation);
		p.x = centerX + (Math.cos(angleDest) * dist);
		p.y = centerY + (Math.sin(angleDest) * dist);
		
		return p;
	}
	
	public static double getLength(Vec2 c1, Vec2 c2) {
		Vec2d cd1 = c1.asDouble();
		Vec2d cd2 = c2.asDouble();
		return getLength(cd1.x, cd1.y, cd2.x, cd2.y);
	}
	
	public static double getSquaredLength(Vec2 c1, Vec2 c2) {
		Vec2d cd1 = c1.asDouble();
		Vec2d cd2 = c2.asDouble();
		return getSquaredLength(cd1.x, cd1.y, cd2.x, cd2.y);
	}
	
	public static double getLength(double xA, double yA, double xB, double yB) {
		return Math.sqrt(getSquaredLength(xA, yA, xB, yB));
	}
	
	public static double getSquaredLength(double xA, double yA, double xB, double yB) {
		return Math.pow(xA - xB, 2) + Math.pow(yA - yB, 2);
	}
	
	public static Vec2 interpolateLinear(Vec2 point1, Vec2 point2, double param) {
		Vec2d pointd1 = point1.asDouble();
		Vec2d pointd2 = point2.asDouble();
		
		Vec2d result = new Vec2d(
				pointd1.x + (pointd2.x - pointd1.x) * param,
				pointd1.y + (pointd2.y - pointd1.y) * param);
		
		return result;
	}
	
	public static double nextGaussian(Random rand, double esperance, double ecartType) {
		return rand.nextGaussian() * ecartType + esperance;
	}
	
	/**
	 * Crée une map contenant les proportions en quantité de chaque
	 * objet que l'on peut trouver dans la liste.
	 * @param data - La liste qui contient les données.
	 * @return Une map associant pour chaque sorte d'élément existant
	 * dans la liste sa fréquence.
	 */
	public static <K extends Enum<?>> Map<K, Double> statsDiagram(List<K> data) {
		HashMap<K, Double> result = new HashMap<K, Double>();
		HashMap<K, Integer> count = new HashMap<K, Integer>();
		
		for (int i = 0 ; i < data.size() ; i++) {
			K dat = data.get(i);
			if (count.containsKey(dat)) {
				count.put(dat, count.get(dat) + 1);
			}
			else {
				count.put(dat, 1);
			}
		}
		
		for (Entry<K, Integer> e : count.entrySet()) {
			result.put(e.getKey(), new Double(e.getValue() / (double) data.size()));
		}
		
		return result;
	}
}
