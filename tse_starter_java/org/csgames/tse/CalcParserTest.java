package org.csgames.tse;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalcParserTest {

	private String[] testStr = { 
			"5+5*5", 
			"34+8", 
			"9*(30+30)", 
			"8*(8+9/3)",
			"90000/10+1",
			"5 + 5 * 5", 
			"34 + 8", 
			"9 * ( 30 + 30 )", 
			"8 * ( 8 + 9 / 3 ) ",
			"90000 / 10 + 1",
			"	5	+	5	*	5	", 
			"	34	+	8	", 
			"	9	*	(	30	+	30	)	", 
			"	8	*	(	8	+	9	/	3	)	",
			"	90000	/	10	+	1	"
			
			};

	@Test
	public void testTrivialEquation() {
		String expected = "2";
		String actual = CalcParser.solve("1+1");
		assertEquals("1+1 should equal 2", expected, actual);
	}
	
	

}
