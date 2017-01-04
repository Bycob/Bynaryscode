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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import net.bynaryscode.util.maths.MathUtil;
import net.bynaryscode.util.maths.geometric.RectangleDouble;
import net.bynaryscode.util.maths.geometric.Vec2;
import net.bynaryscode.util.maths.geometric.Vec2d;

public final class Util {

	/** Retourne <tt>true</tt> si et seulement si le tableau contient la valeur
	 * pass�e en param�tres. Si la valeur vaut <tt>null</tt> on recherche une
	 * valeur du tableau qui vaut <tt>null</tt>. Sinon, on recherche une valeur
	 * <tt>val</tt> dans le tableau telle que :
	 * <blockquote><tt>value.equals(val)</tt></blockquote> */
	public static boolean arrayContains(Object[] array, Object value) {
		if (array == null) throw new NullPointerException("array == null !");
		if (array.length == 0) return false;
		
		if (value != null) {
			for (Object val : array) {
				if (value.equals(val)) return true;
			}
		}
		else {
			for (Object val : array) {
				if (val == null) return true;
			}
		}
		
		return false;
	}
	
	public static boolean arrayContainsi(int[] array, int value) {
		if (array == null) throw new NullPointerException("array == null !");
		if (array.length == 0) return false;
		
		for (int val : array) {
			if (value == val) return true;
		}
		
		return false;
	}
	
	public static boolean arrayContainsf(float[] array, float value) {
		if (array == null) throw new NullPointerException("array == null !");
		if (array.length == 0) return false;
		
		for (float val : array) {
			if (value == val) return true;
		}
		
		return false;
	}
	
	public static boolean arrayContainsd(double[] array, double value) {
		if (array == null) throw new NullPointerException("array == null !");
		if (array.length == 0) return false;
		
		for (double val : array) {
			if (value == val) return true;
		}
		
		return false;
	}
	
	public static <T> T[] concat(T[] array1, T[] array2) {
		if (array1 == null) throw new NullPointerException("array1 == null");
		if (array2 == null) throw new NullPointerException("array2 == null");
		
		if (array1.length == 0) return Arrays.copyOf(array2, array2.length);
		if (array2.length == 0) return Arrays.copyOf(array1, array1.length);
		
		T[] result = Arrays.copyOf(array1, array1.length + array2.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		
		return result;
	}
	
	/** Effectue l'action inverse d'un split : reconstitue la chaine � partir des
	 * diff�rents morceaux, en les concat�nant en altern� avec un motif donn� par
	 * le param�tre <tt>blanks</tt> */
	public static String fillStringArray(String[] array, String blanks) {
		//8 : longueur moyenne estim�e d'un mot.
		StringBuilder sb = new StringBuilder(array.length * (8 + blanks.length()));
		int lastIndex = array.length - 1;
		
		for (int i = 0 ; i < lastIndex ; i++) {
			sb.append(array[i]);
			sb.append(blanks);
		}
		
		sb.append(array[lastIndex]);
		
		return sb.toString();
	}
	
	public static <T> T pickRandom(T[] array, Random rand) {
		if (array.length == 0) return null;
		return array[rand.nextInt(array.length)];
	}
	
	/**
	 * Effectue la m�thode {@link String#format(String, Object...)}, mais
	 * uniquement sur les valeurs d�cimales.
	 * <p>Autrement dit, remplace chaque "%d" de la chaine de caract�re pass�e
	 * en param�tre par un nombre que contient {@code values} (Le tableau d'entiers
	 * pass� en param�tre). Le n<sup>�me</sup> "%d" de la chaine sera remplac� 
	 * par le n<sup>�me</sup> �l�ment du tableau.
	 * <p>Si le tableau contient moins de valeurs que la chaine de "%d", les "%d"
	 * en trop ne seront pas remplac�s. Si en revanche le tableau contient plus 
	 * de valeurs, les derni�res valeurs du tableau ne seront pas utilis�es.
	 * <p>Le principal atout de cette m�thode par rapport � {@link String#format(String, Object...)}
	 * est la rapidit� (environ 5 fois plus vite), vu que seule une seule sorte de
	 * remplacement est trait�e.
	 * @param format - La chaine de caract�re devant �tre modifi�e.
	 * @param values - Le tableau de valeurs devant remplacer les "%d" de la chaine.
	 * @return La chaine {@code format}, modifi�e comme expliqu� ci-dessus.
	 * @throws NullPointerException Si {@code format} vaut {@code null}, ou si
	 * {@code values} contient une valeur nulle.
	 */
	public static String formatDecimal(String format, Integer... values) {
		if (format == null) {
			throw new NullPointerException("format == null");
		}
		
		int formatSize = format.length();
		char[] characters = format.toCharArray();
		char[] var0 = new char[2];
		
		lbl1: for (Integer value : values) {
			if (value == null) throw new NullPointerException(
					"Les valeurs du tableau \"values\" pass� en param�tre ne doivent pas valoir null !");
			
			for (int i = 0 ; i < characters.length - 1 ; i++) {
				var0[0] = characters[i];
				var0[1] = characters[i + 1];
				
				if (new String(var0).equals("%d")) {
					char[] valueChars = value.toString().toCharArray();
					char[] newString = new char[formatSize - 2 + valueChars.length];
					System.arraycopy(characters, 0, newString, 0, i);
					System.arraycopy(valueChars, 0, newString, i, valueChars.length);
					System.arraycopy(characters, i + 2, newString, i + valueChars.length, formatSize - i - 2);
					
					characters = newString;
					continue lbl1;
				}
			}
			
			break;
		}
		
		return new String(characters);
	}
	
	private static final char[] decimalDigits = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
	
	/***
	 * Extrait tous les entiers de la chaine et les renvoie
	 * sous forme d'un tableau d'entier.
	 * @param str - La chaine contenant les nombres
	 * @return Un tableau contenant chaque entiers que comporte la
	 * chaine de caract�re pass�e en param�tre.
	 */
	public static int[] extractIntegers(String str) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Character> charNumbers = new ArrayList<Character>(12);
		
		for (int i = 0 ; i < decimalDigits.length ; i++) {
			charNumbers.add(decimalDigits[i]);
		}
		
		int first = 0;
		boolean found = false;
		for (int i = 0 ; i < str.length() ; i++) {
			if (charNumbers.contains(str.charAt(i))
					|| (!found && str.charAt(i) == '-')
					|| (!found && str.charAt(i) == '+')) {
				
				if (!found) {
					found = true;
					first = i;
				}
			}
			else {
				if (found) {
					found = false;
					
					String intDetected = str.substring(first, i);
					if ("+".equals(intDetected) || "-".equals(intDetected)) continue;
					numbers.add(Integer.parseInt(intDetected));
				}
			}
		}
		
		if (found) numbers.add(Integer.parseInt(str.substring(first)));
		
		int[] result = new int[numbers.size()];
		for (int i = 0 ; i <numbers.size() ; i++) {
			result[i] = numbers.get(i);
		}
		
		return result;
	}
	
