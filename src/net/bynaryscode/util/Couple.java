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
