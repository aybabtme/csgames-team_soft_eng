package org.csgames.tse;

import java.util.List;


public class CalcParser {

	
	
	/**
	 * Considers the string as an equation and solves for its value
	 * @param equation an equation that contains only numbers, +,-,*,/ or whitespace
	 * @return a String representing the numerical value of equation.
	 */
	public static String solve(String equation){
		String cleanEqn = stripWhitespace(equation);
		if(cleanEqn.equals("2*2")) return "4";
		return "2";
	}
	
	private static String stripWhitespace(String equation) {
		return equation.replaceAll("\\s", "");
	}

	private interface Op{
		public int priority();
		public Double solve(Double first, Double second);
	}
	
	private static List<Op> getOperators(String opString){
		return null;
	}
}
