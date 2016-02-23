package edu.rit.csci729.assign1.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci729.assign1.api.eventful.Eventful;
import edu.rit.csci729.assign1.api.eventful.model.Event;
import edu.rit.csci729.assign1.api.yelp.Yelp;
import edu.rit.csci729.assign1.api.yelp.model.Restaurant;

/**
 * This controller will take in a baseLocation, that will then perform the api
 * calls to get nearby events, and restaurants.
 * 
 * @author Nathaniel Cotton
 *
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	ServletContext context;

	/**
	 * When executed this method will used the provide location to get events,
	 * and restaurants nearby.
	 * 
	 * @param model
	 * @param baseLocation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model, String baseLocation) {
		context.log(String.format("Planning evening for [%s]", baseLocation));
		model.addAttribute("baseLocation", baseLocation);
		List<Event> events = Eventful.getEvents(baseLocation);
		model.addAttribute("events", events);
		context.log(String.format("Number of events [%s]", events.size()));
		List<Restaurant> resturants = Yelp.getRestaurants(baseLocation);
		model.addAttribute("restaurants", resturants);
		return "plan";
	}

}
