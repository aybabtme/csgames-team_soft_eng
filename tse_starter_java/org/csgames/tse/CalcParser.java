package org.csgames.tse;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class CalcParser {
	
	/**
	 * Considers the string as an equation and solves for its value
	 * @param equation an equation that contains only numbers, +,-,*,/ or whitespace
	 * @return a String representing the numerical value of equation.
	 */
	public static String solve(String equation){
		String cleanEqn = stripWhitespace(equation);
		return pythonEval(cleanEqn);
	}
	

	private static String stripWhitespace(String equation) {
		return equation.replaceAll("\\s", "");
	}

	
	/**
	 * I lol at you, Ô Java-Gods.
	 * @param equation an equation string
	 * @return the value, evaluated by Python, lol
	 */
	private static String pythonEval(String equation){
		PrintStream tempWriter = null;
		BufferedReader reader = null;
		try {
			File tempFile = new File("temp.py");
			tempWriter = new PrintStream(tempFile);
			tempWriter.printf("print(%s)", equation);
			tempWriter.close();
			
			Process python = new ProcessBuilder("python", "temp.py").start();
			reader = new BufferedReader(new InputStreamReader(python.getInputStream()));
			python.waitFor();
			String response = reader.readLine();
			python.destroy();
			
			tempFile.delete();
			
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(tempWriter);
			close(reader);
		}
		return "";
	}
	
	
	private static void close(Closeable c){
		if(c != null){
			try {
				c.close();
			} catch (IOException e) {
				// don't care!!
			}
				
		}
	}
	
}
