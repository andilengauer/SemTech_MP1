package mp1.jena;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

public class Dbpedia {
	
	private static List<Restaurant> restaurants;
	
	public Dbpedia() {
		
	}
	
	public static List<Restaurant> getDBPediaData() {
		
		QuerySolution qSol;
		RDFNode rName;
		RDFNode rLat;
		RDFNode rLong;
		RDFNode rCountry;
		String strname;
		String strlatitude;
		String strlongitude;
		String strcountry;
		restaurants = new ArrayList<>();
		
		//QUery data from DBPedia
		ParameterizedSparqlString qs = new ParameterizedSparqlString( "" +
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"prefix dbp: <http://dbpedia.org/property/>\n" +
				"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
				"prefix dbo: <http://dbpedia.org/ontology/>\n" +
				"SELECT ?r (SAMPLE(?lat) AS ?LAT) (SAMPLE(?long) AS ?LONG) ?country WHERE {\n" +
                "  ?r rdf:type dbo:Restaurant.\n" +
                "  ?r dbp:country ?country.\n" +
                "  ?r geo:lat ?lat.\n" +
                "  ?r geo:long ?long.\n" +
                "  FILTER(regex(?country, \"United Kingdom\"))\n" +
                "} GROUP BY ?r ?country" );
		
		//System.out.println( qs );
		
		QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );

		ResultSet dbpedia = exec.execSelect();
		
		//ResultSetFormatter.out(dbpedia);
		
		while (dbpedia.hasNext()){
			
			Restaurant r = new Restaurant();
			qSol = dbpedia.nextSolution();
			rName = qSol.get("r");
			rLat = qSol.get("LAT");
			rLong = qSol.get("LONG");
			rCountry = qSol.get("country");

			r.setUri(rName.toString());
			System.out.println("Uri:" + rName.toString());
			
			strname = rName.toString();
			strname = strname.substring(28).replace("(", "").replace(")","").replace(",","").replace("&_","").replace("'","_");
			r.setName(strname);
			
			strlatitude = rLat.toString();
			strlatitude = strlatitude.substring(0,strlatitude.indexOf('^'));
			r.setLatitude(Float.parseFloat(strlatitude));
			
			strlongitude = rLong.toString();
			strlongitude = strlongitude.substring(0,strlongitude.indexOf('^'));
			r.setLongitude(Float.parseFloat(strlongitude));
			
			strcountry = rCountry.toString();
			strcountry = strcountry.substring(0,strcountry.indexOf('^'));
			r.setCountry(strcountry);
			
			//System.out.println(rName.toString() + " | " + rLat.toString() + " | " + rLong.toString() + " | " + rCountry.toString() );
			Dbpedia.restaurants.add(r);
        }
		
		return restaurants;
		
	}

}
