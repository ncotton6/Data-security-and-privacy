package edu.rit.csci729.assign1.api.yelp;

import java.util.ArrayList;
import java.util.List;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.rit.csci729.assign1.api.yelp.model.Restaurant;

public class Yelp {

	private static final String endpoint = "https://api.yelp.com/v2/search";

	public static List<Restaurant> getRestaurants(String location) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		JsonArray businesses = yelpAPICall(location);
		for (JsonElement je : businesses) {
			Restaurant res = new Restaurant();
			JsonObject jo = (JsonObject) je;
			res.setName(jo.get("name").getAsString());
			res.setRating(jo.get("rating").getAsFloat());
			res.setDisplay_phone(jo.get("display_phone").getAsString());
			res.setIs_closed(jo.get("is_closed").getAsBoolean());
			JsonObject loc = (JsonObject) jo.get("location");
			JsonArray disAddr = (JsonArray) loc.get("display_address");
			String[] addr = new String[disAddr.size()];
			for (int i = 0; i < disAddr.size(); ++i) {
				addr[i] = disAddr.get(i).getAsString();
			}
			res.setDisplay_location(addr);
			JsonObject coordinates = (JsonObject) loc.get("coordinate");
			res.setLatitude(coordinates.get("latitude").getAsFloat());
			res.setLongitude(coordinates.get("longitude").getAsFloat());
			restaurants.add(res);
		}
		return restaurants;
	}

	private static JsonArray yelpAPICall(String location) {
		OAuthService service = new ServiceBuilder().provider(YelpAuth.class).apiKey("6Hj9Uacs53ZNvtOhiBPSfg")
				.apiSecret("U5Zg-n0fircpKZtYTNpUgbjHzlk").build();
		Token token = new Token("17NmbUtE4l5eGcgMbZyrcN4upkiPxt-d", "8d_rMbkz49vY3WgfTco9cykeKcM");
		OAuthRequest request = new OAuthRequest(Verb.GET, endpoint);
		request.addQuerystringParameter("term", "food");
		request.addQuerystringParameter("location", location);
		request.addQuerystringParameter("limit", "20");
		request.addQuerystringParameter("sort", "2");
		service.signRequest(token, request);
		Response resp = request.send();
		String body = resp.getBody();
		JsonParser jp = new JsonParser();
		JsonObject jo = (JsonObject) jp.parse(body);
		JsonArray businesses = (JsonArray) jo.get("businesses");
		return businesses;
	}
}
