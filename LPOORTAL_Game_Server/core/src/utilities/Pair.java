package utilities;

public class Pair<F,S> {

	  private final F first;
	  private final S second;

	  public Pair(F first, S second) {
	    this.first = first;
	    this.second = second;
	  }

	  @Override
	  public int hashCode() { 
		  return first.hashCode() ^ second.hashCode(); 
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (!(obj instanceof Pair)) return false;
	    
	    Pair pair = (Pair) obj;
	    return this.first.equals(pair.getFirst()) &&
	           this.second.equals(pair.getSecond());
	  }

	  public F getFirst() {
		  return first; 
	  }
	  public S getSecond() {
		  return second; 
	  }

}