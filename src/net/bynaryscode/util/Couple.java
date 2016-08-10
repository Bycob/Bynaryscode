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

public class Couple<K, V> {
	
	public  K firstValue;
	public V secondValue;
	
	public Couple() {
		this(null, null);
	}
	
	public Couple(K firstValue, V secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	
	public K getFirstValue() {
		return firstValue;
	}
	
	public void setFirstValue(K firstValue) {
		this.firstValue = firstValue;
	}
	
	public V getSecondValue() {
		return secondValue;
	}
	
	public void setSecondValue(V secondValue) {
		this.secondValue = secondValue;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstValue == null) ? 0 : firstValue.hashCode());
		result = prime * result + ((secondValue == null) ? 0 : secondValue.hashCode());
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Couple)) {
			return false;
		}
		Couple other = (Couple) obj;
		if (firstValue == null) {
			if (other.firstValue != null) {
				return false;
			}
		} else if (!firstValue.equals(other.firstValue)) {
			return false;
		}
		if (secondValue == null) {
			if (other.secondValue != null) {
				return false;
			}
		} else if (!secondValue.equals(other.secondValue)) {
			return false;
		}
		return true;
	}
}
