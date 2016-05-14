package net.bynaryscode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

public class RangeMap<K extends Number, V> {
	
	private ArrayList<RangeMapEntry<K, V>> entryList = new ArrayList<RangeMapEntry<K, V>>();
	
	public RangeMap() {
		
	}
	
	public void put(K key, V value) {
		int minorIndex = getMinorIndex(key);
		this.entryList.add(minorIndex + 1, new RangeMapEntry<K, V>(key, value));
	}
	
	/** Donne un couple de deux valeurs dont les cl�s encadrent au plus proche
	 * le nombre pass� en param�tres.<p> C'est-�-dire, on retourne le couple
	 * (v1, v2) ayant pour cl� (c1, c2) tels que c1 soit la cl� la plus grande,
	 * inf�rieure ou �gale au nombre pass� en param�tres, et c2 soit la cl� la
	 * plus petite strictement sup�rieure au nombre pass� en param�tres, ce dans
	 * toute la {@link RangeMap}. */
	public Couple<V, V> get(K key) {
		if (this.entryList.size() == 0) return new Couple<V, V>(null, null);
		
		int minorIndex = getMinorIndex(key);
		
		if (minorIndex == -1) {
			return new Couple<V, V>(null, this.entryList.get(0).value);
		}
		if (minorIndex == this.entryList.size() - 1) {
			return new Couple<V, V>(this.entryList.get(minorIndex).value, null);
		}
		return new Couple<V, V>(this.entryList.get(minorIndex).value, this.entryList.get(minorIndex + 1).value);
	}
	
	/** 
	 * Donne un tableau des valeurs autour de la cl� pass�e en param�tres.
	 * <p>En d'autres termes, donne les <tt>radius</tt> valeurs dont la cl� est
	 * juste inf�rieure ou �gale � la cl� <tt>key</tt> dans la premi�re partie
	 * du tableau, et les <tt>radius</tt> valeurs juste sup�rieures ou �gales.
	 * <p>Si <tt>key</tt> est �gale � la cl� d'une des valeurs dans la map, alors
	 * le tableau comportera <tt>(radius * 2 - 1)</tt> valeurs, soit une de moins
	 * que lorsque <tt>key</tt> tombe entre deux cl�s de la map. */
	public V[] getAround(K key, int radius, V[] array) {
		int minorIndex = getMinorIndex(key);
		int length = radius * 2;
		
		if (minorIndex != -1 && compareKey(key, this.entryList.get(minorIndex).key) == 0) {
			length--;
		}
		
		if (array.length <= length) {
			array = Arrays.copyOf(array, length);
		}
		
		for (int i = 0 ; i < length ; i++) {
			int listIndex = minorIndex - (length - 1) / 2 + i;
			if (listIndex < 0 || listIndex >= this.entryList.size()) {
				array[i] = null;
			}
			else {
				array[i] = this.entryList.get(listIndex).value;
			}
		}
		
		return array;
	}
	
	/** Donne l'indice de la borne la plus proche par d�faut de la cl� dans
	 * la liste des entr�es. Proc�de par dichotomie.
	 * <p>Si toutes les cl�s majorent la cl� pass�e en param�tres, alors on
	 * renvoie -1. Si toutes les cl�s minorent la cl� pass�e en param�tres,
	 * alors on renvoie le plus grand indice de la liste.
	 * <p>Si une cl� est �gale � la cl� pass� en param�tres, on retourne son
	 * indice.
	 * <p>Si la liste des entr�es est vide, retourne -1. */
	private int getMinorIndex(K key) {
		int inf = 0;
		int sup = this.entryList.size() - 1;
		
		if (this.entryList.size() == 0) return -1;
		if (compareKey(key, this.entryList.get(inf).key) < 0) {
			return -1;
		}
		if (compareKey(key, this.entryList.get(sup).key) > 0) {
			return sup;
		}
		
		while (sup - inf > 1) {
			int avg = (sup + inf) / 2;
			RangeMapEntry<K, V> avgEntry = this.entryList.get(avg);
			
			if (compareKey(key, avgEntry.key) > 0) {
				sup = avg;
			}
			else {
				inf = avg;
			}
		}
		
		return inf;
	}
	
	/** Indique si la cl� 1 est sup�rieure, inf�rieure ou �gale � la
	 * cl� 2, selon les conventions de la m�thode {@link Comparable#compareTo(Object)} */
	@SuppressWarnings("unchecked")
	private int compareKey(K key1, K key2) {
		int result = 0;

		Class<? extends Number> keyClass = key1.getClass();
		
		if (!keyClass.isPrimitive()) {
			boolean success = false;
			
			if (Comparable.class.isAssignableFrom(keyClass)) {
				
				try {
					result = ((Comparable<K>) key1).compareTo(key2);
					success = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (!success) {
				result = (int) Math.signum(key1.doubleValue() - key2.doubleValue());
			}
		}
		else if (keyClass == Byte.class || keyClass == Short.class || keyClass == Integer.class || keyClass == Long.class) {
			long diff = key1.longValue() - key2.longValue();
			result = diff > 0 ? 1 : (diff < 0 ? -1 : 0);
		}
		else {
			double diff = key1.doubleValue() - key2.doubleValue();
			result = diff > 0 ? 1 : (diff < 0 ? -1 : 0);
		}
		return result;
	}
	
	public static class RangeMapEntry<K extends Number, V> implements Entry<K, V> {
		public K key;
		public V value;
		
		public RangeMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
	}
}
