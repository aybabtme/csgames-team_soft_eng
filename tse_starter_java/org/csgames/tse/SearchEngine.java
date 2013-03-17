package org.csgames.tse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchEngine {

	private String dataFilepath;
	
	public SearchEngine(String filepath) {
		dataFilepath = filepath;
	}
	
	public static String PATH = "";
	
	public static boolean isMatch(String sentence, String query) {
		SearchEngine e = new SearchEngine(PATH);
		QueryParser q = new QueryParser();
		Query qobj = q.parse(query);
		
		List<String> a = new ArrayList<String>();
		a.add(sentence);
		
		for (QueryItem item : qobj.getItems()) {
			a = e.searchSentence(a, item);
		}
		
		return a.size() > 0;
	}
	
	public List<String> search(Query query) {
		// load data
		List<String> searchResults = DataRetriever.getData(dataFilepath);
		
		for (QueryItem subQuery : query.getItems()) {
			searchResults = searchSentence(searchResults, subQuery);
		}
		
		return searchResults;
	}
	
	private List<String> searchSentence(List<String> data, QueryItem query) {
		String queryWord = query.getWord();
		queryWord = queryWord.toLowerCase();
		
		switch(query.getBeforeOperator()) {
			case Not:
				queryNOT(data, queryWord);
				break;
			case Or:
				queryOR(data, queryWord);
				break;
			case And:
				queryAND(data, queryWord, query.getAtLeast());
				break;
		}
		
		return data;
	}
	
	private void queryNOT(List<String> data, String word) {
		String[] dataAr = new String[data.size()];
		for(int i = 0; i < data.size(); i++){
			dataAr[i] = data.get(i);
		}
		for (String sentence : dataAr) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (caseInsensitiveSentence.contains(word)) {
				data.remove(sentence);
			}
		}
	}
	
	private void queryOR(List<String> data, String word) {
		
	}
	
	private void queryAND(List<String> data, String word, int atLeast) {
		String[] dataAr = new String[data.size()];
		for(int i = 0; i < data.size(); i++){
			dataAr[i] = data.get(i);
		}

		for (String sentence : dataAr) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!queryANDHasAtLeast(caseInsensitiveSentence, word, atLeast)) {
				data.remove(sentence);
			}
		}
	}
	
	private boolean queryANDHasAtLeast(String sentence, String word, int atLeast) {
		if (atLeast < 0) {
			return true;
		}
		else if (sentence.contains(word)) {
			String strippedSentence = sentence.replaceFirst(word, "");
			return queryANDHasAtLeast(strippedSentence, word, atLeast - 1);
		}
		return false;
	}
	
	private void queryEXACT(List<String> data, String word) {
		String[] dataAr = new String[data.size()];
		for(int i = 0; i < data.size(); i++){
			dataAr[i] = data.get(i);
		}
		
		for (String sentence : dataAr) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!caseInsensitiveSentence.contains(word)) {
				data.remove(sentence);
			}
		}
	}
	
	private void queryRANGE(List<String> data, String word) {
		String[] dataAr = new String[data.size()];
		for(int i = 0; i < data.size(); i++){
			dataAr[i] = data.get(i);
		}
		for (String sentence : dataAr) {
			String caseInsensitiveSentence = sentence.toLowerCase();
			
			if (!caseInsensitiveSentence.matches("([0-9])+(\\s..){1}([0-9])+")) {
				data.remove(sentence);
			}
		}
	}
}
