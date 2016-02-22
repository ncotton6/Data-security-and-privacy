package edu.rit.csci729.assign1.api.eventful;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import edu.rit.csci729.assign1.api.eventful.model.Event;

public class Eventful {

	private static final String endpoint = "http://api.eventful.com/rest/events/search?app_key=%s&location=%s&date=Today";

	public static List<Event> getEvents(String location) {
		List<Event> events = new ArrayList<Event>();
		try {
			List<Element> elements = eventfulAPIRequest(location, "jtfKGn2f6RfMLHD9");
			for (Element e : elements) {
				Event event = new Event();
				event.setUrl(e.getChildText("url"));
				event.setTitle(e.getChildText("title"));
				event.setDescription(e.getChildText("description"));
				event.setCity_name(e.getChildText("city_name"));
				event.setCountry_name(e.getChildText("country_name"));
				event.setLatitude(Float.parseFloat(e.getChildText("latitude")));
				event.setLongitude(Float.parseFloat(e.getChildText("longitude")));
				event.setPostal_code(e.getChildText("postal_code"));
				event.setRegion_name(e.getChildText("region_name"));
				event.setVenue_address(e.getChildText("venue_address"));
				event.setStart_time(e.getChildText("start_time"));
				event.setStop_time(e.getChildText("stop_time"));
				events.add(event);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		return events;
	}

	private static List<Element> eventfulAPIRequest(String location, String apiKey) throws IOException, JDOMException {
		URL url = new URL(String.format(endpoint, apiKey, location));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(url);
		Element root = doc.getRootElement();
		Element events = root.getChild("events");
		List<Element> event = events.getChildren("event");
		return event;
	}

}
