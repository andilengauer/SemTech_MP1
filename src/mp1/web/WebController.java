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
	
	public void insertRestaurant(String name, String lng, String lat)
	{
		Restaurant r = new Restaurant();
		r.setName(name);
		r.setLongitude(Float.parseFloat(lng));
		r.setLatitude(Float.parseFloat(lat));
		
		datamanger.insertRestaurant(r);
	}

}
