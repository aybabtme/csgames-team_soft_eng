package org.csgames.tse;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcParserTest {


	@Test
	public void testTrivialEquation() {
		String expected = "2";
		String actual = CalcParser.solve("1+1");
		assertEquals("1+1 should equal 2", expected, actual);
	}

	@Test
	public void testAnotherTrivialEquation() {
		String expected = "4";
		String actual = CalcParser.solve("2*2");
		assertEquals("2*2 should equal 4", expected, actual);
	}

	@Test
	public void testTrivialWithWhiteSpaceEquation() {
		String expected = "4";
		String actual = CalcParser.solve("2 * 2");
		assertEquals("2 * 2 should equal 4", expected, actual);
	}

	private String[][] testStr = { 
			{ "31","6+5*5"},
			{ "42","34+8"},
			{ "540","9*(30+30)"},
			{ "88","8*(8+9/3)"},
			{ "9001","90000/10+1"},
			{ "31","6 + 5 * 5"},
			{ "42","34 + 8"},
			{ "540","9 * ( 30 + 30 )"},
			{ "88","8 * ( 8 + 9 / 3 ) "},
			{ "9001","90000 / 10 + 1"},
			{ "31","  6       +       5       *       5       "},
			{ "42","  34      +       8       "},
			{ "540","  9       *       (       30      +       30	)      "},
			{ "88","  8       *       (       8       +       9	/      3       )       "},
			{ "9001","  90000   /       10      +       1       "}
	};
	
	@Test
	public void testComprehensiveStrings(){
		for(String[] equationPair : testStr){
			String expected = equationPair[0];
			String actual = CalcParser.solve(equationPair[1]);
			assertEquals(
					String.format("Comprehensive test of \"%s\"", equationPair[1]), 
					expected, 
					actual);
		}
	}
}
