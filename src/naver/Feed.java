package naver;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	private final List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public List<FeedMessage> getMessages() {
		return entries;
	}

	public List<FeedMessage> getEntries() {return entries;}
} 
