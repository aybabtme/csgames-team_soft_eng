package org.csgames.tse;

import java.util.List;

public class Query {
	private List<QueryItem> mItems;
	
	public Query(List<QueryItem> items) {
		mItems = items;
	}
	
	public List<QueryItem> getItems() {
		return mItems;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (QueryItem item : mItems) {
			sb.append(item.toString());
			sb.append(' ');
		}
		return sb.toString().trim();
	}
}
