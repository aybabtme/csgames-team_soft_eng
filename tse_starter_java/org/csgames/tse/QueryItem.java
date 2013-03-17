package org.csgames.tse;

public class QueryItem {
	private String mWord;
	private int mAtLeast = 0;
	private QueryOperator mBeforeOperator;
	
	public QueryItem(String word, QueryOperator op) {
		this.mWord = word;
		mBeforeOperator = op;
	}
	
	public String getWord() {
		return mWord;
	}
	
	public int getAtLeast() {
		return mAtLeast;
	}
	
	public void setAtLeast(int v) {
		mAtLeast = v;
	}
	
	public QueryOperator getBeforeOperator() {
		return mBeforeOperator;
	}
	
	public String toString() {
		String r = this.mWord;
		if (mWord.contains(" ")) {
			r = '"' + r + '"';
		}
		
		r = mBeforeOperator.toString().toUpperCase() + " " + r;
		
		if (mAtLeast > 0) {
			r += "{" + Integer.toString(mAtLeast) + "}";
		}
		
		return r;
	}
}
