package mp1.web;

import java.util.List;

import mp1.data.DataManager;
import mp1.jena.Dbpedia;
import mp1.jena.Restaurant;
import mp1.jena.TDbConn;

public class WebController {
	private static WebController instance = null;
	private DataManager datamanger;
	private WebController()
	{
		Init();
	}
	
	private void Init() {
		datamanger = new DataManager();
	}

	public static WebController getInstance()
	{
		if(instance == null)
			instance = new WebController();
		return instance;
	}
	
	
	public String getRestaurants()
	{
		return datamanger.getRestaurantsAsJson();
	}
	public Restaurant getRestaurant(String name)
	{
		return datamanger.getRestaurant(name);
	}
	
	public void insertRestaurant(String name, String lng, String lat)
	{
		Restaurant r = new Restaurant();
		r.setName(name);
		r.setLongitude(Float.parseFloat(lng));
		r.setLatitude(Float.parseFloat(lat));
		
		datamanger.insertRestaurant(r);
	}

	public void rateRestaurant(String restaurant, int rating) {
		datamanger.rateRestaurant(restaurant,rating);
		
	}

	public void deleteRestaurant(String name) {
		datamanger.deleteRestaurant(name);
	}

	public void editRestaurant(String origin, String name, String lng, String lat) {
		Restaurant old = new Restaurant();
		old.setName(origin);
		
		Restaurant r = new Restaurant();
		r.setName(name);
		r.setLongitude(Float.parseFloat(lng));
		r.setLatitude(Float.parseFloat(lat));
		
		datamanger.editRestaurant(old, r);
	}

}
