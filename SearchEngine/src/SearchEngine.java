import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Query;


public class SearchEngine {

	private String dataFilepath;
	
	public SearchEngine(String filepath) {
		dataFilepath = filepath;
	}
	
	public List<String> search(Query query) {
		// load data
		List<String> searchResults = DataRetriever.getData(dataFilepath);
		
		for (QueryItem subQuery : Query.getItems()) {
			searchResults = searchData(searchResults, subQuery);
		}
		
		return searchResults;
	}
	
	private List<String> searchSentence(List<String> data, QueryItem query) {
		String queryWord = query.getWord();
		queryWord = queryWord.toLowerCase();
		
		switch(query.getOperator()) {
			case Operator.NOT:
				queryNOT(data, queryWord);
				break;
			case Operator.OR:
				queryOR(data, queryWord);
				break;
			case Operator.AND:
				queryAND(data, queryWord, query.getAtLeast());
				break;
		}
	}
	
	private void queryNOT(List<String> data, String word) {
		for (String sentence : data) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (caseInsensitiveSentence.contains(word)) {
				data.remove(sentence);
			}
		}
	}
	
	private void queryOR(List<String> data, String word) {
		
	}
	
	private void queryAND(List<String> data, String word, int atLeast) {
		for (String sentence : data) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!queryANDHasAtLeast(caseInsensitiveSentence, word, atLeast)) {
				data.remove(sentence);
			}
		}
	}
	
	private boolean queryANDHasAtLeast(String sentence, String word, int atLeast) {
		if (atLeast <= 0) {
			return true;
		}
		else if (sentence.contains(word)) {
			String strippedSentence = sentence.replaceFirst(word, "");
			queryANDHasAtLeast(strippedSentence, word, atLeast - 1);
		}

		return false;
	}
	
	private void queryEXACT(List<String> data, String word) {
		for (String sentence : data) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!caseInsensitiveSentence.contains(word)) {
				data.remove(sentence);
			}
		}
	}
	
	private void queryRANGE(List<String> data, String word) {
		for (String sentence : data) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!caseInsensitiveSentence.matches("([0-9])+(\\s..){1}([0-9])+")) {
				data.remove(sentence);
			}
		}
	}
}
