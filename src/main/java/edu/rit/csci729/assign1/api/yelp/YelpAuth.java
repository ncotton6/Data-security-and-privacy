package edu.rit.csci729.assign1.api.yelp;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Scribe requires a provider, this is just to satisfy that requirement, it
 * doesn't do anything otherwise.
 * 
 * @author Nathaniel Cotton
 *
 */
public class YelpAuth extends DefaultApi10a {

	public YelpAuth() {
	}

	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorizationUrl(Token arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestTokenEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
