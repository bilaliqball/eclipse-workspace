package JavaSystemCache;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;

//import org.apache.jcs.JCS;
//import org.apache.jcs.access.exception.CacheException;

public class EmployeeCache {	
private CacheAccess<Object, Object> cache; 
	 
public EmployeeCache(){
try{
cache = JCS.getInstance( "empCache" );// Load the cache
cache.put( "123", new Employee( "Nick", "Detroit.USA", "123" ) );
cache.put( "143", new Employee( "Ric",  "Seattle.USA", "143" ) );
cache.put( "153", new Employee( "Jhon", "Chicago.USA", "153" ) );
cache.put( "163", new Employee( "Dan", "Houston.USA", "163" ) ); }
catch( CacheException e ){e.printStackTrace();}}
	 
public void addEmployee( Employee emp ){
try{cache.put( emp.getEmpid(), emp );}
catch( CacheException e ){ e.printStackTrace();}
}
	  
public Employee getEmployee( String empid ){return ( Employee )cache.get( empid );}
	  
public void removeEmployee( String empid ){
try{cache.remove( empid );}
catch( CacheException e ){e.printStackTrace();}
}	 
	  
	  
public class Employee implements java.io.Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name;
private String address;
private String empid; 
public Employee(String name, String address, String empid) {
this.name = name;
this.address = address;
this.empid = empid;} 

public String getName() {return name;}
public void setName(String name) {this.name = name;}
public String getAddress() {return address;}
public void setAddress(String address) {this.address = address;}
public String getEmpid() {return empid;}
public void setEmpid(String empid) {this.empid = empid;}
}
  
public static void main( String[] args ){
EmployeeCache empManager = new EmployeeCache();
// Add employees to the employee manager
/*empManager.addEmployee(new Employee("Name1", "address1", "empid1"));
empManager.addEmployee(new Employee("Name2", "address2", "empid2"));
empManager.addEmployee(new Employee("Name3", "address3", "empid3"));*/
// Get employee
Employee emp = empManager.getEmployee("123");
System.out.println( "Employee details retrieved from cache: " + emp.getName()+"-"+emp.getAddress());
// Remove employee
empManager.removeEmployee("123");
// After removal of employee
System.out.println( "Employee details after removal from cache: " + empManager.getEmployee("123") );
  }
}