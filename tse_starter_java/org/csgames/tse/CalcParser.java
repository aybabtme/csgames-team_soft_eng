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
	

	/**
	 * Remove whitespace from a given string
	 * @param equation
	 * @return
	 */
	private static String stripWhitespace(String equation) {
		return equation.replaceAll("\\s", "");
	}

	
	/**
	 * I lol at you, Ô Java-Gods.  Solves an equation using python. Writes
	 * a Python program on the hard drive, then run it, then read the output,
	 * then return this output as the result.
	 * @param equation an equation string
	 * @return the value, evaluated by Python, lol
	 */
	private static String pythonEval(String equation){
		PrintStream tempWriter = null;
		BufferedReader reader = null;
		try {
			// create the python program
			File tempFile = new File("temp.py");
			tempWriter = new PrintStream(tempFile);
			tempWriter.printf("print(%s)", equation);
			
			// prepare it
			Process python = new ProcessBuilder("python", "temp.py").start();
			reader = new BufferedReader(new InputStreamReader(python.getInputStream()));
			// let it run
			python.waitFor();
			// get the output of the python program
			String response = reader.readLine();
			// kill the python process
			python.destroy();
			// cleanup
			tempFile.delete();
			
			return response;
		} catch (IOException e) {
			// don't care
		} catch (InterruptedException e) {
			// don't care
		} finally {
			close(tempWriter);
			close(reader);
		}
		return "";
	}
	
	/**
	 * Close anything that could be closeable, null or not.  Discards thrown exceptions.
	 * @param c a closeable object, such as a Stream, Socket, Writer, Reader, wtv.
	 */
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
