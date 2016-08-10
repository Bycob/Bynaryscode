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

import java.util.ArrayList;

/**
 * Manipule les numéros de versions sous la forme <code>"['V']X.X.X..."</code>
 * avec X des entiers.
 * @author Louis JEAN
 *
 */
public class Version implements Comparable<Version> {
	
	private String versionString;
	private ArrayList<Integer> versionNumbers = new ArrayList<Integer>();
	
	public static final int SAME_AS = 0;
	public static final int ABOVE = 1;
	public static final int LOWER = -1;
	
	public Version() {
		this("0.0");
	}
	
	public Version(String versionStr) {
		this.versionString = versionStr == null || versionStr.length() == 0 ? "0.0" : versionStr;
		parse();
	}
	
	private void parse() {
		this.versionNumbers.clear();
		
		if (this.versionString.charAt(0) == 'V') {
			this.versionString = this.versionString.substring(1);
		}
		
		int[] values = Util.extractIntegers(this.versionString);
		
		for (int value : values) {
			this.versionNumbers.add(value);
		}
		
		if (this.versionNumbers.size() == 0) {
			throw new NullPointerException("Pas de numéro de version !");
		}
	}
	
	public void setVersion(String versionStr) {
		if (versionStr == null || versionStr.length() == 0) throw new NullPointerException("Pas de numéro de version.");
		this.versionString = versionStr;
		parse();
	}
	
	@Override
	public int compareTo(Version v) {
		if (v == null) {
			throw new NullPointerException("v == null");
		}
		
		for (int i = 0 ; i < Math.max(this.versionNumbers.size(), v.versionNumbers.size()) ; i++) {
			if (i >= this.versionNumbers.size()) return LOWER;
			if (i >= v.versionNumbers.size()) return ABOVE;
			
			if (this.versionNumbers.get(i) > v.versionNumbers.get(i)) return ABOVE;
			if (this.versionNumbers.get(i) < v.versionNumbers.get(i)) return LOWER;
		}
		
		return SAME_AS;
	}
	
	public boolean include(Version v) {
		return this.compareTo(v) >= 0;
	}
	
	@Override
	public String toString() {
		String objstr = "V";
		for (int number : this.versionNumbers) {
			objstr += number + ".";
		}
		if (objstr.charAt(objstr.length() - 1) == '.') {
			objstr.substring(0, objstr.length() - 1);
		}
		return objstr;
	}
}
