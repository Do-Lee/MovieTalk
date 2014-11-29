package naver;

import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSParser {
	public Feed getAllMovies(String search) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			URL url = new URL("http://openapi.naver.com/search?key=896bf8e6f7282fae022ecb24c447ed8f&query="
					+ URLEncoder.encode(search, "UTF-8") + "&display=10&start=1&target=movie");
			Feed feed = new Feed();
			Document doc = builder.parse(url.openConnection().getInputStream());
			Element element = doc.getDocumentElement();
			NodeList movieItemList = element.getElementsByTagName("item");
			if(movieItemList != null && movieItemList.getLength() > 0) {
				FeedMessage message;
				for(int i=0; i<movieItemList.getLength(); i++) {
					Node node = movieItemList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) node;
						message = new FeedMessage(
								removeBTag(e.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue()),
								removeBTag(e.getElementsByTagName("subtitle").item(0).getChildNodes().item(0).getNodeValue()),
								e.getElementsByTagName("link").item(0).getChildNodes().item(0).getNodeValue(),
								e.getElementsByTagName("image").item(0).getChildNodes().item(0).getNodeValue(),
								changeStringToken(e.getElementsByTagName("director").item(0).getChildNodes().item(0).getNodeValue()),
								changeStringToken(e.getElementsByTagName("actor").item(0).getChildNodes().item(0).getNodeValue()),
								e.getElementsByTagName("pubDate").item(0).getChildNodes().item(0).getNodeValue(),
								e.getElementsByTagName("userRating").item(0).getChildNodes().item(0).getNodeValue());
						feed.getMessages().add(message);
					}
				}
			}
			return feed;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String changeStringToken(String src) {
		if(src.endsWith("|")) src = src.substring(0,  src.length() - 1);
		return src.replace("|", ", ");
	}
	
	private String removeBTag(String src) {
		return src.replaceAll("<b>", "").replaceAll("</b>", "");
	}
}
