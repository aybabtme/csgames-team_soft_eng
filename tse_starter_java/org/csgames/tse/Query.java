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
}
