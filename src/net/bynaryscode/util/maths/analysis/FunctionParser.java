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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser {
	
	public static PolynomialFunction parsePolynomial(String function) {
		String regex = "(^[\\+-]?|[\\+-])\\s*(\\d*\\.?\\d*)\\s*(x(\\^(\\d+))?)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(function);
		
		PolynomialFunction result = new PolynomialFunction();
		while (matcher.find()) {
			if ("".equals(matcher.group())) {
				continue;
			}
			
			//Détermination de la puissance
			String powerStr = matcher.group(5);
			int power = 0;
			if (!"".equals(powerStr) && powerStr != null) {
				power = Integer.parseInt(powerStr);
			}
			else if ("x".equals(matcher.group(3))) {
				power = 1;
			}
			//else power = 0
			
			//Coefficient
			if (".".equals(matcher.group(2))) {
				throw new IllegalArgumentException("La fonction passée en paramètres n'est pas valide");
			}
			String coefStr = matcher.group(1) + matcher.group(2);
			
			result.setCoefficient(power, result.getCoefficient(power) + Double.parseDouble(coefStr));
		}
		return result;
	}
}
