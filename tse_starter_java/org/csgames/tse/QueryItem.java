package org.csgames.tse;

public class QueryItem {
	private String mWord;
	private int mAtLeast;
	private QueryOperator mBeforeOperator;
	
	public String getWord() {
		return mWord;
	}
	
	public int getAtLeast() {
		return mAtLeast;
	}
	
	public QueryOperator getBeforeOperator() {
		return mBeforeOperator;
	}
}
