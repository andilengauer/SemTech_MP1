package mp1.data;

import java.util.List;

import org.apache.jena.atlas.json.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mp1.jena.Dbpedia;
import mp1.jena.Restaurant;
import mp1.jena.TDbConn;

public class DataManager {
	private List<Restaurant> dbpediaData ;
	private TDbConn database;
	public DataManager()
	{
		LoadDbPediaData();
		InitDatabase();
	}
	private void InitDatabase() {
		database = new TDbConn();
		database.clearTdb();
		
		for (Restaurant r : dbpediaData) {
			
			//System.out.println(r.getUri() + " | " + r.getName() + " | " + r.getLatitude() + " | " + r.getLongitude() + " | " + r.getCountry() );
			database.addRestaurant(r);
		}
		
	}
	private void LoadDbPediaData()
	{
		dbpediaData = Dbpedia.getDBPediaData();
		//Dbpedia.getDBPediaData();
	}
	
	public List<Restaurant> getRestaurants()
	{
		return database.getRestaurants();
	}
	
	public String getRestaurantsAsJson()
	{
		List<Restaurant> restaurants = getRestaurants();
		JSONArray arr = new JSONArray();
		for(Restaurant r : restaurants)
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("uri", r.getUri());
			jsonObj.put("name", r.getName());
			jsonObj.put("country", r.getCountry());
			jsonObj.put("long", r.getLongitude());
			jsonObj.put("lat", r.getLatitude());
			int rating = r.getRating();
			jsonObj.put("rating",rating == 0 ? "keine":rating);
			arr.add(jsonObj);
		}
		
		return arr.toJSONString();
		
	}
	public void insertRestaurant(Restaurant r) {
		database.addRestaurant(r);
	}
	public void rateRestaurant(String restaurant, int rating) {
		Restaurant r = getRestaurant(restaurant);
		
		r.setRating(rating);
		database.insertRating(r);
		
	}
	public Restaurant getRestaurant(String restaurant) {
		List<Restaurant> restaurants = getRestaurants();
		
		for(Restaurant r : restaurants)
		{
			if(r.getName().equalsIgnoreCase(restaurant))
			{
				return r;
			}
		}
		return null;
	}
	public void deleteRestaurant(String name) {
		Restaurant r = new Restaurant();
		r.setName(name);
		database.deleteRestaurant(r);
	}
	public void editRestaurant(Restaurant old, Restaurant r) {
		r.setRating(old.getRating());
		database.editRestaurant(old, r);
	}
}