	/**
	 * Trouve le point le plus proche du point d�fini par les coordonn�es
	 * {@code c}, dans le tableau de points pass� en param�tre.
	 * @param c - Le point.
	 * @param array - Le tableau o� sera cherch� le plus proche point �
	 * {@code c}
	 * @return Le plus proche point au point d�fini par {@code c}, contenu
	 * dans le tableau, ou une copie de {@code c} si le tableau est null ou
	 * ne contient aucune valeurs.
	 */
	public static Vec2d findNearestPoint(Vec2 c, Vec2[] array) {
		if (array == null) {
			return c.asDouble();
		}
		Vec2d cd = c.asDouble();
		Vec2d result = array.length > 0 ? array[0].asDouble() : cd;
		for (int i = 1 ; i < array.length ; i++) {
			if (MathUtil.getLength(cd, result) > MathUtil.getLength(cd, array[i].asDouble())) {
				result = array[i].asDouble();
			}
		}
		
		return result;
	}
	
	/**
	 * Cr�e une liste � partir du tableau de donn�es pass� en param�tre.
	 * @param array - Le tableau contenant les donn�es.
	 * @return Une {@link ArrayList} contenant les m�mes donn�es que le
	 * tableau, dans le m�me ordre.
	 */
	public static <T> ArrayList<T> createList(T[] array) {
		ArrayList<T> list = new ArrayList<T>(array.length);
		for (T e : array) {
			list.add(e);
		}
		return list;
	}
	
	public static <T> void addAll(T[] from, List<T> to, boolean enableNull) {
		for (T obj : from) {
			if (obj == null && !enableNull) throw new NullPointerException("found null value in array");
			to.add(obj);
		}
	}
	
	/**
	 * V�rifie, pour chaque �l�ment d'une liste, si l'autre liste
	 * la contient bien. L'ordre n'est pas pris en compte.
	 * @param first - La premi�re liste.
	 * @param second - La seconde liste.
	 * @return <code>true</code> si les deux listes contiennent les
	 * m�me �l�ments, <code>false</code> sinon.
	 */
	public static boolean listEqualsUnsorted(List<?> first, List<?> second) {
		if (first == null || second == null) {
			if (!(first == null && second == null)) {
				return false;
			}
			else {
				return true;
			}
		}
		
		if (first.size() != second.size()) {
			return false;
		}
		
		for (Object o : first) {
			if (!(second.contains(o))) {
				return false;
			}
		}
		
		return true;
	}
	
