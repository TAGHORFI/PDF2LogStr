







import java.util.Comparator;

public class Sortbyroll implements Comparator<String[]>{
	
	 public int compare(String[] a, String[] b) 
	    { 
		 	Integer pa = Integer.valueOf(a[5]);
		 	Integer pb = Integer.valueOf(b[5]);
		 	if(pa == pb) {

		 		Integer x = Integer.valueOf(a[0]);
		 		Integer y = Integer.valueOf(b[0]);
		 		return x-y; 
		 		
		 	}
		 	
		 	return pa-pb;
	    } 

	
}
