package mp1.jena;

import java.util.List;
import mp1.jena.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Restaurant> restaurants;
		
		TDbConn tdb = new TDbConn();
		tdb.clearTdb();
		
		List<Restaurant> dbprestaurants = Dbpedia.getDBPediaData();
		
		for (Restaurant r : dbprestaurants) {
			
			//System.out.println(r.getUri() + " | " + r.getName() + " | " + r.getLatitude() + " | " + r.getLongitude() + " | " + r.getCountry() );
			tdb.addRestaurant(r);
		}
		
		Restaurant rating = new Restaurant();
		rating.setName("Rasoi");
		rating.setRating(1);
		
		tdb.insertRating(rating);
		
		//add a test restaurant
		Restaurant test = new Restaurant();
		test.setUri("bla bla");
		test.setCountry("United Kingdom");
		test.setName("My_TESTRESTAURANT");
		test.setLatitude(33.33F);
		test.setLongitude(55.55F);
		tdb.addRestaurant(test);
		
		Restaurant del = new Restaurant();
		del.setUri("bla bla");
		del.setCountry("United Kingdom");
		del.setName("The_Waterside_Inn");
		del.setLatitude(33.33F);
		del.setLongitude(55.55F);
		tdb.deleteRestaurant(del);
		
		Restaurant del2 = new Restaurant();
		del2.setUri("bla bla");
		del2.setCountry("United Kingdom");
		del2.setName("The_Hinds_Head");
		del2.setLatitude(33.33F);
		del2.setLongitude(55.55F);
		
		Restaurant ins = new Restaurant();
		ins.setUri("bla bla");
		ins.setCountry("United Kingdom");
		ins.setName("MANUELL_EINGEFUEGT");
		ins.setLatitude(568.78F);
		ins.setLongitude(123.45F);
		tdb.editRestaurant(del2, ins);
		
		ins.setRating(5);
		tdb.insertRating(ins);
		
		
		restaurants = tdb.getRestaurants();
		
		for (Restaurant r : restaurants) {
			
			System.out.println("MAIN: " + r.getUri() + " | " + r.getName() + " | " + r.getLatitude() + " | " + r.getLongitude() + " | " + r.getRating() + " | " + r.getCountry());
		}
		
		
	}

}