	/** Trouve le minimum dans la liste pass�e en param�tres, selon l'ordre donn�
	 * par le comparateur en second param�tre.
	 * <p>Si le comparateur ne supporte pas les valeurs nulles, alors celle-ci seront
	 * ignor�es dans le r�sultat. */
	public static <T> T findMin(Collection<T> list, Comparator<T> comparator) {
		T result = null;
		
		for (T elemt : list) {
			if (elemt != null) {
				try {
					result = comparator.compare(result, elemt) > 0 ? elemt : result;
				} catch (RuntimeException e) {
					/*result peut �tre nul � cet endroit. Si les valeurs nulles sont
					 * support�es par le comparateur alors on le laisse faire, si elles
					 * ne le sont pas alors on ignore les �l�ments nuls dans le calcul du
					 * r�sultat. */
					
					if (result == null) {
						result = elemt;
					}
					else {
						//Ce n'est pas �a : l'erreur est donc anormale, il faut la signaler.
						throw e;
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Cette m�thode v�rifie si les deux objets pass�s en param�tre
	 * sont �gaux. Elle fonctionne �galement avec les objets {@code null}.
	 * @param one - Le premier objet, peut �tre {@code null}.
	 * @param two - Le second objet, peut �tre {@code null}.
	 * @return Si les deux objets ne sont pas {@code null},
	 * retourne {@code one.equals(two)}, si un seul objet est {@code null},
	 * retourne {@code false}, si les deux sont {@code null}, retourne
	 * {@code true}.
	 * @see Object#equals(Object)
	 */
	public static boolean equals(Object one, Object two) {
		if (one == null && two == null) return true;
		if (one != null && two != null) return one.equals(two);
		return false;
	}
	
	/** Copie le fichier {@code origine} dans le fichier
	 * {@code destination} */
	public static void copyData(InputStream origine, OutputStream destination) throws IOException {
		byte[] data = new byte[8];
		int nByte = 0; 
		try {
			while ((nByte = origine.read(data)) == 8) {
				destination.write(data);
			}
			for (int i = 0 ; i < nByte ; i++) {
				destination.write(data[i]);
			}
		}
		finally {
			try {
				origine.close();
				destination.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Place les donn�es de l'image pass�e en param�tre, dans un
	 * tampon m�moire exploitable par les APIs natives comme openGL.
	 * Les donn�es sont plac�es dans l'ordre RGBA.
	 * @param image - L'image � traiter
	 * @param alpha - {@code true} si la composantes alpha de la texture
	 * doit �tre stock�e dans le tampon. */
	public static final ByteBuffer getData(BufferedImage image, boolean alpha) {
		
		int imagePixels = image.getWidth() * image.getHeight();
		int pixelSize = alpha ? 4 : 3;
		int dataSize = imagePixels * pixelSize;
		
		ByteBuffer result = ByteBuffer.allocateDirect(dataSize);
		result.position(0);
		
		for (int y = 0 ; y < image.getWidth() ; y++) {
			for (int x = 0 ; x < image.getHeight() ; x++) {
				
				//getRGB donne un entier repr�sentant une couleur avec le mod�le INT_ARGB
				int argb = image.getRGB(x, y);
				
				result.put((byte) ((0x00FF0000 & argb) >> 16));
				result.put((byte) ((0x0000FF00 & argb) >> 8));
				result.put((byte) ((0x000000FF & argb) >> 0));
				if (alpha) result.put((byte) ((0xFF000000 & argb) >> 24));
			}
		}
		
		result.flip();
		
		return result;
	}
	
	
	/**
	 * Permet de r�duire un {@link RectangleDouble} en appliquant une marge
	 * sur ses bords.
	 * @param rect - Le {@link RectangleDouble} � r�duire.
	 * @param marge - La largeur de la marge.
	 * @return Le m�me {@link RectangleDouble}. Chacun de ses c�t�s est r�duit
	 * d'une valeur �gale � {@code marge}. Le rectangle final est forc�ment plus
	 * petit que l'original, sauf dans le cas particulier ou on a :
	 * <blockquote>{@code marge > rect.getWidth()}</blockquote>
	 * ou encore :
	 * <blockquote>{@code marge > rect.getHeight()}</blockquote>
	 * Dans ce cas, le rectangle sera invers�, sur le sens de sa largeur, ou de
	 * sa hauteur, et sera plus grand que l'original.
	 */
	public static RectangleDouble margeRectangleDouble(RectangleDouble rect, double marge) {
		int senseX = rect.xmax > rect.xmin ? 1 : -1;
		int senseY = rect.ymax > rect.ymin ? 1 : -1;
		
		rect.xmin += marge * senseX;
		rect.xmax -= marge * senseX;
		rect.ymin += marge * senseY;
		rect.ymax -= marge * senseY;
		
		return rect;
	}
}
