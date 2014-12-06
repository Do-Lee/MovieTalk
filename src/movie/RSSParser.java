package movie;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSParser {
	private static String title;
	private static String subtitle;
	private static String link;
	private static String image;
	private static String director;
	private static String actor;
	private static String pubDate;
	private static String userRating;
	
	public static ArrayList<Movie> getAllMovies(String search) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			ArrayList<Movie> movielist = new ArrayList<Movie>();
			for(int i=1; i<=1000; i +=100) {
				URL url = new URL("http://openapi.naver.com/search?key=896bf8e6f7282fae022ecb24c447ed8f&query="
						+ search + "&display=100&start=" + i + "&target=movie");
				Document doc = builder.parse(url.openConnection().getInputStream());
				Element element = doc.getDocumentElement();
				NodeList movieItemList = element.getElementsByTagName("item");
				if (movieItemList.getLength() > 0) {
					if(movieItemList.getLength() < 100) {
						for (int j=0; j<movieItemList.getLength(); j++) {
							Node node = movieItemList.item(j);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) node;
								title = removeBTag(catchNULL(e.getElementsByTagName("title").item(0)));
								subtitle = removeBTag(catchNULL(e.getElementsByTagName("subtitle").item(0)));
								link = removeBTag(catchNULL(e.getElementsByTagName("link").item(0)));
								image = removeBTag(catchNULL(e.getElementsByTagName("image").item(0)));
								director = changeStringToken(removeBTag(catchNULL(e.getElementsByTagName("director").item(0))));
								actor = changeStringToken(removeBTag(catchNULL(e.getElementsByTagName("actor").item(0))));
								pubDate = removeBTag(catchNULL(e.getElementsByTagName("pubDate").item(0)));
								userRating = removeBTag(catchNULL(e.getElementsByTagName("userRating").item(0)));
								Movie movie = new Movie(title, subtitle, link, image, 
										director, actor, pubDate, userRating);
								movielist.add(movie);
							}
						}
						break;
					} else {
						for (int j=0; j<movieItemList.getLength(); j++) {
							Node node = movieItemList.item(j);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element e = (Element) node;
								title = removeBTag(catchNULL(e.getElementsByTagName("title").item(0)));
								subtitle = removeBTag(catchNULL(e.getElementsByTagName("subtitle").item(0)));
								link = removeBTag(catchNULL(e.getElementsByTagName("link").item(0)));
								image = removeBTag(catchNULL(e.getElementsByTagName("image").item(0)));
								director = changeStringToken(removeBTag(catchNULL(e.getElementsByTagName("director").item(0))));
								actor = changeStringToken(removeBTag(catchNULL(e.getElementsByTagName("actor").item(0))));
								pubDate = removeBTag(catchNULL(e.getElementsByTagName("pubDate").item(0)));
								userRating = removeBTag(catchNULL(e.getElementsByTagName("userRating").item(0)));
								Movie movie = new Movie(title, subtitle, link, image, 
										director, actor, pubDate, userRating);
								movielist.add(movie);
							}
						}
					}
				}
			}
			return movielist;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String changeStringToken(String src) {
		if(src.endsWith("|")) src = src.substring(0,  src.length() - 1);
		return src.replace("|", ", ");
	}
	
	private static String removeBTag(String src) {
		return src.replaceAll("<b>", "").replaceAll("</b>", "");
	}
	
	private static String catchNULL(Node node) {
		return (node != null) ? catchNULL(node.getChildNodes()) : "";
	}
	
	private static String catchNULL(NodeList nodelist) {
		return (nodelist != null) ? catchNULLInLastStage(nodelist.item(0)) : "";
	}
	
	private static String catchNULLInLastStage(Node node) {
		return (node != null) ? node.getNodeValue() : "";
	}
}
