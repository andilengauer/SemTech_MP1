package mp1.jena;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

public class TDbConn {
	
	public static Dataset dataset;
	private String dir = "tdb/";

	public TDbConn() {
		
	dataset = TDBFactory.createDataset(dir);

	}
	
	public static void clearTdb() {
		
		dataset.begin(ReadWrite.WRITE);
		try {
			UpdateRequest request = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"prefix : <http://example.org/>" +
					"DROP GRAPH :newGraph"
			);
			UpdateAction.execute(request, dataset);
			dataset.commit();
			System.out.println("Graph zurückgesetzt!");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			dataset.abort();
		} finally {
			dataset.end();
				}
	}
	
	public static void addRestaurant(Restaurant r) {
		
		//insert the queried Restaurants into our TDB
		dataset.begin(ReadWrite.WRITE);
		try {
			UpdateRequest request = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"prefix : <http://example.org/>" +
					"INSERT DATA { " +
					"GRAPH :newGraph {" +
					":" + r.getName() + " :a :Restaurant;" +
					":latitude " + r.getLatitude() + ";" +
					":longitude " + r.getLongitude() + ".}}"
			);
			UpdateAction.execute(request, dataset);
			dataset.commit();
			System.out.println("Restaurant: " + r.getName() + " wurde hinzugefügt!");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			dataset.abort();
		} finally {
			dataset.end();
		}


	}
	
	
	public static void insertRating(Restaurant r) {
		
		dataset.begin(ReadWrite.WRITE);
		try {
			UpdateRequest request = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"prefix : <http://example.org/>" +
					"INSERT DATA { " +
					"GRAPH :newGraph {" +
					":" + r.getName() + " :rating " + r.getRating() + ".}}"
			);
			UpdateAction.execute(request, dataset);
			dataset.commit();
			System.out.println("Bewertung für: " + r.getName() + " wurde hinzugefügt! - " + r.getRating());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			dataset.abort();
		} finally {
			dataset.end();
		}
	}
	
	public static void deleteRestaurant(Restaurant r) {
		
		dataset.begin(ReadWrite.WRITE);
		try {
			UpdateRequest request = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"prefix : <http://example.org/>" +
					"DELETE WHERE { " +
					"GRAPH :newGraph {" +
					":" + r.getName() + " ?p ?o.}}"
					
			);
			UpdateAction.execute(request, dataset);
			dataset.commit();
			System.out.println("Restaurant wurde entfernt: " + r.getName());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			dataset.abort();
		} finally {
			dataset.end();
		}
	}
	
	public static void editRestaurant(Restaurant del,Restaurant ins) {
		dataset.begin(ReadWrite.WRITE);
		try {
			UpdateRequest delrequest = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
					"prefix : <http://example.org/>" +
					"DELETE WHERE { " +
					"GRAPH :newGraph {" +
					":" + del.getName() + " ?p ?o.}}"
					
			);
			UpdateAction.execute(delrequest, dataset);
			
			
			UpdateRequest insrequest = UpdateFactory.create(
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
							"prefix : <http://example.org/>" +
							"INSERT DATA { " +
							"GRAPH :newGraph {" +
							":" + ins.getName() + " :a :Restaurant;" +
							":latitude " + ins.getLatitude() + ";" +
							":longitude " + ins.getLongitude() + ".}}"
					
			);
			UpdateAction.execute(insrequest, dataset);
			
			dataset.commit();
			System.out.println("Restaurant wurde geändert!" + del.getName() + " | " + ins.getName());
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			dataset.abort();
		} finally {
			dataset.end();
		}
		
	}
	
	public static List<Restaurant> getRestaurants(){
		
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant r = new Restaurant();
		
		dataset.begin(ReadWrite.READ);
		
		try {
			String qs = "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
						"prefix : <http://example.org/>" +
						"SELECT ?rest ?p ?o WHERE {GRAPH :newGraph {?rest ?p ?o}}";
			Query query = QueryFactory.create(qs);
			try (QueryExecution exec = QueryExecutionFactory.create(query, dataset)) {
				ResultSet results = exec.execSelect();
				
				if(results.hasNext()) {
					//ResultSetFormatter.out(results);

					QuerySolution qSol = results.nextSolution();
					//System.out.println(qSol.get("rest").toString() + " | " + qSol.get("p").toString() + " | " + qSol.get("o").toString());
					String auxrestname = qSol.get("rest").toString();
					r.setName(auxrestname.substring(19));
					while(results.hasNext()) {
							qSol = results.nextSolution();
							//System.out.println(qSol.get("rest").toString() + " | " + qSol.get("p").toString() + " | " + qSol.get("o").toString());
							if(auxrestname != qSol.get("rest").toString()) {
								restaurants.add(r);
								r = new Restaurant();
								
							}
								
							auxrestname = qSol.get("rest").toString();
							r.setName(auxrestname.substring(19));
							
							switch(qSol.get("p").toString()) {
									//abh�ngig vom Pr�dikat die Werte setzen
									case "http://example.org/latitude":
										String auxlat = qSol.get("o").toString();
										if(auxlat.indexOf('^') != -1) {
										auxlat = auxlat.substring(0,auxlat.indexOf('^'));
										} else { auxlat = "999.999";}
										r.setLatitude(Float.parseFloat(auxlat));
										break;
									case "http://example.org/longitude":
										String auxlong = qSol.get("o").toString();
										if(auxlong.indexOf('^') != -1) {
										auxlong = auxlong.substring(0,auxlong.indexOf('^'));
										} else { auxlong = "999.999";}										
										r.setLongitude(Float.parseFloat(auxlong));
										break;
									case "http://example.org/rating":
										String auxrating = qSol.get("o").toString();
										auxrating = auxrating.substring(0,auxrating.indexOf('^'));
										r.setRating(Integer.parseInt(auxrating));
										break;
								}
							
					}
				}else{
					
					System.out.println("Keine Restaurants gefunden!!!");
				
				}

			}
		} finally {
			dataset.end();
		}
		restaurants.add(r);
		return restaurants;
	}
	
	
}
