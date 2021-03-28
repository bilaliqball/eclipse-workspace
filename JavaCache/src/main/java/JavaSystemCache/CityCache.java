package JavaSystemCache;
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;


public class CityCache {
    
public static void main( String[] args ) throws IOException {
	CityCache example = new CityCache();
example.testCache();}

private CacheAccess<String, City> cache = null;

public CityCache() throws IOException {
try {cache = JCS.getInstance( "default" );}
catch ( CacheException e ) {System.out.println( String.format( "Problem initializing cache: %s", e.getMessage() ) );}
}


public void putInCache( City city ) {
String key = city.name;
try {cache.put( key, city );}
catch ( CacheException e ) {System.out.println( String.format( "Problem putting city %s in the cache, for key %s%n%s",city.name, key, e.getMessage() ) );}
}

public City retrieveFromCache( String cityKey ) {return cache.get( cityKey );}

public void testCache() {
   
City bermingham=new City("Birmingham", "UK",12345);
putInCache(bermingham);

City lahore=new City("Lahore", "Pakistan",54000);
putInCache(lahore);

City berlin = new City("Berlin", "Germany", 3502000 );
putInCache( berlin );

City johannesburg = new City("Johannesburg", "South Africa", 12200000 );
putInCache(johannesburg);

City newYork = new City("NewYork", "USA", 12200000 );
putInCache(newYork);

//City retrievedCity1 = retrieveFromCache( "Berlin" );
//if ( retrievedCity1 != null ) {System.out.println( retrievedCity1.toString() );}
//else {System.out.println( "No object was found in the cache for the key \"Berlin\"" );}
//City retrievedCity2 = retrieveFromCache( "NewYork" );
//if ( retrievedCity2 != null ) {System.out.println( retrievedCity2.toString() );}
//else {System.out.println( "No object was found in the cache for the key \"NewYork\"" );}


City bir=retrieveFromCache("Birmingham");
City lah=retrieveFromCache("Lahore");
int pop=bir.population;
String cnt=lah.country;

System.out.println(cnt+"has a population of "+pop+"persons.");

}

public class City implements Serializable {
private static final long serialVersionUID = 6392376146163510146L;
public String name;
public String country;
public int population;

public City( String name, String country, int population ) {
this.name = name;
this.country = country;
this.population = population;}

@Override
public String toString() {return String.format( "%s is a city in the country %s with a population of %d", name, country, population );}
}
}