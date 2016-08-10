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

import net.bynaryscode.util.maths.analysis.FunctionParser;
import net.bynaryscode.util.maths.analysis.PolynomialFunction;

public class TestFunctionParser {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testParsePolynomialFunctionNotNull() {
		assertNotNull(FunctionParser.parsePolynomial(""));
	}
	
	@Test
	public void testParsePolynomialFunction() {
		PolynomialFunction function = new PolynomialFunction(3, 2, 5.55);
		assertEquals(function.toString(), FunctionParser.parsePolynomial("").toString());
	}
}
