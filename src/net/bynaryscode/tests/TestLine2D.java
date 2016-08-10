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

package net.bynaryscode.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.bynaryscode.util.maths.geometric.Vec2d;
import net.bynaryscode.util.maths.geometric.Line2D;

public class TestLine2D {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testContains() {
		Line2D line = new Line2D(2, 4, 4, 5);
		assertTrue(line.contains(5, 5.5));
	}
	
	@Test
	public void testContains2() {
		Line2D line = new Line2D(2, 4, 2, 5);
		assertTrue(line.contains(2, 70000));
	}
	
	@Test
	public void testNotContains() {
		Line2D line = new Line2D(2, 4, 4, 5);
		assertFalse(line.contains(5, 20));
	}
	
	@Test
	public void testNotContains2() {
		Line2D line = new Line2D(2, 4, 2, 5);
		assertFalse(line.contains(3, 5));
	}
	
	@Test
	public void testEquationSignum() {
		Line2D line = new Line2D(2, 4, 4, 5);
		assertTrue(line.equation(3, 7) >= 0);
	}
	
	@Test
	public void testSquaredDistance() {
		Line2D line = new Line2D(2, 4, 5, 8);
		double squaredDist = line.squaredDistance(1, 11);
		assertTrue(String.valueOf(squaredDist), squaredDist == 25);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testOrthographicProjection() {
		Line2D line = new Line2D(2, 4, 5, 8);
		assertEquals(line.getOrthographicProjection(1, 11), new Vec2d(5, 8));
	}
}
