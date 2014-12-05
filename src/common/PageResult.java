package common;

import java.util.*;

public class PageResult<T> implements java.io.Serializable{
	private static final long serialVersionUID = -1826830567659349558L;
	
	private List<T> list;
	private int numItemsInPage;
	private int numItems;
	private int numPages;
	private int page;
	
	private final static int delta = 5;
	
	public PageResult(int numItemsInPage, int page) {
		super();
		this.numItemsInPage = numItemsInPage;
		this.page = page;
		numItems = 0;
		numPages = 0;
		list = new ArrayList<T>();
	}
	
	// setters & getters
	public List<T> getList() {return list;}
	public List<T> getListUsers() {return list;}
	public int getNumItems() {return numItems;}
	public int getNumPages() {return numPages;}
	public int getNumItemsInPage() {return numItemsInPage;}
	public int getPage() {return page;}
	public int getStartPageNo() {return (page <= delta) ? 1: page - delta;}
	
	public void setNumItems(int numItems) {
		this.numItems = numItems;
		numPages = (int) Math.ceil((double)numItems / (double)numItemsInPage);
	}
	public int getEndPageNo() {
		int endPageNo = getStartPageNo() + (delta * 2) + 1;

		if (endPageNo > numPages) {
			endPageNo = numPages;
		}
		return endPageNo;
	}
}
