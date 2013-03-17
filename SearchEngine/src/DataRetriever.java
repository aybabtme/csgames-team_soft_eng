import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

	private static final int NEWLINE = 10;
	private static final int END_OF_FILE = -1;
	
	public static List<String> getData(String filepath) {
		ArrayList<String> sentenceData = new ArrayList<String>();

		try {
			// get file
			File rawData = new File(filepath);
			// load file
			FileInputStream inputStream = new FileInputStream(rawData);

			StringBuilder sentence = new StringBuilder();
			
			// read data
			while (true) {
				int character = inputStream.read();

				if ( character == END_OF_FILE ) {
					sentenceData.add(sentence.toString());
					break;
				}
				else if ( character == NEWLINE ) {
					// newline = add sentence and clear string builder
					sentenceData.add(sentence.toString());
					sentence = new StringBuilder();
				}
				else {
					sentence.append((char)character);
				}
			}
			
			// LOWERCASING
			for (String str : sentenceData) {
				str = str.toLowerCase();
			}
			
			inputStream.close(); //cleanup
		}
		catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sentenceData;
	}	
}
