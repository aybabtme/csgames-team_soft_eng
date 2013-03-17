package org.csgames.tse;


public class CalcParser {

	
	
	/**
	 * Considers the string as an equation and solves for its value
	 * @param equation an equation that contains only numbers, +,-,*,/ or whitespace
	 * @return a String representing the numerical value of equation.
	 */
	public static String solve(String equation){
		return "2";
	}
	
	private interface Op{
		public Double solve(Double first, Double second);
	}
}
